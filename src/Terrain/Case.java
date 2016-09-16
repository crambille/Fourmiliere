/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain;

import Combat.Combattant;
import Fourmi.Fourmi_Soldat;
import Item.Food;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andriot
 */
public class Case implements Combattant {
    
    
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    private boolean isFoourmilliere;
    private int position_X;
    private int position_y;
    private ArrayList<Combattant> listeCombattant;
    private Food aFood;
    private boolean yaCombat;
    
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    public Case(int x , int y)
    {
        this.listeCombattant = new ArrayList<Combattant>();
        this.position_X = x;
        this.position_y = y;
        this.isFoourmilliere = false;
        this.aFood = null;
        this.yaCombat = false;
        
    }
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
  
    public void PositionFourmilliere()
    {
        this.isFoourmilliere = true;
    }
    
    public void PositionFood(Food food)
    {
        this.aFood = food;
    }
     
    public int PositionCombattant(Combattant soldat)
    { 
        while(this.listeCombattant.size()>1)
        {
            return 1;
        }
        synchronized(this.listeCombattant)
        {
            this.listeCombattant.add(soldat);
            return 0;
        }
    }
      
    
    public void DeletePositionCombattant(Combattant soldat)
    {
       synchronized(this.listeCombattant)
       {
            this.listeCombattant.remove(soldat);
       }
    }
    
    public boolean IsFight()
    {
        if (this.listeCombattant.size()==2)
        {
             if ( this.listeCombattant.get(0).GetColor() == this.listeCombattant.get(1).GetColor())
                return false;
            else 
                return true;
        }
        else if (this.listeCombattant.size()>2)
        {
            System.out.println("trop de combattant sur une case");
            return false;
        }    
        else
            return false;
    }
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    
    public int GetPositionX()
    {
        return this.position_X;
    }
    
    public int GetPositionY()
    {
        return this.position_y;
    }
        
    public boolean GetYaCombat()
    {
        return this.yaCombat ;
    } 
    public void SetYaCombat()
    {
        this.yaCombat = true;
    }    
    
    public void SetCombatFini()
    {
        this.yaCombat = false;
    } 

    public ArrayList<Combattant> GetListeCombattant()
    {
        return this.listeCombattant;
    }

    
    public boolean GetIsFroumilliere()
    {
        return this.isFoourmilliere;
    }
    
    public boolean GetIsFood()
    {
        if ( this.aFood == null)
            return false;
        else
            return true;
    }
    
    public Food GetFood()
    {
        return  this.aFood;
    }
    
    public void DeleteFood()
    {
        this.aFood = null;
    }

    @Override
    public void PerduCombat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String GetColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Thread GetHisThread() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
