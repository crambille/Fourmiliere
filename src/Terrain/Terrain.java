/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain;

import Fourmi.Fourmi_Reine;
import Fourmi.Fourmi_Soldat;
import Item.Food;
import java.util.ArrayList;

/**
 *
 * @author andriot
 */
public class Terrain {
    
    //Instance unique non préinitialisée 
    private static Terrain INSTANCE = null;
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    private String nom_terrain;
    private Parametre  parametres;
    private int longueur;
    private int largeur;
    private ArrayList <Case> grille; 
    
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    public Terrain ( String nom )
    {
        Parametre pardefault = new Parametre();
        this.parametres = pardefault;
        this.nom_terrain = nom;
        this.largeur = this.parametres.GetLargeur();
        this.longueur = this.parametres.GetLongueur() ;
        
        initialiserGrille();
        Create_Food();
        //afficherListFood();
        
        
        System.out.println("// Création d'un terrain "+ this.nom_terrain+" de dimension : "+ this.longueur +" x " + this.largeur );
    }
            
    public Terrain(String nom,Parametre parametresDonnee)
    {
        parametres = parametresDonnee;
        this.nom_terrain = nom;
        this.largeur = parametres.GetLargeur();
        this.longueur = parametres.GetLongueur() ;
        
        System.out.println("// Création d'un terrain "+ this.nom_terrain+" de dimension : "+ this.longueur +" x " + this.largeur );
    }
    
    
    /***********************************************************************************************/
    /*                                       Destructeur                                           */                       
    /***********************************************************************************************/
    public void finalize()
    {
        System.out.println("//Destruction d'un terrain");
    }
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
    public void initialiserGrille()
    {
        //instancier la liste de case
        grille = new ArrayList<Case>();
        
        int i = 0 ; 
        int j = 0 ;
        //initialisation de la grille
        for ( i = 0; i < this.largeur ;i++)
        {
            for ( j =0; j <this.longueur; j++)
            {
                Case oneCase = new Case(i, j);
                this.grille.add(oneCase);
            }
        }
        System.out.println("// Grille Initialiser "+i+" x "+j);
    }
    
    public void Create_Food()
    {
        for ( int i = 0; i < this.parametres.GetNbFood() ; i++)
        {
            Food unfood = new Food(this.largeur,this.longueur);
            
            //ajouter a la case du terrain
            this.addFood(unfood);
        }
        
    }
    
    public void addFood(Food food)
    {
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == food.GetPositionX() && onecase.GetPositionY()==food.GetPositionY())
            {
                if ( onecase.GetIsFood() == true)
                {
                    Food thefood = onecase.GetFood();
                    thefood.EditAddFood();
                }
                else
                    onecase.PositionFood(food);
            }
        }
    }
    
    public void addFourmilliere(Fourmi_Reine reine)
    {
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == reine.GetPositionX() && onecase.GetPositionY()==reine.GetPositionY())
            {
                onecase.PositionFourmilliere();
            }
        }
    }
    
    public int addSoldat(Fourmi_Soldat soldat)
    {
        int check ;
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == soldat.GetPositionX() && onecase.GetPositionY()==soldat.GetPositionY())
            {
                check= onecase.PositionCombattant(soldat);
                return check;
            }
        }
        return 1;
    }
    
    public void removeSoldat(Fourmi_Soldat soldat)
    {
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == soldat.GetPositionX() && onecase.GetPositionY()==soldat.GetPositionY())
            {
                onecase.DeletePositionCombattant(soldat);
            }
        }
    }
    
    public synchronized  void affichergrille()
    {
        int i = 0;
        System.out.println("\n /////////Grille //////////");
        for(Case onecase :this.grille)
        {
            if ( i == 10)
            {
                System.out.print("*\n");
                i = 0;
            }
            
            if ( i == 0)
                 System.out.print("*");
            
            if(onecase.GetIsFroumilliere() == true)
            {
                System.out.print(" F ");
                i++;
            }
            else if(onecase.GetIsFood()== true)
            {
                if (onecase.GetListeCombattant().size() == 0)
                    System.out.print(" X ");
                else
                    System.out.print(" O ");
                i++;
            }
            else if ( onecase.GetListeCombattant().size() > 0)
            {
                System.out.print(" "+ onecase.GetListeCombattant().size() +" ");
                i++;
            }
            
            else
            {
                System.out.print(" - ");
                i++;
            }
        }
        System.out.print("*\n");
        
        
    }
    
    public void afficherListFood()
    {
        for(Case onecase : this.grille)
        {
            if ( onecase.GetIsFood() == true)
                System.out.print("il y a "+onecase.GetFood().GetNbs()+" in "+onecase.GetPositionX()+"  "+onecase.GetPositionY()+"\n");
        }
    }
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public static Terrain getInstance()
    {
        if (INSTANCE == null)
		{ 	INSTANCE = new Terrain("default");	
		}
		return INSTANCE;
    }
    
    public float GetLargeur()
    {
        return this.largeur;
    }
    
    public float GetLongeur()
    {
        return this.longueur;
    }
    
    public String GetNom()
    {
        return this.nom_terrain;
    }
    
    
    public Parametre GetParametres()
    {
        return this.parametres;
    }
    
    public ArrayList<Case> GetGrille()
    {
        return this.grille;
    }
    
    public Case GetCase(Fourmi_Soldat soldat)
    {
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == soldat.GetPositionX() && onecase.GetPositionY()== soldat.GetPositionY())
            {
                return onecase;
            }
        }
        return null;
    }
    
     public Case GetCasePosition(int x,int y)
    {
        for(Case onecase :this.grille)
        {
            
            if ( onecase.GetPositionX() == x && onecase.GetPositionY()== y)
            {
                return onecase;
            }
        }
        return null;
    }
    
    
}
