package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentVendeurSmartphone extends GuiAgent {
    private String voiture;
    private String domaine;
    private String marque;
    private String version;
    private String taille;
    private String technologie;
    private String ram;
    private String stockage;
    private String batterie;
    private String prix;
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
    ArrayList<String> list = new ArrayList();
    private String goal;
    AcheteurController ac;

    public AgentVendeurSmartphone() {
    }

    protected void setup() {
        this.addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage msg = AgentVendeurSmartphone.this.receive();
                if (msg != null) {
                    Object[] args;
                    try {
                        args = (Object[])msg.getContentObject();
                        AgentVendeurSmartphone.this.domaine = (String)args[0];
                        AgentVendeurSmartphone.this.marque = (String)args[1];
                        AgentVendeurSmartphone.this.version = (String)args[2];
                        AgentVendeurSmartphone.this.taille = (String)args[3];
                        AgentVendeurSmartphone.this.technologie = (String)args[4];
                        AgentVendeurSmartphone.this.ram = (String)args[5];
                        AgentVendeurSmartphone.this.stockage = (String)args[6];
                        AgentVendeurSmartphone.this.batterie = (String)args[7];
                        AgentVendeurSmartphone.this.prix = (String)args[8];
                    } catch (UnreadableException var6) {
                        Logger.getLogger(AgentVendeurSmartphone.class.getName()).log(Level.SEVERE, (String)null, var6);
                    }

                    AgentVendeurSmartphone.regle = new RuleBase(AgentVendeurSmartphone.this.domaine);

                    try {
                        AgentVendeurSmartphone.this.initRuleBase(AgentVendeurSmartphone.regle);
                    } catch (SQLException var5) {
                        Logger.getLogger(AgentVendeurSmartphone.class.getName()).log(Level.SEVERE, (String)null, var5);
                    }

                    AgentVendeurSmartphone.this.demoVehiclesFC(AgentVendeurSmartphone.regle);

                    try {
                        if (AgentVendeurSmartphone.this.goal == null) {
                            System.out.println("makach");
                        } else {
                            args = new Object[]{AgentVendeurSmartphone.this.domaine, AgentVendeurSmartphone.this.marque, AgentVendeurSmartphone.this.version, AgentVendeurSmartphone.this.taille, AgentVendeurSmartphone.this.technologie, AgentVendeurSmartphone.this.ram, AgentVendeurSmartphone.this.stockage, AgentVendeurSmartphone.this.batterie, AgentVendeurSmartphone.this.prix, AgentVendeurSmartphone.this.goal};
                            ACLMessage reply = msg.createReply();
                            reply.setContentObject(args);
                            AgentVendeurSmartphone.this.send(reply);
                        }
                    } catch (Exception var4) {
                    }
                } else {
                    this.block();
                }

            }
        });
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
        }

        RuleVariable marque = new RuleVariable((String)this.list.get(0));
        marque.setLabels(this.ch2);
        marque.setPromptText("La marque");
        rb.variableList.put(marque.name, marque);
        Statement myStmt3 = cnx.createStatement();

        for(ResultSet myRs3 = myStmt3.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(1) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs3.next(); this.chi3 = myRs3.getString("valeur")) {
            this.ch3 = this.ch3 + myRs3.getString("valeur") + " ";
        }

        RuleVariable version = new RuleVariable((String)this.list.get(1));
        version.setLabels(this.ch3);
        version.setPromptText("Version du smartphone");
        rb.variableList.put(version.name, version);
        Statement myStmt4 = cnx.createStatement();

        for(ResultSet myRs4 = myStmt4.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(2) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs4.next(); this.chi4 = myRs4.getString("valeur")) {
            this.ch4 = this.ch4 + myRs4.getString("valeur") + " ";
        }

        RuleVariable technologie = new RuleVariable((String)this.list.get(2));
        technologie.setLabels(this.ch4);
        technologie.setPromptText("technologie");
        rb.variableList.put(technologie.name, technologie);
        Statement myStmt5 = cnx.createStatement();

        for(ResultSet myRs5 = myStmt5.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(3) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs5.next(); this.chi5 = myRs5.getString("valeur")) {
            this.ch5 = this.ch5 + myRs5.getString("valeur") + " ";
        }

        RuleVariable taille = new RuleVariable((String)this.list.get(3));
        taille.setLabels(this.ch5);
        taille.setPromptText("taille");
        rb.variableList.put(taille.name, taille);
        Statement myStmt6 = cnx.createStatement();

        for(ResultSet myRs6 = myStmt6.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(4) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs6.next(); this.chi6 = myRs6.getString("valeur")) {
            this.ch6 = this.ch6 + myRs6.getString("valeur") + " ";
        }

        RuleVariable Ram = new RuleVariable((String)this.list.get(4));
        Ram.setLabels(this.ch6);
        Ram.setPromptText("la ram");
        rb.variableList.put(Ram.name, Ram);
        Statement myStmt7 = cnx.createStatement();

        for(ResultSet myRs7 = myStmt7.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(5) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs7.next(); this.chi7 = myRs7.getString("valeur")) {
            this.ch7 = this.ch7 + myRs7.getString("valeur") + " ";
        }

        RuleVariable stockage = new RuleVariable((String)this.list.get(5));
        stockage.setLabels(this.ch7);
        stockage.setPromptText("stockage");
        rb.variableList.put(stockage.name, stockage);
        Statement myStmt8 = cnx.createStatement();

        for(ResultSet myRs8 = myStmt8.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(6) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs8.next(); this.chi8 = myRs8.getString("valeur")) {
            this.ch8 = this.ch8 + myRs8.getString("valeur") + " ";
        }

        RuleVariable batterie = new RuleVariable((String)this.list.get(6));
        batterie.setLabels(this.ch8);
        batterie.setPromptText("batterie");
        rb.variableList.put(batterie.name, batterie);
        Statement myStmt9 = cnx.createStatement();

        for(ResultSet myRs9 = myStmt9.executeQuery("SELECT DISTINCT `valeur` FROM `clause` WHERE nom_clause ='" + (String)this.list.get(7) + "' and id_regle in (select id_regle from regle where nom_domaine in(SELECT nom_domaine from domaine where nom_domaine ='" + rb.name + "'));"); myRs9.next(); this.chi9 = myRs9.getString("valeur")) {
            this.ch9 = this.ch9 + myRs9.getString("valeur") + " ";
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

    public void demoVehiclesFC(RuleBase rb) {
        ((RuleVariable)rb.variableList.get(this.list.get(0))).setValue(this.marque);
        ((RuleVariable)rb.variableList.get(this.list.get(1))).setValue(this.version);
        ((RuleVariable)rb.variableList.get(this.list.get(2))).setValue(this.technologie);
        ((RuleVariable)rb.variableList.get(this.list.get(3))).setValue(this.taille);
        ((RuleVariable)rb.variableList.get(this.list.get(4))).setValue(this.ram);
        ((RuleVariable)rb.variableList.get(this.list.get(5))).setValue(this.stockage);
        ((RuleVariable)rb.variableList.get(this.list.get(6))).setValue(this.batterie);
        ((RuleVariable)rb.variableList.get(this.list.get(7))).setValue(this.prix);
        rb.forwardChain();

        try {
            String result = ((RuleVariable)rb.variableList.get(this.list.get(8))).value;
            System.out.println(result);
            if (result == null) {
                this.goal = "non";
            } else {
                this.goal = result;
            }
        } catch (Exception var3) {
        }

    }

    protected void takeDown() {
        super.takeDown();
    }

    public void doMove(Location destination) {
        super.doMove(destination);
    }

    public void onGuiEvent(GuiEvent params) {
    }
}
