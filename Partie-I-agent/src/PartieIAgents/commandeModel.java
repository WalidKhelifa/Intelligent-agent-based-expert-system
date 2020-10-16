package PartieIAgents;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class commandeModel {
    private int id_commande;
    private String nom_acheteur;
    private String nom_produit;

    public commandeModel(int id_commande, String nom_acheteur, String nom_produit) {
        this.id_commande = id_commande;
        this.nom_acheteur = nom_acheteur;
        this.nom_produit = nom_produit;
    }

    public int getId_commande() {
        return this.id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getNom_acheteur() {
        return this.nom_acheteur;
    }

    public void setNom_acheteur(String nom_acheteur) {
        this.nom_acheteur = nom_acheteur;
    }

    public String getNom_produit() {
        return this.nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }
}
