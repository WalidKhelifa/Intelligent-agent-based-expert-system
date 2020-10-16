package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.jfoenix.controls.JFXComboBox;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AcheteurController implements Initializable {
    AgentCentrale agc;
    static RuleBase regle;
    static RuleBase currentRuleBase;
    RuleVariable Modele = null;
    String ch = "";
    String ch2 = "";
    String ch3 = "";
    String ch4 = "";
    String ch5 = "";
    String ch6 = "";
    String ch7 = "";
    String ch8 = "";
    String ch9 = "";
    String chi = "";
    String chi2 = "";
    String chi3 = "";
    String chi4 = "";
    String chi5 = "";
    String chi6 = "";
    String chi7 = "";
    String chi8 = "";
    String chi9 = "";
    @FXML
    Pane ap_commander;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane ap_afficherListeVendeur;
    @FXML
    JFXComboBox<String> modele;
    @FXML
    JFXComboBox<String> domaine;
    @FXML
    JFXComboBox<String> marque;
    @FXML
    JFXComboBox<String> technologie;
    @FXML
    JFXComboBox<String> taille;
    @FXML
    JFXComboBox<String> version;
    @FXML
    JFXComboBox<String> batterie;
    @FXML
    JFXComboBox<String> ram;
    @FXML
    JFXComboBox<String> stockage;
    @FXML
    JFXComboBox<String> prix;
    @FXML
    TextArea textArea;
    @FXML
    TextArea textArea2;
    @FXML
    TableView<VendeurModel> table;
    @FXML
    TableColumn nomVendeur;
    @FXML
    TableColumn nomProduit;
    @FXML
    TableColumn nombreProduitDispo;
    @FXML
    ArrayList<String> list = new ArrayList();

    public AcheteurController() {
    }

    public AgentCentrale getAgc() {
        return this.agc;
    }

    public void setAgc(AgentCentrale agc) {
        this.agc = agc;
    }

    public void Commander(ActionEvent event) throws IOException, SQLException {
        this.ap_commander.toFront();
    }

    public void AfficherResultat(ActionEvent event) throws IOException, SQLException {
        this.ap_afficherListeVendeur.toFront();
        Connection cnx = Main.connection;
        ArrayList<VendeurModel> listP = new ArrayList();
        Statement myStmt = cnx.createStatement();

        try {
            ResultSet myRs = myStmt.executeQuery("SELECT d.`nom_domaine`, `username_vendeur`,COUNT(nom_but)as nb FROM `domaine`d,regle r WHERE d.nom_domaine=r.nom_domaine and r.id_regle in (SELECT id_regle from clause) GROUP BY d.nom_domaine;");

            while(myRs.next()) {
                VendeurModel m = new VendeurModel(myRs.getString("username_vendeur"), myRs.getString("nom_domaine"), myRs.getInt("nb"));
                listP.add(m);
            }

            ObservableList<VendeurModel> listObservable = FXCollections.observableArrayList(listP);
            this.table.setItems(listObservable);
        } catch (SQLException var7) {
        }

    }

    public void Deconnecter() throws IOException {
        Main.stage.close();
        Parent parent = (Parent)FXMLLoader.load(this.getClass().getResource("Home.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
    }

    public void initRuleBase(RuleBase rb) throws SQLException {
        Connection cnx = Main.connection;

        ResultSet myRs;
        try {
            Statement myStmt = cnx.createStatement();

            for(myRs = myStmt.executeQuery("SELECT `nom_but`, `valeur_but` FROM `regle` where `nom_domaine` ='" + rb.name + "';"); myRs.next(); this.chi = myRs.getString("valeur_but")) {
                this.Modele = new RuleVariable(myRs.getString("nom_but"));
                rb.goalClauseStack = new Stack();
                rb.variableList = new Hashtable();
                this.ch = this.ch + myRs.getString("valeur_but") + " ";
            }

            this.Modele.setLabels(this.ch);
            this.Modele.setPromptText("le modele de telephone");
            rb.variableList.put(this.Modele.name, this.Modele);
        } catch (SQLException var47) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var47);
        }

        Statement myStmt1 = cnx.createStatement();
        myRs = myStmt1.executeQuery("SELECT DISTINCT `nom_clause`FROM `clause` where id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine = '" + rb.name + "'));");

        while(myRs.next()) {
            this.list.add((new RuleVariable(myRs.getString("nom_clause"))).name);
        }

        Statement myStmt2 = cnx.createStatement();

        for(ResultSet myRs2 = myStmt2.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(0) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs2.next(); this.chi2 = myRs2.getString("valeur")) {
            this.ch2 = this.ch2 + myRs2.getString("valeur") + " ";
            this.marque.getItems().add(myRs2.getString("valeur"));
        }

        RuleVariable marque = new RuleVariable((String)this.list.get(0));
        marque.setLabels(this.ch2);
        marque.setPromptText("La marque");
        rb.variableList.put(marque.name, marque);
        Statement myStmt3 = cnx.createStatement();

        for(ResultSet myRs3 = myStmt3.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(1) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs3.next(); this.chi3 = myRs3.getString("valeur")) {
            this.ch3 = this.ch3 + myRs3.getString("valeur") + " ";
            this.version.getItems().add(myRs3.getString("valeur"));
        }

        RuleVariable version = new RuleVariable((String)this.list.get(1));
        version.setLabels(this.ch3);
        version.setPromptText("Version du smartphone");
        rb.variableList.put(version.name, version);
        Statement myStmt4 = cnx.createStatement();

        for(ResultSet myRs4 = myStmt4.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(2) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs4.next(); this.chi4 = myRs4.getString("valeur")) {
            this.ch4 = this.ch4 + myRs4.getString("valeur") + " ";
            this.technologie.getItems().add(myRs4.getString("valeur"));
        }

        RuleVariable technologie = new RuleVariable((String)this.list.get(2));
        technologie.setLabels(this.ch4);
        technologie.setPromptText("technologie");
        rb.variableList.put(technologie.name, technologie);
        Statement myStmt5 = cnx.createStatement();

        for(ResultSet myRs5 = myStmt5.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(3) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs5.next(); this.chi5 = myRs5.getString("valeur")) {
            this.ch5 = this.ch5 + myRs5.getString("valeur") + " ";
            this.taille.getItems().add(myRs5.getString("valeur"));
        }

        RuleVariable taille = new RuleVariable((String)this.list.get(3));
        taille.setLabels(this.ch5);
        taille.setPromptText("taille");
        rb.variableList.put(taille.name, taille);
        Statement myStmt6 = cnx.createStatement();

        for(ResultSet myRs6 = myStmt6.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(4) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs6.next(); this.chi6 = myRs6.getString("valeur")) {
            this.ch6 = this.ch6 + myRs6.getString("valeur") + " ";
            this.ram.getItems().add(myRs6.getString("valeur"));
        }

        RuleVariable Ram = new RuleVariable((String)this.list.get(4));
        Ram.setLabels(this.ch6);
        Ram.setPromptText("la ram");
        rb.variableList.put(Ram.name, Ram);
        Statement myStmt7 = cnx.createStatement();

        for(ResultSet myRs7 = myStmt7.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(5) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs7.next(); this.chi7 = myRs7.getString("valeur")) {
            this.ch7 = this.ch7 + myRs7.getString("valeur") + " ";
            this.stockage.getItems().add(myRs7.getString("valeur"));
        }

        RuleVariable stockage = new RuleVariable((String)this.list.get(5));
        stockage.setLabels(this.ch7);
        stockage.setPromptText("stockage");
        rb.variableList.put(stockage.name, stockage);
        Statement myStmt8 = cnx.createStatement();

        for(ResultSet myRs8 = myStmt8.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(6) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs8.next(); this.chi8 = myRs8.getString("valeur")) {
            this.ch8 = this.ch8 + myRs8.getString("valeur") + " ";
            this.batterie.getItems().add(myRs8.getString("valeur"));
        }

        RuleVariable batterie = new RuleVariable((String)this.list.get(6));
        batterie.setLabels(this.ch8);
        batterie.setPromptText("batterie");
        rb.variableList.put(batterie.name, batterie);
        Statement myStmt9 = cnx.createStatement();

        for(ResultSet myRs9 = myStmt9.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(7) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs9.next(); this.chi9 = myRs9.getString("valeur")) {
            this.ch9 = this.ch9 + myRs9.getString("valeur") + " ";
            this.prix.getItems().add(myRs9.getString("valeur"));
        }

        RuleVariable Prix = new RuleVariable((String)this.list.get(7));
        Prix.setLabels(this.ch9);
        Prix.setPromptText("Prix");
        rb.variableList.put(Prix.name, Prix);
        Condition cEquals = new Condition("=");
        new Condition("!=");
        new Condition("<");
        rb.ruleList = new Vector();
        int nb_regle = 0;
        Statement myStmt10 = cnx.createStatement();
        ResultSet myRs10 = myStmt10.executeQuery("SELECT COUNT(*) AS nb_regle from regle where nom_domaine = '" + rb.name + "' and id_regle in (select id_regle from clause);");
        if (myRs10.next()) {
            nb_regle = myRs10.getInt("nb_regle");
        }

        ArrayList<Integer> list_id_regle = new ArrayList();
        ArrayList<String> list_nom_regle = new ArrayList();
        Statement myStmt11 = cnx.createStatement();
        ResultSet myRs11 = myStmt11.executeQuery("SELECT DISTINCT `id_regle`,nom_regle FROM `regle` where nom_domaine = '" + rb.name + "' and id_regle in (select id_regle from clause);");

        while(myRs11.next()) {
            list_id_regle.add(myRs11.getInt("id_regle"));
            list_nom_regle.add(myRs11.getString("nom_regle"));
        }

        int cp_nbregle = 0;

        for(int cp_id_regle = 0; cp_nbregle < nb_regle && cp_id_regle < list_id_regle.size(); ++cp_id_regle) {
            int result = (Integer)list_id_regle.get(cp_id_regle);
            ArrayList<String> list_result = new ArrayList();
            Statement myStmt12 = cnx.createStatement();
            ResultSet myRs12 = myStmt12.executeQuery("SELECT `valeur` FROM `clause` WHERE id_regle =" + result + " and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));");

            while(myRs12.next()) {
                list_result.add(myRs12.getString("valeur"));
            }

            new Rule(rb, (String)list_nom_regle.get(cp_id_regle), new Clause(marque, cEquals, (String)list_result.get(0)), new Clause(version, cEquals, (String)list_result.get(1)), new Clause(technologie, cEquals, (String)list_result.get(2)), new Clause(taille, cEquals, (String)list_result.get(3)), new Clause(Ram, cEquals, (String)list_result.get(4)), new Clause(stockage, cEquals, (String)list_result.get(5)), new Clause(batterie, cEquals, (String)list_result.get(6)), new Clause(Prix, cEquals, (String)list_result.get(7)), new Clause(this.Modele, cEquals, (String)list_result.get(8)));
            list_result.clear();
            ++cp_nbregle;
        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        this.ap_home.toFront();
        Connection cnx = Main.connection;
        ArrayList list_domaine = new ArrayList();

        try {
            Statement myStmt = cnx.createStatement();
            ResultSet myRs_1 = myStmt.executeQuery("SELECT `nom_domaine` FROM `domaine`;");

            while(myRs_1.next()) {
                list_domaine.add(myRs_1.getString("nom_domaine"));
            }
        } catch (SQLException var9) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var9);
        }

        for(int i = 0; i < list_domaine.size(); ++i) {
            this.domaine.getItems().add((String)list_domaine.get(i));
        }

        this.domaine.setValue((String)list_domaine.get(0));
        regle = new RuleBase((String)this.domaine.getValue());

        try {
            this.initRuleBase(regle);
        } catch (SQLException var8) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        this.marque.setValue("");
        this.version.setValue("");
        this.technologie.setValue("");
        this.taille.setValue("");
        this.ram.setValue("");
        this.stockage.setValue("");
        this.batterie.setValue("");
        this.prix.setValue("");
        regle.setDisplay(this.textArea);
        currentRuleBase = regle;
        this.nomVendeur.setCellValueFactory(new PropertyValueFactory("nom_vendeur"));
        this.nomProduit.setCellValueFactory(new PropertyValueFactory("nom_domaine"));
        this.nombreProduitDispo.setCellValueFactory(new PropertyValueFactory("nombre_commande"));
    }

    public void press_button() throws SQLException {
        if (((String)this.domaine.getSelectionModel().getSelectedItem()).equals(this.domaine.getValue())) {
            this.marque.getItems().clear();
            this.version.getItems().clear();
            this.technologie.getItems().clear();
            this.taille.getItems().clear();
            this.ram.getItems().clear();
            this.stockage.getItems().clear();
            this.batterie.getItems().clear();
            this.prix.getItems().clear();
            regle = new RuleBase((String)this.domaine.getValue());
            this.initRuleBase(regle);
            this.marque.setValue(this.chi2);
            this.version.setValue(this.chi3);
            this.technologie.setValue(this.chi4);
            this.taille.setValue(this.chi5);
            this.ram.setValue(this.chi6);
            this.stockage.setValue(this.chi7);
            this.batterie.setValue(this.chi8);
            this.prix.setValue(this.chi9);
            this.textArea.setText("");
            regle.reset();
        }

    }

   

    public void rechercher() {
        this.startContainer();
    }

    public void startContainer() {
        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty("gui", "true");
            ProfileImpl profileImpl = new ProfileImpl(properties);
            AgentContainer mainContainer = runtime.createMainContainer(profileImpl);
            mainContainer.start();
            Object[] args = new Object[]{this.domaine.getValue(), this.marque.getValue(), this.version.getValue(), this.taille.getValue(), this.technologie.getValue(), this.ram.getValue(), this.stockage.getValue(), this.batterie.getValue(), this.prix.getValue(), this};
            AgentController ag1 = mainContainer.createNewAgent("AgentCentrale", "projet.complet.agent.AgentCentrale", args);
            AgentController ag2 = mainContainer.createNewAgent("AgentVendeurLaptop", "projet.complet.agent.AgentVendeurLaptop", (Object[])null);
            AgentController ag3 = mainContainer.createNewAgent("AgentVendeurSmartphone", "projet.complet.agent.AgentVendeurSmartphone", (Object[])null);
            ag1.start();
            ag2.start();
            ag3.start();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
    
     public void AddCommande(ActionEvent event) throws IOException, SQLException {
         Connection cnx = Main.connection;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de l'ajout");
        alert.setContentText("Voulez vraiment confirmer l'ajout ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Statement stmt_add_rg = cnx.createStatement();
                stmt_add_rg.execute("INSERT INTO `commande`(`nom_acheteur`, `nom_produit`) VALUES ('patric','" + domaine.getValue() + "');");
                
            } catch (SQLException var9) {
            }
     
    }
    }

    public void reset() {
        this.textArea2.setText("");
        currentRuleBase.reset();
        this.marque.setValue("");
        this.version.setValue("");
        this.technologie.setValue("");
        this.taille.setValue("");
        this.ram.setValue("");
        this.stockage.setValue("");
        this.batterie.setValue("");
        this.prix.setValue("");
    }

    public void ReceptionMsg(Object[] args) {
        ArrayList<Object> list = new ArrayList();
        
       
        for(int i = 0; i < 9; ++i) {
            list.add(args[i]);
            if(!args[i].toString().equals(""))
            {
            this.textArea2.appendText(args[i].toString() + "\n");
            }
        }

        this.textArea2.appendText("---- Le model est : ----\n");
        this.textArea2.appendText(args[9].toString());
        }
    
    
            public void ReceptionMsg1(String arg) {
        
            
            this.textArea2.appendText("\n\n\n\n\n\n     Le model n'existe pas ");
           
            
            }
        
        
       
    
}
