INSERT INTO organisatie (id, naam) VALUES
(1, 'Universiteit van Amsterdam'),
(2, 'Technische Universiteit Delft'),
(3, 'Vrije Universiteit Amsterdam'),
(4, 'Hogeschool Utrecht'),
(5, 'Radboud Universiteit'),
(6, 'Fontys Hogescholen'),
(7, 'Avans Hogeschool'),
(8, 'Hanzehogeschool Groningen'),
(9, 'Saxion Hogeschool'),
(10, 'Windesheim Zwolle');

-- Insert OrganisatieGebruiker records (at least 50 unique combinations)
INSERT INTO organisatie_gebruiker (gebruiker_id, organisatie_id) VALUES
 (1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
 (6, 2), (7, 2), (8, 2), (9, 2), (10, 2),
 (11, 3), (12, 3), (13, 3), (14, 3), (15, 3),
 (16, 4), (17, 4), (18, 4), (19, 4), (20, 4),
 (21, 5), (22, 5), (23, 5), (24, 5), (25, 5),
 (26, 6), (27, 6), (28, 6), (29, 6), (30, 6),
 (31, 7), (32, 7), (33, 7), (34, 7), (35, 7),
 (36, 8), (37, 8), (38, 8), (39, 8), (40, 8),
 (41, 9), (42, 9), (43, 9), (44, 9), (45, 9),
 (46, 10), (47, 10), (48, 10), (49, 10), (50, 10);
