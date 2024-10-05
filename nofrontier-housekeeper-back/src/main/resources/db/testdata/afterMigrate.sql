SET foreign_key_checks = 0;

lock tables `jobs` write, `users` write, `picture` write, `city_attended` write, 
			`token_black_list` write, `housekeeper_address` write, `daily` write, 
			`rating` write, `payment` write, `password_reset` write;  

SET SQL_SAFE_UPDATES=0;

DELETE FROM `jobs`;
DELETE FROM `users`;
DELETE FROM `picture`;
DELETE FROM `city_attended`;
DELETE FROM `token_black_list`;
DELETE FROM `housekeeper_address`;
DELETE FROM `daily`;
DELETE FROM `rating`;
DELETE FROM `payment`;
DELETE FROM `password_reset`;

SET SQL_SAFE_UPDATES=1;

SET foreign_key_checks = 1;

ALTER TABLE `jobs` auto_increment = 1;
ALTER TABLE `users` auto_increment = 1;
ALTER TABLE `picture` auto_increment = 1;
ALTER TABLE `city_attended` auto_increment = 1;
ALTER TABLE `token_black_list` auto_increment = 1;
ALTER TABLE `housekeeper_address` auto_increment = 1;
ALTER TABLE `daily` auto_increment = 1;
ALTER TABLE `rating` auto_increment = 1;
ALTER TABLE `payment` auto_increment = 1;
ALTER TABLE `password_reset` auto_increment = 1;

INSERT INTO `jobs` (`name`, `min_amount`, `qty_hours`, `percent_comission`, `room_hours`, `room_amount`, 
					`living_hours`, `living_amount`, `bathroom_hours`, `bathroom_amount`, `kitchen_hours`, 
					`kitchen_amount`, `yard_hours`, `yard_amount`, `others_hours`, `others_amount`, 
					`icon`, `position`) VALUES    
('CLEANING_1', 896.55, 1, 964.67, 1, 70.68, 1, 992.88, 1, 129.26, 1, 116.8, 1, 111.33, 1, 36.5, 'HL_CLEANING_1', 1),
('CLEANING_2', 14.2, 2, 504.12, 2, 352.37, 2, 7.78, 2, 500.45, 2, 833.32, 2, 106.18, 2, 84.28, 'HL_CLEANING_1', 1),
('CLEANING_3', 46.36, 3, 523.17, 3, 750.85, 3, 329.81, 3, 355.16, 3, 44.22, 3, 234.68, 3, 594.35, 'HL_CLEANING_1', 1),
('CLEANING_4', 993.67, 4, 127.15, 4, 343.74, 4, 460.09, 4, 830.5, 4, 756.3, 4, 755.81, 4, 360.46, 'HL_CLEANING_2', 2),
('CLEANING_5', 502.95, 5, 989.09, 5, 170.79, 5, 834.96, 5, 486.12, 5, 268.14, 5, 100.17, 5, 746.26, 'HL_CLEANING_2', 2),
('CLEANING_6', 650.61, 6, 972.11, 6, 802.12, 6, 855.21, 6, 153.85, 6, 177.16, 6, 314.99, 6, 100.42, 'HL_CLEANING_2', 2),
('CLEANING_7', 62.23, 7, 360.71, 7, 212.18, 7, 13.61, 7, 219.54, 7, 977.52, 7, 504.15, 7, 512.55, 'HL_CLEANING_2', 2),
('CLEANING_8', 136.87, 8, 115.92, 8, 191.01, 8, 59.43, 8, 869.03, 8, 774.1, 8, 913.34, 8, 331.33, 'HL_CLEANING_3', 3),
('CLEANING_9', 6.99, 9, 273.44, 9, 285.59, 9, 620.7, 9, 992.38, 9, 528.88, 9, 998.2, 9, 995.68, 'HL_CLEANING_3', 3),
('CLEANING_10', 341.55, 10, 559.41, 10, 630.38, 10, 567.72, 10, 30.5, 10, 363.39, 10, 734.73, 10, 223.58, 'HL_CLEANING_3', 3);

INSERT INTO `users` (`complete_name`, `email`, `password`, `user_type`) VALUES
('João da Silva', 'joao@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 1),
('Maria Joaquina', 'maria@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 2),
('José Souza', 'jose@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 1),
('Sebastião Martins', 'sebastiao@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 1),
('Manoel Lima', 'manoel@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 1),
('Débora Mendonça', 'debora@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 2),
('Carlos Lima', 'carlos@mail.com', '$2a$10$elmsMlIUipV7rWy6f90rHucH4qyXz1f/RUbU1q57paYZ4sbvoDGuy', 1);

INSERT INTO `city_attended` (`ibge_code`, `city`, `state`) VALUES
('3550308', 'São Paulo', 'SP'),
('3509502', 'Campinas', 'SP'),
('3547809', 'Santo André', 'SP');

INSERT INTO `housekeeper_address` (`address`, `number`, `neighborhood`, `complement`, `zip_code`, `city`, `state`) values 
('Rua Acaju', 110, 'Jardim Textil', 'casa', '03413020', 'São Paulo', 'SP'),
('Estrada Santa Etelvina', 611, 'Vila Marilena', 'casa', '08411400', 'São Paulo', 'SP'), 
('Rua Kaoru Oda', 908, 'Jardim das Vertentes', 'casa', '05541060', 'São Paulo', 'SP'),
('Rua Rússia', 773, 'Jd. Europa', 'casa', '01448040', 'São Paulo', 'SP'),
('Rua Ângelo Arroyo', 333, 'Lot. Vila Esperança', 'casa', '13082630', 'Campinas', 'SP'),
('Rua Irma Maria Basília', 272, 'Jardim do Lago Continuação', 'casa', '13051014', 'Campinas', 'SP'),
('Rua Rio Iguapó', 324, 'Residencial Colina das Nascentes', 'casa', '13058682', 'Campinas', 'SP'),
('Rua Ibitinga', 991, 'Vila João Jorge', 'casa', '13041318', 'Campinas', 'SP'),
('Rua Clara de Morais', 673, 'Vila Suíça', 'casa', '09132270', 'Santo André', 'SP'),
('Travessa Monte Branco', 318, 'Sítio dos Vianas', 'casa', '09171780', 'Santo André', 'SP'),
('Rua dos Orixás', 147, 'Sítio dos Vianas', 'casa', '09171800', 'Santo André', 'SP'),
('Travessa Vila Velha', 157, 'Condomínio Maracanã', 'casa', '09122038', 'Santo André', 'SP');

INSERT INTO `daily` (`created_at`, `updated_at`, `date_service`, `time_service`, `status`, `price`, `value_commission`, `address`, 
					 `number`, `neighborhood`, `complement`, `city`, `state`, `zip_code`, `ibge_code`, 
					 `quantity_bedrooms`, `quantity_rooms`, `quantity_kitchens`, `quantity_bathrooms`, 
					 `quantity_yards`, `quantity_others`, `observations`, `reason_cancellation`, 
					 `customer_id`, `housekeeper_id`, `job_id`) VALUES 
(NOW(), NOW(), '2024-04-28', 1, 'PAID', 616.39, 77.79, 'Nova', 5, '12th Floor', 'Apt 617', 'San Carlos de Bolívar', 'SP', '6550', '9016', 1, 1, 1, 1, 1, 1, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-09-13', 2, 'PAID', 911.56, 29.69, 'Gateway', 1, 'Suite 47', '16th Floor', 'Valerik', 'SP', '366609', '2071', 2, 2, 2, 2, 2, 2, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-08-21', 3, 'PAID', 381.86, 57.53, 'Continental', 5346, 'Room 1404', 'Apt 973', 'Pancol', 'SP', '3200', '8171', 3, 3, 3, 3, 3, 3, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-05-03', 4, 'PAID', 502.96, 77.34, 'Claremont', 804, '3rd Floor', 'Room 1829', 'Al Mazra‘ah', 'SP', 'SP', '1043', 4, 4, 4, 4, 4, 4, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-08-21', 5, 'PAID', 873.33, 96.44, 'Arapahoe', 09337, 'Apt 12', '3rd Floor', 'Balekambang', 'SP', 'SP', '0821', 5, 5, 5, 5, 5, 5, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-05-27', 6, 'PAID', 720.43, 97.74, 'Morrow', 44243, 'Suite 6', 'Suite 65', 'Évry', 'A8', '91044', '8254', 6, 6, 6, 6, 6, 6, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-08-28', 7, 'PAID', 11.48, 23.61, 'Brentwood', 2, 'PO Box 15726', 'Apt 1923', 'Yershov', 'SP', '442080', '8222', 7, 7, 7, 7, 7, 7, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-03-10', 8, 'PAID', 559.7, 19.31, 'Fisk', 31, 'Room 517', 'Suite 37', 'Jengglungharjo', 'SP', 'SP', '246', 8, 8, 8, 8, 8, 8, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-04-29', 9, 'PAID', 299.46, 30.66, 'Glendale', 96767, 'Room 1204', 'Apt 940', 'Tibro', 'O', '543 35', '7760', 9, 9, 9, 9, 9, 9, null, null, 2, 2, 2),
(NOW(), NOW(), '2024-11-15', 10, 'PAID', 36.03, 9.88, 'Merry', 7, '6th Floor', 'Suite 19', 'Lau', '15', '2950-127', '199', 10, 10, 10, 10, 10, 10, null, null, 2, 2, 2);


INSERT INTO `rating` (`created_at`, `updated_at`, `description`, `grade`, `visibility`, `daily_id`, `evaluator_id`, `evaluated_id`) VALUES 
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1),
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1),
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1);


INSERT INTO `payment` (`created_at`, `updated_at`, `status`, `value`, `transaction_id`, `daily_id`) VALUES 
(NOW(), NOW(), 'ACCEPTED', 500.00, 1, 1),
(NOW(), NOW(), 'ACCEPTED', 700.00, 1, 1),
(NOW(), NOW(), 'ACCEPTED', 900.00, 1, 1);


unlock tables;