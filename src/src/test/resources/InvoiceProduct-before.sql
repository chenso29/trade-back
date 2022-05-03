ALTER TABLE invoice_product DROP IF EXISTS invoice_product_id;

DELETE FROM invoice_product;

ALTER TABLE invoice_product ADD invoice_product_id bigint;

TRUNCATE products CASCADE;
TRUNCATE invoice CASCADE;

INSERT INTO invoice (id, comment, data, is_spend, type_of_invoice, company_id, contractor_id, warehouse_id)
VALUES (1, 'Комментарий 0', '2021-08-10 09:03:49.618745', false, 1, 1, 1, 1),
       (2, 'Комментарий 1', '2021-08-10 09:03:49.647741', false, 1, 1, 1, 2),
       (3, 'Комментарий 2', '2021-08-10 09:03:49.649740', false, 1, 1, 1, 3);

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (2, false, null, 'Красные Бананы голден0', 0, 0, 'Бананы0', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (3, false, null, 'Красные Мандарины голден0', 0, 0, 'Мандарины0', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (4, false, null, 'Красные яблоки голден1', 0, 0, 'Яблоки1', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (5, false, null, 'Красные Бананы голден1', 0, 0, 'Бананы1', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2);

INSERT INTO invoice_product (id,amount,price,invoice_id,product_id)
VALUES (94, 1, 1, 1,1),
       (95, 2, 2, 1,2),
       (96, 3, 3, 1,3),
       (97, 4, 4, 1,4),
       (98, 5, 5, 1,5);

SELECT setval('invoice_product_id_seq', max(id))
FROM invoice_product;