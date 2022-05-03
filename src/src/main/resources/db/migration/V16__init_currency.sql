INSERT INTO currency (id, digital_code, full_name, letter_code, short_name, sort_number)
VALUES (1, '25', 'Russian Rubles', 'rub', 'rubles', '1'),
       (2, '25', 'Bellarusian Rubles', 'belrub', 'bel rubles', '2'),
       (3, '25', 'USA Dollars', 'dol', 'eng dollar', '3');
SELECT setval('currency_id_seq', max(id))
FROM currency