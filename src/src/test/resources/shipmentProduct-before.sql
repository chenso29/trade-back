TRUNCATE products CASCADE;
TRUNCATE shipment_products CASCADE;

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, null, null, null,
        null, null);

INSERT INTO shipment_products (id, product_id, amount, price)
VALUES (1, 1, 10, 100),
       (2, 1, 15, 150);