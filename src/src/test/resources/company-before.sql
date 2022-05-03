TRUNCATE companies CASCADE;
TRUNCATE legal_details CASCADE;
TRUNCATE bank_accounts CASCADE;

insert into bank_accounts (id, rcbic, bank, address, correspondent_account, account, main_account, sort_number) values
(1, 'rbic1', 'bank1', 'address1', 'correspondentAccount1', 'account1', true, 'sortNumber1'),
(2, 'rbic2', 'bank2', 'address2', 'correspondentAccount2', 'account2', true, 'sortNumber2'),
(3, 'rbic3', 'bank3', 'address3', 'correspondentAccount3', 'account3', true, 'sortNumber3'),
(4, 'rbic2', 'bank4', 'address4', 'correspondentAccount4', 'account4', true, 'sortNumber4');

INSERT INTO public.legal_details (id, comment_to_address, date_of_the_certificate, first_name, inn, kpp, last_name,
                                  middle_name, number_of_the_certificate, ogrn, okpo, address_id, type_of_contractor_id)
VALUES  (1, 'comment to address', null, 'Михаил', '3664069397', '79271669', 'Иванов', 'Сергеевич', '432145', '236467',
         '1053600591197', null, null);

INSERT INTO companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
VALUES (1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
 'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
 'chiefTestAccount', 'stampTest'),
(2, 'OOO ���� �2', '4321', '0002', '+76572518965', '810-41-1234567824', 'karimogon@mail.ru', true,
 'comment', 'Leader', 'testLeaderMeneger', 'LeaderSignature', 'chief', 'chiefAccount', 'stamp');