TRUNCATE departments CASCADE;
TRUNCATE  employees CASCADE;
TRUNCATE stages CASCADE;

INSERT INTO departments (id, name, sort_number)
VALUES (1, 'Руководство', '1');

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', null, null, 1);

INSERT INTO stages (id, name, description, department_id, employee_id)
VALUES (1, 'Основной этап', 'Описание этапа', 1, 1),
       (2, 'Второй этап', 'Описание этапа', 1, 1);