insert into warehouses (id, address, comment, comment_to_address, name, sort_number)
values  (1, 'Moskva', 'coment', 'coment adress', 'Основной склад', '00001'),
        (2, 'Moskva', 'coment', 'coment adress', 'Основной склад1', '00001'),
        (3, 'Moskva', 'coment', 'coment adress', 'Основной склад2', '00001'),
        (4, 'Moskva', 'coment', 'coment adress', 'Основной склад3', '00001');
SELECT setval('warehouses_id_seq', max(id))
FROM warehouses;