CREATE TABLE `city_attended` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `ibge_code` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cities_attended_users` (
  `city_attended_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY (`user_id`),
  KEY (`city_attended_id`),
  CONSTRAINT FOREIGN KEY (`city_attended_id`) REFERENCES `city_attended` (`id`),
  CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);