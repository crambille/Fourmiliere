/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain;

/**
 *
 * @author andriot
 */
public class Parametre {
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    private int longueur;
    private int largeur;
    private int nombre_chef ;
    private int nombre_soldat ;
    private int food;
    private int vie;
    
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
   
    //Parametres par default
    public Parametre()
    {
        this.longueur = 10;
        this.largeur = 10;
        this.nombre_chef = 2;
        this.nombre_soldat = 2;
        this.food = 8;
        this.vie = 60;
    }
    
    //Parametres saisi
    public Parametre(int longueur, int largeur,int chef , int soldat,int food,int vie)
    {
        this.longueur = longueur;
        this.largeur = largeur;
        this.nombre_chef = chef;
        this.nombre_soldat = soldat;
        this.food = food;
        this.vie = vie;
    }
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
    
    public void afficherParamatres()
    {
         System.out.println("\n--- Paramaetre par default ---");
         System.out.println("largeur du terrain  "+this.largeur);
         System.out.println("longueur du terrain  "+this.longueur);
         System.out.println("nbs de foods  "+this.food);
         System.out.println("nbs de chefs  "+this.nombre_chef);
         System.out.println("nbs de soldats par chef  "+this.nombre_soldat);
         System.out.println("niveau de vie  "+this.vie);
         System.out.println(" ---\n");
    }
    
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public int GetLongueur()
    {
        return this.longueur;
    }
    
    public int GetLargeur()
    {
        return this.largeur;
    }
    
    public int GetNbChef()
    {
        return this.nombre_chef;
    }
    
    public int GetNbSoldat()
    {
        return this.nombre_soldat;
    }
    
    public int GetNbFood()
    {
        return this.food;
    }
    public int GetNiveauVie()
    {
        return this.vie;
    }
    
    
    
}
