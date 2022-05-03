TRUNCATE productions CASCADE;
TRUNCATE requsts_productions CASCADE;

INSERT INTO requsts_productions (id, date_of_the_certificate, number_of_the_certificate, volume, technical_card_id,
                                 warehouse_id)
VALUES (1, '2021-08-10', 'RP-001', 500, 1, 1),
       (2, '2021-08-10', 'RP-002', 1000, 2, 2),
       (3, '2021-08-10', 'RP-003', 720, 2, 1);

INSERT INTO productions (id, requests_productions_id, technical_card_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);

SELECT setval('productions_id_seq', max(id))
FROM productions;
