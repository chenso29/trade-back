TRUNCATE retail_stores CASCADE;

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1);


INSERT INTO prepayment_returns (id, time, retail_store_id, contractor_id, company_id, sum_cash,
                                sum_non_cash, sent, printed, comment)
VALUES (1, '2021-08-11', 1, 1, 1, 88000.00, 0.00, false, false, 'Комментарий 1'),
       (2, '2020-08-11', 1, 1, 1, 0.00, 5100.00, true, true, 'Комментарий 2');