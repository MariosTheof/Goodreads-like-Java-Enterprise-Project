INSERT INTO books VALUES (1	,'Crime and Punishment');
INSERT INTO books VALUES (2	,'Worth a candle');

SELECT * FROM books;


INSERT INTO reviews VALUES (1, 1, 101, 'Review of crime and punishments',
'Thlivero');

INSERT INTO reviews VALUES (2, 2, 101, 'Review of worth a candle',
'Kalo pulp fiction');

-- SELECT * FROM reviews;

INSERT INTO user_books VALUES (102, 1);

-- SELECT * FROM user_books;

SELECT * FROM users


SELECT title, review_text
FROM XX