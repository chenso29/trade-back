TRUNCATE retail_returns CASCADE;

INSERT INTO retail_returns (id, date, retail_store_id,cash_amount,
                            cashless_amount, is_print, is_send, is_spend, comment)
VALUES (1,'2021-09-20 15:08:35', 1,1000,5000,false,true,true,'comment one'),
       (2,'2021-09-20 15:08:35',2,0,8000,false,false,false,'comment two'),
       (3,'2021-09-20 15:08:35',3,7000,0,true,true,true,'comment three');



