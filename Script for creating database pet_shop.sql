-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pet_shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pet_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pet_shop` DEFAULT CHARACTER SET utf8mb3 ;
USE `pet_shop` ;

-- -----------------------------------------------------
-- Table `pet_shop`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `is_admin` TINYINT NOT NULL,
  `is_subscribed` TINYINT NOT NULL,
  `profile_picture_url` VARCHAR(2048) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `postcode` VARCHAR(45) NOT NULL,
  `street_address` VARCHAR(45) NOT NULL,
  `owner_id` INT NOT NULL,
  `apartment_building` VARCHAR(45) NULL DEFAULT NULL,
  `apartment_number` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_users_fk_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `address_users_fk`
    FOREIGN KEY (`owner_id`)
    REFERENCES `pet_shop`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`brands`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`brands` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `logo_url` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`discounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`discounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `percent_discount` INT NOT NULL,
  `start_at` DATETIME NOT NULL,
  `end_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`subcategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`subcategories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `subcategories_categories_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `subcategories_categories_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `pet_shop`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE(10,2) NOT NULL,
  `quantity` INT NOT NULL,
  `info` TEXT NOT NULL,
  `subcategory_id` INT NOT NULL,
  `brand_id` INT NOT NULL,
  `discount_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `products_subcategories_fk_idx` (`subcategory_id` ASC) VISIBLE,
  INDEX `products_brands_fk_idx` (`brand_id` ASC) VISIBLE,
  INDEX `products_discount_fk_idx` (`discount_id` ASC) VISIBLE,
  CONSTRAINT `products_brands_fk`
    FOREIGN KEY (`brand_id`)
    REFERENCES `pet_shop`.`brands` (`id`),
  CONSTRAINT `products_discount_fk`
    FOREIGN KEY (`discount_id`)
    REFERENCES `pet_shop`.`discounts` (`id`),
  CONSTRAINT `products_subcategories_fk`
    FOREIGN KEY (`subcategory_id`)
    REFERENCES `pet_shop`.`subcategories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`images` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `image_url` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `images_prodicts_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `images_prodicts_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `pet_shop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NOT NULL,
  `ordered_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `orders_addresses_fk_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `orders_addresses_fk`
    FOREIGN KEY (`address_id`)
    REFERENCES `pet_shop`.`addresses` (`id`),
  CONSTRAINT `orders_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_shop`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`orders_have_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`orders_have_products` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `orders_have_products_products_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `orders_have_products_orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `pet_shop`.`orders` (`id`),
  CONSTRAINT `orders_have_products_products_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `pet_shop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `posted_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `reviews_users_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `reviews_products_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `reviews_products_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `pet_shop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reviews_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_shop`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pet_shop`.`users_have_favorite_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_shop`.`users_have_favorite_products` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `product_id`),
  INDEX `users_have_favorite_products_products_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `users_have_favorite_products_products_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `pet_shop`.`products` (`id`),
  CONSTRAINT `users_have_favorite_products_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_shop`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
