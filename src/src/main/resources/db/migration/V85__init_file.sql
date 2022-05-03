INSERT INTO file(id, name, extension, placement, employee, key, upload_date_time)
VALUES (1, 'test.docx', '.docx', 'Файлы', 'Сотрудник', '2175d55e974b95f88d3894247ab55cf1', '2001-01-01 00:00:00'),
       (2, 'test1.docx', '.docx', 'Файлы', 'Ещё Сотрудник', '5d15ed79-4161-48c1-9dc4-e261a8625b30', '2001-01-01 00:00:00'),
       (3, '1.png', '.png', 'Файлы', 'Другой Сотрудник', '1','2001-01-01 14:30:00'),
       (4, '2.png', '.png', 'Файлы', 'Другой Сотрудник', '2','2001-01-01 14:30:00');

SELECT setval('file_id_seq', max(id))
FROM file