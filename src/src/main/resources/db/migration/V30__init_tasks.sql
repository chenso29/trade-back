INSERT INTO task (id, completed, creation_date_time, deadline_date_time, description, task_author_id,
                  task_employee_id, task_contractor_id)
VALUES (1, true, '2021-07-31 09:03:48.000000', '2021-09-24 09:03:48.000000', 'Описание задачи номер 0.', 5, 4, 1),
       (2, true, '2021-06-14 09:03:48.000000', '2021-10-24 09:03:48.000000', 'Описание задачи номер 1.', 2, 4, 2),
       (3, true, '2021-05-23 09:03:48.000000', '2021-09-29 09:03:48.000000', 'Описание задачи номер 2.', 1, 1, 1),
       (4, true, '2021-06-27 09:03:48.000000', '2021-10-19 09:03:48.000000', 'Описание задачи номер 3.', 3, 3, 1),
       (5, true, '2021-05-04 09:03:48.000000', '2021-08-23 09:03:48.000000', 'Описание задачи номер 4.', 3, 4, 1),
       (6, true, '2021-06-06 09:03:48.000000', '2021-10-22 09:03:48.000000', 'Описание задачи номер 5.', 1, 5, 2);
SELECT setval('task_id_seq', max(id))
FROM task