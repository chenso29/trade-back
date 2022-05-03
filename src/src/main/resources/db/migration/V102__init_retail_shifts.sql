INSERT INTO retail_shifts (id, data_open, data_close, retail_store_id, warehouse_id, company_id, bank, revenue_per_shift,
                           received, amount_of_discounts, commission_amount, sent, printed, comment)
VALUES (1, current_timestamp, '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 1000.00,1000.00, 1000.00, 1000.00, true , false , 'комент 1'),
       (2, current_timestamp, '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 2000.00,2000.00, 2000.00, 2000.00, true , false , 'комент 2'),
       (3, current_timestamp, '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 3000.00,3000.00, 3000.00, 3000.00, true , false , 'комент 3'),
       (4, current_timestamp, '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 1000.00,1000.00, 1000.00, 1000.00, true , false , 'комент 4');
SELECT setval('retail_shifts_id_seq', max(id))
FROM retail_shifts