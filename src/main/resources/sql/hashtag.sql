-- Active: 1662404743311@@127.0.0.1@3306@instagram
use instagram;

drop table if exists hashtag;

CREATE TABLE
    hashtag(
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        post_id INT NOT NULL,
        created_at TIMESTAMP DEFAULT NOW(),
        CONSTRAINT hashtag_post_id_fk FOREIGN KEY (post_id) REFERENCES post(id)
    );