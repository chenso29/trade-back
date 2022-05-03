INSERT INTO agent_reports (id, comitent_sum, commentary, document_type, number, paid, printed, remuniration_sum, sent,
                           status, sum, time, company_id, contractor_id)
VALUES (1, 1, 'Comment 1', '.odt', '1', 1, 1, 1, 1, 'Status 1', 1, '2021-08-10 12:15:00.000000', 1, 1),
       (2, 1, 'Comment 1', '.odt', '1', 1, 1, 1, 1, 'Status 2', 1, '2021-08-10 12:15:00.000000', 1, 1),
       (3, 1, 'Comment 1', '.odt', '1', 1, 1, 1, 1, 'Status 3', 1, '2021-08-10 12:15:00.000000', 1, 1),
       (4, 1, 'Comment 1', '.odt', '1', 1, 1, 1, 1, 'Status 4', 1, '2021-08-10 12:15:00.000000', 1, 1),
       (5, 1, 'Comment 1', '.odt', '1', 1, 1, 1, 1, 'Status 5', 1, '2021-08-10 12:15:00.000000', 1, 1);
SELECT setval('agent_reports_id_seq', max(id))
FROM agent_reports