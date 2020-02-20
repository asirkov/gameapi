CREATE DATABASE  IF NOT EXISTS `gameapi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gameapi`;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_1_id` bigint(20) NOT NULL,
  `participant_2_id` bigint(20) NOT NULL,
  `result` enum('BLACK_WINS','WHITE_WINS','DRAW') DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `participant_1_id_player_id_idx` (`participant_1_id`),
  KEY `participant_2_id_player_id_idx` (`participant_2_id`),
  CONSTRAINT `participant_1_id_player_id` FOREIGN KEY (`participant_1_id`) REFERENCES `playerDto` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `participant_2_id_player_id` FOREIGN KEY (`participant_2_id`) REFERENCES `playerDto` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `move`
--

DROP TABLE IF EXISTS `move`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `move` (
  `id` bigint(20) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  `move_type` enum('NONE','MOVE','KILL') NOT NULL,
  `move_from` varchar(45) DEFAULT NULL,
  `move_to` varchar(45) DEFAULT NULL,
  `killed_checker` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `move_id_game_id_idx` (`game_id`),
  CONSTRAINT `move_id_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `playerDto`
--

DROP TABLE IF EXISTS `playerDto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playerDto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(128) NOT NULL,
  `games_count` bigint(20) DEFAULT NULL,
  `wins_count` bigint(20) DEFAULT NULL,
  `loses_count` bigint(20) DEFAULT NULL,
  `avatar_data` blob NOT NULL,
  `online` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `player_name_UNIQUE` (`player_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usrDto`
--

DROP TABLE IF EXISTS `usrDto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usrDto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(128) NOT NULL,
  `password_hash` varchar(128) NOT NULL,
  `password_salt` varchar(128) NOT NULL,
  `player_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `player_id_user_id_idx` (`player_id`),
  CONSTRAINT `player_id_user_id` FOREIGN KEY (`player_id`) REFERENCES `usrDto` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-30  3:52:33
