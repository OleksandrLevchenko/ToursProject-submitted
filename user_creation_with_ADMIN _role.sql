/*    These queries provide creating ADMIN role user for complete functionality of the Tours project    */

-- creates user with password 123456 (encrypted)
INSERT INTO `ToursDB`.`users` (`email`, `first_name`, `last_name`, `password`) VALUES ('admin@mail.com', 'Oleksandr', 'Levchenko', '$2a$10$psWtp6ab4IDRrXG4o4mkQOl72DVEPnbIYefgGVkmJFfyB8v.nImji');

-- creates ADMIN role
INSERT INTO `ToursDB`.`roles` (`role_id`, `name`) VALUES ('1', 'admin');

-- assigns ADMIN role to user [1]
INSERT INTO `ToursDB`.`users_roles` (`id`, `role_id`) VALUES ('1', '1');