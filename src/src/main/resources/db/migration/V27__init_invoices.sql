INSERT INTO invoice (id, comment, date, is_spend, is_sent, is_print, type_of_invoice, company_id,
                     contractor_id, warehouse_id, invoices_status_id)
VALUES (30, 'Комментарий 0', '2021-08-10 09:03:49.618745', false, false, false, 1, 1, 1, 1, 1),
       (31, 'Комментарий 1', '2021-08-10 09:03:49.647741', false, false, false, 0, 1, 1, 2, 2),
       (32, 'Комментарий 2', '2021-08-10 09:03:49.649740', false, false, false, 0, 1, 1, 3, 1);

SELECT setval('invoice_id_seq', max(id))
FROM invoice