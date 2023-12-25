
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
public class ajouteretudiant extends Application{
//    
     public static void main(String[] args) {
        launch(args);
    }
     
     @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX MySQL Example");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
         Label nameLabel = new Label("Nom");
        Label prenomLabel = new Label("Prenom");
        Label emailLabel = new Label("email");
        Label aprogeeLabel = new Label("apogee");
        Label filiereLabel = new Label("filiere");
        
         TextField nameField = new TextField();
        TextField  prenomField= new TextField();
       TextField emailField = new TextField();
       TextField aprogeeField = new TextField();
       TextField filiereField = new TextField();
           
       Button addButton = new Button("Ajouter");
       addButton.setOnAction(e -> insertData(nameField.getText(), prenomField.getText(),emailField.getText(),aprogeeField.getText()));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(prenomLabel, 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(nameLabel, 0, 3);
        grid.add(nameField,1 , 3);
        grid.add(nameLabel, 0, 4);
        grid.add(nameField,1 , 4);
        grid.add(addButton, 1, 5);
        
        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
  

}

private void insertData(String nom, String prenom,String email,String aprogee) {
        // Informations de connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/projetjava";
        String utilisateur = "root";
        String motDePasse = "";

        // SQL pour l'insertion
        String sql = "INSERT INTO etudiant (nom, prenom,email,aprogee,filiere) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Remplacement des paramètres dans la requête SQL
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
             preparedStatement.setString(3, email);
               preparedStatement.setString(4, aprogee);
            

            // Exécution de la requête
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
