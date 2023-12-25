
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

public class SupprimerModule extends Application {

    private ObservableList<module> itemList;
    private ListView<module> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Supprimer Module ");

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
            String query = "SELECT intitule, filiere,professeur FROM module";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    
                    String intitule = resultSet.getString("intitule");
                  String filiere = resultSet.getString("filiere");
                    String professeur= resultSet.getString("professeur");
                    module item = new module( intitule, filiere, professeur);
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedItem() {
        // Récupérer l'objet Item sélectionné
        module selectedItem = listView.getSelectionModel().getSelectedItem();

        // Vérifier s'il y a un élément sélectionné
        if (selectedItem != null) {
            // Supprimer l'élément de la base de données
            deleteItemFromDatabase(selectedItem.getintitule());

            // Supprimer l'élément de la liste
            itemList.remove(selectedItem);
        }
    }

    private void deleteItemFromDatabase(String intitule) {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/projetjava";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "DELETE FROM module WHERE module = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, intitule);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe modèle d'objet
    public static class module {
      
        private final String intitule;
        private final String filiere;
         private final String professeur;

        public module(String intitule,String filiere,String professeur) {
            
         this.intitule=intitule;
            this.filiere=filiere; 
            this.professeur=professeur;
          
        }
        public String getintitule() {
            return intitule;
        }

        public String getfiliere() {
            return filiere;
        }
        public String getprofesseur() {
            return professeur;
        }

        
       

        @Override
        public String toString() {
            return getintitule();
        }
    }
}

