INSERT INTO loss (id, comment, date, is_print, is_sent, company_id, warehouse_id)
VALUES (22, 'Комментарий 1', '2021-09-19 09:03:00.000000', false, false, 1, 1),
       (23, 'Комментарий 2', '2021-09-20 09:03:00.000000', true, true, 2, 1),
       (24, 'Комментарий 3', '2021-09-21 09:03:00.000000', false, true, 1, 2);
SELECT setval('loss_id_seq', max(id))
FROM loss