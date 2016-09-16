/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fourmi;

import Terrain.Terrain;
import simulation.RunInterface;

/**
 *
 * @author andriot
 */
public abstract class Fourmi_Basique implements  RunInterface{
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    
    protected Thread t;
    protected  Terrain mon_terrain;
    protected boolean vie;
    protected int id;
    protected boolean isEclos;
    protected int position_X;
    protected int position_Y;
    protected int niveau_vie;
    protected couleur couleur;
    
    
    protected enum couleur{ 
        WHITE,
        BLACK,
        RED,
        YELLOW,
        BLUE;
    
        public static couleur getRandom() 
        {
            return values()[(int) (Math.random() * values().length)];
        }
    };
      
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    public Fourmi_Basique()
    {
        //this.niveau_vie = 1000;
        //System.out.println("// Création d'une fourmie ");
    }
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
    //Méthodes
    public void SetTerrain(Terrain nouveau_terrain)
    {
        mon_terrain= nouveau_terrain;
    }
    
    
    //Méthodes abstraites 
    public abstract void run();
    
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public int GetId()
    {
        return this.id;
    }
    
    public int GetPositionX()
    {
        return this.position_X;
    }
    
    public int GetPositionY()
    {
        return this.position_Y;
    }
    
    public Terrain GetTerrain()
    {
        return this.mon_terrain;
    }
    
    public Thread GetHisThread()
    {
        return this.t;
    }
}
