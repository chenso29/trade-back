INSERT INTO movement (id, comment, date, when_changed_date,
                      is_print, is_sent, is_spend, company_id,
                      project_id, employee_changed_id, warehouse_id, warehouse_to_id)
VALUES (1, 'Перемещение 1', '2021-08-10 09:03:00.000000', '2021-08-10', false, false, false, 1, 1, 1, 1, 2),
       (2, 'Перемещение 2', '2021-08-10 09:03:00.000000', '2021-08-10', false, false, false, 1, 1, 2, 1, 3),
       (3, 'Перемещение 3', '2021-08-10 09:03:00.000000', '2021-08-10', false, true, false, 1, 1, 1, 2, 1);
SELECT setval('movement_id_seq', max(id))
FROM movement