INSERT INTO retail_sales (id, time, retail_store_id, contractor_id, company_id, sum_cash,
                          sum_non_cash, prepayment, sum_discount, sum, sent, printed, comment)
VALUES (1, '2021-08-11', 1, 1, 1, 145000.00, 0.00, 0.00, 0.00, 145000.00, false, false, 'Комментарий 1'),
       (2, '2020-08-11', 2, 2, 2, 0.00, 145000.00, 0.00, 0.00, 145000.00, true, true, 'Комментарий 2'),
       (3, '2019-08-11', 3, 3, 3, 236000.00, 0.00, 0.00, 0.00, 236000.00, false, true, 'Комментарий 3'),
       (4, '2018-08-11', 1, 1, 1, 145000.00, 0.00, 0.00, 1000.00, 144000.00, true, false, 'Комментарий 4'),
       (5, '2017-08-11', 2, 2, 2, 100000.00, 0.00, 0.00, 0.00, 100000.00, false, true, 'Комментарий 5'),
       (6, '2016-08-11', 3, 3, 3, 0.00, 300000.00, 0.00, 0.00, 300000.00, true, false, 'Комментарий 6');
SELECT setval('retail_sales_id_seq', max(id))
FROM retail_sales