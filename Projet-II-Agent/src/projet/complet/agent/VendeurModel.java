package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class VendeurModel {
    private String nom_vendeur;
    private String nom_domaine;
    private int nombre_commande;

    public VendeurModel(String nom_vendeur, String nom_domaine, int nombre_commande) {
        this.nom_vendeur = nom_vendeur;
        this.nom_domaine = nom_domaine;
        this.nombre_commande = nombre_commande;
    }

    public String getNom_vendeur() {
        return this.nom_vendeur;
    }

    public void setNom_vendeur(String nom_vendeur) {
        this.nom_vendeur = nom_vendeur;
    }

    public String getNom_domaine() {
        return this.nom_domaine;
    }

    public void setNom_domaine(String nom_domaine) {
        this.nom_domaine = nom_domaine;
    }

    public int getNombre_commande() {
        return this.nombre_commande;
    }

    public void setNombre_commande(int nombre_commande) {
        this.nombre_commande = nombre_commande;
    }
}
