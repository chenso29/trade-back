INSERT INTO invoice_product (id, amount, price, invoice_id, product_id)
VALUES (1, 52.00, 34.00, 30, 1),
       (2, 51.00, 127.00, 30, 2),
       (3, 77.00, 130.00, 30, 3),
       (4, 29.00, 100.00, 31, 1),
       (5, 78.00, 100.00, 31, 2),
       (6, 84.00, 139.00, 31, 3),
       (7, 31.00, 84.00, 32, 1),
       (8, 62.00, 48.00, 32, 2),
       (9, 82.00, 119.00, 32, 3);
SELECT setval('invoice_product_id_seq', max(id))
FROM invoice_product;