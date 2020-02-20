DROP DATABASE IF EXISTS `gameapi`;
CREATE DATABASE  IF NOT EXISTS `gameapi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gameapi`;

--
-- Table structure for table `usr`
--

DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr` (
    `usr_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `password_salt` varchar(255) NOT NULL,
    `player_name` varchar(255) NOT NULL,
    `games_count` bigint(20) DEFAULT NULL,
    `wins_count` bigint(20) DEFAULT NULL,
    `loses_count` bigint(20) DEFAULT NULL,
    `avatar_data` longblob,
    `active` tinyint(4) NOT NULL DEFAULT '0',
    `update_time` timestamp default CURRENT_TIMESTAMP,
    PRIMARY KEY (`usr_id`),
    UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
    `friend_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `usr1_id` bigint(20) NOT NULL,
    `usr2_id` bigint(20) NOT NULL,
    PRIMARY KEY (`friend_id`),
    KEY `usr1_id_usr_id` (usr1_id),
    KEY `usr2_id_usr_id` (usr2_id),
    CONSTRAINT `user1_id_user_id` FOREIGN KEY (usr1_id) REFERENCES `usr` (`usr_id`) ON UPDATE CASCADE,
    CONSTRAINT `user2_id_user_id` FOREIGN KEY (usr2_id) REFERENCES `usr` (`usr_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
    `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `usr1_id` bigint(20) NOT NULL,
    `usr2_id` bigint(20) NOT NULL,
    `result` enum('BLACK_WINS','WHITE_WINS','DRAW') DEFAULT NULL,
    `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`game_id`),
    KEY `participant_1_id_player_id_idx` (usr1_id),
    KEY `participant_2_id_player_id_idx` (usr2_id),
    CONSTRAINT `participant_1_id_player_id` FOREIGN KEY (usr1_id) REFERENCES `usr` (`usr_id`) ON UPDATE CASCADE,
    CONSTRAINT `participant_2_id_player_id` FOREIGN KEY (usr2_id) REFERENCES `usr` (`usr_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `move`
--

DROP TABLE IF EXISTS `move`;
CREATE TABLE `move` (
    `move_id` bigint(20) NOT NULL,
    `game_id` bigint(20) NOT NULL,
    `move_type` enum('NONE','MOVE','KILL') NOT NULL,
    `move_from` varchar(45) DEFAULT NULL,
    `move_to` varchar(45) DEFAULT NULL,
    `killed_checker` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`move_id`),
    KEY `move_id_game_id_idx` (`game_id`),
    CONSTRAINT `move_id_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `session`
--
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
    `session_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `usr_id` bigint(20) NOT NULL,
    `session_key` varchar(255) NOT NULL,
    `update_time` timestamp default CURRENT_TIMESTAMP,
    `active` tinyint(4) NOT NULL DEFAULT '0',
    PRIMARY KEY (`session_id`),
    UNIQUE KEY `user_id_UNIQUE` (`usr_id`),
    KEY `session_id_user_idx` (`usr_id`),
    CONSTRAINT `session_id_user_idx` FOREIGN KEY (`usr_id`) REFERENCES `usr` (`usr_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


