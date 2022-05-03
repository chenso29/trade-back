TRUNCATE requsts_productions CASCADE;
ALTER TABLE requsts_productions DROP CONSTRAINT IF EXISTS FK1pxvfl8rgagtov03yhao6lvh2;
ALTER TABLE requsts_productions DROP CONSTRAINT IF EXISTS FKa2bd3abtixgb5c9f27bbo8kcg;

INSERT INTO requsts_productions (id, date_of_the_certificate, number_of_the_certificate, volume, technical_card_id, warehouse_id)
VALUES (1, '2021-08-11', 'RP-001', 500, 1, 1),
       (2, '2021-08-11', 'RP-002', 1000, 2, 2),
       (3, '2021-08-11', 'RP-003', 720, 2, 1);

SELECT setval('requsts_productions_id_seq', max(id))
FROM requsts_productions;
