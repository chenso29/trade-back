TRUNCATE products CASCADE;
TRUNCATE contractors CASCADE;
TRUNCATE access_parameters CASCADE;

INSERT INTO access_parameters (id, general_access, department_id, employee_id)
VALUES (1, false, 1, 1),
       (2, false, 2, 2),
       (3, false, 3, 3);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012', 'inbox@5ka.ru', '8 (800) 555-55-05',
        'Агроаспект, ООО', '8 (800) 555-55-05', '2', 2, 2, 1, 2, 1, 2),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1);
INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (2, false, null, 'Красные Бананы голден0', 0, 0, 'Бананы0', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (3, false, null, 'Красные Мандарины голден0', 0, 0, 'Мандарины0', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3);