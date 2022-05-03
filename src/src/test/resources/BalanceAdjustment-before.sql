TRUNCATE balance_adjustments CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO public.companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                              leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                              stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', null, null);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', null, null, null, null, null, null);

INSERT INTO balance_adjustments (id, date, company_id, contractor_id, account, cash_office, comment, date_changed,
                              who_changed)
VALUES (1, '2021-06-23 15:10', 1, 1, 'Счет 1', 'Касса 1', 'Комм 1', '2021-06-23 15:10', '1'),
       (2, '2021-06-23 15:10', 1, 1, 'Счет 2', 'Касса 2', 'Комм 2', '2021-06-23 15:10', '1'),
       (3, '2021-06-23 15:10', 1, 1, 'Счет 3', 'Касса 3', 'Комм 3', '2021-06-23 15:10', '1'),
       (4, '2021-06-23 15:10', 1, 1, 'Счет 4', 'Касса 4', 'Комм 4', '2021-06-23 15:10', '1');

