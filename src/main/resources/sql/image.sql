-- Active: 1662404743311@@127.0.0.1@3306@instagram

use instagram;

drop table if exists image;

CREATE TABLE
    image (
        id INT AUTO_INCREMENT PRIMARY KEY,
        post_id INT NOT NULL,
        s3_endpoint TEXT NOT NULL,
        image_type VARCHAR(10) NOT NULL,
        length INT,
        width INT,
        lagitude BIGINT,
        longitude BIGINT,
        created_at TIMESTAMP DEFAULT NOw(),
        CONSTRAINT img_user_id_fk FOREIGN KEY (post_id) REFERENCES post(id)
    );