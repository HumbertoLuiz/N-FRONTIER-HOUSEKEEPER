CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `grade` double NOT NULL,
  `visibility` bit(1) NOT NULL,
  `evaluated_id` bigint NOT NULL,
  `evaluator_id` bigint DEFAULT NULL,
  `daily_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`evaluated_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`daily_id`) REFERENCES `daily` (`id`),
  FOREIGN KEY (`evaluator_id`) REFERENCES `users` (`id`)
);