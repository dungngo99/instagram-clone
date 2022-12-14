create database if not exists instagram;
use instagram;

drop table if exists `jwt`;
create table if not exists `jwt` (
    `id` int(11) primary key auto_increment,
    `jwt` text not null,
	`uuid` text not null,
    `created_at` datetime default current_timestamp on update current_timestamp,
    `expired_at` datetime default null,
    `user_id` int(11) not null,
    constraint user_id_fk foreign key (user_id) references users(user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;