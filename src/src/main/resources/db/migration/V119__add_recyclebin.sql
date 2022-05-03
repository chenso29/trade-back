ALTER TABLE operations ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE technical_operations ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE supplier_accounts ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE shipments ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE payments ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE movement ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE loss ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE invoice ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE inventarizations ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE acceptances ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE corrections ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;

ALTER TABLE internal_order ADD COLUMN IF NOT EXISTS is_recyclebin boolean default false;