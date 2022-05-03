TRUNCATE task CASCADE;

INSERT INTO task (id, completed, creation_date_time, deadline_date_time, description, task_author_id,
                  task_employee_id)
VALUES (1, true, '2021-07-31 09:03:48', '2021-09-24 09:03:48', 'Описание задачи номер 0.', 5, 4),
       (2, true, '2021-06-14 09:03:48.000000', '2021-10-24 09:03:48.000000', 'Описание задачи номер 1.', 2, 4),
       (3, true, '2021-05-23 09:03:48.000000', '2021-09-29 09:03:48.000000', 'Описание задачи номер 2.', 1, 1);
SELECT setval('task_id_seq', max(id))
FROM task