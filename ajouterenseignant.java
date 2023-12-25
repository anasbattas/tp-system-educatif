
package controlleur.Ajouter;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ajouterenseignant extends Application{
    
    private TextField nameField = new TextField();
    private TextField prenomField = new TextField();
    private TextField emailField = new TextField();
    private TextField gradeField = new TextField();
    private TextField departementField = new TextField();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Ajouter Enseignant");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add UI elements to the grid
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Prenom:"), 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(new Label("Email :"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Grade"), 0, 3);
        grid.add(gradeField, 1, 3);
        grid.add(new Label("Departement"), 0, 4);
        grid.add(departementField, 1, 4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveUserInfo());

        grid.add(saveButton, 1, 5);

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveUserInfo() {
        // Get user input
        String name = nameField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String grade = gradeField.getText();
        String departement = departementField.getText();
      
      

        // Perform database operations (insert into MySQL, for example)
        try {
            // Load the MySQL JDBC driver (replace with your database details)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Replace with your database connection details
            String url = "jdbc:mysql://localhost:3306/projetjava";
            String username = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // SQL statement with placeholders to prevent SQL injection
                String insertQuery = "INSERT INTO enseignant (nom, prenom,email,grade,departement) VALUES (?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, email);
                     preparedStatement.setString(4, grade);
                      preparedStatement.setString(5, departement);
                    
                    

                    // Execute the insert statement
                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // For simplicity, we'll just print the user input in this example
        System.out.println("User Info Saved: " + name + " " + prenom+" "+email+" "+departement+" "+grade );
    }
    
}
