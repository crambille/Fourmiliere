/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author andriot
 */
public class Food extends Item_Basque{
    
    /***********************************************************************************************/
    /*                                       Attributs                                             */                       
    /***********************************************************************************************/
    private int nbs ;
    
    /***********************************************************************************************/
    /*                                       Constructeur                                          */                       
    /***********************************************************************************************/
    
    public Food(int longueur,int largeur)
    {
        this.position_X = (int)(Math.random()*(largeur));
        this.position_Y = (int)(Math.random()*(longueur));
        this.nbs = 1;
        
        System.out.println("//Creation d'un food en position "+this.position_X  +"  "+this.position_Y);
    }
    
    
    /***********************************************************************************************/
    /*                                       Destructeur                                           */                       
    /***********************************************************************************************/
    public void finalize()
    {
         System.out.println("//Suppression du manger trouvé en postion "+this.position_X  +"  "+this.position_Y);
    }
     
    
    /***********************************************************************************************/
    /*                                        Methodes                                             */                       
    /***********************************************************************************************/
    public void EditAddFood()
    {
        this.nbs += 1;
    }
    
     public void EditDelFood()
    {
        this.nbs -= 1;  
        
    }
     
    /***********************************************************************************************/
    /*                                        Accesseur                                            */                       
    /***********************************************************************************************/
    public int GetNbs()
    {
        return this.nbs;
    }
}
