INSERT INTO inventarizations (id, comment, date, status, is_sent, is_print, company_id, warehouse_id)
VALUES (27, 'Инвентаризация 1', '2021-06-29 14:14:00.000000', false, false, false, 1, 1),
       (28, 'Инвентаризация 2', '2021-06-29 14:14:00.000000', false, false, false, 1, 1),
       (29, 'Инвентаризация 3', '2021-06-29 14:14:00.000000', false, false, false, 1, 1);
SELECT setval('inventarizations_id_seq', max(id))
FROM inventarizations