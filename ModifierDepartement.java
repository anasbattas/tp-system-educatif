
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

public class ModifierDepartement extends Application {

    private TextField intituleField;
    private TextField responsableField;
    ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("page modifier Departement");

        intituleField = new TextField();
        intituleField.setPromptText("Intitulé");

       responsableField = new TextField();
       responsableField.setPromptText("Responsable");

        Button modifyButton = new Button("Modifier");
        modifyButton.setOnAction(e -> modifyStudent());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(intituleField,  responsableField , modifyButton);

        Scene scene = new Scene(layout, 300, 260);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void modifyStudent() {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "UPDATE departement SET responsable = ?  WHERE intitule = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1,  responsableField.getText());
                   preparedStatement.setString(2, intituleField.getText());
                   
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Modification réussie !");
                } else {
                    System.out.println("Aucune modification effectuée. Vérifiez l'INTITULE de DEPARTEMENT.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

