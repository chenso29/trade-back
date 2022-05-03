TRUNCATE legal_details CASCADE;
TRUNCATE addresses CASCADE;
TRUNCATE type_of_contractors CASCADE;
TRUNCATE employees CASCADE;
TRUNCATE positions CASCADE;

INSERT INTO positions (id, name, sort_number)
VALUES (1, 'Генеральный директор', '1'),
       (2, 'Коммерческий директор', '2'),
       (3, 'Финансовый директор', '3'),
       (4, 'Директор по продажам', '4'),
       (5, 'Технический директор', '5'),
       (6, 'Главный бухгалтер', '6'),
       (7, 'Начальник отдела снабжения', '7'),
       (8, 'Начальник отдела продаж', '8'),
       (9, 'Начальник транспортного отдела', '9'),
       (10, 'Начальник отдела маркетинга', '10'),
       (11, 'Начальник складского комплекса', '11'),
       (12, 'Бухгалтер', '12'),
       (13, 'Менеджер по закупкам', '13'),
       (14, 'Менеджер по продажам', '14'),
       (15, 'Кладовщик', '15'),
       (16, 'Грузчик', '16'),
       (17, 'Маркетолог', '17'),
       (18, 'Водитель', '18'),
       (19, 'Кассир', '19'),
       (20, 'Уборщица', '20');

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', 1, null, 1),
       (2, 'Some special text about Sima', 'simaogon@mail.ru', 'Sima', '526317984678', 'Simonova', 'Semenovna', '54321',
        '+7(999)222-11-33', '2', 2, null, 2),
       (3, 'Some special text about Vera', 'veraogon@mail.ru', 'Vera', '526317555678', 'Belive', 'Henrichovna', '76543',
        '+7(999)777-11-33', '3', 2, null, 5),
       (4, 'Some special text about Karim', 'karimogon@mail.ru', 'Karim', '526316666678', 'Islentiev', 'Dmitrievich',
        'qwerty', '+7(999)222-77-00', '4', 2, null, 4),
       (5, 'Some special text about Sasha', 'sashaogon@mail.ru', 'Sasha', '526317984600', 'Petko', '', 'asdfg',
        '+7(999)222-00-33', '5', 2, null, 19);

INSERT INTO addresses (id, apartment, city, country, house, index, region, street)
VALUES (1, '15', 'Москва', 'Россия', '11', '123456', 'Московская', 'ул. Ленина'),
       (2, '22', 'Вашингтон', 'USA', '6', '188678', 'Колумбия', 'ул. 5я Авеню'),
       (3, '1', 'Столица Панамы', 'Panama', '2', '123456', 'Область', 'ул. Индейцев'),
       (4, '3', 'г.Москва', 'Россия', 'д.14, стр.7', '123456', 'Область', 'ул. Подвойского'),
       (5, '5', 'г. Москва', 'Россия', 'д. 20', '123498', 'Область', 'ул. Тверская');

INSERT INTO type_of_contractors (id, name, sort_number)
VALUES (1, 'Юридическое лицо', '1'),
       (2, 'Индивидуальный предприниматель', '2'),
       (3, 'Физическое лицо', '3');

insert into legal_details(id, comment_to_address, date_of_the_certificate, first_name, inn, kpp, last_name,
                          middle_name, number_of_the_certificate, ogrn, okpo, address_id, type_of_contractor_id)
values

(1,'comment to address', '2020-10-10', 'Михаил', 3664069397, 79271669, 'Иванов', 'Сергеевич', 432145, 1056161351655, 70541561 , 3, 1),
(2,'comment to address', '2019-08-08', 'Андрей', 3664049551, 79165116, 'Сергеев', 'Андреевич', 432165, 1053561651515, 70651615 , 2, 2),
(3,'comment to address', '2018-06-06', 'Сергей', 3661564265, 79196813, 'Михайлов', 'Антонович', 432445, 1051566516515, 70651656 , 1, 3);

SELECT setval('legal_details_id_seq', max(id)) FROM legal_details;