
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

public class supprimerEtudiant extends Application {

    private ObservableList<etudiant> itemList;
    private ListView<etudiant> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Supprimer Etudiant ");

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
            String query = "SELECT aprogee, nom ,prenom,email,filiere FROM etudiant";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int aprogee = resultSet.getInt("aprogee");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                   String filiere = resultSet.getString("filiere");
                    String email = resultSet.getString("email");
                    etudiant item = new etudiant(aprogee, nom,prenom, email,filiere);
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedItem() {
        // Récupérer l'objet Item sélectionné
        etudiant selectedItem = listView.getSelectionModel().getSelectedItem();

        // Vérifier s'il y a un élément sélectionné
        if (selectedItem != null) {
            // Supprimer l'élément de la base de données
            deleteItemFromDatabase(selectedItem.getId());

            // Supprimer l'élément de la liste
            itemList.remove(selectedItem);
        }
    }

    private void deleteItemFromDatabase(int etudiantId) {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "DELETE FROM etudiant WHERE aprogee = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, etudiantId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe modèle d'objet
    public static class etudiant {
        private final int aprogee;
        private final String nom;
        private final String prenom;
         private final String  filiere;
         private final String email;

        public etudiant(int aprogee, String nom, String prenom, String email, String filiere) {
            this.aprogee = aprogee;
            this.nom = nom;
            this.prenom=prenom;
            this.filiere=filiere; 
            this.email=email;
        }

        public int getId() {
            return aprogee;
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
        public String getfiliere() {
            return filiere;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
