TRUNCATE technical_cards CASCADE;
DELETE FROM technical_card_groups;

INSERT INTO technical_card_groups (id, comment, name, sort_number)
VALUES (1, 'Комментарий 1', 'Группа технических карт №1', '1'),
       (2, 'Комментарий 2', 'Группа технических карт №2', '2'),
       (3, 'Комментарий 3', 'Группа технических карт №3', '3');

SELECT setval('technical_card_groups_id_seq', max(id))
FROM technical_card_groups;