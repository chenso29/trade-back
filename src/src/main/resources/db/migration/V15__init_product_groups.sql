INSERT INTO product_groups (id, name, service_group, sort_number)
VALUES (1, 'Товарная группа №1', false, '1'),
       (2, 'Товарная группа №2', false, '2'),
       (3, 'Товарная группа №3', false, '3'),
       (4, 'Товарная группа №4', false, '4'),
       (5, 'Товарная группа №5', false, '5'),
       (6, 'Товарная группа №6', false, '6'),
       (7, 'Товарная группа №7', false, '7'),
       (8, 'Товарная группа №8', false, '8'),
       (9, 'Товарная группа №9', false, '9'),
       (10, 'Товарная группа №10', false, '10'),
       (11, 'Товарная группа №11', false, '11'),
       (12, 'Товарная группа №12', false, '12'),
       (13, 'Товарная группа №13', false, '13'),
       (14, 'Товарная группа №14', false, '14'),
       (15, 'Товарная группа №15', false, '15');
SELECT setval('product_groups_id_seq', max(id))
FROM product_groups
