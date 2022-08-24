NOTE:
For complete functionality, ADMIN role is required. ADMIN role can only be added manually to the database for security reasons. To do this user_creation_with_ADMIN _role.sql can be executed (using MySQLWorkbench for example).

For testing purposes data_for_TOURS_project.sql can be executed to have more data in all tables of the project.



    This is a full stack Java Tours project.
----------------------------------------------------------------------------------------------------
FUNCTIONALITY:
Kuala Lumpur tourist portal has main page with some external links to other touristic resouces, social media links (for following), buttons LOGIN and REGISTER with further functionality:

- LOGIN page signs in existing users.

- REGISTER page creates a new user (with NON ADMIN role only).



User with ADMIN role has access to such pages with corresponding functionality:

- HOME page redirects to the main page.

- ATTRACTIONS page presents the list of already existing tourist Attractions and ability to edit or delete the existing Attractions or create new Attractions. This is done with redirection to corresponding pages:
    - EDIT ATTRACTION page lets edit "attraction title" and "attraction description" fiels.
    - ADD ATTRACTION page creates new Attraction with entered "attraction title" and "attraction description" data.

- TOURS page presents the list of Tours - schedule of Attractions (each record contains "date", "attraction title" and "price"). All existing Tours can be edited or deleted. A new Tour can be also added. This is done with redirection to corresponding pages:
    - EDIT TOUR page lets edit "tour date", "tour price" and choose "attraction title" (from the drop-down list of existing attractions) for particular Tour.
    - ADD ATTRACTION page creates new Tour with entered "tour date", "tour price" and choosen "attraction title" (from the drop-down list of existing attractions) for particular Tour.
    
- RESERVATIONS page presents the list of already existing Tours (schedule of Attractions with "date", "attraction title" and "price" for each Tour) and list of current user Reservations. Tours can be added to Reservations list of current user ("Reserve" button). Already reserved Tours can be removed from Reservations list ("Delete" button).

- USERS page shows list of all registered users ("first name", "last name" and "e-mail" for each user).

- LOG OUT page logs out current user.



User with NON ADMIN role has access to such pages with corresponding functionality:

- HOME page redirects to the main page.

- RESERVATIONS page presents the list of already existing Tours (schedule of Attractions with "date", "attraction title" and "price" for each Tour) and list of current user Reservations. Tours can be added to Reservations list of current user ("Reserve" button). Already reserved Tours can be removed from Reservations list ("Delete" button).

- LOG OUT page logs out current user.
