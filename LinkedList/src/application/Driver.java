package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {
	private Linkedlist<String> linkedList = new Linkedlist<>(); // LinkedList for storing data
	private TextArea displayArea = new TextArea(); // Area to display the linked list
	private TextField inputField = new TextField(); // Input field for user data

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Linked List Operations");

		// Layout and controls
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(10);

		Label label = new Label("Enter value:");
		inputField.setPromptText("Enter a string to insert/delete/search");

		Button insertButton = new Button("Insert");
		insertButton.setOnAction(e -> insertAction());

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteAction());

		Button searchButton = new Button("Search");
		searchButton.setOnAction(e -> searchAction());

		Button displayButton = new Button("Display");
		displayButton.setOnAction(e -> displayList());

		Button clearButton = new Button("Clear List");
		clearButton.setOnAction(e -> clearList());

		// Display area configuration
		displayArea.setEditable(false);
		displayArea.setPrefHeight(200);

		// Layout setup
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(label, 0, 0);
		gridPane.add(inputField, 1, 0);
		gridPane.add(insertButton, 0, 1);
		gridPane.add(deleteButton, 1, 1);
		gridPane.add(searchButton, 2, 1);
		gridPane.add(displayButton, 3, 1);
		gridPane.add(clearButton, 4, 1);

		vbox.getChildren().addAll(gridPane, displayArea);

		// Create the scene and show the stage
		Scene scene = new Scene(vbox, 600, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Insert action
	private void insertAction() {
		String value = inputField.getText();
		if (!value.isEmpty()) {
			linkedList.insert(value);
			displayArea.appendText("Inserted: " + value + "\n");
			inputField.clear();
		} else {
			displayArea.appendText("Please enter a value to insert.\n");
		}
	}

	// Delete action
	private void deleteAction() {
		String value = inputField.getText();
		if (!value.isEmpty()) {
			boolean result = linkedList.delete(value);
			if (result) {
				displayArea.appendText("Deleted: " + value + "\n");
			} else {
				displayArea.appendText("Value not found for deletion.\n");
			}
			inputField.clear();
		} else {
			displayArea.appendText("Please enter a value to delete.\n");
		}
	}

	// Search action
	private void searchAction() {
		String value = inputField.getText();
		if (!value.isEmpty()) {
			boolean found = linkedList.search(value);
			if (found) {
				displayArea.appendText("Found: " + value + "\n");
			} else {
				displayArea.appendText("Value not found.\n");
			}
			inputField.clear();
		} else {
			displayArea.appendText("Please enter a value to search.\n");
		}
	}

	// Display the linked list
	private void displayList() {
		displayArea.appendText("Current Linked List: ");
		linkedList.print(); // Prints the linked list in the console
		displayArea.appendText("\n");
	}

	// Clear the list
	private void clearList() {
		linkedList.clear();
		displayArea.appendText("The linked list has been cleared.\n");
	}
}
