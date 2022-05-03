INSERT INTO access_parameters (id, general_access, department_id, employee_id)
VALUES (1, false, 1, 1),
       (2, false, 2, 2),
       (3, false, 3, 3),
       (4, false, 4, 4),
       (5, false, 5, 5),
       (6, false, 1, 1),
       (7, false, 2, 2),
       (8, false, 3, 3),
       (9, false, 4, 4),
       (10, false, 5, 5);
SELECT setval('access_parameters_id_seq', max(id))
FROM access_parameters