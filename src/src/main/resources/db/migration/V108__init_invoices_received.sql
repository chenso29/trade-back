INSERT INTO invoices_received (id, comment, data, incom_data, incom_number, is_spend, is_send, is_print, company_id, contractor_id, acceptance_id)
VALUES (1, 'Комментарий 0', '2021-08-10 09:03:00', '2021-08-10 09:03:45', 1, false, false, true, 1, 1, 1),
       (2, 'Комментарий 1', '2021-09-10 09:03:00', '2021-08-10 10:03:40', 2, false, true, true, 1, 1, 2),
       (3, 'Комментарий 2', '2021-06-10 09:03:10', '2021-08-10 11:03:10', 3, false, false, true, 1, 1, 3),
       (4, 'Комментарий 3', '2021-03-10 09:03:20', '2021-08-10 11:03:20', 4, false, true, true, 1, 2, 4),
       (5, 'Комментарий 4', '2021-01-10 09:03:40', '2021-08-10 11:03:33', 5, false, false, true, 1, 2, 5);
SELECT setval('invoices_received_id_seq', max(id))
FROM invoices_received