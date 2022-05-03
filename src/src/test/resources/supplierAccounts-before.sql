TRUNCATE supplier_accounts CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contracts CASCADE;
TRUNCATE legal_details CASCADE;
TRUNCATE contractors CASCADE;
TRUNCATE access_parameters CASCADE;

INSERT INTO access_parameters (id, general_access, department_id, employee_id)
VALUES (1, false, 1, 1),
       (2, false, 2, 2),
       (3, false, 3, 3);

INSERT INTO legal_details (id, comment_to_address, date_of_the_certificate, first_name, inn,
                           kpp, last_name, middle_name, number_of_the_certificate, ogrn, okpo,
                           address_id, type_of_contractor_id)
values  (1, 'comment to address', null, 'Михаил', '3664069397', '79271669', 'Иванов', 'Сергеевич',
         '432145', '236467', '1053600591197', 1, 1);

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id,
                       contractor_id, legal_detail_id)
VALUES (1, 200.00, false, 'no comments', '2021-08-10', '1', 3, 1, 1, 1);

INSERT INTO supplier_accounts(id, comment, date, is_spend, company_id, contract_id, contractor_id,
                              warehouse_id)
VALUES (1, 'Комментарий 1', '2021-07-23 15:10', false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-07-23 15:10', false, 1, 1, 1, 1),
       (3, 'Комментарий 3', '2021-07-23 15:10', true, 1, 1, 1, 1);