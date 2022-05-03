create table if not exists return_from_buyers_products
(
    return_from_buyers_id   bigserial       not null,
    products_id             bigserial       not null,
    primary key (return_from_buyers_id, products_id)
);

alter table if exists return_from_buyers_products
    add constraint fkk9bircjlb3f08tr2idibv8dgm foreign key (return_from_buyers_id) references return_from_buyers;

alter table if exists return_from_buyers_products
    add constraint fk1dsr5px8vv9iio7nvnouj5fvs foreign key (products_id) references products;

INSERT INTO return_from_buyers_products (return_from_buyers_id, products_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3);

