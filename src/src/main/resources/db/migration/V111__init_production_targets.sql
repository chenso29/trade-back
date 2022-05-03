INSERT INTO production_targets (id, date, company_id, delivery_planned_moment, material_warehouse_id,
                                production_warehouse_id, production_start, production_end, shared, Owner, employee_owner,
                                published, printed, description, updated, updated_by_name)
VALUES (1, '2021-08-10 12:15:00.000000', 1, '2021-08-11 12:15:00.000000', 1, 4,
        '2021-08-09 08:00:00.000000',  '2021-08-22 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 1', '2021-08-22 08:00:00.000000', 'Я'),
       (2, '2021-08-10 12:15:00.000000', 1, '2022-08-01 12:15:00.000000', 2, 3,
        '2021-08-11 08:00:00.000000',  '2021-11-19 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 2', '2021-08-22 08:00:00.000000', 'Я'),
       (3, '2021-08-10 12:15:00.000000', 1, '2021-08-11 12:15:00.000000', 3, 1,
        '2021-09-09 08:00:00.000000',  '2021-08-19 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 3', '2021-08-22 08:00:00.000000', 'Я');
SELECT setval('production_targets_id_seq', max(id))
FROM production_targets