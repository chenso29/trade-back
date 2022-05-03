TRUNCATE employees CASCADE;
TRUNCATE employees_roles CASCADE;
TRUNCATE images CASCADE;

ALTER TABLE employees_roles DROP CONSTRAINT IF EXISTS FKr9b8ry8qtdtoc8pcw56ug54x5;

INSERT INTO employees_roles (employee_id, roles_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (4, 2);

INSERT INTO images (id, image_url, sort_number)
VALUES (1, 'src/main/resources/file/employee_image.png', 1),
       (2, 'src/main/resources/file/employee_image.png', 1),
       (3, 'src/main/resources/file/employee_image.png', 1),
       (4, 'src/main/resources/file/employee_image.png', 1);

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', 1, 1, 1),
       (2, 'Some special text about Sima', 'simaogon@mail.ru', 'Sima', '526317984678', 'Simonova', 'Semenovna', '54321',
        '+7(999)222-11-33', '2', 2, 2, 2),
       (3, 'Some special text about Vera', 'veraogon@mail.ru', 'Vera', '526317555678', 'Belive', 'Henrichovna', '76543',
        '+7(999)777-11-33', '3', 3, 3, 5),

       (4, 'Some special text about Sasha', 'sashaogon@mail.ru', 'Sasha', '526317984600', 'Petko', '', 'asdfg',
        '+7(999)222-00-33', '5', 2, 4, 19);

SELECT setval('employees_id_seq', max(id)) FROM employees;