create table sales_sub_goods_for_sale
(
    id                  bigserial not null,
    name                int8 not null,
    code                int8 not null,
    vendor_code         int8 not null,
    transferred         int4 not null,
    accepted            int4 not null,
    amount              int8 not null,
    sum                 numeric(19,2) not null,
    returned            int8 not null,
    remainder           numeric(19,2) not null,
    report_amount       int8 not null,
    report_sum          numeric(19,2) not null,
    not_report_amount   int8 not null,
    not_report_sum     numeric(19,2) not null,
    primary key (id)
);

insert into sales_sub_goods_for_sale (id, name, code, vendor_code, transferred, accepted,
amount, sum, returned, remainder, report_amount, report_sum, not_report_amount, not_report_sum)
values
    (1, 1, 10010, 32213, 40, 100, 80, 40000, 11, 5500, 78, 1000, 2, 1000),
    (2, 2, 10011, 43221, 50, 85, 68, 27200, 9, 3600, 65, 1200, 3, 1200),
    (3, 1, 10010, 32213, 70, 80, 90, 45000, 11, 5500, 86, 1000, 4, 1000),
    (4, 5, 10021, 45642, 50, 85, 68, 20400, 9, 3600, 65, 1200, 3, 900),
    (5, 3, 10033, 38813, 20, 30, 80, 56000, 11, 7700, 73, 1000, 7, 3500),
    (6, 5, 10021, 45642, 50, 60, 71, 28400, 7, 2800, 68, 1200, 3, 1200);
