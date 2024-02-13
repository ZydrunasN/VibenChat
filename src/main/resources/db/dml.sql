INSERT INTO user(username, email, password)
VALUES
       ('Rokas','rokas@gmail.com','ad544s1ad4sadasd4'),
       ('Jonas','Jonas@gmail.com','sadasdasd6d4as6d4'),
       ('Antanas','Antanaitis@gmail.com','bdfdsfsdff54'),
       ('Mantas','mantas@gmail.com','fgfd3ghfdhfdh4dfg63');

INSERT INTO room(name, music_genre, room_id, admin_id)
VALUES
    ('Best music room ever','Pop Music','asdasw2dasd65asd5sad4sad4',1),
    ('Come vibe with us','Rock','as46dfas4f7sdf4s6dfg',2),
    ('Join us, we friendly','Pop Music','sadsa3454d5sadsad',3),
    ('Top Rock music here','Rock','hs6d4as6f4d4s64gfdsg',3),
    ('Only best vibes here','Hip Hop','46asd4hgfdhfgh6',4);


INSERT INTO authority(name, description)
VALUES (
        'USER',
        'common privileges of User');


INSERT INTO user_authorities(user_id, authorities_id)
VALUES (
        1,
        1);