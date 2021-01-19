CREATE TABLE STUDENT (id INT null Primary Key AUTO_INCREMENT, name VARCHAR(20), stId VARCHAR(25), exs DEC(100), assign1 DEC(100), assign2 DEC(100), assign3 DEC(100), midterm DEC(100), finalEx DEC(100), wA DEC(100), grade VARCHAR(100));
INSERT INTO STUDENT (name, stId, exs, assign1, assign2, assign3, midterm, finalEx, wA, grade) VALUES
('Adelia', '432', 100, 100, 100, 100, 100, 100, 100, 'A+'),
('Scott', '234', 100, 100, 95.6, 93.5, 65.4, 91.2, 86, 'A'),
('Lilianna', '987', 86, 82.7, 93, 79.4, 76.2, 78.6, 81, 'A'),
('Galina', '224', 100, 98, 97, 93, 87.5, 84.9, 90, 'A+'),
('Yulia', '157', 100, 100, 95, 90, 65, 70, 79, 'B+'),
('Kristina', '531', 95, 86, 73, 83, 73, 61, 74, 'B'),
('Austin', '843', 94.5, 85.8, 76, 98.6, 87.8, 100, 95, 'A'),
('Maya', '582', 89, 82, 91, 65, 64, 70, 73, 'B'),
('Bella', '397', 85, 58, 64, 57, 51, 62, 60, 'C'),
('Tonie', '678', 89, 90, 90, 79, 86.5, 57.3, 78,'B+');