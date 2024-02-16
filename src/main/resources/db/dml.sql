INSERT INTO user(username, email, password)
VALUES
       ('Rokas','rokas@gmail.com','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpFWsCxoKDOYKCf03HzH6W'),
       ('Jonas','Jonas@gmail.com','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpFWsCxoJHOYKCfO1fzH6W'),
       ('Antanas','Antanaitis@gmail.com','$2a$10$jsnoVmm6na5dtS7OnBq5Wedo3y4HGZdkpFWsCxoKDOYKCfO1fzH6W'),
       ('Mantas','mantas@gmail.com','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo4y4HGZdkpFWsCxoKDOYKCf04BzH6W');

INSERT INTO room(name, music_genre, room_id, admin_id)
VALUES
    ('Best music room ever','Pop Music','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpFWsCxoKDOYKCc31fzH6W',1),
    ('Come vibe with us','Rock','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpFWsCxoKDOYKCfO1hzH6W',2),
    ('Join us, we friendly','Pop Music','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpFWsCxoKDOYKCfO1fzH6W',3),
    ('Top Rock music here','Rock','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y4HGZdkpSVsCxoKDOYKCfO1fzH6W',3),
    ('Only best vibes here','Hip Hop','$2a$10$jsnoVmm6na5dtS7OnBq9Wedo3y6HGZdkpFWsCxoKDOYKCf03fzH6W',4);


INSERT INTO authority(name, description)
VALUES (
        'USER',
        'common privileges of User');


INSERT INTO user_authorities(user_id, authorities_id)
VALUES (
        1,
        1);