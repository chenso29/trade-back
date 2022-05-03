INSERT INTO remains (id, available, balance, cost_price, days_on_warehouse, expectation, irreducible_balance,
                     name, reserve, sales_cost, sales_sum, sum_of_cost_price, vendor_code, unit_id)
VALUES (1, 46973, 23134, 45773, 11, 28034, 39535, 'Remain one', 93078, 56196, 64145, 66039, '234789', 38),
       (2, 97887, 74597, 36314, 15, 35171, 71436, 'Remain two', 78878, 45471, 67934, 57119, '451238', 38),
       (3, 34277, 25254, 56596, 13, 93081, 65531, 'Remain three', 60223, 50033, 87981, 31066, '624380', 38);
SELECT setval('remains_id_seq', max(id))
FROM remains