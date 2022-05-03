TRUNCATE TABLE payments;

INSERT INTO payments (id, number, payment_methods, sum, date, time, type_of_payment, type_of_document, expense_item, company_id, contract_id, contractor_id,
                      project_id, is_conducted)
VALUES (1, '00001', 'CASH', 100.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','INCOMING', 'Входящий платеж', null, 1, 1, 1, 1, true),
       (2, '00002', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Исходящий платеж','RENTAL', 1, 1, 1, 1, true),
       (3, '00003', 'CASH', 100.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','INCOMING', 'Приходный ордер',null, 1, 1, 1, 2, true),
       (4, '00004', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Расходный ордер','RENTAL', 1, 1, 1, 2, true),
       (5, '00005', 'CASH', 100.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','INCOMING', 'Входящий платеж',null, 1, 1, 1, 3, false),
       (6, '00006', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Исходящий платеж','RENTAL', 1, 1, 1, 3, false),
       (7, '00007', 'CASH', 100.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','INCOMING', 'Приходный ордер',null, 1, 2, 1, 1, false),
       (8, '00008', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Расходный ордер','RENTAL', 1, 2, 1, 1, false),
       (9, '00009', 'CASH', 100.00, '2021-08-10 09:03:50.000000','2021-08-10 09:03:50.000000', 'INCOMING', 'Входящий платеж',null, 1, 2, 1, 2, true ),
       (10, '000010', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Исходящий платеж','RENTAL', 1, 2, 1, 2, true),
       (11, '000011', 'CASH', 100.00, '2021-08-10 09:03:50.000000','2021-08-10 09:03:50.000000', 'INCOMING', 'Приходный ордер',null, 1, 2, 1, 3, false),
       (12, '000012', 'BANK', 50.00, '2021-08-10 09:03:50.000000', '2021-08-10 09:03:50.000000','OUTGOING', 'Расходный ордер','RENTAL', 1, 2, 1, 3, false);
SELECT setval('payments_id_seq', max(id))
FROM payments