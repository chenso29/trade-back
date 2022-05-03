TRUNCATE price_lists CASCADE;
TRUNCATE companies CASCADE;
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

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                              leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                              stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'OOO "Организация №2"', true, '733126789654', '00002', 'stamp', 2, 1),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'OOO "Организация №3"', true, '799123786542', '00003', 'stamp', 3, 1);

INSERT INTO price_lists (id, commentary, number, printed, sent, time, company_id)
VALUES (1, 'comment1', 'number1', false, false, '1234-12-12 12:34', 1),
       (2, 'comment2', 'number2', false, false, '1234-12-12 12:34', 2),
       (3, 'comment3', 'number3', false, false, '1234-12-12 12:34', 3);

SELECT setval('price_lists_id_seq', max(id))
FROM price_lists;