TRUNCATE contractor_groups CASCADE;

INSERT INTO contractor_groups (id, name, sort_number)
VALUES (1, 'Name 1', '1'),
       (2, 'Name 2', '2'),
       (3, 'Name 3', '3'),
       (4, 'Name 4', '4'),
       (5, 'Name 5', '5'),
       (6, 'Name 6', '6'),
       (7, 'Name 7', '7'),
       (8, 'Name 8', '8'),
       (9, 'Name 9', '9');

SELECT setval('contractor_groups_id_seq', max(id))
FROM contractor_groups;