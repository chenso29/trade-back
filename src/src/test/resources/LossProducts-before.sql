TRUNCATE products CASCADE;

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1);

INSERT INTO loss_products (id, amount, price, product_id)
VALUES (1, 50.00, 100.00, 1),
       (2, 44.00, 87.00, 1);