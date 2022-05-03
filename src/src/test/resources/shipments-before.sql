TRUNCATE access_parameters CASCADE;
TRUNCATE contractors CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE shipments CASCADE;

INSERT INTO access_parameters (id, general_access, department_id, employee_id)
VALUES (1, false, 1, 1);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456',
        'alena.pechnikova@x5.ru', '8 (495) 232-59-24','Торговый Дом "Перекресток", ЗАО',
        '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO warehouses(id, name, sort_number, address, comment_to_address, comment)
VALUES (1, 'name1', 'sort num 1', 'address1', 'comment to addr 1', 'comment1');

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO shipments (id, date, warehouse_id, contractor_id, company_id,
                       paid, is_print, is_sent, is_spend, comment)
VALUES (1,'2021-08-10 12:15:00.000000',1,1,1, 1000,false,true,true,'comment one'),
       (2,'2021-08-10 12:15:00.000000',1, 1,1,0,false,false,false,'comment two');