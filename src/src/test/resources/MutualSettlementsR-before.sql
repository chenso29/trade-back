
TRUNCATE mutual_settlements CASCADE;
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




INSERT INTO mutual_settlements (id, contractor_id, employee_id, initial_balance, final_balance, income, expenses)
VALUES (1, 1, 2, 45773, 28034, 39535, 93078),
       (2, 2, 2, 36314, 35171, 71436, 45471),
       (3, 3, 3, 56596, 93081, 65531, 31066);
SELECT setval('mutual_settlements_id_seq', max(id))
FROM mutual_settlements

