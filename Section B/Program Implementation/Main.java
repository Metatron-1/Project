import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends Application {
    // Contact class
    static class Contact {
        String name;
        String phoneNumber;
        String relation;

        public Contact(String name, String phoneNumber, String relation) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.relation = relation;
        }

        @Override
        public String toString() {
            return name + " - " + phoneNumber + " - " + relation;
        }
    }

    // Node class for linked list
    static class Node {
        Contact contact;
        Node next;

        public Node(Contact contact) {
            this.contact = contact;
            this.next = null;
        }
    }

    // Phonebook class
    static class Phonebook {
        private Node head;

        public Phonebook() {
            this.head = null;
        }

     public void insertContact(String name, String phoneNumber, String relation) {
    if (searchContact(name) != null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Duplicate Contact");
        alert.setHeaderText(null);
        alert.setContentText("A contact with the name '" + name + "' already exists.");
        alert.showAndWait();
        return;
    } //This if statement checks if the entered contact already exists in the phonebook

    Contact newContact = new Contact(name, phoneNumber, relation);
    Node newNode = new Node(newContact);

    if (head == null) {
        head = newNode;
    } else {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
}



        public Contact searchContact(String name) {
            Node current = head;
            while (current != null) {
                if (current.contact.name.equalsIgnoreCase(name)) {
                    return current.contact;
                }
                current = current.next;
            }
            return null;
        }

        public boolean deleteContact(String name) {
            if (head == null) return false;

            if (head.contact.name.equalsIgnoreCase(name)) {
                head = head.next;
                return true;
            }

            Node current = head;
            while (current.next != null && !current.next.contact.name.equalsIgnoreCase(name)) {
                current = current.next;
            }

            if (current.next != null) {
                current.next = current.next.next;
                return true;
            }

            return false;
        }

        public void updateContact(String oldName, String newName, String newPhone, String newRelation) {
            Contact contact = searchContact(oldName);
            if (contact != null) {
                contact.name = newName;
                contact.phoneNumber = newPhone;
                contact.relation = newRelation;
            }
        }

        public List<Contact> getAllContacts() {
            List<Contact> contacts = new ArrayList<>();
            Node current = head;
            while (current != null) {
                contacts.add(current.contact);
                current = current.next;
            }
            return contacts;
        }

        public void sortContacts() {
            List<Contact> contacts = getAllContacts();
            contacts.sort(Comparator.comparing(contact -> contact.name));
            head = null;
            for (Contact contact : contacts) {
                insertContact(contact.name, contact.phoneNumber, contact.relation);
            }
        }
    }

    // Main application
    private Phonebook phonebook = new Phonebook();
    private ListView<String> contactListView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PhoneBook Application");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #8B4513;"); // Set background color to brown

        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-background-color: #FAF3E0; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setStyle("-fx-background-color: #FAF3E0; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        TextField relationField = new TextField();
        relationField.setPromptText("Relation");
        relationField.setStyle("-fx-background-color: #FAF3E0; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        // Buttons
        Button insertButton = new Button("Insert Contact");
        styleButton(insertButton);

        Button searchButton = new Button("Search Contact");
        styleButton(searchButton);

        Button deleteButton = new Button("Delete Contact");
        styleButton(deleteButton);

        Button updateButton = new Button("Update Contact");
        styleButton(updateButton);

        Button sortButton = new Button("Sort A-Z");
        styleButton(sortButton);

        // Button Actions
        insertButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();
            String relation = relationField.getText();
            if (!name.isEmpty() && !phoneNumber.isEmpty() && !relation.isEmpty()) {
                phonebook.insertContact(name, phoneNumber, relation);
                updateContactListView();
                clearFields(nameField, phoneField, relationField);
            }
        });

        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            Contact contact = phonebook.searchContact(name);
            if (contact != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, contact.toString());
                alert.setTitle("Contact Found");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contact not found.");
                alert.setTitle("Error");
                alert.show();
            }
        });

        deleteButton.setOnAction(e -> {
            String name = nameField.getText();
            if (phonebook.deleteContact(name)) {
                updateContactListView();
                clearFields(nameField, phoneField, relationField);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contact not found.");
                alert.setTitle("Error");
                alert.show();
            }
        });

        updateButton.setOnAction(e -> {
            String oldName = nameField.getText();
            String newName = nameField.getText();
            String newPhone = phoneField.getText();
            String newRelation = relationField.getText();
            phonebook.updateContact(oldName, newName, newPhone, newRelation);
            updateContactListView();
            clearFields(nameField, phoneField, relationField);
        });

        sortButton.setOnAction(e -> {
            phonebook.sortContacts();
            updateContactListView();
        });

        // Layout
        HBox inputLayout = new HBox(10, nameField, phoneField, relationField);
        layout.getChildren().addAll(inputLayout, insertButton, searchButton, deleteButton, updateButton, sortButton, contactListView);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateContactListView() {
        contactListView.getItems().clear();
        for (Contact contact : phonebook.getAllContacts()) {
            contactListView.getItems().add(contact.toString());
        }
    }

    private void clearFields(TextField nameField, TextField phoneField, TextField relationField) {
        nameField.clear();
        phoneField.clear();
        relationField.clear();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-font-weight: bold;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));
    }
}
