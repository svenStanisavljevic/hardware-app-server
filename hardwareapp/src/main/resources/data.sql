DELETE FROM user_authority;
DELETE FROM authority;
DELETE FROM user;
DELETE FROM review;
DELETE FROM hardware;

INSERT INTO hardware(code, name, price, type, instock) VALUES ('258137', 'Kplus 8GB RAM', 11.99, 'RAM', 551);
INSERT INTO hardware(code, name, price, type, instock) VALUES ('774387', 'Ryzen 69000', 788.19, 'GPU', 92);
INSERT INTO hardware(code, name, price, type, instock) VALUES ('448113', 'Corsair Power supply', 121.79, 'OTHER', 117);
INSERT INTO hardware(code, name, price, type, instock) VALUES ('932092', 'Masterboard', 415.49, 'MBO', 14);

INSERT INTO review(title, text, rating, hardware_id) VALUES ('Odlično, skoro ko kvaliteta vindija', 'Fenomenomenalno', 5, 1);
INSERT INTO review(title, text, rating, hardware_id) VALUES ('Katastrofalno!!!!', 'Ne preporučam!!!!!!!', 1, 4);
INSERT INTO review(title, text, rating, hardware_id) VALUES ('Vrh', 'Dost dobro', 5, 3);
INSERT INTO review(title, text, rating, hardware_id) VALUES ('Generičan naslov', 'Supp', 5, 2);
INSERT INTO review(title, text, rating, hardware_id) VALUES ('Ajde oke', 'Natprosječno', 4, 1);

insert into user(username, password)
values
    ('user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    ('Sven', '$2a$12$.qz56fQ1v4.F6J5iHPTJMui7nAyfi9yq8Fa0aj5FX5Zz8ujDJkFti'),
    ('Hanza', '$2a$12$458WgSV49MbWgES82afZ9uo90gosIkwPckxEeiasKgeR.lgj5BHCG');
insert into authority (authority_name)
values
    ('ROLE_ADMIN'),
    ('ROLE_USER'),
    ('ROLE_UPDATER');
insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1),
    (3, 3);
