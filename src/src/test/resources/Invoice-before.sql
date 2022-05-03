TRUNCATE invoice CASCADE;
TRUNCATE contractors CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE access_parameters CASCADE;
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

INSERT INTO access_parameters (id, general_access, department_id, employee_id)
VALUES (1, false, 1, 1),
       (2, false, 2, 2),
       (3, false, 3, 3);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012', 'inbox@5ka.ru', '8 (800) 555-55-05',
        'Агроаспект, ООО', '8 (800) 555-55-05', '2', 2, 2, 1, 2, 1, 2),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1);

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

insert into warehouses (id, address, comment, comment_to_address, name, sort_number)
values  (1, null, null, null, 'Основной склад', '00001'),
        (2, null, null, null, 'Основной склад1', '00001'),
        (3, null, null, null, 'Основной склад2', '00001'),
        (4, null, null, null, 'Основной склад3', '00001');

INSERT INTO invoice (id,comment,date,is_spend,type_of_invoice,company_id,contractor_id,warehouse_id)
VALUES (1,'comment 1','2222-11-01 00:01',false,1,1,1,1),
       (2,'comment 2','2222-11-01 00:02',false,1,1,1,1),
       (3,'comment 3','2222-11-01 00:03',false,1,1,1,1);

SELECT setval('invoice_id_seq', max(id))
FROM invoice;