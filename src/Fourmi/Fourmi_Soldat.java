/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fourmi;


import Combat.Combat;
import Terrain.Case;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import Combat.Combattant;
import java.awt.Color;

/**
 *
 * @author andriot
 */
public class Fourmi_Soldat extends Fourmi_Basique implements Observer, Combattant{
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    private Fourmi_Chef mon_chef;
    private boolean nourriture_trouvee;
    private int etat;//0 rien //1 cherche a manger //retourne a la fourmillière
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
     public Fourmi_Soldat(Fourmi_Chef chef,int id)
    {
        this.mon_chef = chef;
        this.couleur = this.mon_chef.couleur;
        this.mon_terrain = this.mon_chef.mon_terrain;
        this.position_X = this.mon_chef.position_X;
        this.position_Y = this.mon_chef.position_Y;
        this.niveau_vie = this.mon_terrain.GetParametres().GetNiveauVie();
        
        this.isEclos = false;
        this.nourriture_trouvee = false;
        this.id = id;
        this.etat = 0;
        System.out.println(this.couleur+"-Soldat"+this.id+"  :  Je suis un oeuf "+this.couleur);
        
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
            System.out.println(this.couleur+"-Soldat"+this.id+" : je suis éclos");
            this.t = new Thread(this);
            this.t.start();
        }
      
        if ( arg == "rentrer")
        {
            System.out.println(this.couleur+"-Soldat"+this.id+" : je rentre ma reine");
            //this.t.interrupt();
            this.etat = 2;
            this.goHome();
        }
            
    }
    
    public void move()
    {
        //System.out.println("Soldat"+this.id+"  :  Je bouge");
        int x ;
        int y;
        do 
        {
            x = -1 +(int)(Math.random()*(1-(-1)+1));
            y = -1 +(int)(Math.random()*(1-(-1)+1));
        }
        while(this.position_X + x > this.mon_terrain.GetLargeur() || this.position_X + x <0   ||  this.position_Y + y > this.mon_terrain.GetLongeur() || this.position_Y + y <0 || (x == 0 && y == 0) );
        
        //ajouter le soldat a la nouvelle case et la supprimer de l'ancienne
        this.mon_terrain.removeSoldat(this);
        
        
        this.position_X = this.position_X + x;
        this.position_Y = this.position_Y + y;
        
        this.mon_terrain.addSoldat(this);
        
        //perd de la vie
        this.niveau_vie = this.niveau_vie - 1;
        
        //si il a plus de vie
        if ( this.niveau_vie == 0 || this.niveau_vie <0)
        {
            this.mon_terrain.removeSoldat(this);
            System.out.println(this.couleur+"-Soldat"+this.id+"  :  Je suis mort");
            //suprrimer des observers
            this.mon_chef.GetReine().supprimerSoldat(this);
            //signaler à mon chef que je suis mort
            this.mon_chef.removeSoldat();
            //Déreféncer le thread
            this.t.interrupt();
            
        }
            
        
        else
        {
            //Case où est le soldat
            Case position_soldat = this.mon_terrain.GetCase(this);
            
            
            if (position_soldat != null )
            {
                //Regarder si il y a un autre combattant
                if ( position_soldat.IsFight() == true)
                {
                    position_soldat.SetYaCombat();
                    System.out.println("Combat !!");
                    
                    Combat uncombat = new Combat(position_soldat.GetListeCombattant().get(0),position_soldat.GetListeCombattant().get(1));
                    position_soldat.SetCombatFini();
                   
                }
            
                //Regarder si il y a de la nourriture
                else if (position_soldat.GetIsFood() == true)
                {
                    nourriture_trouvee = true;
                    position_soldat.GetFood().EditDelFood();
                    if (position_soldat.GetFood().GetNbs() == 0)
                        position_soldat.DeleteFood();
                }
                    
            }
            
        }
        
    }
    
    
    public void goHome()
    {
        this.mon_terrain.removeSoldat(this);
        
        if ( this.position_X < this.mon_chef.GetReine().position_X)
            this.position_X += 1;
        
        else if (this.position_X > this.mon_chef.GetReine().position_X)
            this.position_X -= 1;
        
        if ( this.position_Y < this.mon_chef.GetReine().position_Y)
            this.position_Y += 1;
        
        else if ( this.position_Y > this.mon_chef.GetReine().position_Y)
            this.position_Y -= 1;
        
        //si il a trouver la fourmillière
        if(this.position_X == this.mon_chef.GetReine().position_X &&  this.position_Y == this.mon_chef.GetReine().position_Y)
        {
            System.out.println(this.couleur+"-Soldat"+this.id +" : j'ai déposé la nourriture à la fourmiliière");
            nourriture_trouvee = false;
            this.mon_chef.addNourriture();
        }
        else
            this.mon_terrain.addSoldat(this);
            
    }
    
            
    public void AfficherFourmiSoldat()
    {
        System.out.println("\n-----   Information du Soldat ------");
        System.out.println("Id du Soldat :"+this.id);
        System.out.println("Couleur du Soldat :"+this.couleur);
        System.out.println("Etat du Soldat :"+this.isEclos);
        System.out.println("Terrain du Soldat :"+this.mon_terrain.GetNom());
        System.out.println("Chef du Soldat :"+this.mon_chef.GetId());
        System.out.println("PositionX du Soldat :"+this.position_X);
        System.out.println("PositionY du Soldat :"+this.position_Y);
        System.out.println("-----------\n");
    }
    
    @Override
    public void run()
    {
        while(!this.t.isInterrupted())
        {  
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("//thread is interrupted ");
            }
            
            
            if(nourriture_trouvee == false)
            {
                if ( this.etat != 1)
                {
                    System.out.println(this.couleur+"-Soldat"+this.id +" : je vais chercher à manger");
                    this.etat = 1;
                }
                this.move();
       
                
            }
                
            else
            {
                if ( this.etat != 2)
                {
                    System.out.println(this.couleur+"-Soldat"+this.id +" : je rentre à la fourmillière");
                     this.etat = 2;
                }
                this.goHome();
               
            }
            
            //this.mon_terrain.affichergrille();
        } 
    }
    
    @Override
    public String GetColor()
    {
            return this.couleur.toString();
    }
    
    @Override
    public void PerduCombat()
    {
        this.niveau_vie = 0;
        //Supprimer de la liste ed combattant
        this.mon_terrain.removeSoldat(this);
        System.out.println(this.couleur+"-Soldat"+this.id +" : je suis mort");
        //suprrimer des observers
        this.mon_chef.GetReine().supprimerSoldat(this);
        //signaler à mon chef que je suis mort
        this.mon_chef.removeSoldat();
        //Déreféncer le thread
        this.t.interrupt();
        
    }

   
}
