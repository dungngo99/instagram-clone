-- Active: 1662404743311@@127.0.0.1@3306@instagram

use instagram;

drop table if exists user;

create table
    if not exists `user`(
        `id` int(11) primary key AUTO_INCREMENT,
        `user_name` varchar(45),
        `password` varchar(45),
        `new_password` varchar(45),
        `first_name` varchar(45) default null,
        `last_name` varchar(45) default null,
        `date_of_birth` date default null,
        `created_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `uuid` BINARY(16) default null
    ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;