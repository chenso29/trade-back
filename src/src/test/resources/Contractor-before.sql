TRUNCATE addresses CASCADE;
TRUNCATE contractor_groups CASCADE;
TRUNCATE contractor_statuses CASCADE;
TRUNCATE legal_details CASCADE;
TRUNCATE type_of_prices CASCADE;



INSERT INTO addresses (id, apartment, city, country, house, index, region, street)
VALUES (1, '15', 'Москва', 'Россия', '11', '123456', 'Московская', 'ул. Ленина');

INSERT INTO contractor_groups (id, name, sort_number)
VALUES (1, 'Name 1', '1');

INSERT INTO contractor_statuses (id, name)
VALUES (1, 'Новый'),
       (2, 'Выслано предложение'),
       (3, 'Переговоры'),
       (4, 'Сделка заключена'),
       (5, 'Сделка не заключена');

INSERT INTO legal_details(id, comment_to_address, date_of_the_certificate,
                          first_name, inn, kpp, last_name,middle_name, number_of_the_certificate,
                          ogrn, okpo, address_id, type_of_contractor_id)
VALUES (1,'comment to address', '2020-10-10', 'Михаил', 3664069397, 79271669,
        'Иванов', 'Сергеевич', 432145, 1056161351655, 70541561 , 1, 1);

INSERT INTO type_of_prices (id, name, sort_number)
VALUES (1, 'Оптовая цена', '1');

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456',
        'alena.pechnikova@x5.ru', '8 (495) 232-59-24','Торговый Дом "Перекресток", ЗАО',
        '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012',
        'inbox@5ka.ru', '8 (800) 555-55-05','Агроаспект, ООО', '8 (800) 555-55-05',
        '2', 1, 1, 1, 1, 1, 1),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678',
        'info@izbenka.msk.ru', '8 (495) 981-13-45','Вкусвилл, ООО', '8 (495) 981-13-45',
        '3', 1, 1, 1, 1, 1, 1);