TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE production_targets CASCADE;

INSERT INTO warehouses (id, address, comment, comment_to_address, name, sort_number)
VALUES  (1, 'Moskva', 'coment', 'coment adress', 'Основной склад', '00001');

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1);

INSERT INTO production_targets (id, date, company_id, delivery_planned_moment, material_warehouse_id,
                                production_warehouse_id, production_start, production_end, shared, Owner, employee_owner,
                                published, printed, description, updated, updated_by_name)
VALUES (1, '2021-08-10 12:15:00.000000', 1, '2021-08-11 12:15:00.000000', 1, 1,
        '2021-08-09 08:00:00.000000',  '2021-08-22 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 1', '2021-08-22 08:00:00.000000', 'Я')
    (2, '2021-08-10 12:15:00.000000', 1, '2022-08-01 12:15:00.000000', 1, 1,
        '2021-08-11 08:00:00.000000',  '2021-11-19 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 2', '2021-08-22 08:00:00.000000', 'Я');