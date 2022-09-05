create database if not exists instagram;
use instagram;

drop table if exists users;
create table if not exists `users`(
	`user_id` int(11) primary key AUTO_INCREMENT,
    `user_name` varchar(45),
    `password` varchar(45),
    `new_password` varchar(45),
    `first_name` varchar(45) default null,
    `last_name` varchar(45) default null,
    `date_of_birth` date default null,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `login_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`logout_at` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
    `uuid` BINARY(16) default null
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
