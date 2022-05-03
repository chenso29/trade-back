ALTER TABLE contractors ADD COLUMN IF NOT EXISTS shortname varchar(255);

UPDATE  contractors SET shortname = 'Перекресток' WHERE id = 1;
UPDATE  contractors SET shortname = 'Агроаспект' WHERE id = 2;
UPDATE  contractors SET shortname = 'Вкусвилл' WHERE id = 3;
UPDATE  contractors SET shortname = 'Альфа-М' WHERE id = 4;
UPDATE  contractors SET shortname = 'Отдохни - 77' WHERE id = 5;
UPDATE  contractors SET shortname = 'Продмир' WHERE id = 6;
UPDATE  contractors SET shortname = 'Зельгрос' WHERE id = 7;
UPDATE  contractors SET shortname = 'Лабиринт-М' WHERE id = 8;
UPDATE  contractors SET shortname = 'Эскорт Сервис' WHERE id = 9;
UPDATE  contractors SET shortname = 'Арома Маркет' WHERE id = 10;
