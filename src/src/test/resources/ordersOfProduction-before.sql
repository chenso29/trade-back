TRUNCATE companies CASCADE;
TRUNCATE technical_cards CASCADE;
TRUNCATE orders_of_production CASCADE;

INSERT INTO public .companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                              leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                              stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO technical_cards (id, comment, name, production_cost, technical_card_group_id)
VALUES (1, 'Комментарий1', 'Техническая карта №1', '1000', 1);

INSERT INTO orders_of_production (id, date, company_id, technical_card_id, volume, produce,
                                  planned_production_date, is_sent, is_print, comment)
VALUES (1, '2021-08-10 12:15:00.000000', 1, 1, 120, 0, '2022-08-10 12:15:00.000000',
        false, true, 'Комментарий1'),
       (2, '2021-08-10 12:15:00.000000', 1, 1, 130, 0, '2022-08-10 12:15:00.000000',
        false, true, 'Комментарий2');