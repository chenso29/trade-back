INSERT INTO technical_operations (id, comment, is_print, is_sent, volume, date, technical_card_id, warehouse_id)
VALUES (1,  'Комментарий1', false, true, 12,'2021-08-10 12:15:00.000000', 2, 2),
       (2,  'Комментарий2', true, false, 13,'2021-08-10 12:15:00.000000', 2, 2),
       (3,  'Комментарий3', false, true, 14,'2021-08-10 12:15:00.000000', 2, 2);

SELECT setval('technical_operations_id_seq', max(id))
FROM technical_operations