INSERT INTO buyers_return (id, date, warehouse_id, contractor_id, company_id, sum,
                        is_sent, is_print, comment)
VALUES (1,current_date,1,1,1,50000,false,true,'comment one'),
       (2,current_date,1,1,1,47700,false,false,'comment two'),
       (3,current_date,1,1,1,935000,true,true,'comment three');
