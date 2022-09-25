-- Active: 1662404743311@@127.0.0.1@3306@instagram

use instagram;

DROP TABLE IF EXISTS post;

CREATE TABLE
    post (
        id INT AUTO_INCREMENT PRIMARY KEY,
        user_id int(11) not null,
        content TEXT NOT NULL,
        likes INT NOT NULL DEFAULT 0,
        comments INT NOT NULL DEFAULT 0,
        share_url VARCHAR(100) NOT NULL,
        created_at TIMESTAMP DEFAULT NOW(),
        modified_at TIMESTAMP DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
        constraint post_id_fk FOREIGN KEY (user_id) REFERENCES user(id)
    ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;