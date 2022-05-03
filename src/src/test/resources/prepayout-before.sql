TRUNCATE contractors CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE retail_stores CASCADE;
TRUNCATE prepayout CASCADE;

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456',
        'alena.pechnikova@x5.ru', '8 (495) 232-59-24','Торговый Дом "Перекресток", ЗАО',
        '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1);

INSERT INTO prepayout (id, date, retail_store_id, contractor_id, company_id, cash, cashless, discount, sum,
                       is_sent, is_print, comment)
VALUES (1,'2021-08-11 12:00',1,1,1,50000, 35000, 5000, 90000, false,true,'comment one'),
       (2,'2021-08-10 12:00',1,1,1,20000, 2000, 8000, 30000, false,false,'comment two');