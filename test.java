
package controlleur.Ajouter;

//classe pour tester 
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class test extends Application {

    private ObservableList<etudiant> studentList;
    private TableView<etudiant> tableView;
    private TextField nameTextField;
    private TextField aprogeeTextField;
     private TextField prenomTextField;
      private TextField emailTextField;
       private TextField filiereTextField;
    private Button modifyButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page Mdifier Etudiant ");

        // Charger les données depuis la base de données
        loadStudentsFromDatabase();

        // Créer la TableView avec liaison de données
        tableView = createTableView();

        // Champs de texte pour la modification
        nameTextField = new TextField();
        aprogeeTextField= new TextField();
        prenomTextField= new TextField();
       emailTextField= new TextField();
        filiereTextField= new TextField();
        // Bouton de modification
        modifyButton = new Button("Modifier");
        modifyButton.setOnAction(e -> modifySelectedStudent());

        // Mettre en page les éléments
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(tableView, nameTextField,aprogeeTextField ,emailTextField,prenomTextField,filiereTextField ,modifyButton);

        // Afficher la scène
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<etudiant> createTableView() {
        TableView<etudiant> tableView = new TableView<>();
        studentList = FXCollections.observableArrayList();

        TableColumn<etudiant, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<etudiant, String> prenomColumn = new TableColumn<>("Âge");
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());

        tableView.getColumns().addAll(nameColumn, prenomColumn);
        tableView.setItems(studentList);

        // Sélectionner un étudiant pour la modification
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField.setText(newValue.getnom());
                prenomTextField.setText(String.valueOf(newValue.getprenom()));
                modifyButton.setDisable(false); // Activer le bouton de modification lorsque l'étudiant est sélectionné
            }
        });

        return tableView;
    }

    private void loadStudentsFromDatabase() {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
        String username = "votre_utilisateur";
        String password = "votre_mot_de_passe";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT id, nom, age FROM etudiants";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int aprogee = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                     String email = resultSet.getString("email");
                      String filiere = resultSet.getString("filiere");
                    etudiant student = new etudiant(aprogee, nom, prenom,email,filiere);
                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifySelectedStudent() {
        etudiant selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Mettre à jour les informations dans la base de données
            updateStudentInDatabase(selectedStudent.getaprogee(), nameTextField.getText(), Integer.parseInt(prenomTextField.getText()));

            // Mettre à jour les informations dans la TableView
            selectedStudent.setName(nameTextField.getText());
            selectedStudent.setName(prenomTextField.getText());
            
        }
    }

    private void updateStudentInDatabase(int studentId, String newName, int newAge) {
        // Remplacez les informations suivantes par vos détails de base de données
        String jdbcUrl = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
        String username = "votre_utilisateur";
        String password = "votre_mot_de_passe";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "UPDATE etudiants SET nom = ?, age = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, newAge);
                preparedStatement.setInt(3, studentId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class etudiant {
        private final SimpleIntegerProperty aprogee;
        private final SimpleStringProperty nom;
        private final SimpleStringProperty prenom ;
         private final SimpleStringProperty email;
          private final SimpleStringProperty filiere;

        public etudiant(int aprogee, String nom, String prenom,String email,String filiere) {
            this.aprogee = new SimpleIntegerProperty(aprogee);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.email = new SimpleStringProperty(email);
               this.filiere = new SimpleStringProperty(filiere);
            
        }

        public int getaprogee() {
            return aprogee.get();
        }

        public String getnom() {
            return nom.get();
        }

        public void setName(String newName) {
            nom.set(newName);
        }

        public String getprenom() {
            return prenom.get();
        }

        public void setAge(String newAge) {
            email.set(newAge);
        }

        public SimpleIntegerProperty aprogeeProperty() {
            return aprogee;
        }

        public SimpleStringProperty nameProperty() {
            return nom;
        }

        public SimpleStringProperty prenomProperty() {
            return prenom;
        }
    }
}

