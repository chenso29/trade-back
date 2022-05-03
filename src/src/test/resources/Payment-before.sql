TRUNCATE payments CASCADE;
TRUNCATE projects CASCADE;
TRUNCATE contracts CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO projects (id, code, description, name)
VALUES (1, '0000', 'description', 'name'),
       (2, '0000', 'description', 'name2'),
       (3, '0000', 'description', 'name3'),
       (4, '0000', 'description', 'name4');



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

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012', 'inbox@5ka.ru', '8 (800) 555-55-05',
        'Агроаспект, ООО', '8 (800) 555-55-05', '2', 2, 2, 1, 2, 1, 2),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1),
       (4, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1);

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id,
                       contractor_id, legal_detail_id)
VALUES (1, 200.00, false, 'no comments', '2021-08-10', '1', 3, 1, 2, 1),
       (2, 200.00, false, 'no comments', '2021-08-10', '2', 3, 1, 2, 1),
       (3, 200.00, false, 'no comments', '2021-08-10', '3', 3, 1, 3, 1),
       (4, 200.00, false, 'no comments', '2021-08-10', '4', 3, 1, 4, 1);

INSERT INTO payments (id, number, payment_methods, sum, time, type_of_payment, company_id, contract_id, contractor_id,
                      project_id)
VALUES (1, '1', 'CASH', 100, '2021-07-30 13:23:24', 'INCOMING', 1, 1, 2, 2),
       (2, '2', 'BANK', 50, '2021-07-30 13:23:24', 'OUTGOING', 1, 2, 2, 3),
       (162, '3', 'CASH', 100, '2021-07-30 13:23:24', 'INCOMING', 2, 3, 1, 3);

SELECT setval('payments_id_seq', max(id))
FROM payments;