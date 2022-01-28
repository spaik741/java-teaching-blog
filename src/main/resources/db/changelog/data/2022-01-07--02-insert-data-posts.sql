--liquibase formatted sql

--changeset mihail:2022-01-07--02-insert-data-posts
insert into posts (title, text, date_post, id_user) values ('Java в 2021 году: обновления 16 и 17, популярность в Азии и стабильность в рейтингах',
'Раз в полгода язык Java получает обновление. Ожидаемо, в 2021 году вышли два релиза — Java 16 и Java 17.
В релиз Java 16 вошло 17 улучшений. Среди новшеств 16-й версии: новый инструмент упаковки для доставки автономных Java-приложений, улучшенное управление памятью, дополнительные функции инкубации и предварительного просмотра, усовершенствования, устраняющие несовместимый с будущим код, а также новые порты для JDK для Alpine Linux и других Linux-дистрибутивов.
Java 16 завершили сопоставление шаблонов для instanceof и Record, языковых улучшений, впервые появившихся в Java 14.
В Java 17, вышедшую в сентябре 2021 года, вошли 14 изменений. Большая часть изменений касается увеличения производительности языка и платформы в целом. Также создатели убрали много устаревших компонентов и технологий, ставших неактуальными из-за отсутствия поддержки со стороны ПО от сторонних разработчиков.
В ключевые изменения вошла улучшенная поддержка sealed-классов и интерфейсов, блокирующих возможность наследовать свойства из этих компонентов объектами и другими классами (JEP 409).
Стоит отметить и усовершенствованную блокировку доступа к внутренним API платформы JDK, а также улучшение производительности за счет использования технологии Vector API, удаление экспериментальных компиляторов AOT и JIT и использование фреймворка Metal для рендеринга на устройствах Apple.
Коммерческие сборки JDK 17 от Oracle для Windows и других платформ доступны на сайте компании-разработчика. Бесплатная версия OpenJDK 17 с открытым исходным кодом и ежеквартальными обновлениями доступна для загрузки по этой ссылке. JDK 17 имеет долгосрочную (LTS) поддержку в течение 8 лет. Следующий выпуск LTS Java выйдет в 2023 году с Java 21. Это изменит периодичность выпуска LTS с трех до двух лет.',
'2021-10-05'),
('Метод Constructor в Java I Перегрузка Constructor. Абстрактные классы и интерфейсы Java', 'Как и другие методы, мы также можем определить метод Constructor в нашей Java-программе, но, в отличие от других методов, мы не можем вызвать Constructor напрямую; Java вызывает конструктор автоматически при создании объекта. Когда мы используем ключевое слово new для создания объекта класса, Java делает три вещи:
Выделяет память для объекта.
Инициализируйте эту переменную экземпляра объекта с их начальным значением или значением по умолчанию.
Вызывает конструктор Method класса.
Если в классе не определен какой-либо метод Constructor, мы все равно создадим объект этого класса, но мы должны установить переменную экземпляра или вызвать другие методы, которые впоследствии должны инициализировать объект этим объектом.
Определяя метод Constructor в наших собственных классах, мы можем установить начальные значения переменной экземпляра, вызвать метод на основе этой переменной или вызвать методы для других объектов или вычислить начальные свойства нашего объекта. Мы также можем перегрузить Constructor, как и обычные методы, для создания объекта, который имеет определенные свойства на основе аргумента, который мы передаем для new.',
'2021-12-23'),
('Мой первый опыт работы с Jackson-ом.', 'Всем привет!
Как-то раз в далекой-далекой галактике нашелся очень длинный JSON... И стало лень создавать под него POJO. И задался я вопросом: Представим ситуацию, в которой я получаю ответ в виде JSON с, например, курсами валют. В самом JSON''е очень много полей, а мне нужно 2 из них. И вот хотелось бы узнать - могу ли я создать класс с нужными мне полями и попытаться запарсить этот джсон в объект класса? Поймет джексон, что я от него хочу?
И, соответственно, если поймет и так сделать можно - как сделать правильнее и что будет работать быстрее?
Итак, касательно вопроса, который возник у меня  по поводу JSON и восприятия его джексоном: джексон все поймет. Он умный. Только нужно ему чуть-чуть помочь в этом. Создаем POJO - обычный джава класс, в котором будут описаны нужные нам переменные из JSON''a. Тут же скажу, что для этого крайне желательно изучить сам JSON, для которого мы пишем класс (скорее всего в нем будут вложенные классы, которые тоже нужно создать). Далее с помощью аннотации @JsonCreator над конструктором мы показываем, что поля этого класса нужно заполнить из JSON''a. В параметрах конструктора мы можем указать какие именно поля джсона присваивать полям класса с помощью аннотации @JsonParam("ИмяПоляИзДжсон").
Если полей в джсоне больше чем нам нужно (а изначально это и был мой вопрос) - мы должны об этом предупредить и объяснить, что остальные поля нам не нужны. Для этого мы используем аннотацию @JsonIgnoreProperties(ignoreUnknown=true). Тогда при встрече с неизвестными полями программа не будет падать.',
'2022-01-01');