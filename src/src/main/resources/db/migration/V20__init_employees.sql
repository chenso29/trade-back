INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', 1, 1, 1),
       (2, 'Some special text about Sima', 'simaogon@mail.ru', 'Sima', '526317984678', 'Simonova', 'Semenovna', '54321',
        '+7(999)222-11-33', '2', 2, 2, 2),
       (3, 'Some special text about Vera', 'veraogon@mail.ru', 'Vera', '526317555678', 'Belive', 'Henrichovna', '76543',
        '+7(999)777-11-33', '3', 5, 3, 5),
       (4, 'Some special text about Karim', 'karimogon@mail.ru', 'Karim', '526316666678', 'Islentiev', 'Dmitrievich',
        'qwerty', '+7(999)222-77-00', '4', 4, 4, 4),
       (5, 'Some special text about Sasha', 'sashaogon@mail.ru', 'Sasha', '526317984600', 'Petko', 'Petrovich', 'asdfg',
        '+7(999)222-00-33', '5', 4, 5, 19),
       (6, 'Some special text about Oleg', 'olegogon@mail.ru', 'Oleg', '526317974236', 'Petrov', 'Ivanovich', 'terry',
        '+7(999)555-66-77', '6', 4, 6, 19);

SELECT setval('employees_id_seq', max(id))
FROM employees