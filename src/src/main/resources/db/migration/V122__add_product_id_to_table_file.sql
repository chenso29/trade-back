ALTER table file ADD COLUMN IF NOT EXISTS  product_id BIGINT;
alter table if exists file add constraint FKat6c5e3exs7ym1gwd1dg6xduj foreign key (product_id) references products;