INSERT INTO technical_card_groups (id, comment, name, sort_number)
VALUES (1, 'Комментарий1', 'Группа технических карт №1', '1'),
       (2, 'Комментарий2', 'Группа технических карт №2', '2');
SELECT setval('technical_card_groups_id_seq', max(id))
FROM technical_card_groups