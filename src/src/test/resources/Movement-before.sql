DELETE FROM movement_movement_products;
DELETE FROM movement;

TRUNCATE movement_products CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE products CASCADE;

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (2, false, null, 'Красные Бананы голден0', 0, 0, 'Бананы0', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (3, false, null, 'Красные Мандарины голден0', 0, 0, 'Мандарины0', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (4, false, null, 'Красные яблоки голден1', 0, 0, 'Яблоки1', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (5, false, null, 'Красные Бананы голден1', 0, 0, 'Бананы1', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (6, false, null, 'Красные Мандарины голден1', 0, 0, 'Мандарины1', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (7, false, null, 'Красные яблоки голден2', 0, 0, 'Яблоки2', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (8, false, null, 'Красные Бананы голден2', 0, 0, 'Бананы2', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (9, false, null, 'Красные Мандарины голден2', 0, 0, 'Мандарины2', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (10, false, null, 'Красные яблоки голден3', 0, 0, 'Яблоки3', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (11, false, null, 'Красные Бананы голден3', 0, 0, 'Бананы3', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (12, false, null, 'Красные Мандарины голден3', 0, 0, 'Мандарины3', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3,3),
       (84, false, null, 'Красные яблоки голден4', 0, 0, 'Яблоки4', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (72, false, null, 'Красные Бананы голден4', 0, 0, 'Бананы4', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (75, false, null, 'Красные Мандарины голден4', 0, 0, 'Мандарины4', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3,3);


INSERT INTO movement_products (id, amount, price, product_id)
VALUES (1, 53.00, 79.00, 1),
       (2, 91.00, 88.00, 2),
       (3, 94.00, 82.00, 3),
       (4, 56.00, 70.00, 4),
       (5, 75.00, 64.00, 5),
       (6, 72.00, 100.00, 6),
       (7, 84.00, 83.00, 7),
       (8, 69.00, 55.00, 8),
       (9, 86.00, 63.00, 9),
       (10, 78.00, 55.00, 10),
       (11, 69.00, 100.00, 11),
       (12, 100.00, 57.00, 12);

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

insert into public.warehouses (id, address, comment, comment_to_address, name, sort_number)
values  (1, null, null, null, 'Основной склад', '00001'),
        (2, null, null, null, 'Основной склад1', '00001'),
        (3, null, null, null, 'Основной склад2', '00001'),
        (4, null, null, null, 'Основной склад3', '00001');

INSERT INTO movement (id, comment, date, is_print, is_sent, company_id, warehouse_from_id,warehouse_to_id)
VALUES (1, 'Перемещение 1', '2021-07-16 15:10', false, false, 1, 1, 2),
       (2, 'Перемещение 2', '2021-07-16 15:10', false, false, 1, 2, 1),
       (3, 'Перемещение 3', '2021-07-16 15:10', false, false, 1, 1, 2);

INSERT INTO movement_movement_products (movement_id, movement_products_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9);

SELECT setval('movement_id_seq', max(id))
FROM movement;

