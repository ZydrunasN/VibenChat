CREATE TABLE user(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    member_room_id BIGINT,
    FOREIGN KEY (member_room_id) REFERENCES room(id)
);

CREATE TABLE room(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    music_genre VARCHAR(100),
    room_id VARCHAR(100) NOT NULL,
    admin_id BIGINT NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES user(id),
    CONSTRAINT room_key UNIQUE (room_id)
);

CREATE TABLE current_song(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    chunk_number INTEGER NOT NULL,
    position BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE chat_message(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    time VARCHAR(100) NOT NULL,
    message_text VARCHAR(1000) NOT NULL,
    room_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE authority(
    id BIGINT primary key auto_increment,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(2000),
    CONSTRAINT authority_key UNIQUE (name)
);

CREATE TABLE user_authority(
    user_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    CONSTRAINT user_authority_key UNIQUE (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id)
);