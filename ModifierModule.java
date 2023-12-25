
package controlleur.Ajouter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifierModule extends Application {

    private TextField intituleField;
    private TextField filiereleField;
    private TextField professeurField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("page modifier Module");

        intituleField = new TextField();
        intituleField.setPromptText("Intitulé");

       filiereleField = new TextField();
       filiereleField.setPromptText("Filiere");
       
       professeurField = new TextField();
      professeurField.setPromptText("Professeur");

        Button modifyButton = new Button("Modifier");
        modifyButton.setOnAction(e -> modifyMODULE());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(intituleField, professeurField ,filiereleField , modifyButton);

        Scene scene = new Scene(layout, 300, 260);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void modifyMODULE() {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "UPDATE module SET professeur = ? , SET filiere = ?   WHERE intitule = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, professeurField.getText());
                preparedStatement.setString(2,  filiereleField.getText());
                   preparedStatement.setString(3, intituleField.getText());
                    
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Modification réussie !");
                } else {
                    System.out.println("Aucune modification effectuée. Vérifiez l'INTITULE de MODULE.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


