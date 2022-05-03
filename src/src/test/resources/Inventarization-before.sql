DELETE FROM inventarizations_inventarization_products;
DELETE FROM inventarizations;
DELETE FROM inventarization_products;

INSERT INTO inventarizations (id, comment, date, status, company_id, warehouse_id)
VALUES (1, 'Инвентаризация 1', '2021-07-03 12:25', false, 1, 1),
       (2, 'Инвентаризация 2', '2021-07-03 12:25', false, 2, 1),
       (3, 'Инвентаризация 3', '2021-07-03 12:25', false, 3, 1);

INSERT INTO inventarizations_inventarization_products (inventarization_id, inventarization_products_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO inventarization_products (id, actual_amount, price, product_id)
VALUES (1, 11, 111, 1),
       (2, 22, 222, 2),
       (3, 33, 333, 3);

SELECT setval('inventarizations_id_seq', max(id))
FROM inventarizations;

