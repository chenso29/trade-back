TRUNCATE return_suppliers;
TRUNCATE contracts CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;


INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'OOO "Организация №2"', true, '733126789654', '00002', 'stamp', 2, 2),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'OOO "Организация №3"', true, '799123786542', '00003', 'stamp', 3, 3);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', null, 1, 1, 1, 1, 1);

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id, contractor_id, legal_detail_id)
VALUES (1, 200, false, 'no comments', '2021-08-06', '1', 3, 1, 1, 1);

INSERT INTO return_suppliers (id, comment, date, is_print, is_send, company_id, contract_id, contractor_id,
                              warehouse_id)
VALUES (1, 'Комментарий 1', '2021-06-23 15:10', false, false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-06-23 15:10', true, false, 1, 1, 1, 1),
       (3, 'Комментарий 3', '2021-06-23 15:10', false, true, 1, 1, 1, 1),
       (4, 'Комментарий 4', '2021-06-23 15:10', true, true, 1, 1, 1, 1);