INSERT INTO contractor_statuses (id, name)
VALUES (1, 'Новый'),
       (2, 'Выслано предложение'),
       (3, 'Переговоры'),
       (4, 'Сделка заключена'),
       (5, 'Сделка не заключена');
SELECT setval('contractor_statuses_id_seq', max(id))
FROM contractor_statuses