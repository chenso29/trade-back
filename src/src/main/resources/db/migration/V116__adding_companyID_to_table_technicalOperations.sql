ALTER TABLE technical_operations ADD COLUMN IF NOT EXISTS company_id BIGINT;

UPDATE technical_operations SET company_id = 2 WHERE id = 1;
UPDATE technical_operations SET company_id = 2 WHERE id = 2;
UPDATE technical_operations SET company_id = 2 WHERE id = 3;
