TRUNCATE technical_cards CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE technical_operations CASCADE;

INSERT INTO technical_cards (id, comment, name, production_cost, technical_card_group_id)
VALUES (1, 'Комментарий 1', 'Техническая карта №1', '1000', 1);

INSERT INTO warehouses(id, name, sort_number, address, comment_to_address, comment)
VALUES (1, 'name1', 'sort num 1', 'address1', 'comment to addr 1', 'comment1');

INSERT INTO companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
VALUES (1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
        'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
        'chiefTestAccount', 'stampTest');

INSERT INTO technical_operations (id, comment, is_print, is_sent, volume, date, technical_card_id,
                                  warehouse_id, company_id)
VALUES (1,  'Комментарий1', false, true, 12,'2021-08-10 12:15:00.000000', 1, 1, 1),
       (2,  'Комментарий2', true, false, 13,'2021-08-10 12:15:00.000000', 1, 1, 1);