TRUNCATE audit CASCADE;

insert into audit(id, description, date, employee_id) values
(1, 'desc1', '2020-07-19 21:32:57.8', 1),
(2, 'desc2', '2020-07-19 21:32:57.8', 1);

SELECT setval('audit_id_seq', max(id))
FROM audit;

