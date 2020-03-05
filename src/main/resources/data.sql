DROP TABLE IF EXISTS listing;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS category;


CREATE TABLE `user` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_key` (`username`)
);



CREATE TABLE `category` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_key` (`name`)
);


CREATE TABLE `listing` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(64) DEFAULT NULL,
  `description` VARCHAR(256) DEFAULT NULL,
  `price` DECIMAL(10,4),
  `user_id` BIGINT(20) unsigned NOT NULL,
  `category_id` BIGINT(20) unsigned NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_fkey` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `category_fkey` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);

ALTER TABLE `user` ALTER COLUMN id RESTART WITH 100001;
ALTER TABLE `listing` ALTER COLUMN id RESTART WITH 100001;
ALTER TABLE `category` ALTER COLUMN id RESTART WITH 100001;