TRUNCATE contractors CASCADE;
TRUNCATE invoice CASCADE;
TRUNCATE products CASCADE;
TRUNCATE return_amount_by_product CASCADE;

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456',
        'alena.pechnikova@x5.ru', '8 (495) 232-59-24','Торговый Дом "Перекресток", ЗАО',
        '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO invoice (id,comment,date,is_spend,type_of_invoice,company_id,contractor_id,warehouse_id)
VALUES (1,'comment 1','2222-11-01 00:01',false,1,1,1,1);

INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false,
        1.00, 1.00, 1, 1, 1, 1, 1);

INSERT INTO return_amount_by_product (id,product_id,invoice_id,contractor_id,amount)
VALUES (1,1,1,1,1),
       (1,2,1,1,1);