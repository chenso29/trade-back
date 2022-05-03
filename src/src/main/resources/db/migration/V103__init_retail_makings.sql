INSERT INTO retail_makings (id, comment, date, sum, is_print, is_sent, from_whom, company_id, retail_store_id)
VALUES (1, 'Комментарий 1', '2021-09-19 09:03:00.000000', 5000, true, true, 'Гриша', 1, 1),
       (2, 'Комментарий 2', '2021-09-19 09:03:00.000000', 4000, true, false, 'Гриша', 2, 2),
       (3, 'Комментарий 3', '2021-09-19 09:03:00.000000', 41000, false, false , 'Гриша', 3, 3);
SELECT setval('retail_makings_id_seq', max(id))
FROM retail_makings