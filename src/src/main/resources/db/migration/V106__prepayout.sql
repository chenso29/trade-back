INSERT INTO prepayout (id, date, retail_store_id, contractor_id, company_id, cash, cashless, discount, sum,
                       is_sent, is_print, comment)
VALUES (1,current_date,1,1,1,50000, 35000, 5000, 90000, false,true,'comment one'),
       (2,current_date,1,1,1,20000, 2000, 8000, 30000, false,false,'comment two'),
       (3,current_date,1,1,1,500000, 200000, 100000, 800000,true,true,'comment three');