-- Active: 1662404743311@@127.0.0.1@3306@instagram

use instagram;

drop table if exists follow;

CREATE TABLE
    follow(
        id INT AUTO_INCREMENT PRIMARY KEY,
        followed INT NOT NULL,
        follower INT NOT NULL,
        create_at TIMESTAMP DEFAULT NOW(),
        CONSTRAINT followed_user_id_fk FOREIGN KEY (followed) REFERENCES user(id),
        CONSTRAINT follower_user_id_fk FOREIGN KEY (follower) REFERENCES user(id)
    );