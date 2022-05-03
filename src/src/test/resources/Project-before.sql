TRUNCATE projects CASCADE;

INSERT INTO projects (id, code, description, name)
VALUES (1, '0001', 'description1', 'name1'),
       (2, '0002', 'description2', 'name2'),
       (3, '0003', 'description3', 'name3');

SELECT setval('projects_id_seq', max(id))
FROM projects;