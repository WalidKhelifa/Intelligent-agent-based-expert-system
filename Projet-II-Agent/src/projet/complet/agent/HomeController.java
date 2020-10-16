package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    @FXML
    private JFXComboBox<String> typeUser;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label erreur;

    public HomeController() {
    }

    public void seConnecter(ActionEvent event) throws IOException, SQLException {
        
        try {

            Connection cnx = Main.connection; // se connecter a la BDD ..
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("select password,typeuser from user where username='" + username.getText() + "'");

            //results set
            if (myRs.next() && password.getText().equals(myRs.getString("password")) && typeUser.getValue().equals(myRs.getString("typeuser"))) {
                
                if(typeUser.getValue().equals("Acheteur"))
            {
            Main.stage.close(); 
            Parent parent = (Parent)FXMLLoader.load(this.getClass().getResource("Acheteur.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Main.stage = stage;
            stage.setTitle("Acheteur");   
            }
                if(typeUser.getValue().equals("Vendeur"))
            {
            Main.stage.close(); 
            Parent parent = (Parent)FXMLLoader.load(this.getClass().getResource("Vendeur.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Main.stage = stage;
            stage.setTitle("Vendeur");    
            }
                
            
          
            }else
            {
                erreur();
            }
        } catch (SQLIntegrityConstraintViolationException e) {

        
        
        
        
        }
        
    }

    public void inscrire(ActionEvent event) throws IOException, SQLException {
        this.erreur.setText("");
        this.erreur.setTextFill(Color.web("#cc3333"));
        Alert alert;
        if (!this.vide()) {
            if (this.TestEmail()) {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de l'inscription");
                alert.setContentText("Voulez vraiment confirmer l'inscription ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        Connection cnx = Main.connection;
                        Statement myStmt = cnx.createStatement();
                        myStmt.execute("INSERT INTO `user`(`username`,`email`,`typeuser`, `password`) VALUES ('" + this.user.getText() + "','" + this.email.getText() + "','" + (String)this.type.getValue() + "','" + this.pass.getText() + "');");
                        Parent parent;
                        Scene scene;
                        Stage stage;
                        if (((String)this.type.getValue()).equals("Acheteur")) {
                            parent = (Parent)FXMLLoader.load(this.getClass().getResource("Acheteur.fxml"));
                            scene = new Scene(parent);
                            stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                            stage.setResizable(false);
                            Main.stage = stage;
                            stage.setTitle("Acheteur");
                        } else {
                            parent = (Parent)FXMLLoader.load(this.getClass().getResource("Vendeur.fxml"));
                            scene = new Scene(parent);
                            stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                            stage.setResizable(false);
                            Main.stage = stage;
                            stage.setTitle("Vendeur");
                        }
                    } catch (SQLIntegrityConstraintViolationException var9) {
                        this.erreur.setText("Compte déja existant !");
                    }
                }
            }

            this.clear();
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void clear() throws IOException {
        this.user.clear();
        this.email.clear();
        this.pass.clear();
    }

    public boolean vide() {
        return this.user.getText().isEmpty() || this.email.getText().isEmpty() || this.pass.getText().isEmpty();
    }

    public void erreur() {
        Alert alert;
        if (!this.username.getText().equals("") && !this.password.getText().equals("")) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText((String)null);
            alert.setContentText("Identifiant ou mot de passe incorrect!");
            this.username.setText("");
            this.password.clear();
            alert.show();
        } else {
            this.username.setText("");
            this.password.clear();
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText((String)null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();
        }
        

    }

    public boolean TestEmail() {
        int s = 0;
        String str = this.email.getText();

        for(int i = 1; i < str.length(); ++i) {
            if (str.charAt(i) == '@') {
                ++s;
            }

            if (s == 1 && str.charAt(i) == '.') {
                ++s;
            }

            if (s == 2 && str.charAt(i) == '@') {
                ++s;
            }
        }

        if (s != 2) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText((String)null);
            alert.setContentText("Email incorrect!");
            alert.show();
            return false;
        } else {
            return true;
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.typeUser.getItems().addAll(new String[]{"Acheteur", "Vendeur"});
        this.typeUser.setValue("Acheteur");
        this.type.getItems().addAll(new String[]{"Acheteur", "Vendeur"});
        this.type.setValue("Acheteur");
    }
}
