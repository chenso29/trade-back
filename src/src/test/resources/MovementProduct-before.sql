ALTER TABLE movement_movement_products DROP IF EXISTS movement_products_id;

DELETE FROM movement_products;

TRUNCATE products CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012', 'inbox@5ka.ru', '8 (800) 555-55-05',
        'Агроаспект, ООО', '8 (800) 555-55-05', '2', 2, 2, 1, 2, 1, 2),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1);

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
       (78, false, null, 'Красные Мандарины голден2', 0, 0, 'Мандарины2', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (100, false, null, 'Красные яблоки голден3', 0, 0, 'Яблоки3', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (86, false, null, 'Красные Бананы голден3', 0, 0, 'Бананы3', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (69, false, null, 'Красные Мандарины голден3', 0, 0, 'Мандарины3', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3,3),
       (84, false, null, 'Красные яблоки голден4', 0, 0, 'Яблоки4', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (72, false, null, 'Красные Бананы голден4', 0, 0, 'Бананы4', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (75, false, null, 'Красные Мандарины голден4', 0, 0, 'Мандарины4', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3,3);


ALTER TABLE movement_movement_products
    ADD movement_products_id bigint;


INSERT INTO movement_products (id, product_id, amount, price)
VALUES (1, 1, 11.00, 12.00),
       (2, 2, 13.00, 14.00),
       (3, 3, 15.00, 16.00),
       (4, 4, 17.00, 18.00),
       (5, 75.00, 64.00, 5),
       (6, 72.00, 100.00, 6),
       (7, 84.00, 83.00, 7),
       (8, 69.00, 55.00, 8),
       (9, 86.00, 63.00, 9),
       (10, 78.00, 55.00, 10),
       (11, 69.00, 100.00, 11),
       (12, 100.00, 57.00, 12);


SELECT setval('movement_products_id_seq', max(id))
FROM movement_products;