Phonebook Application:

Overview

The Phonebook Application is a simple contact management system that allows users to store, search, update, delete, and display contact information. The app uses linked lists for data storage and management, offering basic contact management functionalities in a command-line or GUI interface. This application is suitable for beginners learning data structures, especially linked lists, in Java.

Features

The application includes the following functionalities:

1. Insert Contact: Add a new contact with a name and phone number.


2. Search Contact: Find a contact by name and display the contact details.


3. Display All Contacts: Show all contacts in the phonebook in alphabetical order.


4. Delete Contact: Remove a contact by name from the phonebook.


5. Update Contact: Modify the phone number of an existing contact.


6. Sort Contacts: Automatically sort contacts alphabetically when displaying.


7. Analyze Search Efficiency: (Optional) Outputs basic efficiency metrics for search operations.



Modules

The Phonebook Application is divided into the following modules:

1. Node

Represents a single node in the linked list.

Contains a Contact object and a reference to the next Node.


2. Contact

Represents a contact in the phonebook.

Includes fields for the contact’s name and phoneNumber.

Overrides toString() to display contact details in a readable format.


3. Phonebook

Core class containing the linked list and methods for managing contacts.

Functions:

insertContact(String name, String phoneNumber): Adds a new contact to the end of the linked list.

searchContact(String name): Searches for a contact by name and returns the contact if found.

deleteContact(String name): Removes a contact by name from the list.

displayContacts(): Displays all contacts in alphabetical order.

updateContact(String name, String newPhoneNumber): Finds a contact by name and updates the phone number.

sortContacts(): Sorts contacts by name (for faster searching and displaying).



4. PhonebookGUI:

Provides a graphical user interface (using JavaFX).

Allows users to interact with the phonebook functions through buttons, text fields, and lists.


Project Structure:

The project has a simple structure with a main class (Phonebook) that holds all the logic. Additional classes include Node and Contact to handle the linked list data structure and individual contact details, respectively.

Contributors:

Name of Contributor 1: Honest Haimbangu(TL): 224073370

Name of Contributor 2: Muletwa Liswani:  224084437

Name of Contributor 3: Mika Orberholzer: 224023500

Name of Contributor 4: Ndidalela Shanyanana: 223109983

Name of Contributor 5: Sofia Katonyala: 224032739

Name of Contributor 6: Hills Lutombi:  223049204

