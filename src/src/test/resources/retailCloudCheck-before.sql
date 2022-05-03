TRUNCATE currency CASCADE;
TRUNCATE employees CASCADE;
TRUNCATE retail_stores CASCADE;
TRUNCATE retail_cloud_checks CASCADE;

INSERT INTO currency (id, digital_code, full_name, letter_code, short_name, sort_number)
VALUES (1, '25', 'Russian Rubles', 'rub', 'rubles', '1');

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Vasya', '526317984689', 'Vasiliev', 'Vasilievich',
        '12345', '+7(999)111-22-33', '1', 1, 1, 1);

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system,
                           revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Онлайн', 'ОСН', true, 'Магазин 1', 'ОСН', 10000.00, 'SI', 1);

INSERT INTO retail_cloud_checks (id, date, initiator_id, fiscalization_point_id, status,
                                 check_status, total, currency_id,cashier_id)
VALUES (1, '2021-09-19 09:03:00.000000', 1 , 1, 'Хранение', 'Оплачен', 80000, 1, 1),
       (2, '2021-09-19 09:03:00.000000', 1 , 1, 'Хранение', 'Оплачен', 80000, 1, 1);