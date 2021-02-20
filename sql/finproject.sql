drop database finproject;
create database finproject
  CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use finproject;

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL,
  primary key(role_id)
) ENGINE=InnoDB;

INSERT INTO `roles` (`role_id`, `role`) VALUES
(1, 'менеджер'),
(2, 'мастер'),
(3, 'клиент');

CREATE TABLE `users` (
  `user_id` int(11) AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sault` varchar(10) NOT NULL,
  `wallet` int(100) ,
  `add_money` int(100) ,
  `role_id` int(11) DEFAULT 3,
  FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
  primary key(user_id)
) ENGINE=InnoDB;

INSERT INTO `users` VALUES
(1, 'admin@admin.com','Администратор','Юрий','d622beb054dc541d462c11ea412f36600c310a3e2f274842a0f71f69ab12e0c0','frlxggs',null,null,1),
(2, 'first@master.com','Мастер','Влад','40c4e5db62d24f7adc83d3cc7f91705cbb8849e7c2122c6b25f7b4388bf9f49b','efbkfoo',null,null,2),
(3, 'second@master.com','Мастер','Олег','5137a684433ea8e8c92727c0b1a8a17c6796e24c0773777ec6d2818d788708d3','pqjpovo',null,null,2);

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(20) NOT NULL,
  primary key(service_id)
) ENGINE=InnoDB;

INSERT INTO `services` (`service_id`, `service_name`) VALUES
(1, 'телевизор'),
(2, 'ноутбук'),
(3, 'холодильник'),
(4, 'конвектор'),
(5, 'микроволновая печь'),
(6, 'мультиварка'),
(7, 'телефон');

CREATE TABLE `request_statuses` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL,
  primary key(status_id)
) ENGINE=InnoDB;

INSERT INTO `request_statuses` (`status_id`, `status_name`) VALUES
(1, 'в обработке'),
(2, 'ожидает оплату'),
(3, 'оплачено'),
(4, 'отменено'),
(5, 'в работе'),
(6, 'выполнено');

CREATE TABLE `service_request` (
  `request_id` int(11) AUTO_INCREMENT,
  `user_id` int(11),
  `service_id` int(11),
  `cost` int(100) DEFAULT NULL,
  `status_id` int(11),
  `master_id` int(11),
  `request_date` date,
  `feedback` varchar(300) DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE,
  FOREIGN KEY (status_id) REFERENCES request_statuses(status_id) ON DELETE CASCADE,
  FOREIGN KEY (master_id) REFERENCES users(user_id) ON DELETE CASCADE,
  primary key(request_id)
) ENGINE=InnoDB;


