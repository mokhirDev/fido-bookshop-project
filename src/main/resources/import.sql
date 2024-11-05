INSERT INTO users (name, fullname, username, password, email, phone)
VALUES ('Mokhirbek', 'Makhkamov', 'mokhirDev', '123123', 'mokhirbek.makhkam@gmail.com', '+998903571847'),
       ('Alice', 'Johnson', 'aliceJ', 'password123', 'alice.johnson@example.com', '+12345678901'),
       ('Bob', 'Smith', 'bobS', 'securePass!45', 'bob.smith@example.com', '+12345678902'),
       ('Charlie', 'Brown', 'charlieB', 'qwerty789', 'charlie.brown@example.com', '+12345678903'),
       ('Diana', 'Prince', 'dianaP', 'wonderWoman!99', 'diana.prince@example.com', '+12345678904'),
       ('Evan', 'Stone', 'evanS', 'evan1234', 'evan.stone@example.com', '+12345678905'),
       ('Fiona', 'Apple', 'fionaA', 'apple@2023', 'fiona.apple@example.com', '+12345678906'),
       ('George', 'White', 'georgeW', 'george!pass', 'george.white@example.com', '+12345678907'),
       ('Helen', 'Black', 'helenB', 'black&white', 'helen.black@example.com', '+12345678908'),
       ('Ivan', 'Ivanov', 'ivanI', 'passwordIvan', 'ivan.ivanov@example.com', '+998903571800');

INSERT INTO books (name, title, published, price)
VALUES ('Effective Java', 'A Guide to Best Practices in Java Programming', '2018-01-01', 45.99),
       ('Clean Code', 'A Handbook of Agile Software Craftsmanship', '2008-08-01', 39.99),
       ('Design Patterns', 'Elements of Reusable Object-Oriented Software', '1994-10-21', 49.95),
       ('Refactoring', 'Improving the Design of Existing Code', '1999-07-08', 47.95),
       ('Java Concurrency in Practice', 'A Guide to Multithreading and Parallel Programming', '2006-05-19', 54.99),
       ('Spring in Action', 'Comprehensive Guide to Spring Framework', '2018-09-06', 42.95),
       ('Head First Java', 'An Introduction to Java Programming', '2005-02-09', 35.50),
       ('Java: The Complete Reference', 'Comprehensive Guide to Java Programming', '2019-05-01', 60.00),
       ('Programming Groovy', 'Dynamic Productivity for the Java Developer', '2008-11-01', 33.99),
       ('Pro Git', 'Everything about Git', '2014-11-18', 25.00);


INSERT INTO users_books (users_id, books_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 3),
       (3, 4),
       (4, 5),
       (5, 6),
       (5, 7),
       (6, 8),
       (7, 9),
       (8, 10);
