package prjlinkedlists.linkedlistdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LinkedListTestProgram extends Application {

    private gameLeaderboard topTen = new gameLeaderboard();
    private TextArea displayArea;

    @Override
    public void start(Stage primaryStage) {
        TextField commandInput = new TextField();
        commandInput.setPromptText("Enter command (e.g., 'insert name score')");

        Button submitButton = new Button("Submit");
        displayArea = new TextArea();
        displayArea.setEditable(false);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(commandInput, submitButton, displayArea);

        submitButton.setOnAction(e -> {
            String command = commandInput.getText();
            processCommand(command);
            commandInput.clear();
        });

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Top Performers List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processCommand(String command) {
        String[] parts = command.split("\\s+", 3);
        if (parts.length == 3 && "insert".equals(parts[0])) {
            try {
                String name = parts[1];
                int score = Integer.parseInt(parts[2]);
                topTen.insert(name, score);
                displayArea.setText(topTen.toString());
            } catch (NumberFormatException nfe) {
                showAlert("Invalid Command", "Score must be an integer.");
            }
        } else {
            showAlert("Invalid Command", "Command must be of the form 'insert name score'.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}