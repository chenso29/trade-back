INSERT INTO attribute_of_calculation_objects (id, is_service, name, sort_number)
VALUES (1, true, 'Услуга', '1'),
       (2, true, 'Работа', '2'),
       (3, true, 'Предоставление РИД', '3'),
       (4, true, 'Составной предмет расчета', '4'),
       (5, true, 'Иной предмет расчета', '5'),
       (6, false, 'Товар', '6'),
       (7, false, 'Подакцизный товар', '7'),
       (8, false, 'Составной предмет расчета', '8'),
       (9, false, 'Иной предмет расчета', '9');
SELECT setval('attribute_of_calculation_objects_id_seq', max(id))
FROM attribute_of_calculation_objects
