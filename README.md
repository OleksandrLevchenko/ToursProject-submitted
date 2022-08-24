NOTE:
For complete functionality, ADMIN role is required. ADMIN role can only be added manually to the database for security reasons. To do this user_creation_with_ADMIN _role.sql can be executed (using MySQLWorkbench for example).

For testing purposes data_for_TOURS_project.sql can be executed to have more data in all tables of the project.



TOURS is a full-stack Java project.
----------------------------------------------------------------------------------------------------

All Kuala Lumpur tourist portal pages have 11 external links to other touristic resources, social media links (Facebook, Twitter, Instagram, YouTube), and a Light/Dark view toggle switch. MAIN page has buttons LOGIN and REGISTER with further functionality:

- LOGIN page signs in existing users.

- REGISTER page creates a new user (with NON-ADMIN role only).



User with ADMIN role has access to such pages/buttons with corresponding functionality:

- HOME button redirects to the MAIN page.

- ATTRACTIONS page presents the list of already existing tourist Attractions and the ability to edit or delete the existing Attractions or create new Attractions. This is done with redirection to corresponding pages:
    - EDIT ATTRACTION page lets edit "attraction title" and "attraction description" fields.
    - ADD ATTRACTION page creates new Attraction with entered "attraction title" and "attraction description" data.

- TOURS page presents the list of Tours - schedule of Attractions (each record contains "date", "attraction title" and "price"). All existing Tours can be edited or deleted. A new Tour can be also added. This is done with redirection to corresponding pages:
    - EDIT TOUR page lets edit "tour date", "tour price" and choose "attraction title" (from the drop-down list of existing attractions) for a particular Tour.
    - ADD ATTRACTION page creates a new Tour with entered "tour date", "tour price" and chosen "attraction title" (from the drop-down list of existing attractions) for a particular Tour.
    
- RESERVATIONS page presents the list of already existing Tours (schedule of Attractions with "date", "attraction title" and "price" for each Tour) and a list of current user Reservations. Tours can be added to the Reservations list of the current user ("Reserve" button). Already reserved Tours can be removed from the Reservations list ("Delete" button).

- USERS page shows the list of all registered users ("first name", "last name" and "e-mail" for each user).

- LOG OUT page logs out the current user.



User with NON-ADMIN role has access to such pages/buttons with corresponding functionality:

- HOME button redirects to the MAIN page.

- RESERVATIONS page presents the list of already existing Tours (schedule of Attractions with "date", "attraction title" and "price" for each Tour) and a list of current user Reservations. Tours can be added to the Reservations list of the current user ("Reserve" button). Already reserved Tours can be removed from the Reservations list ("Delete" button).

- LOG OUT page logs out the current user.
