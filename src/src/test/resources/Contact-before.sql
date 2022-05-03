TRUNCATE contacts CASCADE;

INSERT INTO contacts (id, comment, email, full_name, phone, position)
VALUES (1, 'Коментарий 1', 'email1@mail.ru', 'Иванов Иван Иванович', '+777-777-77-77', 'Контактное лицо 1'),
       (2, 'Коментарий 2', 'email2@mail.ru', 'Алексеев Алексей Алексеевич', '+888-888-88-88', 'Контактное лицо 2'),
       (3, 'Коментарий 3', 'email3@mail.ru', 'Гэри Стивен Возняк', '+999-999-99-99', 'Контактное лицо 3');