
ALTER TABLE `user`
  ADD `key_pix` varchar(255) DEFAULT NULL,
  ADD `cpf` varchar(11) DEFAULT NULL,
  ADD `birth` date DEFAULT NULL,
  ADD `reputation` double DEFAULT NULL,
  ADD `phoneNumber` varchar(11) DEFAULT NULL,
  ADD `document_picture` bigint DEFAULT NULL,
  ADD `user_picture` bigint DEFAULT NULL,
  ADD UNIQUE KEY (`cpf`),
  ADD UNIQUE KEY (`key_pix`),
  ADD FOREIGN KEY (`user_picture`) REFERENCES `picture` (`id`),
  ADD FOREIGN KEY (`document_picture`) REFERENCES `picture` (`id`);