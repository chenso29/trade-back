INSERT INTO retail_operation_with_points (number, type_operation, number_of_points, accrual_date,
                                          bonus_program_id, contractor_id, task_id)
VALUES (1, 'начисление', 1000, '2021-08-10 12:15:00.000000', 1, 1, 1),
       (1, 'начисление', 2000, '2021-08-10 12:15:00.000000', 2, 1, 2),
       (1, 'начисление', 3000, '2021-08-10 12:15:00.000000', 3, 2, 3),
       (1, 'начисление', 4000, '2021-08-10 12:15:00.000000', 5, 5, 5),
       (1, 'списание', 5000, '2021-08-10 12:15:00.000000' , 4, 4, 2);