INSERT INTO correction_products (id, amount, price, product_id)
VALUES (1, 64.00, 73.00, 1),
       (2, 68.00, 55.00, 2),
       (3, 95.00, 59.00, 3),
       (4, 96.00, 74.00, 4),
       (5, 73.00, 85.00, 5),
       (6, 80.00, 81.00, 6),
       (7, 95.00, 66.00, 7),
       (8, 76.00, 89.00, 8),
       (9, 71.00, 92.00, 9),
       (10, 79.00, 62.00, 10),
       (11, 71.00, 84.00, 11),
       (12, 80.00, 61.00, 12);
SELECT setval('correction_products_id_seq', max(id))
FROM correction_products