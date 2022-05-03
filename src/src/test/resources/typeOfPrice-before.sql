TRUNCATE type_of_prices CASCADE;


INSERT INTO type_of_prices (id, name, sort_number)
VALUES
       (1, 'Оптовая цена', '1'),
       (2, 'Розничная цена', '2'),
       (3, 'Специальная цена', '3');



SELECT setval('type_of_prices_id_seq', max(id))
FROM type_of_prices;