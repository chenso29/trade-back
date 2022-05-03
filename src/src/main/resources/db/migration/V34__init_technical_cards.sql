INSERT INTO technical_cards (id, comment, name, production_cost, technical_card_group_id)
VALUES (1, 'Комментарий1', 'Техническая карта №1', '1000', 1),
       (2, 'Комментарий2', 'Техническая карта №2', '1100', 1),
       (3, 'Комментарий3', 'Техническая карта №3', '1200', 2),
       (4, 'Комментарий4', 'Техническая карта №4', '1300', 2);
SELECT setval('technical_cards_id_seq', max(id))
FROM technical_cards