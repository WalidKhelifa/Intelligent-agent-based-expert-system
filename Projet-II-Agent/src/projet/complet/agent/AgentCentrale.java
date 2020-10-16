package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import jade.core.AID;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentCentrale extends GuiAgent {
    private String domaine;
    private String marque;
    private String version;
    private String taille;
    private String technologie;
    private String ram;
    private String stockage;
    private String batterie;
    private String prix;
    private String model;
    AcheteurController ac;

    public AgentCentrale() {
    }

    protected void setup() {
        Object[] args = this.getArguments();
        this.domaine = (String)args[0];
        this.marque = (String)args[1];
        this.version = (String)args[2];
        this.taille = (String)args[3];
        this.technologie = (String)args[4];
        this.ram = (String)args[5];
        this.stockage = (String)args[6];
        this.batterie = (String)args[7];
        this.prix = (String)args[8];
        this.ac = (AcheteurController)args[9];
        this.ac.setAgc(this);
        if (this.domaine.equals("Smartphone")) {
            
            this.addBehaviour(new OneShotBehaviour() {
                public void action() {
                    Object[] args = new Object[]{AgentCentrale.this.domaine, AgentCentrale.this.marque, AgentCentrale.this.version, AgentCentrale.this.taille, AgentCentrale.this.technologie, AgentCentrale.this.ram, AgentCentrale.this.stockage, AgentCentrale.this.batterie, AgentCentrale.this.prix};
                    ACLMessage acl2 = new ACLMessage();
                    acl2.addReceiver(new AID("AgentVendeurSmartphone", false));

                    try {
                        acl2.setContentObject(args);
                    } catch (IOException var4) {
                        Logger.getLogger(AgentCentrale.class.getName()).log(Level.SEVERE, (String)null, var4);
                    }

                    AgentCentrale.this.send(acl2);
                }
            });
        } else {
            this.addBehaviour(new OneShotBehaviour() {
                public void action() {
                    Object[] args = new Object[]{AgentCentrale.this.domaine, AgentCentrale.this.marque, AgentCentrale.this.version, AgentCentrale.this.taille, AgentCentrale.this.technologie, AgentCentrale.this.ram, AgentCentrale.this.stockage, AgentCentrale.this.batterie, AgentCentrale.this.prix};
                    ACLMessage acl = new ACLMessage();
                    acl.addReceiver(new AID("AgentVendeurLaptop", false));

                    try {
                        acl.setContentObject(args);
                    } catch (IOException var4) {
                        Logger.getLogger(AgentCentrale.class.getName()).log(Level.SEVERE, (String)null, var4);
                    }

                    AgentCentrale.this.send(acl);
                }
            });
        }

        this.addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage msg = AgentCentrale.this.receive();
                if (msg != null) {
                    try {
                        Object[] args = (Object[])msg.getContentObject();
                        AgentCentrale.this.domaine = (String)args[0];
                        AgentCentrale.this.marque = (String)args[1];
                        AgentCentrale.this.version = (String)args[2];
                        AgentCentrale.this.taille = (String)args[3];
                        AgentCentrale.this.technologie = (String)args[4];
                        AgentCentrale.this.ram = (String)args[5];
                        AgentCentrale.this.stockage = (String)args[6];
                        AgentCentrale.this.batterie = (String)args[7];
                        AgentCentrale.this.prix = (String)args[8];
                        AgentCentrale.this.model = (String)args[9];
                        if(!args[9].equals("non"))
                        {
                         AgentCentrale.this.ac.ReceptionMsg(args);   
                        }else
                        {
                         AgentCentrale.this.ac.ReceptionMsg1("7ok");   
                        }
                        
                    } catch (UnreadableException var3) {
                        Logger.getLogger(AgentVendeurSmartphone.class.getName()).log(Level.SEVERE, (String)null, var3);
                    }
                } else {
                    
                    this.block();
                    
                }
                
                

            }
        });
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
