INSERT INTO payouts (id, comment, date, is_print, is_sent, who_was_paid, company_id, retail_store_id)
VALUES (1, 'Коммент', '2021-08-10 12:15:42.736035', true, true, 'Сергеев Петр Сергеевич', 1, 1),
       (2, 'Комментарий', '2021-08-10 12:15:42.782910', true, false, 'Стрелецкая Анастасия Михайловна', 2, 2),
       (3, 'Комментарий комментария', '2021-08-10 12:15:42.782910', false, true, 'Стрелецкая Анастасия Михайловна', 3,
        3);
SELECT setval('payouts_id_seq', max(id))
FROM payouts