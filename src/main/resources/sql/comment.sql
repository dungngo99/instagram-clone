use instagram;

drop table if exists comment;

CREATE TABLE
    comment(
        id INT AUTO_INCREMENT PRIMARY KEY,
        author_id INT NOT NULL,
        post_id INT NOT NULL,
        content TEXT,
        likes INT DEFAULT 0,
        comments INT DEFAULT 0,
        created_at TIMESTAMP DEFAULT NOW(),
        modified_at TIMESTAMP DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
        CONSTRAINT comment_post_id_fk FOREIGN KEY (post_id) REFERENCES post(id)
    );