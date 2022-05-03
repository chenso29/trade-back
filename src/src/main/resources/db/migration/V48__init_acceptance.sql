INSERT INTO acceptances (id, comment, incoming_number, date, when_changed_date, is_print, is_sent, is_spend,
                         contract_id, contractor_id, warehouse_id, company_id, employee_changed_id, project_id)
VALUES (38, 'Комментарий 1', '1001', '2021-08-10', '2021-08-10', true, true, true, 1, 1, 1, 1, 1, 1),
       (39, 'Комментарий 2', '1002', '2021-08-10', '2021-08-10', false, true, true, 2, 2, 2, 1, 1, 1),
       (40, 'Комментарий 3', '1003', '2021-08-10', '2021-08-10', false, false, true, 3, 3, 3, 1, 1, 1),
       (41, 'Комментарий 4', '1004', '2021-08-10', '2021-08-10', true, true, false, 4, 4, 4, 1, 1, 1);
SELECT setval('acceptances_id_seq', max(id))
FROM acceptances