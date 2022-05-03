INSERT INTO projects (id, code, description, name)
VALUES (1, '0000', 'description', 'name'),
       (2, '0000', 'description', 'name2'),
       (3, '0000', 'description', 'name3'),
       (4, '0000', 'description', 'name4');
SELECT setval('projects_id_seq', max(id))
FROM projects