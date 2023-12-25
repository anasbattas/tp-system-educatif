
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

public class SupprimerFiliere extends Application {

    private ObservableList<filiere> itemList;
    private ListView<filiere> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Supprimer Filiere ");

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
            String query = "SELECT intitule, resonsable,departement FROM filiere";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    
                    String intitule = resultSet.getString("Intitule");
                  String responsable = resultSet.getString("Responsable");
                    String departement= resultSet.getString("Departement");
                    filiere item = new filiere( intitule, responsable,departement );
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedItem() {
        // Récupérer l'objet Item sélectionné
        filiere selectedItem = listView.getSelectionModel().getSelectedItem();

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
            String query = "DELETE FROM filiere WHERE intitule = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, intitule);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe modèle d'objet
    public static class filiere {
      
        private final String intitule;
        private final String responsable;
         private final String departement;

        public filiere(String intitule,String responsable,String departement) {
            
         this.intitule=intitule;
            this.responsable=responsable; 
            this.departement=departement;
          
        }
        public String getintitule() {
            return intitule;
        }

        public String getresponsable() {
            return responsable;
        }
        public String getdepartement() {
            return departement;
        }

        
       

        @Override
        public String toString() {
            return getintitule();
        }
    }
}


