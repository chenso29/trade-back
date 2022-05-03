INSERT INTO internal_order (id, comment, date, is_print, is_sent, company_id, warehouse_id)
VALUES (17, 'Внутренний заказ 1', '2021-08-10 12:15:00.000000', false, false, 1, 1),
       (18, 'Внутренний заказ 4', '2021-08-10 12:15:00.000000', false, false, 2, 1),
       (19, 'Внутренний заказ 7', '2021-08-10 12:15:00.000000', false, false, 3, 1),
       (20, 'Внутренний заказ 10', '2021-08-10 12:15:00.000000', false, false, 4, 1),
       (21, 'Внутренний заказ 13', '2021-08-10 12:15:00.000000', false, false, 5, 1);
SELECT setval('internal_order_id_seq', max(id))
FROM internal_order