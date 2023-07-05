# Брокер сообщений Kafka.


## Цель: ##
Познакомиться с основными принципами асинхронного взаимодействия микросервисов через брокер сообщения Kafka.


## Задачи: ##
- запустить Kafka в docker, использую docker-compose (можно взять отсюда https://github.com/GUR-ok/arch-brokerage-intercessor/blob/master/docker-compose.yml)
- создать микросервис ProducerService на SpringBoot, настроить продюсера сообщений, использую KafkaTemplate (можно использовать для справки этот проект https://github.com/GUR-ok/arch-brokerage-intercessor или любой интернет-ресурс). Топик создать с 1 партицией. Сервис должен иметь REST-эндпоинт, принимающий строку сообщения и ключ, который потом отправляется в топик Kafka. Почитать зачем нужен ключ.
- создать микросервис ConsumerService на SpringBoot, настроить консьюмер сообщений, читающий сообщения из созданного топика, вычитанное сообщение сохранять в БД или просто печатать в консоль. Обратить внимание на groupId.
- запустить 1 экз. продьюсера, 2 экз. консьюмера. У консьюмеров одинаковый groupId. Посмотреть как обрабатываются сообщения. Какой из сервисов-коньюмеров обрабатывает сообщения. (*)
- создать новый топик с двумя партициями. Также запустить сервисы 1экз+2экз. Отправить сообщения с разными ключами. Отправить несколько сообщений с одинаковым ключом. Посмотреть какой из экземпляров обрабатывает сообщения. (**)
- у 2экз. консьюмеров сделать разный groupId. Отправить сообщения и проанализировать результат. (***)


## Результаты: ##
 - (*): все сообщения обработаются вторым консьюмером
 - (**): сообщения с разными ключами поровну распределяются между консьюмерами, если число сообщений нечётное, то лишний обрабатывается во втором консьюмере; сообщения с одинаковыми ключами обработаются на одном консьюмере, причём при распределении задач они буду считаться, как одна задача
 - (***): каждая группа получила и обработала сообщения, соответственно и каждый из двух консьюмер


## Ответы на вопросы ##
1) Как обрабатываются сообщения, если консьюмеров больше чем партиций в топике?
   
   Распределяются между консьюмерами так, чтобы число консьюмеров было равно числу партиций, остальные консьюмеры отдыхают, при этом сообщения с одинаковыми ключами обрабатываются всегда одним консьюмером
4) Зачем передается ключ вместе с сообщением?

   
   Нужда в этом ключе возникает при параллелизации задач, сообщения с одинаковыми ключами обрабатываются одним консьюмером. В остальных случаях его использование опционально
5) Зачем нужен groupId?

   Каждая группа занимается обработкой сообщений в полном объёме независимо от остальных. Внутри одной группы нагрузка распределяется между всеми консьюмерами, принадлежащих этой группе.
7) Гарантия доставки и порядок сообщений в партиции в Kafka


   FIFO (First In First Out), гарантия доставки обусловлена принципами, рассмотренными ниже.
9) Семантика доставки (atLeastOnce, atMostOnce, ExactlyOnce)
   - atLeastOnce: информация не потеряется, но может задвоиться
   - atMostOnce: информация может задвоиться, но не потеряется
   - ExactlyOnce: информация не потеряется и не задвоится (очень дорогая и несёт за собой определённые риски)