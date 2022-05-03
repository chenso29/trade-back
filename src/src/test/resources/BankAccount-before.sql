TRUNCATE bank_accounts CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE companies_bank_accounts CASCADE;

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', null, null),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'OOO "Организация №2"', true, '733126789654', '00002', 'stamp', null, null),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'OOO "Организация №3"', true, '799123786542', '00003', 'stamp', null, null);

insert into bank_accounts (id, rcbic, bank, address, correspondent_account, account, main_account, sort_number) values
(5, 'rbic1', 'bank1', 'address1', 'correspondentAccount1', 'account1', true, 'sortNumber1'),
(2, 'rbic2', 'bank2', 'address2', 'correspondentAccount2', 'account2', true, 'sortNumber2'),
(3, 'rbic3', 'bank3', 'address3', 'correspondentAccount3', 'account3', true, 'sortNumber3'),
(4, 'rbic2', 'bank4', 'address4', 'correspondentAccount4', 'account4', true, 'sortNumber4');

INSERT INTO companies_bank_accounts (company_id, bank_accounts_id) VALUES (1, 4), (2, 2), (3, 3);