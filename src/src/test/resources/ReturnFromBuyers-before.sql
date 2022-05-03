TRUNCATE return_from_buyers CASCADE;

INSERT INTO return_from_buyers(id, date, warehouse_id, contractor_id, company_id, total_price, contract_id, is_send, is_print, comment, is_conducted)
VALUES (1, '12.02.2021 12:33', 1, 1, 1, 1000.00, 1, 'false', 'false', 'Возврат1', 'true'),
       (2, '27.05.2021 10:13', 2, 2, 2, 4000.00, 2, 'true', 'true', 'Возврат2', 'false');
SELECT setval('return_from_buyers_id_seq', max(id))
FROM return_from_buyers