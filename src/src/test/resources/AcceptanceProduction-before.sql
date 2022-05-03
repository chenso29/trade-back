TRUNCATE acceptance_productions CASCADE;
TRUNCATE products CASCADE;

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, null, null, null,
        null, null);
INSERT INTO acceptance_productions (id, amount,product_id)
VALUES (1, 2, 1),
       (2, 3, 1),
       (3, 4, 1),
       (4, 6, 1),
       (5, 7, 1);

SELECT setval('acceptance_productions_id_seq', max(id))
FROM acceptance_productions;