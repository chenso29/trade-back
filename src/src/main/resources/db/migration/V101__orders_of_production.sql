INSERT INTO orders_of_production (id, date, company_id, technical_card_id, volume, produce,
                                  planned_production_date, is_sent, is_print, comment)
VALUES (1, '2021-08-10 12:15:00.000000', 1, 1, 120, 0, '2022-08-10 12:15:00.000000', false, true, 'Комментарий1'),
       (2, '2021-08-10 12:15:00.000000', 2, 2, 130, 0, '2022-08-10 12:15:00.000000', false, true, 'Комментарий2'),
       (3, '2021-08-10 12:15:00.000000', 1, 1, 140, 0, '2022-08-10 12:15:00.000000', false, true, 'Комментарий3');
SELECT setval('orders_of_production_id_seq', max(id))
FROM orders_of_production