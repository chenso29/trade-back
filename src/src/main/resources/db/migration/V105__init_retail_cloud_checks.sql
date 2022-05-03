INSERT INTO retail_cloud_checks (id, date, initiator_id, fiscalization_point_id, status, check_status, total, currency_id,cashier_id)
VALUES (1, '2021-09-19 09:03:00.000000', 1 , 1, 'Хранение', 'Оплачен', 80000, 1, 1),
       (2, '2021-09-19 09:03:00.000000', 1 , 1, 'Хранение', 'Оплачен', 80000, 1, 1),
       (3, '2021-09-19 09:03:00.000000', 1 , 1, 'Хранение', 'Оплачен', 80000, 1, 1);
SELECT setval('retail_cloud_checks_id_seq', max(id))
FROM retail_cloud_checks