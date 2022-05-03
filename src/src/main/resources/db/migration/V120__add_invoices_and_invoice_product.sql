INSERT INTO invoice (id, comment, date, is_spend, is_sent, is_print, type_of_invoice, company_id,
                     contractor_id, warehouse_id, invoices_status_id)
VALUES (33, 'Комментарий 3', '2021-12-06 10:10:10.000000', false, false, false, 1, 1, 1, 1, 1),
       (34, 'Комментарий 4', '2021-12-07 10:10:10.000000', false, false, false, 0, 1, 1, 1, 1),
       (35, 'Комментарий 5', '2021-12-08 10:10:10.000000', false, false, false, 0, 1, 1, 1, 1);

INSERT INTO invoice_product (id, amount, price, invoice_id, product_id)
VALUES (10, 30.00, 100.00, 33, 1),
       (11, 50.00, 200.00, 34, 2),
       (12, 10.00, 100.00, 35, 3);

SELECT setval('invoice_product_id_seq', max(id))
FROM invoice_product;

SELECT setval('invoice_id_seq', max(id))
FROM invoice