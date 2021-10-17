DROP DATABASE IF EXISTS `serpiente`;

SET SQL_MODE=NO_AUTO_VALUE_ON_ZERO;

CREATE DATABASE `serpiente`;
USE `serpiente`;

CREATE TABLE players(
    player_id int auto_increment primary key,
    player_name varchar(255),
    points double
);
CREATE TABLE games(
    game_id int auto_increment primary key,
    player_name varchar(255),
    points double
);