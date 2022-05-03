TRUNCATE roles CASCADE;

INSERT INTO roles (id, name, sort_number)
VALUES (1, 'admin', '1'),
       (2, 'user', '1'),
       (3, 'guest', '1');

SELECT setval('roles_id_seq', max(id))
FROM roles;