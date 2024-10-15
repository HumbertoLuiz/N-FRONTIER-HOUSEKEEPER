CREATE TABLE `jobs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bathroom_hours` int NOT NULL,
  `kitchen_hours` int NOT NULL,
  `others_hours` int NOT NULL,
  `bedroom_hours` int NOT NULL,
  `yard_hours` int NOT NULL,
  `room_hours` int NOT NULL,
  `icon` varchar(14) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` int NOT NULL,
  `percent_comission` decimal(19,2) NOT NULL,
  `qty_hours` int NOT NULL,
  `bathroom_amount` decimal(19,2) NOT NULL,
  `kitchen_amount` decimal(19,2) NOT NULL,
  `min_amount` decimal(19,2) NOT NULL,
  `others_amount` decimal(19,2) NOT NULL,
  `bedroom_amount` decimal(19,2) NOT NULL,
  `yard_amount` decimal(19,2) NOT NULL,
  `room_amount` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
);