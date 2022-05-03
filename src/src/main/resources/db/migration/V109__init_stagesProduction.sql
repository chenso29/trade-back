INSERT INTO stages (id, name, description, department_id, employee_id)
VALUES (1, 'Основной этап', 'Описание этапа', 1, 1),
       (2, 'Второй этап', 'Описание этапа', 2, 2);
SELECT setval('stages_id_seq', max(id))
FROM stages

