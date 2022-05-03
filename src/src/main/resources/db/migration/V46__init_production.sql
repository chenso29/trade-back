INSERT INTO productions (id, requests_productions_id, technical_card_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);
SELECT setval('productions_id_seq', max(id))
FROM productions