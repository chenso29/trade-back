TRUNCATE internal_order CASCADE;
TRUNCATE internal_order_products CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE warehouses CASCADE;

ALTER TABLE internal_order_products DROP CONSTRAINT IF EXISTS fk9t9i5pfy3byj13yyhnhen84sj;

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

INSERT INTO internal_order (id, comment, date, is_print, is_sent, company_id, warehouse_id)
VALUES (1, 'Комментарий 1', '1234-12-12 12:34', false, false, 1, 1),
       (2, 'Комментарий 2', '1234-12-12 12:34', true, false, 2, 1),
       (3, 'Комментарий 3', '1234-12-12 12:34', false, true, 3, 1);

INSERT INTO internal_order_products (id, amount, price, product_id)
VALUES (1, 10, 100, 1),
       (2, 20, 100, 2),
       (3, 30, 100, 3);


SELECT setval('internal_order_id_seq', max(id))
FROM internal_order;