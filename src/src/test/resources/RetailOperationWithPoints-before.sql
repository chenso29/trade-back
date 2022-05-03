TRUNCATE retail_operation_with_points CASCADE;
TRUNCATE retail_operation_with_points_files CASCADE;
TRUNCATE task CASCADE;


INSERT INTO task (id, completed, creation_date_time, deadline_date_time, description, task_author_id,
                  task_employee_id)
VALUES (1, true, '2021-07-31 09:03:48.000000', '2021-09-24 09:03:48.000000', 'Описание задачи номер 0.', 5, 4);

INSERT INTO retail_operation_with_points (id,number, date, type_operation, number_of_points, accrual_date,
                                          bonus_program_id, contractor_id, task_id)
VALUES (1, 1, '2021-08-10 12:15:00.000000', 'accrual', 1000, '2021-08-10 12:15:00.000000', 1, 1, 1),
       (2, 1, '2021-08-10 12:15:00.000000', 'начисление', 2000, '2021-08-10 12:15:00.000000', 2, 1, 1),
       (3, 1, '2021-08-10 12:15:00.000000', 'начисление', 3000, '2021-08-10 12:15:00.000000', 3, 1, 1),
       (4, 1, '2021-08-10 12:15:00.000000', 'начисление', 4000, '2021-08-10 12:15:00.000000', 5, 1, 1),
       (5, 1, '2021-08-10 12:15:00.000000', 'списание', 5000, '2021-08-10 12:15:00.000000' , 4, 1, 1 );

INSERT INTO retail_operation_with_points_files (retail_operation_with_points_id, files_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 2),
       (5, 2),
       (5, 1);