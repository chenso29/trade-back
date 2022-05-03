TRUNCATE contracts CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE legal_details CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
VALUES (1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
        'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
        'chiefTestAccount', 'stampTest'),
       (2, 'OOO ���� �2', '4321', '0002', '+76572518965', '810-41-1234567824', 'karimogon@mail.ru', true,
        'comment', 'Leader', 'testLeaderMeneger', 'LeaderSignature', 'chief', 'chiefAccount', 'stamp');

INSERT INTO public.legal_details (id, comment_to_address, date_of_the_certificate, first_name, inn, kpp, last_name,
                                  middle_name, number_of_the_certificate, ogrn, okpo, address_id, type_of_contractor_id)
VALUES  (1, 'comment to address', null, 'Михаил', '3664069397', '79271669', 'Иванов', 'Сергеевич', '432145', '236467',
         '1053600591197', null, null);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id, contractor_id, legal_detail_id)
VALUES (1, 200, false, 'no comments', '2021-08-06', '1', 3, 1, 1, 1),
       (2, 200, false, 'no comments', '2021-08-06', '2', 3, 1, 1, 1),
       (3, 200, false, 'no comments', '2021-08-06', '3', 3, 1, 1, 1);

SELECT setval('contracts_id_seq', max(id))
FROM contracts;