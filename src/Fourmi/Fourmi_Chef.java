/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fourmi;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author andriot
 */
public class Fourmi_Chef  extends Fourmi_Basique  implements Observer{
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    
    private Fourmi_Reine ma_reine;
    private int nbsSoldatsVivants;
    private int nbsSoldatsMorts;
    private int nbNourriture;
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    
    public Fourmi_Chef(Fourmi_Reine reine, int id)
    {
        this.ma_reine = reine;
        this.couleur = this.ma_reine.couleur;
        this.mon_terrain = this.ma_reine.mon_terrain;
        this.position_X = this.ma_reine.position_X;
        this.position_Y = this.ma_reine.position_Y;
        
        this.isEclos = false;
        this.id = id;
        this.nbsSoldatsMorts = 0;
        System.out.println(this.couleur+"-Chef"+this.id+"  :  Je suis un oeuf "+this.couleur);
       
        
    }
 
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
    //Recoit un message pour eclore
    public void update(Observable o, Object arg)
    {
        if ( arg == "eclore")
        {
            this.isEclos = true;
            System.out.println(this.couleur+"-Chef"+this.id+" : je suis éclos");
            this.t = new Thread(this);
            this.t.start();
        }
    }
    
    public void addSoldat()
    {
        this.nbsSoldatsVivants += 1;
    }
           
     public void removeSoldat()
    {
        this.nbsSoldatsVivants -= 1;
        this.nbsSoldatsMorts += 1;
        System.out.println(this.couleur+"-Chef"+this.id+" : j'ai perdu un soldat, il m'en reste "+this.nbsSoldatsVivants);
        if ( this.nbsSoldatsVivants == 0)
        {
            this.ma_reine.supprimerChef(this);
        }
    }
     
    public void addNourriture()
    {
        nbNourriture += 1;
        this.ma_reine.addNourriture();
        System.out.println(this.couleur+"-Chef"+this.id+" : j'ai "+this.nbNourriture + " foods");
    }
     
    public void AfficherFourmiChef()
    {
        System.out.println("\n-----   Information du chef ------");
        System.out.println("Id du chef :"+this.id);
        System.out.println("Couleur du chef :"+this.couleur);
        System.out.println("Etat du chef :"+this.isEclos);
        System.out.println("Terrain du chef :"+this.mon_terrain.GetNom());
        System.out.println("Reine du chef :"+this.ma_reine.GetId());
        System.out.println("PositionX du chef :"+this.position_X);
        System.out.println("PositionY du chef :"+this.position_Y);
        System.out.println("-----------\n");
    }
    
    //Methodes 
    @Override
    public void run()
    {
        while(!this.t.isInterrupted())
        { 
            //Verifier si j'ai des soldats
            if ( this.nbsSoldatsVivants == 0)
            {
                System.out.println(this.couleur+"-Chef"+this.id+" : je suis mort, j'avais "+this.nbNourriture +" de foods");
                this.ma_reine.supprimerChef(this);
                this.t.interrupt();
            }
        }
    }
    
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public Fourmi_Reine GetReine()
    {
        return this.ma_reine;
    }
}
