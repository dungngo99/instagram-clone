-- Active: 1662404743311@@127.0.0.1@3306@instagram

use instagram;

drop table if exists likes;

CREATE TABLE
    likes(
        id INT AUTO_INCREMENT PRIMARY KEY,
        liked_id INT NOT NULL,
        liker_id INT NOT NULL,
        post_id INT NOT NULL,
        created_at TIMESTAMP DEFAULT NOW(),
        CONSTRAINT liked_user_id_fk FOREIGN KEY (liked_id) REFERENCES user(id),
        CONSTRAINT liker_user_id_fk FOREIGN KEY (liker_id) REFERENCES user(id),
        CONSTRAINT like_post_user_id_fk FOREIGN KEY (post_id) REFERENCES post(id)
    );