INSERT INTO price_lists (id, commentary, number, printed, sent, time, company_id)
VALUES (1, 'Тестовый комментарий 1', '00011', false, false, '2021-08-10 12:15:00.000000', 1),
       (2, 'Тестовый комментарий 2', '00012', false, false, '2021-08-10 12:15:00.000000', 2),
       (3, 'Тестовый комментарий 3', '00013', false, false, '2021-08-10 12:15:00.000000', 3),
       (4, 'Тестовый комментарий 4', '00014', false, false, '2021-08-10 12:15:00.000000', 4),
       (5, 'Тестовый комментарий 5', '00015', false, false, '2021-08-10 12:15:00.000000', 5);
SELECT setval('price_lists_id_seq', max(id))
FROM price_lists