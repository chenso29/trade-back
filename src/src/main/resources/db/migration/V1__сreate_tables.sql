create table acceptance_productions
(
    id         bigserial not null,
    price      numeric(19,2),
    amount     int8,
    product_id int8,
    acceptance_id int8,
    primary key (id)
);

create table hibernate_sequences
(
    sequence_name varchar(255),
    next_val int8
);

create table acceptances
(
    id                   bigserial not null,
    comment              varchar(255),
    incoming_number      varchar(255),
    date                 timestamp    default current_date,
    when_changed_date    date    default current_date,
    is_print             boolean default false,
    is_sent              boolean default false,
    is_spend             boolean default false,
    contractor_id        int8      not null,
    warehouse_id         int8      not null,
    company_id           int8      not null,
    employee_changed_id  int8,
    project_id           int8,
    contract_id          int8,
    primary key (id)
);

create table access_parameters
(
    id             bigserial not null,
    general_access boolean,
    department_id  int8,
    employee_id    int8,
    primary key (id)
);

create table addresses
(
    id        bigserial not null,
    apartment varchar(255),
    city      varchar(255),
    country   varchar(255),
    house     varchar(255),
    index     varchar(255),
    region    varchar(255),
    street    varchar(255),
    primary key (id)
);

create table agent_reports
(
    id               bigserial                  not null,
    comitent_sum     int8                       not null,
    commentary       varchar(255),
    document_type    varchar(255),
    number           varchar(255) default 00001 not null,
    paid             int8                       not null,
    printed          int8,
    remuniration_sum int8                       not null,
    sent             int8,
    status           varchar(255),
    sum              int8                       not null,
    time             timestamp                  not null,
    company_id       int8                       not null,
    contractor_id    int8                       not null,
    primary key (id)
);

create table attribute_of_calculation_objects
(
    id          bigserial not null,
    is_service  boolean default false,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table balance_adjustments
(
    id            bigserial not null,
    account       varchar(255),
    cash_office   varchar(255),
    comment       varchar(255),
    date          varchar(255),
    date_changed  varchar(255),
    who_changed   varchar(255),
    company_id    int8,
    contractor_id int8,
    primary key (id)
);

create table bank_accounts
(
    id                    bigserial not null,
    account               varchar(255),
    address               varchar(255),
    bank                  varchar(255),
    correspondent_account varchar(255),
    main_account          boolean,
    rcbic                 varchar(255),
    sort_number           varchar(255),
    primary key (id)
);

create table bonus_program
(
    id                              bigserial not null,
    name                            varchar(255) not null,
    active_status                   boolean,
    all_contractors                 boolean,
    accrual_rule                    numeric(19,1),
    write_off_rules                 numeric(19,1),
    max_payment_percentage          int2,
    number_of_days                  int2,
    welcome_points                  boolean,
    number_of_points                int8,
    registration_in_bonus_program   boolean,
    first_purchase                  boolean,
    primary key (id)
);

create table bonus_program_contractor_groups
(
    bonus_program_id          int8 not null,
    contractor_groups_id      int8 not null
);

create table cities
(
    id          bigserial not null,
    name        varchar(255),
    district_id int8,
    primary key (id)
);

create table companies
(
    id                         bigserial not null,
    chief_accountant           varchar(255),
    chief_accountant_signature varchar(255),
    comment_to_address         varchar(255),
    email                      varchar(255),
    fax                        varchar(255),
    inn                        varchar(255),
    leader                     varchar(255),
    leader_manager_position    varchar(255),
    leader_signature           varchar(255),
    name                       varchar(255),
    payer_vat                  boolean,
    phone                      varchar(255),
    sort_number                varchar(255),
    stamp                      varchar(255),
    address_id                 int8,
    legal_detail_id            int8,
    primary key (id)
);

create table companies_bank_accounts
(
    company_id       int8 not null,
    bank_accounts_id int8 not null
);

create table contacts
(
    id        bigserial not null,
    comment   varchar(255),
    email     varchar(255),
    full_name varchar(255),
    phone     varchar(255),
    position  varchar(255),
    primary key (id)
);

create table contractor_groups
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table contractor_statuses
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);

create table contractors
(
    id                   bigserial not null,
    comment              varchar(255),
    comment_to_address   varchar(255),
    dicsount_card_number varchar(255),
    email                varchar(255),
    fax                  varchar(255),
    name                 varchar(255),
    phone                varchar(255),
    sort_number          varchar(255),
    access_parameters_id int8,
    address_id           int8,
    contractor_group_id  int8,
    contractor_status_id int8,
    legal_detail_id      int8,
    type_of_price_id     int8,
    primary key (id)
);

create table contractors_bank_accounts
(
    contractor_id    int8 not null,
    bank_accounts_id int8 not null
);

create table contractors_contact
(
    contractor_id int8 not null,
    contact_id    int8 not null
);

create table contracts
(
    id              bigserial not null,
    amount          numeric(19, 2) default 0,
    archive         boolean        default false,
    comment         varchar(255),
    contract_date   date           default current_date,
    number          varchar(255),
    bank_account_id int8,
    company_id      int8      not null,
    contractor_id   int8      not null,
    legal_detail_id int8,
    primary key (id)
);

create table correction_products
(
    id         bigserial not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    product_id int8,
    primary key (id)
);

create table corrections
(
    id                bigserial not null,
    comment           varchar(255),
    date              timestamp not null,
    is_print          boolean default false,
    is_sent           boolean default false,
    write_off_product boolean default false,
    company_id        int8      not null,
    warehouse_id      int8      not null,
    primary key (id)
);

create table corrections_correction_products
(
    correction_id          int8 not null,
    correction_products_id int8 not null
);

create table currency
(
    id           bigserial not null,
    digital_code varchar(255),
    full_name    varchar(255),
    letter_code  varchar(255),
    short_name   varchar(255),
    sort_number  varchar(255),
    primary key (id)
);

create table departments
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table districts
(
    id        bigserial not null,
    name      varchar(255),
    region_id int8,
    primary key (id)
);

create table employees
(
    id            bigserial    not null,
    description   varchar(255),
    email         varchar(255) not null,
    first_name    varchar(255),
    inn           varchar(255),
    last_name     varchar(255) not null,
    middle_name   varchar(255),
    password      varchar(255) not null,
    phone         varchar(255),
    sort_number   varchar(255),
    department_id int8,
    image_id      int8,
    position_id   int8,
    primary key (id)
);

create table employees_roles
(
    employee_id int8 not null,
    roles_id    int8 not null,
    primary key (employee_id, roles_id)
);

create table fias_db
(
    id         bigserial not null,
    aoguid     varchar(255),
    aolevel    varchar(255),
    formalname varchar(255),
    parentguid varchar(255),
    shortname  varchar(255),
    primary key (id)
);

create table file
(
    id          bigserial not null,
    name        varchar(255),
    extension   varchar(255),
    placement   varchar(255),
    employee    varchar(255),
    key         varchar(255),
    upload_date_time timestamp default current_timestamp,
    primary key (id)
);



create table operations
(
    id bigserial not null,
    company_id int8 not null,
    date         timestamp not null,
    is_print     boolean default false,
    is_sent      boolean default false,
    comment varchar(255)

);

create table images
(
    id          bigserial not null,
    image_url   varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table internal_order
(
    id           bigserial not null,
    comment      varchar(255),
    date         timestamp not null,
    is_print     boolean default false,
    is_sent      boolean default false,
    company_id   int8      not null,
    warehouse_id int8      not null,
    primary key (id)
);

create table internal_order_internal_order_products
(
    internal_order_id          int8 not null,
    internal_order_products_id int8 not null
);

create table internal_order_products
(
    id         bigserial      not null,
    amount     numeric(19, 2) not null,
    price      numeric(19, 2) not null,
    product_id int8,
    primary key (id)
);

create table inventarization_products
(
    id            bigserial not null,
    actual_amount numeric(19, 2),
    price         numeric(19, 2),
    product_id    int8,
    primary key (id)
);

create table inventarizations
(
    id           bigserial not null,
    comment      varchar(255),
    date         timestamp not null,
    status       boolean default false,
    is_print     boolean default false,
    is_sent      boolean default false,
    company_id   int8      not null,
    warehouse_id int8      not null,

    primary key (id)
);

create table inventarizations_inventarization_products
(
    inventarization_id          int8 not null,
    inventarization_products_id int8 not null
);

create table invoice
(
    id              bigserial not null,
    comment         varchar(255),
    date            timestamp not null,
    is_spend        boolean default false,
    is_print     boolean default false,
    is_sent      boolean default false,
    type_of_invoice int4      not null,
    company_id      int8      not null,
    contractor_id   int8      not null,
    warehouse_id    int8      not null,
    invoices_status_id  int8      default 1,
    primary key (id)
);

create table invoice_invoice_products
(
    invoice_id          int8 not null,
    invoice_products_id int8 not null
);

create table invoice_product
(
    id         bigserial                not null,
    amount     numeric(19, 2) default 0 not null,
    price      numeric(19, 2) default 0 not null,
    invoice_id int8                     not null,
    product_id int8                     not null,
    primary key (id)
);

create table invoices_received
(
    id              bigserial not null,
    comment         varchar(255),
    data            timestamp not null,
    incom_data      timestamp not null,
    incom_number    bigserial not null,
    is_spend        boolean default false,
    is_send         boolean default false,
    is_print        boolean default false,
    company_id      int8      not null,
    contractor_id   int8      not null,
    acceptance_id      int8      not null,
    primary key (id)
);


create table legal_details
(
    id                        bigserial not null,
    comment_to_address        varchar(255),
    date_of_the_certificate   date,
    first_name                varchar(255),
    inn                       varchar(255),
    kpp                       varchar(255),
    last_name                 varchar(255),
    middle_name               varchar(255),
    number_of_the_certificate varchar(255),
    ogrn                      varchar(255),
    okpo                      varchar(255),
    address_id                int8,
    type_of_contractor_id     int8,
    primary key (id)
);

create table loss
(
    id           bigserial not null,
    comment      varchar(255),
    date         timestamp not null,
    is_print     boolean default false,
    is_sent      boolean default false,
    company_id   int8      not null,
    warehouse_id int8      not null,
    primary key (id)
);

create table loss_loss_products
(
    loss_id          int8 not null,
    loss_products_id int8 not null
);

create table loss_products
(
    id         bigserial      not null,
    amount     numeric(19, 2) not null,
    price      numeric(19, 2) not null,
    product_id int8,
    primary key (id)
);

create table movement
(
    id                  bigserial not null,
    comment             varchar(255),
    date                timestamp not null,
    when_changed_date   date    default current_date,
    is_print            boolean default false,
    is_sent             boolean default false,
    is_spend            boolean default false,
    company_id          int8      not null,
    project_id          int8      not null,
    employee_changed_id int8      not null,
    warehouse_id   int8      not null,
    warehouse_to_id     int8      not null,
    primary key (id)
);

create table movement_movement_products
(
    movement_id          int8 not null,
    movement_products_id int8 not null
);

create table movement_products
(
    id         bigserial not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    product_id int8,
    primary key (id)
);

create table orders_of_production
(
    id                  bigserial not null,
    date                timestamp not null,
    company_id          int8      not null,
    technical_card_id   int8,
    volume              int4,
    produce             int4,
    planned_production_date timestamp not null,
    is_sent             boolean default false,
    is_print            boolean default false,
    comment             varchar(255),
    primary key (id)
);

create table payments
(
    id              bigserial                  not null,
    number          varchar(255) default 00001 not null,
    payment_methods varchar(255),
    sum             numeric(19, 2),
    date          timestamp,
    time          timestamp,
    type_of_payment varchar(255),
    expense_item    varchar(255),
    company_id      int8                       not null,
    contract_id     int8,
    contractor_id   int8                       not null,
    project_id      int8,
    comment             varchar(255),
    is_sent             boolean default false,
    is_print            boolean default false,
    primary key (id)
);

create table payouts
(
    id              bigserial not null,
    comment         varchar(255),
    date            timestamp,
    is_print        boolean default false,
    is_sent         boolean default false,
    who_was_paid    varchar(255),
    company_id      int8,
    retail_store_id int8,
    primary key (id)
);

create table positions
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table prepayment_returns
(
    id                      bigserial    not null,
    time                    varchar(255),
    retail_store_id         int8         not null,
    contractor_id           int8         not null,
    company_id              int8         not null,
    sum_cash                numeric(19, 2),
    sum_non_cash            numeric(19, 2),
    sent                    boolean default false,
    printed                 boolean default false,
    comment                 varchar(255),
    primary key (id)
);

create table price_lists
(
    id         bigserial                  not null,
    commentary varchar(255),
    number     varchar(255) default 00001 not null,
    printed    boolean default false,
    sent       boolean default false,
    time       timestamp,
    company_id int8                       not null,
    primary key (id)
);

create table product_groups
(
    id            bigserial not null,
    name          varchar(255),
    service_group boolean,
    sort_number   varchar(255),
    main_group    int8,
    primary key (id)
);

create table product_prises
(
    id               bigserial not null,
    value            numeric(19, 2),
    type_of_price_id int8,
    primary key (id)
);

create table productions
(
    id                      bigserial not null,
    requests_productions_id int8,
    technical_card_id       int8,
    primary key (id)
);

create table products
(
    id                                 bigserial not null,
    archive                            boolean,
    country                            varchar(255),
    description                        varchar(255),
    item_nubmber                       int4,
    minimum_balance                    int4,
    name                               varchar(255),
    purchase_price                     numeric(19, 2),
    sale_tax                           varchar(255),
    service                            boolean,
    volume                             numeric(19, 2),
    weight                             numeric(19, 2),
    attribute_of_calculation_object_id int8,
    contractor_id                      int8,
    product_group_id                   int8,
    tax_system_id                      int8,
    unit_id                            int8,
    primary key (id)
);

create table products_images
(
    product_id int8 not null,
    images_id  int8 not null
);

create table products_product_prices
(
    product_id        int8 not null,
    product_prices_id int8 not null
);

create table projects
(
    id          bigserial    not null,
    code        varchar(255),
    description varchar(255),
    name        varchar(255) not null,
    primary key (id)
);

create table purchase_control
(
    id                  int8         not null,
    article_number      int8         not null,
    product_code        int8         not null,
    product_measure     varchar(255) not null,
    product_name        varchar(255) not null,
    product_quantity    int8,
    current_balance_id  int8         not null,
    forecast_id         int8         not null,
    history_of_sales_id int8         not null,
    primary key (id)
);

create table purchase_current_balance
(
    id                           int8 not null,
    days_store_on_the_warehouse  int8,
    products_available_for_order int8,
    products_awaiting            int8,
    products_reserve             int8,
    rest_of_the_warehouse        int8,
    primary key (id)
);

create table purchase_forecast
(
    id                int8 not null,
    ordered           boolean default false,
    reserved_days     int8,
    reserved_products int8,
    primary key (id)
);

create table purchase_history_of_sales
(
    id                    int8 not null,
    product_margin        numeric(19, 2),
    product_profit_margin numeric(19, 2),
    product_sales_per_day int8,
    sum_of_products       numeric(19, 2),
    product_price_id      int8,
    primary key (id)
);

create table regions
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);

create table remains
(
    id                  bigserial not null,
    available           int4,
    balance             int4,
    cost_price          int4,
    days_on_warehouse   int4,
    expectation         int4,
    irreducible_balance int4,
    name                varchar(255),
    reserve             int4,
    sales_cost          int4,
    sales_sum           int4,
    sum_of_cost_price   int4,
    vendor_code         varchar(255),
    unit_id             int8,
    primary key (id)
);

create table requsts_productions
(
    id                        bigserial not null,
    date_of_the_certificate   date      not null,
    number_of_the_certificate varchar(255),
    volume                    int4,
    technical_card_id         int8      not null,
    warehouse_id              int8      not null,
    primary key (id)
);

create table retail_shifts
(
    id                      bigserial    not null,
    data_open               timestamp not null,
    data_close              timestamp,
    retail_store_id         int8 not null,
    warehouse_id            int8 not null,
    company_id              int8 not null,
    bank                    varchar(255),
    revenue_per_shift       numeric(19, 2),
    received                numeric(19, 2),
    amount_of_discounts     numeric(19, 2),
    commission_amount       numeric(19, 2),
    sent                    boolean,
    printed                 boolean,
    comment                 varchar(255),
    primary key (id)
);

create table retail_cloud_checks
(
    id                      bigserial    not null,
    date                    timestamp not null,
    initiator_id            int8 not null,
    fiscalization_point_id  int8 not null,
    status                  varchar(255),
    check_status            varchar(255),
    total                   numeric(19, 2),
    currency_id             int8 not null,
    cashier_id              int8 not null,
    primary key (id)
);

create table retail_stores
(
    id                      bigserial    not null,
    activity_status         varchar(255),
    default_taxation_system varchar(255),
    is_active               boolean default false,
    name                    varchar(255) not null,
    order_taxation_system   varchar(255),
    revenue                 numeric(19, 2),
    sales_invoice_prefix    varchar(255),
    company_id              int8         not null,
    primary key (id)
);

create table retail_stores_cashiers
(
    retail_store_id int8 not null,
    cashiers_id     int8 not null
);

create table retail_makings
(
    id              bigserial not null,
    comment         varchar(255),
    date            timestamp,
    sum             numeric(19, 2),
    is_print        boolean default false,
    is_sent         boolean default false,
    from_whom       varchar(255),
    company_id      int8,
    retail_store_id int8,
    primary key (id)
);

create table return_suppliers
(
    id            int8    not null,
    comment       varchar(255) not null,
    date          varchar(255) not null,
    is_print      boolean default false,
    is_send       boolean default false,
    company_id    int8         not null,
    contract_id   int8         not null,
    contractor_id int8         not null,
    warehouse_id  int8         not null,
    primary key (id)
);

create table roles
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table streets
(
    id      bigserial not null,
    name    varchar(255),
    city_id int8,
    primary key (id)
);

create table supplier_accounts
(
    id            int8    not null,
    comment       varchar(255),
    date          timestamp,
    is_spend         boolean default false,
    is_print        boolean default false,
    is_sent         boolean default false,
    company_id    int8         not null,
    contract_id   int8         not null,
    contractor_id int8         not null,
    warehouse_id  int8         not null,
    type_of_invoice int4      not null,
    planned_date_payment    timestamp,
    primary key (id)
);

create table task
(
    id                 bigserial not null,
    completed          boolean   not null,
    creation_date_time timestamp not null,
    deadline_date_time timestamp,
    description        text      not null,
    task_author_id     int8      not null,
    task_employee_id   int8      not null,
    task_contractor_id int8,
    primary key (id)
);

create table task_comment
(
    id                  bigserial                           not null,
    comment             text                                not null,
    published_date_time TIMESTAMP default CURRENT_TIMESTAMP not null,
    publisher_id        int8                                not null,
    task_id             int8                                not null,
    primary key (id)
);

create table task_task_comments
(
    task_id          int8 not null,
    task_comments_id int8 not null
);

create table tax_systems
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table technical_card_groups
(
    id          bigserial not null,
    comment     varchar(255),
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table technical_card_productions
(
    id         bigserial not null,
    amount     int8,
    product_id int8,
    primary key (id)
);

create table technical_cards
(
    id                      bigserial not null,
    comment                 varchar(255),
    name                    varchar(255),
    production_cost         varchar(255),
    technical_card_group_id int8,
    primary key (id)
);

create table technical_cards_final_production
(
    technical_card_id   int8 not null,
    final_production_id int8 not null
);

create table technical_cards_materials
(
    technical_card_id int8 not null,
    materials_id      int8 not null
);

create table technical_operations
(
    id                bigserial not null,
    comment           varchar(255),
    is_print          boolean default false,
    is_sent           boolean default false,
    volume            int4,
    date              timestamp    not null ,
    technical_card_id int8,
    warehouse_id      int8,
    primary key (id)
);

create table type_of_contractors
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table type_of_prices
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table units
(
    id          bigserial not null,
    full_name   varchar(255),
    short_name  varchar(255),
    sort_number varchar(255),
    primary key (id)
);

create table warehouses
(
    id                 bigserial    not null,
    address            varchar(255),
    comment            varchar(255),
    comment_to_address varchar(255),
    name               varchar(255) not null,
    sort_number        varchar(255),
    primary key (id)
);

create table mutual_settlements
(
    id                  bigserial not null,
    contractor_id       int8 not null,
    employee_id         int8 not null,
    initial_balance     int4,
    final_balance       int4,
    income              int4,
    expenses            int4,
    primary key (id)
);

create table retail_sales
(
    id                      bigserial    not null,
    time                    varchar(255),
    retail_store_id         int8         not null,
    contractor_id           int8         not null,
    company_id              int8         not null,
    sum_cash                numeric(19, 2),
    sum_non_cash            numeric(19, 2),
    prepayment              numeric(19, 2),
    sum_discount            numeric(19, 2),
    sum                     numeric(19, 2),
    sent                    boolean default false,
    printed                 boolean default false,
    comment                 varchar(255),
    primary key (id)
);

create table shipments
(
    id                      bigserial    not null,
    date                    timestamp    not null,
    warehouse_id            int8         not null,
    contractor_id           int8         not null,
    company_id              int8         not null,
    paid                    numeric(19, 2),
    is_print                boolean      default false,
    is_sent                boolean      default false,
    is_spend                boolean      default false,
    comment                 varchar(255),
    primary key (id)
);

create table shipment_products
(
    id         bigserial      not null,
    amount     numeric(19, 2) not null,
    price      numeric(19, 2) not null,
    product_id int8,
    primary key (id)
);

create table shipments_shipment_products
(
    shipment_id          int8 not null,
    shipment_products_id int8 not null
);

create table retail_returns
(
    id                      bigserial    not null,
    date                    timestamp    not null,
    retail_store_id         int8         not null,
    cash_amount             numeric(19, 2),
    cashless_amount         numeric(19, 2),
    is_print                boolean      default false,
    is_send                 boolean      default false,
    is_spend                boolean      default false,
    comment                 varchar(255),
    primary key (id)
);

create table revenue
(
    id                           bigserial    not null,
    product_id                   bigserial    not null,
    unit_id                      bigserial    not null,
    description                  varchar(255),
    item_number                  int4,
    amount_acceptance            numeric(19, 2) default 0 not null,
    incoming_number_date         timestamp    not null,
    amount_shipment              numeric(19, 2) default 0 not null,
    acceptance_id                int8,
    acceptance_production_id     int8,
    invoice_product_id           int8,
    start_of_period_amount       int4,
    start_of_period_sum_of_price int4,
    end_of_period_amount         int4,
    end_of_period_sum_of_price   int4,
    coming_amount                int4,
    coming_sum_of_price          int4,
    spending_amount              int4,
    spending_sum_of_price        int4,
    primary key (id)
);

create table issued_invoices
(
    id              bigserial not null,
    comment         varchar(255),
    data            timestamp not null,
    is_spend        boolean default false,
    is_send         boolean default false,
    is_print        boolean default false,
    company_id      int8      not null,
    contractor_id   int8      not null,
    payment_id      int8      not null,
    primary key (id)
);

create table retail_operation_with_points
(
    id                  bigserial not null,
    number              int8      not null,
    date                timestamp default current_timestamp not null,
    type_operation      varchar(255),
    number_of_points    int8,
    accrual_date        timestamp,
    bonus_program_id    int8 not null,
    contractor_id       int8 not null,
    task_id             int8,
    primary key (id)
);

create table retail_operation_with_points_files
(
    retail_operation_with_points_id int8,
    files_id                        int8
);

create table buyers_return
(
    id                      bigserial    not null,
    date                    timestamp    not null,
    warehouse_id            int8         not null,
    contractor_id           int8         not null,
    company_id              int8         not null,
    sum                     numeric(19, 2),
    is_sent                 boolean      default false,
    is_print                boolean      default false,
    comment                 varchar(255),
    primary key (id)
);

create table prepayout
(
    id                      bigserial    not null,
    date                    timestamp    not null,
    retail_store_id         int8         not null,
    contractor_id           int8         not null,
    company_id              int8         not null,
    cash                    numeric(19, 2),
    cashless                numeric(19, 2),
    discount                numeric(19, 2),
    sum                     numeric(19, 2),
    is_sent                 boolean      default false,
    is_print                boolean      default false,
    comment                 varchar(255),
    primary key (id)
);

create table invoices_status
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    status_name VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE if exists invoice
    ADD CONSTRAINT FK_INVOICE_ON_INVOICESTATUS FOREIGN KEY (invoices_status_id) REFERENCES invoices_status;

CREATE TABLE production_targets
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date                    TIMESTAMP,
    company_id              BIGINT,
    delivery_planned_moment TIMESTAMP,
    material_warehouse_id      BIGINT,
    production_warehouse_id    BIGINT,
    production_start        TIMESTAMP,
    production_end          TIMESTAMP,
    shared                  BOOLEAN,
    owner                   VARCHAR(255),
    employee_owner          VARCHAR(255),
    published               BOOLEAN,
    printed                 BOOLEAN,
    description             VARCHAR(255),
    updated                 TIMESTAMP,
    updated_by_name         VARCHAR(255),
    CONSTRAINT pk_production_targets PRIMARY KEY (id)
);

ALTER TABLE production_targets
    ADD CONSTRAINT FK_PRODUCTION_TARGETS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

CREATE TABLE stages
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(255),
    description   VARCHAR(255),
    department_id BIGINT,
    employee_id   BIGINT,
    CONSTRAINT pk_stages PRIMARY KEY (id)
);

CREATE TABLE supplier_account_products_list(
    id         bigserial                NOT NULL,
    amount     NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    price      NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    sum        NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    percent_nds VARCHAR(255),
    nds        NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    total      NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    supplier_account_id INT8            NOT NULL,
    product_id          INT8            NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE stages
    ADD CONSTRAINT FK_STAGES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES departments (id);

ALTER TABLE stages
    ADD CONSTRAINT FK_STAGES_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES employees (id);

-- alter table if exists acceptances_acceptance_production
--     add constraint UK_k24cuhwej1auh77h7plrfbani unique (acceptance_production_id);

alter table if exists companies
    add constraint UK_ikuvd8d97wjjvpvyn5y525g6r unique (inn);

alter table if exists companies_bank_accounts
    add constraint UK_qyxd7r3rsb63lilkejhucbbj8 unique (bank_accounts_id);

alter table if exists contractors_bank_accounts
    add constraint UK_bkyev49t26r8xpyhot4ki4fv unique (bank_accounts_id);

alter table if exists contractors_contact
    add constraint UK_qyyy11c392y0aqmbg9p0s5rrd unique (contact_id);

alter table if exists corrections_correction_products
    add constraint UK_s7ym7rfsw15h6f8rsb9bev876 unique (correction_products_id);

alter table if exists employees
    add constraint UK_j9xgmd0ya5jmus09o0b8pqrpb unique (email);

alter table if exists internal_order_internal_order_products
    add constraint UK_s35abcclqdtal78175xqmy1uj unique (internal_order_products_id);

alter table if exists inventarizations_inventarization_products
    add constraint UK_tlqjrwk0io6s0vt3q7v8anesi unique (inventarization_products_id);

alter table if exists legal_details
    add constraint UK_dpdx1blnekw9ykwwi6vi2fowx unique (inn);

alter table if exists loss_loss_products
    add constraint UK_eiehxydb44cb7pdvatvaiiwox unique (loss_products_id);

alter table if exists movement_movement_products
    add constraint UK_1xw2yxdmw4crhttfw80ee4vpy unique (movement_products_id);

alter table if exists products_product_prices
    add constraint UK_py988v1kgcjlaamucopp4dkhx unique (product_prices_id);

alter table if exists task_task_comments
    add constraint UK_kcuwmqp2ugkku9v7rjl2xf420 unique (task_comments_id);

alter table if exists technical_cards_final_production
    add constraint UK_q2ty4nymg83yoh97x2l7w0ltw unique (final_production_id);

alter table if exists technical_cards_materials
    add constraint UK_g2gfp85pii8p0cxl1dwfejbih unique (materials_id);

alter table if exists file
    add constraint UK_fwefewfcjladsgsg24trgg2hx unique (key);

alter table if exists acceptance_productions
    add constraint FKg7kb2cae2ujgi6ykgs0rwp9dj foreign key (product_id) references products;

alter table if exists acceptance_productions
    add constraint FKg7kb2cae2ujgi6ykgs0rwp9djasdas foreign key (acceptance_id) references acceptances;

alter table if exists acceptances
    add constraint FKpao7pn3tqmxa3a9wle050l0ne foreign key (contract_id) references contracts;

alter table if exists acceptances
    add constraint FK44exjjry62arkf1m1614ifk13 foreign key (contractor_id) references contractors;

alter table if exists acceptances
    add constraint FKe7lg7d802ux57vf2gbn7w51ph foreign key (warehouse_id) references warehouses;

alter table if exists acceptances
    add constraint FKpao7pn3tq333futurmxa0l0ne foreign key (employee_changed_id) references employees;

alter table if exists acceptances
    add constraint FKpao7pn3tq334futurmxa0l0ne foreign key (project_id) references projects;

alter table if exists acceptances
    add constraint FKpao7pn3tq335futurmxa0l0ne foreign key (company_id) references companies;

alter table if exists access_parameters
    add constraint FKmmt9baknvckxx1a7n8xy4a481 foreign key (department_id) references departments;

alter table if exists access_parameters
    add constraint FKnl56p2uwihtg8bh3k3c4w9ahm foreign key (employee_id) references employees;

alter table if exists agent_reports
    add constraint FKogqngsc7r5gprd176vueh4ugj foreign key (company_id) references companies;

alter table if exists agent_reports
    add constraint FKm7q0fhb6lyqq5otu8u2mogb8a foreign key (contractor_id) references contractors;

alter table if exists balance_adjustments
    add constraint FKqbjlnf5g1uwpdoa8lwygua3ha foreign key (company_id) references companies;

alter table if exists balance_adjustments
    add constraint FKrf8xhcxaq8ix0rs04ic2b47tm foreign key (contractor_id) references contractors;

alter table if exists cities
    add constraint FKdwm5dsoos3l4ybpjbrwdme4h foreign key (district_id) references districts;

alter table if exists companies
    add constraint FK8w70yf6urddd0ky7ev90okenf foreign key (address_id) references addresses;

alter table if exists companies
    add constraint FKmi4ikf6y17wva3x4fl9jwqst8 foreign key (legal_detail_id) references legal_details;

alter table if exists companies_bank_accounts
    add constraint FKppxjmywtml3qeqdgpjum4wass foreign key (bank_accounts_id) references bank_accounts;

alter table if exists companies_bank_accounts
    add constraint FK8ki5rafdmm0p5ae92dt2n1yi9 foreign key (company_id) references companies;

alter table if exists contractors
    add constraint FKmx41paet8cecmkb5j5ys8h9mf foreign key (access_parameters_id) references access_parameters;

alter table if exists contractors
    add constraint FKc9uygcm5jaal5ctjp3v6nebbv foreign key (address_id) references addresses;

alter table if exists contractors
    add constraint FKnnprlv1um6mvte0sy7hrrdduo foreign key (contractor_group_id) references contractor_groups;

alter table if exists contractors
    add constraint FK5v4xcjxp74gkd6xuhr5ipof9j foreign key (contractor_status_id) references contractor_statuses;

alter table if exists contractors
    add constraint FKeuquohches3x842tjbco3cv8w foreign key (legal_detail_id) references legal_details;

alter table if exists contractors
    add constraint FKn2phmj4s7i44dsufp58gc2l0p foreign key (type_of_price_id) references type_of_prices;

alter table if exists contractors_bank_accounts
    add constraint FKgpr2msav05fu5kowxxgu4xhpk foreign key (bank_accounts_id) references bank_accounts;

alter table if exists contractors_bank_accounts
    add constraint FKmae57nmfqesxon2pupak61ek1 foreign key (contractor_id) references contractors;

alter table if exists contractors_contact
    add constraint FK97hxla96pv8ki03vu5fhl8pq foreign key (contact_id) references contacts;

alter table if exists contractors_contact
    add constraint FKelqdelyklxcpskmvpkjmdog1 foreign key (contractor_id) references contractors;

alter table if exists contracts
    add constraint FKsllu9bmnuppljlko7299lcd6k foreign key (bank_account_id) references bank_accounts;

alter table if exists contracts
    add constraint FKn5pkn19y02t2psn9puo74jign foreign key (company_id) references companies;

alter table if exists contracts
    add constraint FK8j3mjrj3nchirim7n6nnqorqp foreign key (contractor_id) references contractors;

alter table if exists contracts
    add constraint FKqtw4aaypsdp68m8sao605s5br foreign key (legal_detail_id) references legal_details;

alter table if exists correction_products
    add constraint FKce5t6mbl3wyno68nskoi1knxy foreign key (product_id) references products;

alter table if exists corrections
    add constraint FK6ywb8bc8s80xemuaqxbrfb8o7 foreign key (company_id) references companies;

alter table if exists corrections
    add constraint FKpya65ni473wpqb5633a4scds5 foreign key (warehouse_id) references warehouses;

alter table if exists corrections_correction_products
    add constraint FKaeuhfctrdv2do40pknq8jrewx foreign key (correction_products_id) references correction_products;

alter table if exists corrections_correction_products
    add constraint FKrsstp42n3sve7ltasn7htw16a foreign key (correction_id) references corrections;

alter table if exists districts
    add constraint FKtg2xciun6nr44x122k273u59a foreign key (region_id) references regions;

alter table if exists employees
    add constraint FKgy4qe3dnqrm3ktd76sxp7n4c2 foreign key (department_id) references departments;

alter table if exists employees
    add constraint FKbt9e37riyf0i1au4g2l6rkuge foreign key (image_id) references images;

alter table if exists employees
    add constraint FKngcpgx7fx5kednw3m7u0u8of3 foreign key (position_id) references positions;

alter table if exists employees_roles
    add constraint FKcl8avellvioc3id4mokbhn27o foreign key (roles_id) references roles;

alter table if exists employees_roles
    add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 foreign key (employee_id) references employees;

alter table if exists internal_order
    add constraint FK928uj9emdaov8fp1tpn40vn98 foreign key (company_id) references companies;

alter table if exists internal_order
    add constraint FKg24c7o0u5dmapsfc2ikggwul9 foreign key (warehouse_id) references warehouses;

alter table if exists internal_order_internal_order_products
    add constraint FK580w2af5g09uvevcxk4oyfx43 foreign key (internal_order_products_id) references internal_order_products;

alter table if exists internal_order_internal_order_products
    add constraint FKb8tdmt0sr1pgdrxi6xn1ptgu0 foreign key (internal_order_id) references internal_order;

alter table if exists internal_order_products
    add constraint FK9t9i5pfy3byj13yyhnhen84sj foreign key (product_id) references products;

alter table if exists inventarization_products
    add constraint FKldmky59cro0r0fbwxmvlbh6hm foreign key (product_id) references products;

alter table if exists inventarizations
    add constraint FKjgvmr6f5rqi6j5s1iqbti9lm4 foreign key (company_id) references companies;

alter table if exists inventarizations
    add constraint FK1gtaf13w4h5vpm17tkmxrymoj foreign key (warehouse_id) references warehouses;

alter table if exists inventarizations_inventarization_products
    add constraint FKt71ck6vb7kv2g21uigbck9fk2 foreign key (inventarization_products_id) references inventarization_products;

alter table if exists inventarizations_inventarization_products
    add constraint FK4rw558t7ljkt1443y1qpxlul4 foreign key (inventarization_id) references inventarizations;

alter table if exists invoice
    add constraint FK1giaw4wbtqmlyctyvbudh1bsv foreign key (company_id) references companies;

alter table if exists invoice
    add constraint FK74i9gvm3p2cu1pecfqlkk94om foreign key (contractor_id) references contractors;

alter table if exists invoice
    add constraint FKqh4oobn1va28ndllia7til340 foreign key (warehouse_id) references warehouses;

alter table if exists invoice_product
    add constraint FKhrqne4uostar9vds76ynsosov foreign key (invoice_id) references invoice on delete cascade;

alter table if exists invoice_product
    add constraint FKnx1g8d7ui4e8tnxd3ipak378e foreign key (product_id) references products;

alter table if exists legal_details
    add constraint FK3sq6yh6ijcldtdslicwdtvdjx foreign key (address_id) references addresses;

alter table if exists legal_details
    add constraint FK2i5dblr23ubpwophn2wyjp116 foreign key (type_of_contractor_id) references type_of_contractors;

alter table if exists loss
    add constraint FK2cojnqb10sweqhpjdotxsmveg foreign key (company_id) references companies;

alter table if exists loss
    add constraint FKbq7g1lqjeaaadiumdwktq9plv foreign key (warehouse_id) references warehouses;

alter table if exists loss_loss_products
    add constraint FKm5yg69m6agie7vlqbs11xcb7q foreign key (loss_products_id) references loss_products;

alter table if exists loss_loss_products
    add constraint FKaofp7vj7jc73dt4ha0oqryuph foreign key (loss_id) references loss;

alter table if exists loss_products
    add constraint FKmy0mpg3b1f1siptgjshc8swgm foreign key (product_id) references products;

alter table if exists movement
    add constraint FKmw69c00bls7o0l2sjn4amvvtp foreign key (company_id) references companies;

alter table if exists movement
    add constraint FKnqykedj900w609dsqkhur85w6 foreign key (warehouse_id) references warehouses;

alter table if exists movement
    add constraint FKerexspoex4yny4yh9n9ceut6i foreign key (warehouse_to_id) references warehouses;

alter table if exists movement
    add constraint FKerexspoex311kiornn9ceut6i foreign key (project_id) references projects;

alter table if exists movement
    add constraint FKerexspoex001kiornn9ceut6i foreign key (employee_changed_id) references employees;

alter table if exists movement_movement_products
    add constraint FKt4to9dsosl52jjtj93efu2kkn foreign key (movement_products_id) references movement_products;

alter table if exists movement_movement_products
    add constraint FKgyoo7d1hdmcc9ajcesx9f5l8 foreign key (movement_id) references movement;

alter table if exists movement_products
    add constraint FKgbblappdwa89xeesywukjwg8b foreign key (product_id) references products;

alter table if exists payments
    add constraint FKd7gx3doh12b2qx2b9j2e1dsxe foreign key (company_id) references companies;

alter table if exists payments
    add constraint FKqywegtqyijw241foqfkseq1l6 foreign key (contract_id) references contracts;

alter table if exists payments
    add constraint FKjev52jap657kr14sjks1b7ee4 foreign key (contractor_id) references contractors;

alter table if exists payments
    add constraint FK7h0as5hqhn845eewc7usiy0x3 foreign key (project_id) references projects;

alter table if exists payouts
    add constraint FKroqm8f0ov8s7bjskxvfq6icba foreign key (company_id) references companies;

alter table if exists payouts
    add constraint FK41xfgnbvj1w99to403bco2w4t foreign key (retail_store_id) references retail_stores;

alter table if exists price_lists
    add constraint FKk5jrv1mpo9xlk41egwbjesdcc foreign key (company_id) references companies;

alter table if exists product_groups
    add constraint FK2sypmfi0uobfa6pd6xxc5675v foreign key (main_group) references product_groups;

alter table if exists product_prises
    add constraint FKm6xf6kblha9q09sbfovwb0w2r foreign key (type_of_price_id) references type_of_prices;

alter table if exists productions
    add constraint FKcj1mtnleca1gri5tgbrvpq9g6 foreign key (requests_productions_id) references requsts_productions;

alter table if exists productions
    add constraint FK1qpn0r4nwk2g0p47i929pxr6l foreign key (technical_card_id) references technical_cards;

alter table if exists products
    add constraint FKlel9sf4v4iyplfw17e7e7481c foreign key (attribute_of_calculation_object_id) references attribute_of_calculation_objects;

alter table if exists products
    add constraint FKnnc51dj9kv0ohq9qlvo5o3kyl foreign key (contractor_id) references contractors;

alter table if exists products
    add constraint FKrmlc4hd8nhyq1bsmwbljo76mk foreign key (product_group_id) references product_groups;

alter table if exists products
    add constraint FKowi9xfymax2kghvx7272bff2x foreign key (tax_system_id) references tax_systems;

alter table if exists products
    add constraint FKeex0i50vfsa5imebrfdiyhmp9 foreign key (unit_id) references units;

alter table if exists products_images
    add constraint FK1y02706a7p37urafhqnn3c0l9 foreign key (images_id) references images;

alter table if exists products_images
    add constraint FKgt41wyswrex82sy6iwdup2eak foreign key (product_id) references products;

alter table if exists products_product_prices
    add constraint FK5l4kuihurk7km875phpbem2j1 foreign key (product_prices_id) references product_prises;

alter table if exists products_product_prices
    add constraint FKcv83819ndjubt7fwp1r9oxte9 foreign key (product_id) references products;

alter table if exists purchase_control
    add constraint FKgcwl1pbs9a5n34qe1i5f1d3el foreign key (current_balance_id) references purchase_current_balance;

alter table if exists purchase_control
    add constraint FKhbhy03itaynvfdi0oarhm1fe7 foreign key (forecast_id) references purchase_forecast;

alter table if exists purchase_control
    add constraint FKha6o8n91dt5o7jrdlbwayaeup foreign key (history_of_sales_id) references purchase_history_of_sales;

alter table if exists purchase_history_of_sales
    add constraint FK8uo6imxuoyx8ud8j2otbwc94p foreign key (product_price_id) references product_prises;

alter table if exists remains
    add constraint FK2166y98xx8vyo8i676cl3hl3r foreign key (unit_id) references units;

alter table if exists retail_returns
    add constraint FKyukj31nmv79popvypjj712nxq foreign key (retail_store_id) references retail_stores;

alter table if exists retail_makings
    add constraint FKo54jncxuyghgh452xcvyyu399 foreign key (company_id) references companies;

alter table if exists retail_makings
    add constraint FKo54jncxuyghgh452xcvyyubb9 foreign key (retail_store_id) references retail_stores;

alter table if exists requsts_productions
    add constraint FK1pxvfl8rgagtov03yhao6lvh2 foreign key (technical_card_id) references technical_cards;

alter table if exists requsts_productions
    add constraint FKa2bd3abtixgb5c9f27bbo8kcg foreign key (warehouse_id) references warehouses;

alter table if exists retail_stores
    add constraint FKkdt6slh5pnd3v9eykc5684yj2 foreign key (company_id) references companies;

alter table if exists retail_cloud_checks
    add constraint FKkdt6unbv6ewpzx09wwrzkop33 foreign key (initiator_id) references retail_stores;

alter table if exists retail_cloud_checks
    add constraint FKkdt6unbv6ewpzx09wwrzkop34 foreign key (fiscalization_point_id) references retail_stores;

alter table if exists retail_cloud_checks
    add constraint FKkdt6unbv6ewpzx09wwrzkop35 foreign key (currency_id) references currency;

alter table if exists retail_cloud_checks
    add constraint FKkdt6unbv6ewpzx09wwrzkop36 foreign key (cashier_id) references employees;

alter table if exists retail_stores_cashiers
    add constraint FKgtoaonklkp0gr76tdxv3uwqay foreign key (cashiers_id) references employees;

alter table if exists retail_stores_cashiers
    add constraint FKa7ddfe4y0s8glerhmjocdnucg foreign key (retail_store_id) references retail_stores;

alter table if exists return_suppliers
    add constraint FKh4gq1pl3vhe2r97blvimi3ip7 foreign key (company_id) references companies;

alter table if exists return_suppliers
    add constraint FKfw2ccfsa5pf9c050t30kuj0ue foreign key (contract_id) references contracts;

alter table if exists return_suppliers
    add constraint FKkhe8y1adf9sgikmsara59tgtd foreign key (contractor_id) references contractors;

alter table if exists return_suppliers
    add constraint FKp4awxnhbica1gnlpsgccqawul foreign key (warehouse_id) references warehouses;

alter table if exists shipments
    add constraint FKyukj31nmv76yzxvypjj712nxq foreign key (contractor_id) references contractors;

alter table if exists shipments
    add constraint FKyukj31nmv77yzxvypjj712nxq foreign key (company_id) references companies;

alter table if exists shipments
    add constraint FKyukj31nmv78yzxvypjj712nxq foreign key (warehouse_id) references warehouses;

alter table if exists shipments_shipment_products
    add constraint FK_eiehxydb400hopvatvaiiwox unique (shipment_products_id);

alter table if exists shipments_shipment_products
    add constraint FKyukj31nmv78yzxvypuf500nxq foreign key (shipment_products_id) references shipment_products;

alter table if exists shipments_shipment_products
    add constraint FKyukj31nmv78yzxvypuf626nxq foreign key (shipment_id) references shipments;

alter table if exists streets
    add constraint FKefsv8gxpfki4pn4x1nfp7cv4h foreign key (city_id) references cities;

alter table if exists supplier_accounts
    add constraint FKfcamsdfqwxwmeq7ppvbvk7ns7 foreign key (company_id) references companies;

alter table if exists supplier_accounts
    add constraint FKp2negnkcddsrblsxhenecvat9 foreign key (contract_id) references contracts;

alter table if exists supplier_accounts
    add constraint FK1i3ffi5cu6ryoth28qvs3md8s foreign key (contractor_id) references contractors;

alter table if exists supplier_accounts
    add constraint FKs4wfnn57lxnnj08ago4rq8xct foreign key (warehouse_id) references warehouses;

alter table if exists task
    add constraint FKj3qpqsuq54edds4dy022rs512 foreign key (task_author_id) references employees;

alter table if exists task
    add constraint FKj3qpqsuq54edds4dy022rs514 foreign key (task_contractor_id) references contractors;

alter table if exists task
    add constraint FK363f1j710x76o20oexp5jb8gs foreign key (task_employee_id) references employees;

alter table if exists task_comment
    add constraint FK1ckd20gqwkxvc67kx81uux9fr foreign key (publisher_id) references employees;

alter table if exists task_comment
    add constraint FKpoxt1sd4otmq9p94rp1atkgs7 foreign key (task_id) references task;

alter table if exists task_task_comments
    add constraint FKrt426eimnhew5wx44crgf8yub foreign key (task_comments_id) references task_comment;

alter table if exists task_task_comments
    add constraint FKebgr3j9cjegqgdjrse90ltc75 foreign key (task_id) references task;

alter table if exists technical_card_productions
    add constraint FKni6wr3xxq0g06wm8tvre8alfs foreign key (product_id) references products;

alter table if exists technical_cards
    add constraint FKmaf6g1v6hwa9utjo92ewsi3n6 foreign key (technical_card_group_id) references technical_card_groups;

alter table if exists technical_cards_final_production
    add constraint FKia7579bhavk5i9yoehh9ywgn4 foreign key (final_production_id) references technical_card_productions;

alter table if exists technical_cards_final_production
    add constraint FK67jcypm5ku8f95ovmedr1o42n foreign key (technical_card_id) references technical_cards;

alter table if exists technical_cards_materials
    add constraint FKjemu940wkpo89ttehyti9s89a foreign key (materials_id) references technical_card_productions;

alter table if exists technical_cards_materials
    add constraint FK8pqg40xlgrdalu7ofc8j6rtql foreign key (technical_card_id) references technical_cards;

alter table if exists technical_operations
    add constraint FKes7av3gux8fme3mhrddjyvyyw foreign key (technical_card_id) references technical_cards;

alter table if exists technical_operations
    add constraint FKfp96s0jdxgedvuicqrhsbslmr foreign key (warehouse_id) references warehouses;

alter table  if exists mutual_settlements
    add constraint fk7h156vxaoa80s9ruy6llp36pt foreign key (contractor_id) references contractors;

alter table  if exists mutual_settlements
    add constraint fk95bd6lohwuoqtbe5xbtdpp1x8 foreign key (employee_id) references employees;

alter table if exists retail_sales
    add constraint FKkdt6slh5gfd4f9eykc5684yj2 foreign key (retail_store_id) references retail_stores;

alter table if exists retail_sales
    add constraint FKsdt6slh5gfddsgsd4c5684yj2 foreign key (contractor_id) references contractors;

alter table if exists retail_sales
    add constraint FKkdt6slh5gdsaf9eykc5684ya2 foreign key (company_id) references companies;

alter table if exists bonus_program_contractor_groups
    add constraint FK543t34tgre4335grerg4342tg foreign key (bonus_program_id) references bonus_program;

alter table if exists bonus_program_contractor_groups
    add constraint FKb8ferg34t3gerg43gw3gt45h3 foreign key (contractor_groups_id) references contractor_groups;

alter table if exists retail_operation_with_points
    add constraint FKkdtfdgdfh43g34ggt54g684y4 foreign key (bonus_program_id) references bonus_program;

alter table if exists retail_operation_with_points
    add constraint FKkdhftjrj54y4g35gt54g6hdfh foreign key (contractor_id) references contractors;

alter table if exists retail_operation_with_points
    add constraint FKkdtfgdhrh543hfdhfdh5h5h4y foreign key (task_id) references task;

alter table if exists retail_operation_with_points_files
    add constraint FKkdergregreg43ggtehethfdh5 foreign key (retail_operation_with_points_id) references retail_operation_with_points;

alter table if exists retail_operation_with_points_files
    add constraint FKkdtffdgdfgdr43g34ggh5h4y foreign key (files_id) references file;

alter table if exists buyers_return
    add constraint FKyukj31nmv77yzxfdhds712nxq foreign key (contractor_id) references contractors;

alter table if exists buyers_return
    add constraint FKyukj31nmv78yzxfdhds712nxq foreign key (company_id) references companies;

alter table if exists buyers_return
    add constraint FKyukj31nmv79yzxfdhds712nxq foreign key (warehouse_id) references warehouses;

alter table if exists retail_shifts
    add constraint FKe28ujSdsfsdfsdfdfewfwf329 foreign key (company_id) references companies;

alter table if exists retail_shifts
    add constraint FKe28udsgsdgeg4g24gwfwf329 foreign key (retail_store_id) references retail_stores;

alter table if exists retail_shifts
    add constraint FKe28ujSdsfsd32feefsevsd39 foreign key (warehouse_id) references warehouses;

alter table if exists prepayout
    add constraint FKe30udsgsdgeg4g24gwfwf329 foreign key (retail_store_id) references retail_stores;

alter table if exists prepayout
    add constraint FKe31ujSdsfsd32feefsevsd39 foreign key (contractor_id) references contractors;

alter table if exists prepayout
    add constraint FKe32ujSdsfsdfsdfdfewfwf329 foreign key (company_id) references companies;

alter table if exists prepayment_returns
    add constraint FKmik5755210kotbogjonkklin7 foreign key (retail_store_id) references retail_stores;

alter table if exists prepayment_returns
    add constraint FKmik5755210kotokljonkklin7 foreign key (contractor_id) references contractors;

alter table if exists prepayment_returns
    add constraint FKmik5755210tikbogjonkklin7 foreign key (company_id) references companies;
