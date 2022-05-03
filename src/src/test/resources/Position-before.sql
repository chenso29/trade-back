TRUNCATE positions CASCADE;

INSERT INTO positions (id, name, sort_number)
VALUES (1, 'Stanislav', '1'),
       (2, 'Vasya', '2'),
       (3, 'Evgen', '3');

SELECT setval('positions_id_seq', max(id))
FROM positions;