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

INSERT INTO jobs (`name`, `min_amount`, `qty_hours`, `percent_comission`, `bedroom_hours`, 
                  `bedroom_amount`, `room_hours`, `room_amount`, `bathroom_hours`, 
                  `bathroom_amount`, `kitchen_hours`, `kitchen_amount`, `yard_hours`, 
                  `yard_amount`, `others_hours`, `others_amount`, `icon`, `position`) 
VALUES 
('Job 1', 150.00, 40, 10.5, 2, 50.00, 3, 70.00, 1, 30.00, 2, 40.00, 1, 20.00, 1, 25.00, 'HL_CLEANING_1', 3),
('Job 2', 200.00, 35, 12.0, 3, 60.00, 4, 80.00, 2, 35.00, 3, 45.00, 1, 25.00, 1, 30.00, 'HL_CLEANING_1', 3),
('Job 3', 180.00, 50, 9.0, 2, 55.00, 3, 75.00, 1, 32.00, 2, 42.00, 2, 22.00, 1, 27.00, 'HL_CLEANING_1', 3),
('Job 4', 160.00, 30, 8.5, 1, 52.00, 3, 78.00, 1, 34.00, 2, 43.00, 1, 21.00, 1, 29.00, 'HL_CLEANING_2', 2),
('Job 5', 220.00, 45, 15.0, 4, 65.00, 5, 85.00, 2, 37.00, 3, 47.00, 2, 26.00, 1, 33.00, 'HL_CLEANING_2', 2),
('Job 6', 170.00, 32, 11.5, 2, 53.00, 4, 79.00, 1, 33.00, 2, 44.00, 2, 23.00, 2, 31.00, 'HL_CLEANING_2', 2),
('Job 7', 195.00, 48, 13.0, 3, 59.00, 5, 82.00, 2, 36.00, 3, 46.00, 1, 24.00, 1, 28.00, 'HL_CLEANING_3', 1),
('Job 8', 210.00, 42, 14.5, 4, 64.00, 4, 83.00, 1, 31.00, 2, 41.00, 3, 27.00, 2, 35.00, 'HL_CLEANING_3', 1),
('Job 9', 185.00, 39, 10.0, 2, 57.00, 3, 77.00, 2, 38.00, 2, 39.00, 2, 28.00, 1, 32.00, 'HL_CLEANING_3', 1),
('Job 10', 205.00, 36, 9.5, 1, 54.00, 4, 81.00, 1, 29.00, 2, 38.00, 3, 30.00, 1, 34.00, 'HL_CLEANING_3', 1);


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

INSERT INTO daily (`created_at`, `updated_at`, `date_service`, `time_service`, `status`, `price`, 
                   `value_commission`, `address`, `number`, `neighborhood`, `complement`, `city`, 
                   `state`, `zip_code`, `ibge_code`, `quantity_bedrooms`, `quantity_rooms`, 
                   `quantity_kitchens`, `quantity_bathrooms`, `quantity_yards`, `quantity_others`, 
                   `observations`, `reason_cancellation`, `customer_id`, `housekeeper_id`, `job_id`) 
VALUES 
(NOW(), NOW(), '2024-10-07 08:00:00', 3, 'CONFIRMED', 200.00, 20.00, '123 Main St', '101', 'Downtown', null, 'Springfield', 'SP', '12345678', '3550308', 2, 1, 1, 1, 0, 1, 'Nenhuma', null, 1, 2, 1),
(NOW(), NOW(), '2024-10-08 09:00:00', 2, 'PAID', 180.00, 15.00, '456 Oak St', '102', 'Northside', 'Apt 5', 'Metropolis', 'MT', '87654321', '3157807', 3, 2, 1, 2, 1, 1, 'N/A', null, 2, 3, 2),
(NOW(), NOW(), '2024-10-09 07:30:00', 4, 'NO_PAYMENT', 250.00, 25.00, '789 Pine St', '103', 'West End', null, 'Gotham', 'GO', '23456789', '5300108', 4, 2, 2, 1, 1, 0, null, null, 3, 4, 3),
(NOW(), NOW(), '2024-10-10 10:00:00', 3, 'CONCLUDED', 220.00, 18.00, '101 Maple St', '104', 'Eastside', null, 'Star City', 'SC', '34567890', '4216602', 1, 1, 1, 2, 1, 1, 'Nenhuma', 'Condições climáticas', 4, 5, 4),
(NOW(), NOW(), '2024-10-11 08:30:00', 5, 'CONFIRMED', 300.00, 30.00, '202 Cedar St', '105', 'Midtown', 'Bldg 2', 'Central City', 'CC', '45678901', '4106902', 3, 3, 1, 1, 2, 2, 'N/A', null, 5, 6, 5),
(NOW(), NOW(), '2024-10-12 06:45:00', 4, 'PAID', 270.00, 25.00, '303 Willow St', '106', 'Uptown', null, 'Coast City', 'CO', '56789012', '3304557', 2, 2, 1, 2, 1, 1, 'N/A', null, 1, 2, 6),
(NOW(), NOW(), '2024-10-13 09:15:00', 2, 'NO_PAYMENT', 150.00, 12.00, '404 Birch St', '107', 'Suburb', null, 'Bludhaven', 'BH', '67890123', '3305802', 1, 1, 0, 1, 0, 1, null, 'Cliente cancelou', 2, 3, 7),
(NOW(), NOW(), '2024-10-14 11:00:00', 3, 'CONCLUDED', 210.00, 20.00, '505 Elm St', '108', 'Village', 'Suite 3', 'Keystone City', 'KC', '78901234', '3302902', 2, 1, 1, 2, 1, 1, null, null, 3, 4, 8),
(NOW(), NOW(), '2024-10-15 07:00:00', 4, 'CONFIRMED', 230.00, 22.00, '606 Walnut St', '109', 'Countryside', null, 'Hub City', 'HC', '89012345', '3304557', 3, 2, 2, 1, 1, 0, 'Cliente requer atenção especial', null, 4, 5, 9),
(NOW(), NOW(), '2024-10-16 08:45:00', 5, 'PAID', 280.00, 28.00, '707 Spruce St', '110', 'Lakeside', null, 'Fawcett City', 'FC', '90123456', '3306305', 4, 3, 1, 2, 2, 2, 'Observações adicionais', 'Problema técnico', 5, 6, 10);


INSERT INTO `rating` (`created_at`, `updated_at`, `description`, `grade`, `visibility`, `daily_id`, `evaluator_id`, `evaluated_id`) VALUES 
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1),
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1),
(NOW(), NOW(), 'BLA, BLA, BLA', 7.0, true, 1, 1, 1);


INSERT INTO `payment` (`created_at`, `updated_at`, `status`, `value`, `transaction_id`, `daily_id`) VALUES 
(NOW(), NOW(), 'ACCEPTED', 500.00, 1, 1),
(NOW(), NOW(), 'ACCEPTED', 700.00, 1, 1),
(NOW(), NOW(), 'ACCEPTED', 900.00, 1, 1);


unlock tables;