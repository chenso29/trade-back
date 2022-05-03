INSERT INTO type_of_contractors (id, name, sort_number)
VALUES (1, 'Юридическое лицо', '1'),
       (2, 'Индивидуальный предприниматель', '2'),
       (3, 'Физическое лицо', '3');
SELECT setval('type_of_contractors_id_seq', max(id))
FROM type_of_contractors