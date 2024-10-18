CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `status` varchar(20) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `value` decimal(19,2) NOT NULL,
  `daily_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY (`daily_id`),
  CONSTRAINT FOREIGN KEY (`daily_id`) REFERENCES `daily` (`id`)
);