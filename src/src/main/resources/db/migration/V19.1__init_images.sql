INSERT INTO images (id, image_url, sort_number)
VALUES (1, 'src/main/resources/file/employee_image.png', '1'),
       (2, 'src/main/resources/file/employee_image.png', '2'),
       (3, 'src/main/resources/file/employee_image.png', '3'),
       (4, 'src/main/resources/file/employee_image.png', '4'),
       (5, 'src/main/resources/file/employee_image.png', '5'),
       (6, 'src/main/resources/file/employee_image.png', '6');

SELECT setval('images_id_seq', max(id))
FROM images