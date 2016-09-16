/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fourmi;

import Terrain.Case;
import Terrain.Terrain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andriot
 */
public class Fourmi_Reine extends Fourmi_Basique{
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    
    private Signaux_Reine odeur;
    private int quantitéFood;
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    
    public Fourmi_Reine(Terrain terrain_reine,int id)
    {
        odeur = new Signaux_Reine();
        
        this.SetTerrain(terrain_reine);
        this.id=id;
        this.isEclos = true;
        this.quantitéFood = 0;
        
        //couleur et position aléatoire
        this.couleur = this.couleur.getRandom();
        this.position_X = (int)(Math.random()*(this.mon_terrain.GetLargeur()));
        this.position_Y = (int)(Math.random()*(this.mon_terrain.GetLongeur()));
        
        System.out.println(this.couleur+"-Reine : Je suis la reine "+this.couleur);
        
        this.mon_terrain.addFourmilliere(this);
        this.AfficherFourmiReine();
        
        
        this.t = new Thread(this);
        this.t.start();
    }
    
    /***********************************************************************************************/
    /*                                       Destructeur                                           */                       
    /***********************************************************************************************/
    
    public void finalize()
    {
        System.out.println(this.couleur+"-Reine: Je suis morte");
    }
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
     public void ChangeTerrain ( Terrain nouveau_terrain)
    {
        System.out.println(this.couleur+"-Reine : je quitte le terrain :"+this.mon_terrain.GetNom() + "  pour le terrain :"+nouveau_terrain.GetNom());
        this.SetTerrain(nouveau_terrain);
    }
    
    public void pondre()
    {
        int i = 0 , j=0;
        int nb =0;
        for (  i = 0; i < this.mon_terrain.GetParametres().GetNbChef(); i++)
        {
            Fourmi_Chef unchef = new Fourmi_Chef(this,i);
            this.odeur.addObserver(unchef);
           // unchef.AfficherFourmiChef();
            for (  j = 0; j < this.mon_terrain.GetParametres().GetNbSoldat(); j++)
            {
                Fourmi_Soldat unsoldat = new Fourmi_Soldat(unchef,(nb));
                unchef.addSoldat();
                this.odeur.addObserver(unsoldat);
                //unsoldat.AfficherFourmiSoldat();
                nb += 1;
            }
        }
        
        System.out.println(this.couleur+"-Reine : j'ai pondu "+i+" oeufs chef");
        System.out.println(this.couleur+"-Reine : j'ai pondu "+(i*j)+" oeufs soldat");
        
    }
    
    public void supprimerChef(Fourmi_Chef chef)
    {
        this.odeur.deleteObserver(chef);
         //Afficher le nombre d'observeurs restants de ma reine
        this.GetNbObserverChef();
    }
   
    public void supprimerSoldat(Fourmi_Soldat soldat)
    {
        this.odeur.deleteObserver(soldat);
        //Afficher le nombre d'observeurs restants de ma reine
        this.GetNbObserverChef();
    }
    
    public void eclore()
    {
        System.out.println("//envoit signal eclore");
        this.odeur.signal_pheromone("eclore");
    }
    
    public void rentrer()
    {
        System.out.println("//envoit signal rentrer");
        this.odeur.signal_pheromone("rentrer");
    }
    
    public void addNourriture()
    {
        quantitéFood += 1;
        System.out.println(this.couleur+"-Reine : j'ai "+this.quantitéFood + " foods");
    }
    public void AfficherFourmiReine()
    {
        System.out.println("\n-----   Information de la reine ------");
        System.out.println("Id de la reine :"+this.id);
        System.out.println("Couleur de la reine :"+this.couleur);
        System.out.println("Etat de la reine :"+this.isEclos);
        System.out.println("Terrain de la reine :"+this.mon_terrain.GetNom());
        System.out.println("PositionX de la reine :"+this.position_X);
        System.out.println("PositionY de la reine :"+this.position_Y);
        System.out.println("-----------\n");
    }
    
    //Methodes hérités
    @Override
    public void run()
    {
        this.GetNbObserverChef();
        System.out.println("//lancement Thread de la fourmi reine");
        this.pondre();
        this.GetNbObserverChef();
        
        while(this.odeur.countObservers()>0)
        {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Case.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.mon_terrain.affichergrille();
        }
        
        
    }
    
     
    
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public void GetNbObserverChef()
    {
        System.out.println(this.couleur+"-Reine : j'ai " + this.odeur.countObservers() +" observeur(s)");
    }
    
    public Signaux_Reine GetSignal()
    {
        return this.odeur;
    }
    
    public String GetColor()
    {
            return this.couleur.toString();
    }
}
