INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1),
       (2, 'Был в сети вчера', 'ОСН2', true, 'Магазин 2', 'УСН. Доход', 20000.00, 'SI', 2),
       (3, 'Был в сети 2 часа назад', 'ОСН2', true, 'Магазин 3', 'ЕСХН', 15700.00, 'SI', 3),
       (4, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 12222.00, 'SI', 1),
       (5, 'Был в сети вчера', 'ОСН2', true, 'Магазин 2', 'УСН. Доход', 22222.00, 'SI', 2),
       (6, 'Был в сети 2 часа назад', 'ОСН2', true, 'Магазин 3', 'ЕСХН', 17777.00, 'SI', 3);
SELECT setval('retail_stores_id_seq', max(id))
FROM retail_stores