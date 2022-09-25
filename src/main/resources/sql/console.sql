-- Active: 1662404743311@@127.0.0.1@3306@instagram

create database if not exists instagram;

select * from users;

select * from jwt;

select * from post;

select count(*) from jwt
where user_id = 1;