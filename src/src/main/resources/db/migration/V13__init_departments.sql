INSERT INTO departments(id, name, sort_number)
VALUES (1, 'Руководство', '1'),
       (2, 'Отдел бухгалтерии', '2'),
       (3, 'Отдел закупок', '3'),
       (4, 'Отдел продаж', '4'),
       (5, 'Складской комплекс', '5'),
       (6, 'Транспортный отдел', '6'),
       (7, 'Финансовый отдел', '7'),
       (8, 'Отдел маркетинга', '8'),
       (9, 'HR отдел', '9');
SELECT setval('departments_id_seq', max(id))
FROM departments