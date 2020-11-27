INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('1-1-1991', 'Admin', 'Admincic', 'admin@gmail.com', 'admin', 'ADMIN', 0);
INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('2-2-1992', 'Petar', 'Peric', 'petarperic@gmail.com', 'pass123', 'REGISTERED_USER', 0);
INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('3-3-1993', 'Ivan', 'Horvat', 'ivanhorvat@gmail.com', 'pass123', 'REGISTERED_USER', 0);
INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('4-4-1994', 'John', 'Doe', 'johndoe@gmail.com', 'pass123', 'REGISTERED_USER', 0);
INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('5-5-1995', 'Jane', 'Doe', 'janedoe@gmail.com', 'pass123', 'REGISTERED_USER', 0);
INSERT INTO person (date_of_birth, first_name, last_name, email, password, role, days_late) VALUES ('6-6-1996', 'Ana', 'Anic', 'anaanic@gmail.com', 'pass123', 'REGISTERED_USER', 0);

INSERT INTO book (title, is_loaned) VALUES ('Knjiga1', 'Y');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga2', 'Y');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga3', 'N');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga4', 'Y');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga5', 'N');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga6', 'N');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga3', 'Y');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga1', 'N');
INSERT INTO book (title, is_loaned) VALUES ('Knjiga1', 'N');

INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'N', 0, '1-1-2020', '15-1-2020', '1', '2');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 2, '4-3-2020', '17-3-2020', '2', '3');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 6, '5-10-2020', '19-10-2020', '4', '4');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 4, '9-2-2020', '23-2-2020', '3', '2');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 5, '7-4-2020', '21-4-2020', '4', '5');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 10, '5-1-2020', '19-1-2020', '7', '5');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 4, '2-8-2020', '16-8-2020', '7', '2');
INSERT INTO loan (is_completed, is_late, days_late, loan_date, return_date, book_id, person_id) VALUES ('N', 'Y', 6, '1-9-2020', '15-9-2020', '7', '4');
