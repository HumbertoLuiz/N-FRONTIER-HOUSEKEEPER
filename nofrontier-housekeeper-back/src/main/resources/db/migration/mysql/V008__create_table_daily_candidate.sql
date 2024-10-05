CREATE TABLE `daily_candidate` (
  `daily_id` bigint NOT NULL,
  `candidate_id` bigint NOT NULL,
  KEY (`candidate_id`),
  KEY (`daily_id`),
  CONSTRAINT FOREIGN KEY (`candidate_id`) REFERENCES `users` (`id`),
  CONSTRAINT FOREIGN KEY (`daily_id`) REFERENCES `daily` (`id`)
);