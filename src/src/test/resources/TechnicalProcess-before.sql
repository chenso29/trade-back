TRUNCATE technical_processes CASCADE;
TRUNCATE departments CASCADE;
TRUNCATE employees CASCADE;
TRUNCATE stages CASCADE;
TRUNCATE TECHNICAL_PROCESS_PRODUCTION_STAGES CASCADE;

INSERT INTO departments (id, name, sort_number)

VALUES  (1, 'name1', 'sort num 1'),
        (2, 'name2', 'sort num 2'),
        (3, 'name3', 'sort num 3');

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', null, null, 1),
       (2, 'Some special text about Sima', 'simaogon@mail.ru', 'Sima', '526317984678', 'Simonova', 'Semenovna', '54321',
        '+7(999)222-11-33', '2', null, null, 2),
       (3, 'Some special text about Vera', 'veraogon@mail.ru', 'Vera', '526317555678', 'Belive', 'Henrichovna', '76543',
        '+7(999)777-11-33', '3', null, null, 5),
       (4, 'Some special text about Karim', 'karimogon@mail.ru', 'Karim', '526316666678', 'Islentiev', 'Dmitrievich',
        'qwerty', '+7(999)222-77-00', '4', null, null, 4),
       (5, 'Some special text about Sasha', 'sashaogon@mail.ru', 'Sasha', '526317984600', 'Petko', '', 'asdfg',
        '+7(999)222-00-33', '5', null, null, 19);

INSERT INTO stages(id, name, description, department_id, employee_id)
VALUES (1, 'name1', 'description', 1, 1),
       (2, 'name2', 'description', 2, 2);

INSERT INTO technical_processes (id, name, description, is_archived, is_shared, department_owner_id, employee_owner_id,
                               date_of_change, employee_who_last_changed_id)
VALUES (1, 'Основной тех.процесс', 'Тестовое описание ТП', false, false, 1, 2, '2021-10-11 08:00:00.000000', 3),
       (2, 'Дополнительный тех.процесс', 'Тестовое описание ТП', false, false, 2, 3, '2021-10-11 08:00:00.000000', 4);

-- INSERT INTO technical_process_production_stages(technical_process_id, production_stage_id)
-- VALUES (1, 1),
--        (2, 2);

SELECT setval('technical_processes_id_seq', max(id))
FROM technical_processes;

