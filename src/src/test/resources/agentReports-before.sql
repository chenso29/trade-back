TRUNCATE agent_reports CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO public.companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                              leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                              stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', null, null);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', null, null, null, null, null, null);

INSERT INTO agent_reports (id, comitent_sum, commentary, document_type, number, paid, printed, remuniration_sum, sent,
                          status, sum, time, company_id, contractor_id)
VALUES (1, 1, 'Комментарий 1', '.doc', '1', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (2, 1, 'Комментарий 2', '.doc', '2', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (3, 1, 'Комментарий 3', '.doc', '3', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1);

SELECT setval('agent_reports_id_seq', max(id)) FROM agent_reports;