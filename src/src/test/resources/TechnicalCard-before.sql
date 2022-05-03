TRUNCATE technical_cards_materials CASCADE;
TRUNCATE technical_cards_final_production CASCADE;

DELETE FROM technical_cards;
DELETE FROM technical_card_groups;

INSERT INTO technical_card_groups (id, comment, name, sort_number)
VALUES (1, 'Комментарий 1', 'Группа технических карт №1', '1'),
       (2, 'Комментарий 2', 'Группа технических карт №2', '2'),
       (3, 'Комментарий 3', 'Группа технических карт №3', '3');

INSERT INTO technical_cards (id, comment, name, production_cost, technical_card_group_id)
VALUES (1, 'Комментарий 1', 'Техническая карта №1', '1000', 1),
       (2, 'Комментарий 2', 'Техническая карта №2', '2000', 2),
       (3, 'Комментарий 3', 'Техническая карта №3', '3000', 3);

SELECT setval('technical_cards_id_seq', max(id))
FROM technical_cards;