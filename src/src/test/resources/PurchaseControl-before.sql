TRUNCATE purchase_control CASCADE;
ALTER TABLE purchase_control DROP CONSTRAINT IF EXISTS FKha6o8n91dt5o7jrdlbwayaeup;
ALTER TABLE purchase_control DROP CONSTRAINT IF EXISTS FKhbhy03itaynvfdi0oarhm1fe7;
ALTER TABLE purchase_control DROP CONSTRAINT IF EXISTS FKha6o8n91dt5o7jrdlbwayaeup;

INSERT INTO purchase_control (id, article_number, product_code, product_measure, product_name, product_quantity,
                              current_balance_id, forecast_id, history_of_sales_id)
VALUES (1, 1, 1, 'quantity', 'skirt', 10000, 1, 1, 1),
       (2, 2, 2, 'quantity', 'skirt', 10000, 1, 1, 1),
       (3, 3, 3, 'quantity', 'skirt', 10000, 1, 1, 1);
