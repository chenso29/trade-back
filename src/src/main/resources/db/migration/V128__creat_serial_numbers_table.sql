create table serial_numbers
(
    id                  bigserial not null,
    code                int8 not null,
    vendor_code         int8 not null,
    product_id          int8 not null,
    warehouse_id        int8 not null,
    type_document       varchar(255) not null,
    document_number     int8 not null,
    description         varchar(255) not null,
    primary key (id)
);

insert into serial_numbers (id, code, vendor_code, product_id, warehouse_id,
type_document, document_number, description)
values
    (1, 1, 10010, 1, 1, 'Документ 1', 1, 'Описание'),
    (2, 2, 10011, 2, 2, 'Документ 2', 2, 'Описание'),
    (3, 1, 10010, 1, 1, 'Документ 3', 3, 'Описание'),
    (4, 5, 10021, 5, 3, 'Документ 4', 4, 'Описание'),
    (5, 3, 10033, 3, 3, 'Документ 5', 5, 'Описание'),
    (6, 5, 10021, 5, 1, 'Документ 6', 6, 'Описание');