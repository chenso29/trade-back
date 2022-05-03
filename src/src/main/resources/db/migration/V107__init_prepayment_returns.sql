INSERT INTO prepayment_returns (id, time, retail_store_id, contractor_id, company_id, sum_cash,
                          sum_non_cash, sent, printed, comment)
VALUES (1, '2021-08-11', 1, 1, 1, 88000.00, 0.00, false, false, 'Комментарий 1'),
       (2, '2020-08-11', 2, 2, 2, 0.00, 5100.00, true, true, 'Комментарий 2'),
       (3, '2019-08-11', 3, 3, 3, 77000.00, 0.00, false, true, 'Комментарий 3');
SELECT setval('prepayment_returns_id_seq', max(id))
FROM prepayment_returns