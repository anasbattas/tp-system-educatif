
package Controlleurs.Supprimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;

public class supprimerEnseignant extends Application {

    private ObservableList<enseignant> itemList;
    private ListView<enseignant> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Supprimer Enseignant ");

        // Charger les données depuis la base de données
        loadItemsFromDatabase();

        // Créer une ListView avec liaison de données
        listView = new ListView<>(itemList);

        // Créer un bouton de suppression
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(e -> deleteSelectedItem());

        // Mettre en page les éléments
        HBox layout = new HBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(listView, deleteButton);

        // Afficher la scène
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadItemsFromDatabase() {
        itemList = FXCollections.observableArrayList();

        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT grade, nom,prenom,departement,email FROM enseignant";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String grade = resultSet.getString("grade");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                     String departement = resultSet.getString ("departement");
                    String email = resultSet.getString("email");
                    enseignant item = new enseignant(grade, nom,prenom, departement,email);
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedItem() {
        // Récupérer l'objet Item sélectionné
        enseignant selectedItem = listView.getSelectionModel().getSelectedItem();

        // Vérifier s'il y a un élément sélectionné
        if (selectedItem != null) {
            // Supprimer l'élément de la base de données
            deleteItemFromDatabase(selectedItem.getName());

            // Supprimer l'élément de la liste
            itemList.remove(selectedItem);
        }
    }

    private void deleteItemFromDatabase(String nom) {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "DELETE FROM enseignant WHERE nom = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nom);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe modèle d'objet
    public static class enseignant {
        private final String grade;
        private final String nom;
        private final String prenom;
         private final String departement ;
         private final String email;

        public enseignant(String grade, String nom,String prenom,String departement,String email) {
            this.grade = grade;
            this.nom = nom;
            this.prenom=prenom;
            this.departement=departement; 
            this.email=email;
        }

        public String getgrade() {
            return grade;
        }

        public String getName() {
            return nom;
        }
        public String getemail() {
            return email;
        }

        public String getprenom() {
            return prenom;
        }
        public String getdepartement() {
            return departement;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
