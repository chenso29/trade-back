INSERT INTO contractor_groups (id, name, sort_number)
VALUES (1, 'Покупатель', '1'),
       (2, 'Поставщик', '2');
SELECT setval('contractor_groups_id_seq', max(id))
FROM contractor_groups