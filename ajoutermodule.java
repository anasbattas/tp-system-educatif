
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
public class ajoutermodule extends Application {

    private TextField intituleField = new TextField();
    private TextField filiereField = new TextField();
    private TextField professeurField = new TextField();
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Administration Information ");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add UI elements to the grid
        grid.add(new Label("Intitule:"), 0, 0);
        grid.add(intituleField, 1, 0);
        grid.add(new Label("Professuer:"), 0, 1);
        grid.add(professeurField, 1, 1);
        grid.add(new Label("filiere"), 0, 2);
        grid.add(filiereField, 1, 2);
        

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveUserInfo());

        grid.add(saveButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveUserInfo() {
        // Get user input
        String intitule = intituleField.getText();
        String professeur = professeurField.getText();
        String filiere = filiereField.getText();
      

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
                String insertQuery = "INSERT INTO module  (intitule, professeur,filiere) VALUES (?, ?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, intitule);
                    preparedStatement.setString(2, professeur);
                     preparedStatement.setString(3, filiere);
                    

                    // Execute the insert statement
                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // For simplicity, we'll just print the user input in this example
        System.out.println("User Info Saved: " + intitule+ " " + professeur+" "+filiere );
    }
}