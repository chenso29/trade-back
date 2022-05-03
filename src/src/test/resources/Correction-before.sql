TRUNCATE corrections CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE corrections_correction_products CASCADE;
TRUNCATE correction_products CASCADE;

INSERT INTO companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
VALUES (1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
        'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
        'chiefTestAccount', 'stampTest');
INSERT INTO correction_products (id, product_id, amount, price) VALUES
(1, null, 11.00, 12.00),
(2, null, 13.00, 14.00),
(3, null, 15.00, 16.00),
(4, null, 17.00, 18.00),
(5, null, 18.00, 19.00),
(6, null, 19.00, 20.00),
(7, null, 11.00, 12.00),
(8, null, 13.00, 14.00),
(9, null, 15.00, 16.00),
(10, null, 17.00, 18.00),
(11, null, 18.00, 19.00),
(12, null, 19.00, 20.00);

INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product, company_id, warehouse_id)
VALUES (1, 'Оприходование 1', '2021-06-23 15:10', false, false, false, 1, 1),
       (2, 'Оприходование 2', '2021-06-23 15:10', false, false, false, 1, 1),
       (3, 'Оприходование 3', '2021-06-23 15:10', false, false, false, 1, 1);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);

SELECT setval('corrections_id_seq', max(id))
FROM corrections;

