TRUNCATE correction_products CASCADE;
TRUNCATE products CASCADE;

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (5, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, null, null, null,
        null, null);

INSERT INTO correction_products (id, product_id, amount, price) VALUES
(1, 5, 11.00, 12.00),
(2, 5, 13.00, 14.00),
(3, 5, 15.00, 16.00),
(4, 5, 17.00, 18.00),
(5, 5, 18.00, 19.00),
(6, 5, 19.00, 20.00),
(7, 5, 20.00, 21.00),
(8, 5, 21.00, 22.00),
(9, 5, 22.00, 23.00);

SELECT setval('correction_products_id_seq', max(id)) FROM correction_products;
