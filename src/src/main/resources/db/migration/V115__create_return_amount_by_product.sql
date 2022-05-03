create table if not exists  return_amount_by_product
(
    id bigserial not null,
    product_id int8,
    invoice_id int8,
    contractor_id int8,
    amount numeric default 0,
    primary key (id)
);

insert into return_amount_by_product (product_id,invoice_id,contractor_id,amount)
values
    (1,30,1,1),
    (2,30,1,1),
    (3,30,1,1),
    (1,31,1,2),
    (2,31,1,2),
    (3,31,1,2),
    (1,32,1,3),
    (2,32,1,3),
    (3,32,1,3);

