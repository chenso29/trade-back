TRUNCATE loss CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE loss_products CASCADE;

ALTER TABLE loss DROP CONSTRAINT IF EXISTS FK2cojnqb10sweqhpjdotxsmveg;
ALTER TABLE loss DROP CONSTRAINT IF EXISTS FKbq7g1lqjeaaadiumdwktq9plv;

INSERT INTO loss (id, comment, date, is_print, is_sent, company_id, warehouse_id)
VALUES (1, 'Comment1', '2021-07-01 11:11', true, false, 1, 1),
       (2, 'Comment1', '2021-08-02 12:12', false, true, 1, 2),
       (3, 'Comment1', '2021-09-03 13:13', true, false, 2, 2);

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
values  (1, 'Address1', 'Comment1', 'CommentAddress1', 'Основной склад', '00001'),
        (2, 'Address2', 'Comment2', 'CommentAddress2', 'Основной склад1', '00001'),
        (3, 'Address3', 'Comment3', 'CommentAddress3', 'Основной склад2', '00001'),
        (4, 'Address4', 'Comment4', 'CommentAddress4', 'Основной склад3', '00001');

INSERT INTO loss_products (id, amount, price, product_id)
VALUES (1, 11, 100, 1),
       (2, 22, 200, 2),
       (3, 33, 300, 3);


