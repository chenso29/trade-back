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
SELECT setval('movement_products_id_seq', max(id))
FROM movement_products