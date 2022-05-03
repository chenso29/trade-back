TRUNCATE companies CASCADE;
TRUNCATE retail_stores CASCADE;
TRUNCATE retail_makings CASCADE;

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1);

INSERT INTO retail_makings (id, comment, date, sum, is_print, is_sent, from_whom,
                            company_id, retail_store_id)
VALUES (1, 'Комментарий 1', '2021-09-19 09:03:00.000000', 5000, true, true, 'Гриша', 1, 1),
       (2, 'Комментарий 2', '2021-09-19 09:03:00.000000', 4000, true, false, 'Гриша', 1, 1);