TRUNCATE bonus_program CASCADE;
TRUNCATE bonus_program_contractor_groups CASCADE;

INSERT INTO bonus_program (id, name, active_status, all_contractors, accrual_rule, write_off_rules,
                           max_payment_percentage, number_of_days, welcome_points, number_of_points,
                           registration_in_bonus_program, first_purchase)
VALUES (1, 'Бонусная программа 1', true, false, 2.5, 1.0, 50, 10, true, 10000, true, false),
       (2, 'Бонусная программа 2', true, false, 0.5, 1.5, 50, 20, false , null, null, null),
       (3, 'Бонусная программа 3', false , true , 2.0, 1.0, 50, 14, true, 10000, true, false),
       (4, 'Бонусная программа 4', true, true, 1.0, 0.5, 50, 15, true, 10000, true, false),
       (5, 'Бонусная программа 5', false , true, 4.0, 3.0, 20, 10, false , null, null, null);
SELECT setval('bonus_program_id_seq', max(id))
FROM bonus_program;

INSERT INTO bonus_program_contractor_groups (bonus_program_id, contractor_groups_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 2),
       (4, 1),
       (5, 2),
       (5, 1);