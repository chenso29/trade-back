INSERT INTO loss_products (id, amount, price, product_id)
VALUES (1, 50.00, 100.00, 1),
       (2, 44.00, 87.00, 2),
       (3, 14.00, 55.00, 3),
       (4, 50.00, 11.00, 4),
       (5, 99.00, 19.00, 5),
       (6, 25.00, 17.00, 6),
       (7, 10.00, 53.00, 7),
       (8, 77.00, 27.00, 8),
       (9, 5.00, 99.00, 9),
       (10, 9.00, 42.00, 10);
SELECT setval('loss_products_id_seq', max(id))
FROM loss_products