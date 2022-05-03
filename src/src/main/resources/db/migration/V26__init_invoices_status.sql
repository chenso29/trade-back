INSERT INTO invoices_status (id, status_name)
VALUES (1,'Новый'),
       (2,'Подтвержден'),
       (3,'Собран'),
       (4,'Отгружен');
SELECT setval('invoices_status_id_seq', max(id))
FROM invoices_status