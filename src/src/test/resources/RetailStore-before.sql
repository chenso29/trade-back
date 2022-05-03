TRUNCATE retail_stores CASCADE;
TRUNCATE companies CASCADE;

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

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system, revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Был в сети вчера', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 1),
       (2, 'Онлайн', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 2),
       (3, 'Был в сети 2 часа назад', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 3);

SELECT setval('retail_stores_id_seq', max(id))
FROM retail_stores;