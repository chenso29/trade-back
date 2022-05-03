DROP TABLE corrections_correction_products;

create table corrections_correction_products
(
    correction_id          int8 not null,
    correction_products_id int8 not null,
    FOREIGN KEY (correction_id) REFERENCES corrections(id) ON DELETE CASCADE,
    FOREIGN KEY (correction_products_id) REFERENCES correction_products(id) ON DELETE CASCADE
);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (4, 1),
       (4, 2),
       (4, 3),
       (25, 4),
       (25, 5),
       (25, 6),
       (26, 7),
       (26, 8),
       (26, 9);