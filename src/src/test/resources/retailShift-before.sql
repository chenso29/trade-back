TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE retail_stores CASCADE;
TRUNCATE retail_shifts CASCADE;


INSERT INTO warehouses(id, name, sort_number, address, comment_to_address, comment)
VALUES (1, 'name1', 'sort num 1', 'address1', 'comment to addr 1', 'comment1');

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1);

INSERT INTO retail_shifts (id, data_open, data_close, retail_store_id, warehouse_id, company_id,
                           bank, revenue_per_shift, received, amount_of_discounts, commission_amount,
                           sent, printed, comment)
VALUES (1, '2021-08-10 12:15:00.000000', '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 1000.00,1000.00,
        1000.00, 1000.00, true , false , 'комент 1'),
       (2, '2021-08-10 12:15:00.000000', '2021-08-10 12:15:00.000000', 1, 1, 1, 'банк 1', 2000.00,2000.00,
        2000.00, 2000.00, true , false , 'комент 2');