create table if not exists return_from_buyers
(
    id              bigserial       not null,
    date            varchar(255)    not null,
    warehouse_id    bigserial       not null,
    contractor_id   bigserial       not null,
    company_id      bigserial       not null,
    total_price     numeric(19,2),
    contract_id     bigserial       not null,
    is_send         boolean         default false,
    is_print        boolean         default false,
    comment         varchar(255),
    is_conducted    boolean         default false,
    primary key (id)
);

alter table if exists return_from_buyers
    add constraint fklke07x6j8mgfoytbpditw2mva foreign key (warehouse_id) references warehouses;

alter table if exists return_from_buyers
    add constraint fk3e4uwafac63o1h9rqx6w9widp foreign key (contractor_id) references contractors;

alter table if exists return_from_buyers
    add constraint fkevrr17pxb6ajig34fqjc83cfo foreign key (company_id) references companies;

alter table if exists return_from_buyers
    add constraint fkfjqkgiuub8w5xor4bu67gl1ku foreign key (contract_id) references contracts;

INSERT INTO return_from_buyers(id, date, warehouse_id, contractor_id, company_id, total_price, contract_id, is_send, is_print, comment, is_conducted)
VALUES (1, '12.02.2021 12:33', 1, 1, 1, 1000.00, 1, 'false', 'false', 'Возврат1', 'true'),
       (2, '27.05.2021 10:13', 2, 2, 2, 4000.00, 2, 'true', 'true', 'Возврат2', 'false');
SELECT setval('return_from_buyers_id_seq', max(id))
FROM return_from_buyers

