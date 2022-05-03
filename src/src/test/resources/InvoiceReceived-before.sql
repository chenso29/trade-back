TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;
TRUNCATE contracts CASCADE;
TRUNCATE acceptances CASCADE;
TRUNCATE invoices_received CASCADE;

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address,
                       email, fax, inn, leader, leader_manager_position, leader_signature,
                       name, payer_vat, phone, sort_number, stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax,
                         name, phone,sort_number, access_parameters_id, address_id,
                         contractor_group_id,contractor_status_id, legal_detail_id,
                         type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id,
                       contractor_id, legal_detail_id)
VALUES (1, 200.00, false, 'no comments', '2021-08-10', '1', 3, 1, 1, 1);

INSERT INTO acceptances (id, comment, incoming_number, date, when_changed_date,
                         is_print, is_sent, is_spend, contract_id, contractor_id,
                         warehouse_id, company_id, employee_changed_id, project_id)
VALUES (1, 'Комментарий 1', '1001', '2021-08-10', '2021-08-10', true, true, true,
        1, 1, 1, 1, 1, 1);



INSERT INTO invoices_received (id, comment, data, incom_data, incom_number, is_spend, is_send,
                               is_print, company_id, contractor_id, acceptance_id)
VALUES (1, 'Комментарий 0', '2021-08-10T09:03:00', '2021-08-10T09:03:45', 1, false, false, true,
        1, 1, 1),
       (2, 'Комментарий 1', '2021-09-10T09:03:00', '2021-08-10T10:03:40', 2, false, true, true,
        1, 1, 1),
       (3, 'Комментарий 2', '2021-06-10T09:03:10', '2021-08-10T11:03:10', 3, false, false, true,
        1, 1, 1);
