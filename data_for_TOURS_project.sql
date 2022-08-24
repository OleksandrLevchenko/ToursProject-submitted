/*    These queries provide data for TOURS project to check its functionality    */

-- creates 3 users with password 123456 (encrypted)
INSERT INTO `ToursDB`.`users` (`email`, `first_name`, `last_name`, `password`) VALUES ('admin@mail.com', 'Oleksandr', 'Levchenko', '$2a$10$psWtp6ab4IDRrXG4o4mkQOl72DVEPnbIYefgGVkmJFfyB8v.nImji');
INSERT INTO `ToursDB`.`users` (`email`, `first_name`, `last_name`, `password`) VALUES ('batman@mail.com', 'Bruce', 'Wayne', '$2a$10$psWtp6ab4IDRrXG4o4mkQOl72DVEPnbIYefgGVkmJFfyB8v.nImji');
INSERT INTO `ToursDB`.`users` (`email`, `first_name`, `last_name`, `password`) VALUES ('superman@mail.com', 'Kent', 'Clark', '$2a$10$psWtp6ab4IDRrXG4o4mkQOl72DVEPnbIYefgGVkmJFfyB8v.nImji');

-- creates 2 roles: ADMIN and USER
INSERT INTO `ToursDB`.`roles` (`role_id`, `name`) VALUES ('1', 'admin');
INSERT INTO `ToursDB`.`roles` (`role_id`, `name`) VALUES ('2', 'user');

-- assigns ADMIN role to user [1] and USER role to users [2] and [3]
INSERT INTO `ToursDB`.`users_roles` (`id`, `role_id`) VALUES ('1', '1');
INSERT INTO `ToursDB`.`users_roles` (`id`, `role_id`) VALUES ('2', '2');
INSERT INTO `ToursDB`.`users_roles` (`id`, `role_id`) VALUES ('3', '2');

-- adds 5 attractions
INSERT INTO `ToursDB`.`attractions` (`id`, `title`, `description`) VALUES ('1', 'Petronas Twin Towers', 'Admittance and photo fee included');
INSERT INTO `ToursDB`.`attractions` (`id`, `title`, `description`) VALUES ('2', 'Bird Park', 'Transfer and entry ticket included');
INSERT INTO `ToursDB`.`attractions` (`id`, `title`, `description`) VALUES ('3', 'National Museum', 'Entry ticket with dinner included');
INSERT INTO `ToursDB`.`attractions` (`id`, `title`, `description`) VALUES ('4', 'Central Market', 'Advertising sertificate for coffe shop.');
INSERT INTO `ToursDB`.`attractions` (`id`, `title`, `description`) VALUES ('5', 'Batu Caves', 'Admittance is free. Transfer included.');

-- creates tour schedule with attractions from the above list
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('1', '2022-09-11', '1', '9.99');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('2', '2022-09-30', '2', '25.55');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('3', '2022-10-20', '3', '29.99');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('4', '2022-10-22', '3', '29.99');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('5', '2022-11-05', '4', '10.00');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('6', '2022-11-11', '5', '17.30');
INSERT INTO `ToursDB`.`tours` (`id`, `date`, `attraction_id`, `price`) VALUES ('7', '2022-11-22', '5', '19.30');