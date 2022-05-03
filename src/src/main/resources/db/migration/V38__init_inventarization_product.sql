INSERT INTO inventarization_products (id, actual_amount, price, product_id)
VALUES (1, 83.00, 79.00, 1),
       (2, 61.00, 78.00, 2),
       (3, 62.00, 66.00, 3),
       (4, 81.00, 76.00, 4),
       (5, 82.00, 81.00, 5),
       (6, 65.00, 79.00, 6),
       (7, 76.00, 74.00, 7),
       (8, 76.00, 83.00, 8),
       (9, 82.00, 73.00, 9),
       (10, 67.00, 55.00, 10),
       (11, 99.00, 98.00, 11),
       (12, 63.00, 75.00, 12);
SELECT setval('inventarization_products_id_seq', max(id))
FROM inventarization_products