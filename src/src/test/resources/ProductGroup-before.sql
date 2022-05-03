TRUNCATE product_groups CASCADE;

INSERT INTO product_groups (id, name, service_group, sort_number)
VALUES (1, 'Товарная группа №1', false, '1'),
       (2, 'Товарная группа №2', false, '2'),
       (3, 'Товарная группа №3', false, '3');