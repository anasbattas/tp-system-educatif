
package controlleur.Ajouter;

import Controlleurs.Supprimer.SupprimerDepartement;
import Controlleurs.Supprimer.SupprimerFiliere;
import Controlleurs.Supprimer.SupprimerModule;
import Controlleurs.Supprimer.supprimerEnseignant;
import Controlleurs.Supprimer.supprimerEtudiant;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main1  extends Application{

   public static void main(String[] args) {
       Application.launch(args);
   
}
   
 @Override
    public void start(Stage primaryStage) {
    	//  Initialize the Stage, groups, and scene
        primaryStage.setTitle("Page Administration");
        
        Group group1 = new Group();
        Scene scene1 = new Scene(group1, 340, 520);
        
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(0);
        btn.setText("Ajouter Departement ");
         btn.setOnAction(e -> ajouterdepartement2(primaryStage));
        group1.getChildren().add(btn);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        
        
         Button btn1 = new Button();
        btn1.setLayoutX(100);
        btn1.setLayoutY(40);
        btn1.setText("Modifier Departement");
        btn1.setOnAction(e -> ModifierDepartement(primaryStage));
        group1.getChildren().add(btn1);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        
        Button btn2 = new Button();
        btn2.setLayoutX(100);
        btn2.setLayoutY(80);
        btn2.setText("Supprimer Departement");
        btn2.setOnAction(e -> SupprimerDepartement(primaryStage));
        group1.getChildren().add(btn2);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Button btn3 = new Button();
        btn3.setLayoutX(100);
        btn3.setLayoutY(120);
        btn3.setText(" Ajouter Etudiant   ");
         btn3.setOnAction(e -> ajouteretudiant2(primaryStage));
        group1.getChildren().add(btn3);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Button btn4 = new Button();
        btn4.setLayoutX(100);
        btn4.setLayoutY(160);
        btn4.setText(" Supprimer Etudiant   ");
        btn4.setOnAction(e -> supprimerEtudiant(primaryStage));
        group1.getChildren().add(btn4);
        
        primaryStage.setScene(scene1);
        primaryStage.show();
        
         Button btn5 = new Button();
        btn5.setLayoutX(100);
        btn5.setLayoutY(200);
        btn5.setText(" Modifier Etudiant   ");
        btn5.setOnAction(e -> ModifierEtudiant(primaryStage));
        group1.getChildren().add(btn5);
        primaryStage.setScene(scene1);
        primaryStage.show();


       Button btn6 = new Button();
        btn6.setLayoutX(100);
        btn6.setLayoutY(240);
        btn6.setText(" Ajouter Module      ");
        btn6.setOnAction(e -> ajoutermodule(primaryStage));
        group1.getChildren().add(btn6);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Button btn7 = new Button();
        btn7.setLayoutX(100);
        btn7.setLayoutY(280);
        btn7.setText(" Ajouter Enseignant ");
         btn7.setOnAction(e -> ajoutereenseignait(primaryStage));
        group1.getChildren().add(btn7);
        primaryStage.setScene(scene1);
        primaryStage.show();
           
        
        
        Button btn8 = new Button();
        btn8.setLayoutX(100);
        btn8.setLayoutY(320);
        btn8.setText(" Modifier Enseignant ");
         btn8.setOnAction(e -> ModifierEnseignant(primaryStage));
        group1.getChildren().add(btn8);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Button btn9 = new Button();
        btn9.setLayoutX(100);
        btn9.setLayoutY(360);
        btn9.setText(" Supprimer  Enseignant  ");
         btn9.setOnAction(e -> supprimerEnseignant(primaryStage));
        group1.getChildren().add(btn9);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        
        Button btn10 = new Button();
        btn10.setLayoutX(100);
        btn10.setLayoutY(400);
        btn10.setText(" Ajouter Filere      ");
        btn10.setOnAction(e -> ajouterfiliere(primaryStage));
        group1.getChildren().add(btn10);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
         Button btn11 = new Button();
        btn11.setLayoutX(100);
        btn11.setLayoutY(440);
        btn11.setText(" Supprimer Filere       ");
        btn11.setOnAction(e -> SupprimerFiliere(primaryStage));
        group1.getChildren().add(btn11);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Button btn12 = new Button();
        btn12.setLayoutX(100);
        btn12.setLayoutY(480);
        btn12.setText(" Modifier Filere       ");
        btn12.setOnAction(e -> ModifierFiliere(primaryStage));
        group1.getChildren().add(btn12);
        primaryStage.setScene(scene1);
        primaryStage.show();


}
private void ajouterdepartement2(Stage primaryStage) {
        ajouterdepartement2 aj = new ajouterdepartement2();
        aj.start(primaryStage);
    }
private void ajouteretudiant2(Stage primaryStage) {
        ajouteretudiant2 aj = new ajouteretudiant2();
        aj.start(primaryStage);
    }
private void ajoutereenseignait(Stage primaryStage) {
       ajouterenseignant aj = new ajouterenseignant();
        aj.start(primaryStage);
    }
private void ajouterfiliere(Stage primaryStage) {
       ajouterfiliere aj = new ajouterfiliere();
        aj.start(primaryStage);
    }
private void ajoutermodule(Stage primaryStage) {
       ajoutermodule aj = new ajoutermodule();
        aj.start(primaryStage);
    }
private void SupprimerModule(Stage primaryStage) {
       SupprimerModule aj = new SupprimerModule();
        aj.start(primaryStage);
    }
private void supprimerEtudiant(Stage primaryStage) {
       supprimerEtudiant aj = new supprimerEtudiant();
        aj.start(primaryStage);
    }
private void supprimerEnseignant(Stage primaryStage) {
     supprimerEnseignant aj = new supprimerEnseignant();
        aj.start(primaryStage);
    }
private void SupprimerDepartement(Stage primaryStage) {
     SupprimerDepartement aj = new SupprimerDepartement();
        aj.start(primaryStage);
    }
private void SupprimerFiliere(Stage primaryStage) {
     SupprimerFiliere aj = new SupprimerFiliere();
        aj.start(primaryStage);
    }
private void ModifierDepartement(Stage primaryStage) {
     ModifierDepartement aj = new ModifierDepartement();
        aj.start(primaryStage);
    }
private void ModifierEnseignant(Stage primaryStage) {
     ModifierEnseignant aj = new ModifierEnseignant();
        aj.start(primaryStage);
    }
private void ModifierEtudiant(Stage primaryStage) {
    ModifierEtudiant aj = new ModifierEtudiant();
        aj.start(primaryStage);
    }
private void ModifierFiliere(Stage primaryStage) {
    ModifierFiliere aj = new ModifierFiliere();
        aj.start(primaryStage);
    }
private void ModifierModule(Stage primaryStage) {
    ModifierFiliere aj = new ModifierFiliere();
        aj.start(primaryStage);
    }


}
