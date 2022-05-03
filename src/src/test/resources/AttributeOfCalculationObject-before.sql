truncate attribute_of_calculation_objects CASCADE;

delete from attribute_of_calculation_objects;

insert into attribute_of_calculation_objects(id, name, sort_number, is_service)values
(1, 'name1', 'sort num 1', true),
(2, 'name2', 'sort num 2', true),
(3, 'name3', 'sort num 3', true);

SELECT setval('attribute_of_calculation_objects_id_seq', max(id)) FROM attribute_of_calculation_objects;