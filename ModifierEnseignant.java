
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

public class ModifierEnseignant extends Application {

    private TextField gradeField;
    private TextField nomField;
    private TextField prenomField;
    private TextField emailField;
    private TextField departementField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("page modifier Etudiants");

        gradeField= new TextField();
        gradeField.setPromptText("Grade");

        nomField = new TextField();
        nomField.setPromptText("Nom");

        prenomField = new TextField();
        prenomField.setPromptText("prenom");
        
        emailField = new TextField();
        emailField.setPromptText("email");

        departementField= new TextField();
       departementField.setPromptText("Departement");

        Button modifyButton = new Button("Modifier");
        modifyButton.setOnAction(e -> modifyStudent());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(gradeField,  nomField, prenomField,emailField,departementField, modifyButton);

        Scene scene = new Scene(layout, 400, 260);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void modifyStudent() {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "UPDATE enseignant SET grade = ?, prenom = ?  ,departement=? WHERE  email= ? and nom=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(5, nomField.getText());
                   preparedStatement.setString(2, prenomField.getText());
                   preparedStatement.setString(4, emailField.getText());
                   preparedStatement.setString(3, departementField.getText());
                   preparedStatement.setString(1, gradeField.getText());
                

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Modification réussie !");
                } else {
                    System.out.println("Aucune modification effectuée. Vérifiez l'ID de l'étudiant.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

