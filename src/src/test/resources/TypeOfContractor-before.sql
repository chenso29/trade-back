TRUNCATE type_of_contractors CASCADE;
delete from type_of_contractors;

insert into type_of_contractors(id, name, sort_number)
values

(1, 'TestType', '1'),
(2, 'TypeTest', '2');