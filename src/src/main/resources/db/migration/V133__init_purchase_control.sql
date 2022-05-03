DROP TABLE purchase_control;

create table purchase_control
(
    id                  int8         not null,
    date                timestamp    default current_date,
    article_number      int8         not null,
    product_code        int8         not null,
    product_measure     varchar(255) not null,
    product_name        int8         not null,
    product_quantity    int8,
    current_balance_id  int8         not null,
    forecast_id         int8         not null,
    history_of_sales_id int8         not null,
    primary key (id)
);

INSERT INTO purchase_control (id, date, article_number, product_code, product_measure, product_name, product_quantity,
                              current_balance_id, forecast_id, history_of_sales_id)
VALUES (1, '2021-08-10T09:03:49.000240700', 1, 1, 'quantity', 1, 10000, 1, 1, 1),
       (2, '2021-07-10T09:03:49.000240700', 2, 2, 'quantity', 2, 10000, 1, 1, 1),
       (3, '2021-06-10T09:03:49.000240700', 3, 3, 'quantity', 1, 10000, 1, 1, 1),
       (4, '2021-05-10T09:03:49.000240700', 3, 3, 'quantity', 2, 10000, 1, 1, 1),
       (5, '2021-04-10T09:03:49.000240700', 3, 3, 'quantity', 1, 10000, 1, 1, 1),
       (6, '2021-03-10T09:03:49.000240700', 3, 3, 'quantity', 2, 10000, 1, 1, 1);

