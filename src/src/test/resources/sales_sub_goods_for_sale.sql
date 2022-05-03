DELETE FROM sales_sub_goods_for_sale;

insert into sales_sub_goods_for_sale (id, name, code, vendor_code, transferred, accepted,
amount, sum, returned, remainder, report_amount, report_sum, not_report_amount, not_reporn_sum)
values
    (1, 'Банан', 1, 10, 100, 1000, 20, 3000, 23, 32 ,324, 2500, 123, 1500),
    (2, 'Яблоко', 1, 10, 100, 1000, 20, 3000, 23, 32 ,324, 2500, 123, 1500),
    (3, 'Фрукт', 1, 10, 100, 1000, 20, 3000, 23, 32 ,324, 2500, 123, 1500),
    (4, 'Малина', 1, 10, 100, 1000, 20, 3000, 23, 32 ,324, 2500, 123, 1500);

SELECT setval('sales_sub_goods_for_sale', max(id))
FROM sales_sub_goods_for_sale;