use database instagram;

delete table if exists post;

CREATE TABLE post (
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	content TEXT NOT NULL DEFAULT '',
	likes INT NOT NULL DEFAULT 0,
	comments INT NOT NULL DEFAULT 0,
	share_url VARCHAR(100) NOT NULL,
	create_at TIMESTAMP DEFAULT NOW(),
	delete_at TIMESTAMP,
	CONSTRAINT post_user_id_fk FOREIGN KEY (user_id)
	REFERENCES USER(id)
);