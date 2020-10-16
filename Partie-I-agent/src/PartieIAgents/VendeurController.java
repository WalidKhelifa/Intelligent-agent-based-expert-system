package PartieIAgents;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VendeurController implements Initializable {
    static RuleVarDialog dlg;
    static RuleBase regle;
    static RuleBase regle2;
    static RuleBase laptop;
    static RuleBase currentRuleBase;
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
    Pane ap_regle;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane ap_add_domaine;
    @FXML
    AnchorPane ap_affich_commande;
    @FXML
    AnchorPane ap_delete_regle;
    @FXML
    TextArea textArea;
    @FXML
    TextArea textArea1;
    @FXML
    JFXComboBox<String> nom_pardefault;
    @FXML
    JFXComboBox<String> domaine;
    @FXML
    JFXTextField marque;
    @FXML
    JFXTextField version;
    @FXML
    JFXTextField taille;
    @FXML
    JFXTextField technologie;
    @FXML
    JFXTextField ram;
    @FXML
    JFXTextField stockage;
    @FXML
    JFXTextField batterie;
    @FXML
    JFXTextField prix;
    @FXML
    JFXTextField modele;
    @FXML
    JFXComboBox<String> choisir_regle;
    @FXML
    JFXComboBox<String> choisir_delete_regle;
    @FXML
    JFXTextField nom_regle_add;
    @FXML
    Label l_nom_but;
    @FXML
    Label l_marque;
    @FXML
    Label l_version;
    @FXML
    Label l_technologie;
    @FXML
    Label l_taille_ecran;
    @FXML
    Label l_ram;
    @FXML
    Label l_stockage;
    @FXML
    Label l_batterie;
    @FXML
    Label l_prix;
    @FXML
    TableView<commandeModel> table;
    @FXML
    TableColumn numero_commande;
    @FXML
    TableColumn nom_acheteur;
    @FXML
    TableColumn nom_produit;

    public VendeurController() {
    }

    @FXML
    public void AfficherCommande(ActionEvent event) throws IOException, SQLException {
        this.ap_affich_commande.toFront();
        Connection cnx = Main.connection;
        ArrayList<commandeModel> listP = new ArrayList();
        Statement myStmt = cnx.createStatement();

        try {
            ResultSet myRs = myStmt.executeQuery("SELECT `id_commande`, `nom_acheteur`, `nom_produit` FROM `commande`;");

            while(myRs.next()) {
                commandeModel m = new commandeModel(myRs.getInt("id_commande"), myRs.getString("nom_acheteur"), myRs.getString("nom_produit"));
                listP.add(m);
            }

            ObservableList<commandeModel> listObservable = FXCollections.observableArrayList(listP);
            this.table.setItems(listObservable);
        } catch (SQLException var7) {
        }

    }

    public void addDomaine(ActionEvent event) throws IOException, SQLException {
        this.ap_add_domaine.toFront();
    }

    public void addRegle(ActionEvent event) throws IOException, SQLException {
        this.ap_regle.toFront();
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
        RuleVariable Modele = null;

        ResultSet myRs;
        try {
            Statement myStmt = cnx.createStatement();

            for(myRs = myStmt.executeQuery("SELECT `nom_but`, `valeur_but` FROM `regle` where `nom_domaine` ='" + rb.name + "';"); myRs.next(); this.chi = myRs.getString("valeur_but")) {
                Modele = new RuleVariable(myRs.getString("nom_but"));
                rb.goalClauseStack = new Stack();
                rb.variableList = new Hashtable();
                this.ch = this.ch + myRs.getString("valeur_but") + " ";
                this.modele.setText(myRs.getString("valeur_but"));
            }

            Modele.setLabels(this.ch);
            Modele.setPromptText("le modele de telephone");
            rb.variableList.put(Modele.name, Modele);
        } catch (SQLException var49) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var49);
        }

        Statement myStmt1 = cnx.createStatement();
        myRs = myStmt1.executeQuery("SELECT DISTINCT `nom_clause`FROM `clause` where id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine = '" + rb.name + "'));");
        ArrayList list = new ArrayList();

        while(myRs.next()) {
            list.add((new RuleVariable(myRs.getString("nom_clause"))).name);
        }

        Statement myStmt2 = cnx.createStatement();

        for(ResultSet myRs2 = myStmt2.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(0) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs2.next(); this.chi2 = myRs2.getString("valeur")) {
            this.ch2 = this.ch2 + myRs2.getString("valeur") + " ";
            
        }

        RuleVariable marque = new RuleVariable((String)list.get(0));
        marque.setLabels(this.ch2);
        marque.setPromptText("La marque");
        rb.variableList.put(marque.name, marque);
        Statement myStmt3 = cnx.createStatement();

        for(ResultSet myRs3 = myStmt3.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(1) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs3.next(); this.chi3 = myRs3.getString("valeur")) {
            this.ch3 = this.ch3 + myRs3.getString("valeur") + " ";
            
        }

        RuleVariable version = new RuleVariable((String)list.get(1));
        version.setLabels(this.ch3);
        version.setPromptText("Version du smartphone");
        rb.variableList.put(version.name, version);
        Statement myStmt4 = cnx.createStatement();

        for(ResultSet myRs4 = myStmt4.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(2) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs4.next(); this.chi4 = myRs4.getString("valeur")) {
            this.ch4 = this.ch4 + myRs4.getString("valeur") + " ";
            
        }

        RuleVariable technologie = new RuleVariable((String)list.get(2));
        technologie.setLabels(this.ch4);
        technologie.setPromptText("technologie");
        rb.variableList.put(technologie.name, technologie);
        Statement myStmt5 = cnx.createStatement();

        for(ResultSet myRs5 = myStmt5.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(3) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs5.next(); this.chi5 = myRs5.getString("valeur")) {
            this.ch5 = this.ch5 + myRs5.getString("valeur") + " ";
            
        }

        RuleVariable taille = new RuleVariable((String)list.get(3));
        taille.setLabels(this.ch5);
        taille.setPromptText("taille");
        rb.variableList.put(taille.name, taille);
        Statement myStmt6 = cnx.createStatement();

        for(ResultSet myRs6 = myStmt6.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(4) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs6.next(); this.chi6 = myRs6.getString("valeur")) {
            this.ch6 = this.ch6 + myRs6.getString("valeur") + " ";
            
        }

        RuleVariable Ram = new RuleVariable((String)list.get(4));
        Ram.setLabels(this.ch6);
        Ram.setPromptText("la ram");
        rb.variableList.put(Ram.name, Ram);
        Statement myStmt7 = cnx.createStatement();

        for(ResultSet myRs7 = myStmt7.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(5) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs7.next(); this.chi7 = myRs7.getString("valeur")) {
            this.ch7 = this.ch7 + myRs7.getString("valeur") + " ";
            
        }

        RuleVariable stockage = new RuleVariable((String)list.get(5));
        stockage.setLabels(this.ch7);
        stockage.setPromptText("stockage");
        rb.variableList.put(stockage.name, stockage);
        Statement myStmt8 = cnx.createStatement();

        for(ResultSet myRs8 = myStmt8.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(6) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs8.next(); this.chi8 = myRs8.getString("valeur")) {
            this.ch8 = this.ch8 + myRs8.getString("valeur") + " ";
            
        }

        RuleVariable batterie = new RuleVariable((String)list.get(6));
        batterie.setLabels(this.ch8);
        batterie.setPromptText("batterie");
        rb.variableList.put(batterie.name, batterie);
        Statement myStmt9 = cnx.createStatement();

        for(ResultSet myRs9 = myStmt9.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)list.get(7) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs9.next(); this.chi9 = myRs9.getString("valeur")) {
            this.ch9 = this.ch9 + myRs9.getString("valeur") + " ";
            
        }

        RuleVariable Prix = new RuleVariable((String)list.get(7));
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

            if (list_nom_regle.get(cp_id_regle).equals("") || list_result.get(8).equals("")) {
                    
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText((String)null);
            alert.setContentText("Veillez introduire la marque et le modele !");
            alert.show();
                    
            }

            else {
                 new Rule(rb, (String)list_nom_regle.get(cp_id_regle), new Clause(marque, cEquals, (String)list_result.get(0)), new Clause(version, cEquals, (String)list_result.get(1)), new Clause(technologie, cEquals, (String)list_result.get(2)), new Clause(taille, cEquals, (String)list_result.get(3)), new Clause(Ram, cEquals, (String)list_result.get(4)), new Clause(stockage, cEquals, (String)list_result.get(5)), new Clause(batterie, cEquals, (String)list_result.get(6)), new Clause(Prix, cEquals, (String)list_result.get(7)), new Clause(Modele, cEquals, (String)list_result.get(8)));

            }
            
            
            
            list_result.clear();
            ++cp_nbregle;
        }

    }

    public void press() throws SQLException {
        
        if (((String)this.nom_pardefault.getSelectionModel().getSelectedItem()).equals(this.nom_pardefault.getValue())) {
            this.domaine.setValue((String)this.nom_pardefault.getValue());
            this.marque.clear();
            this.version.clear();
            this.technologie.clear();
            this.taille.clear();
            this.ram.clear();
            this.stockage.clear();
            this.batterie.clear();
            this.prix.clear();
            this.modele.clear();
            this.textArea.clear();
            this.textArea1.clear();
            regle = new RuleBase((String)this.nom_pardefault.getValue());
            this.initRuleBase(regle);
            currentRuleBase = regle;
            currentRuleBase.displayRules(this.textArea);
            currentRuleBase.displayRules(this.textArea1);
            this.choisir_regle.setValue((String)this.nom_pardefault.getValue());
            this.marque.setText("");
            this.version.setText("");
            this.technologie.setText("");
            this.taille.setText("");
            this.ram.setText("");
            this.stockage.setText("");
            this.batterie.setText("");
            this.prix.setText("");
            this.modele.setText("");
        }

    }

    public void press_add() throws SQLException {
        if (((String)this.domaine.getSelectionModel().getSelectedItem()).equals(this.domaine.getValue())) {
            this.nom_pardefault.setValue((String)this.domaine.getValue());
            this.marque.clear();
            this.version.clear();
            this.technologie.clear();
            this.taille.clear();
            this.ram.clear();
            this.stockage.clear();
            this.batterie.clear();
            this.prix.clear();
            this.modele.clear();
            this.textArea.clear();
            this.textArea1.clear();
            regle = new RuleBase((String)this.domaine.getValue());
            this.initRuleBase(regle);
            currentRuleBase = regle;
            currentRuleBase.displayRules(this.textArea);
            currentRuleBase.displayRules(this.textArea1);
            this.choisir_regle.setValue((String)this.domaine.getValue());
            this.marque.setText("");
            this.version.setText("");
            this.technologie.setText("");
            this.taille.setText("");
            this.ram.setText("");
            this.stockage.setText("");
            this.batterie.setText("");
            this.prix.setText("");
            this.modele.setText("");
        }

    }

    public void add_regle() throws SQLException {
        Connection cnx = Main.connection;
        if(!this.nom_regle_add.getText().equals("") && !this.modele.getText().equals(""))
        {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de l'ajout");
        alert.setContentText("Voulez vraiment confirmer l'ajout ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK ) {
            try {
                Statement stmt_add_rg = cnx.createStatement();
                stmt_add_rg.execute("INSERT INTO `regle`( `nom_regle`, `nom_but`, `valeur_but`, `nom_domaine`) VALUES ('" + this.nom_regle_add.getText() + "','" + this.l_nom_but.getText() + "','" + (String)this.modele.getText() + "','" + (String)this.domaine.getValue() + "');");
            } catch (SQLException var9) {
            }

            int id_regle_add = 0;
            Statement Stmt_id_regle = cnx.createStatement();
            ResultSet myRs_id_regle = Stmt_id_regle.executeQuery("SELECT `id_regle` FROM `regle` WHERE nom_regle = '" + this.nom_regle_add.getText() + "' and nom_domaine ='" + (String)this.domaine.getValue() + "';");
            if (myRs_id_regle.next()) {
                id_regle_add = myRs_id_regle.getInt("id_regle");
            }

            try {
                Statement stmt_add_rg = cnx.createStatement();
                stmt_add_rg.execute("INSERT INTO `clause`(`nom_clause`, `valeur`, `id_regle`) VALUES ('" + this.l_marque.getText() + "','" + (String)this.marque.getText()+ "'," + id_regle_add + "),('" + this.l_version.getText() + "','" + (String)this.version.getText() + "'," + id_regle_add + "),('" + this.l_technologie.getText() + "','" + (String)this.technologie.getText() + "'," + id_regle_add + "),('" + this.l_taille_ecran.getText() + "','" + (String)this.taille.getText() + "'," + id_regle_add + "),('" + this.l_ram.getText() + "','" + (String)this.ram.getText() + "'," + id_regle_add + "),('" + this.l_stockage.getText() + "','" + (String)this.stockage.getText() + "'," + id_regle_add + "),('" + this.l_batterie.getText() + "','" + (String)this.batterie.getText() + "'," + id_regle_add + "),('" + this.l_prix.getText() + "','" + (String)this.prix.getText() + "'," + id_regle_add + "),('" + this.l_nom_but.getText() + "','" + (String)this.modele.getText()+ "'," + id_regle_add + ");");
            } catch (SQLException var8) {
            }
        }
        }else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText((String)null);
            alert.setContentText("Veillez introduire le modele et le nom de la regle !");
            alert.show();
        }
        if(!this.nom_regle_add.getText().equals(""))
        {
        this.choisir_delete_regle.getItems().add(this.nom_regle_add.getText());
        }
        this.nom_regle_add.clear();
        this.marque.clear();
        this.version.clear();
        this.technologie.clear();
        this.taille.clear();
        this.ram.clear();
        this.stockage.clear();
        this.batterie.clear();
        this.prix.clear();
        this.modele.clear();
        this.textArea.clear();
        this.textArea1.clear();
        regle = new RuleBase((String)this.domaine.getValue());
        this.initRuleBase(regle);
        currentRuleBase = regle;
        currentRuleBase.displayRules(this.textArea);
        currentRuleBase.displayRules(this.textArea1);
        this.choisir_regle.setValue((String)this.domaine.getValue());
        this.modele.clear();

    }

    public void delete_Regle(ActionEvent event) throws IOException, SQLException {
        this.ap_delete_regle.toFront();
        
    }

    public void press_delete() throws SQLException {
        ArrayList<String> list_delete = new ArrayList();
        this.choisir_delete_regle.getItems().clear();
        if (((String)this.choisir_regle.getSelectionModel().getSelectedItem()).equals(this.choisir_regle.getValue())) {
            Connection cnx = Main.connection;
            Statement Stmt = cnx.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT DISTINCT `nom_regle` FROM `regle` WHERE nom_domaine = '" + (String)this.choisir_regle.getValue() + "' and id_regle in (SELECT id_regle from clause);");

            while(myRs.next()) {
                list_delete.add(myRs.getString("nom_regle"));
            }

            for(int i = 0; i < list_delete.size(); ++i) {
                this.choisir_delete_regle.getItems().add((String)list_delete.get(i));
            }

            this.choisir_delete_regle.setValue((String)list_delete.get(0));
            this.textArea1.clear();
            this.textArea.clear();
            regle = new RuleBase((String)this.choisir_regle.getValue());
            this.initRuleBase(regle);
            currentRuleBase = regle;
            currentRuleBase.displayRules(this.textArea1);
            currentRuleBase.displayRules(this.textArea);
            this.nom_pardefault.setValue((String)this.choisir_regle.getValue());
            this.domaine.setValue((String)this.choisir_regle.getValue());
            list_delete.clear();
            
        }

    }

    public void buttonDelete() throws SQLException {
        Connection cnx = Main.connection;
        Statement myStmt = cnx.createStatement();

        try {
            myStmt.execute("DELETE FROM `regle` WHERE `nom_regle`='" + (String)this.choisir_delete_regle.getValue() + "';");
        } catch (SQLException var4) {
        }

        this.choisir_delete_regle.getItems().remove(this.choisir_delete_regle.getValue());
        this.textArea1.clear();
        this.textArea.clear();
        regle = new RuleBase((String)this.choisir_regle.getValue());
        this.initRuleBase(regle);
        currentRuleBase = regle;
        currentRuleBase.displayRules(this.textArea1);
        currentRuleBase.displayRules(this.textArea);
        this.nom_pardefault.setValue((String)this.choisir_regle.getValue());
        this.domaine.setValue((String)this.choisir_regle.getValue());
        this.modele.setText("");
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.ap_home.toFront();
        Connection cnx = Main.connection;
        ArrayList list_domaine = new ArrayList();

        try {
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT `nom_domaine` FROM `domaine`;");

            while(myRs.next()) {
                list_domaine.add(myRs.getString("nom_domaine"));
            }
        } catch (SQLException var15) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var15);
        }

        for(int i = 0; i < list_domaine.size(); ++i) {
            this.nom_pardefault.getItems().add((String)list_domaine.get(i));
            this.domaine.getItems().add((String)list_domaine.get(i));
            this.choisir_regle.getItems().add((String)list_domaine.get(i));
        }

        this.nom_pardefault.setValue((String)list_domaine.get(0));
        this.domaine.setValue((String)list_domaine.get(0));
        this.choisir_regle.setValue((String)list_domaine.get(0));
        regle = new RuleBase((String)this.nom_pardefault.getValue());

        try {
            this.initRuleBase(regle);
        } catch (SQLException var13) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var13);
        }

        currentRuleBase = regle;
        currentRuleBase.displayRules(this.textArea);
        currentRuleBase.displayRules(this.textArea1);
            this.marque.clear();
            this.version.clear();
            this.technologie.clear();
            this.taille.clear();
            this.ram.clear();
            this.stockage.clear();
            this.batterie.clear();
            this.prix.clear();
            this.modele.clear();
        ArrayList<String> list_delete_regle = new ArrayList();
        Statement Stmt1 = null;

        try {
            Stmt1 = cnx.createStatement();
        } catch (SQLException var12) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var12);
        }

        ResultSet myRs1 = null;

        try {
            myRs1 = Stmt1.executeQuery("SELECT DISTINCT `nom_regle` FROM `regle` WHERE nom_domaine = '" + (String)this.choisir_regle.getValue() + "' and id_regle in (SELECT id_regle from clause);");
        } catch (SQLException var11) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var11);
        }

        try {
            while(myRs1.next()) {
                list_delete_regle.add(myRs1.getString("nom_regle"));
            }
        } catch (SQLException var14) {
            Logger.getLogger(VendeurController.class.getName()).log(Level.SEVERE, (String)null, var14);
        }

        for(int x = 0; x < list_delete_regle.size(); ++x) {
            this.choisir_delete_regle.getItems().add((String)list_delete_regle.get(x));
        }

        this.choisir_delete_regle.setValue((String)list_delete_regle.get(0));
        this.numero_commande.setCellValueFactory(new PropertyValueFactory("id_commande"));
        this.nom_acheteur.setCellValueFactory(new PropertyValueFactory("nom_acheteur"));
        this.nom_produit.setCellValueFactory(new PropertyValueFactory("nom_produit"));
    }
}
