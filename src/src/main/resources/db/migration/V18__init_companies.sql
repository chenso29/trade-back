INSERT INTO public.companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                              leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                              stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'ООО "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'ООО "Организация №2"', true, '733126789654', '00002', 'stamp', 2, 2),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'ООО "Организация №3"', true, '799123786542', '00003', 'stamp', 3, 3),
       (4, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '810-41-1234567890', '7712345001', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'ООО "Организация №4"', true, '749512345678', '00004', 'stamp', 4, 1),
       (5, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization4@mail.com',
        '920-12-2365723233', '9543564001', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'ООО "Организация №5"', true, '733126789654', '00005', 'stamp', 5, 2);

SELECT setval('companies_id_seq', max(id))
FROM companies