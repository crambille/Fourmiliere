/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combat;


/**
 *
 * @author andriot
 */
public class Combat <A extends Combattant, B extends Combattant> {
    
    private A combattant1;
    private B combattant2;
    
    
    
    public Combat(A c1, B c2)
    {
        this.combattant1 = c1;
        this.combattant2 = c2;
        
       String s1 = c1.GetColor();
       String s2 = c2.GetColor();
       
       if ( s1.equals(s2) == false)
           lancerCombat();
        
    }
    
    public void lancerCombat()
    {
    
        System.out.println("Fourmi "+this.combattant1.GetColor() +"  contre Fourmi "+this.combattant2.GetColor());
         
        int point1 = 0;
        int point2 = 0;
        
        while(point1 == point2)
        {
            point1 = (int)(Math.random()*(10));
            point2 = (int)(Math.random()*(10));
        }
        
        
        //si le combattant 1 gagne
        if( point1 > point2)
        {
            this.combattant2.PerduCombat();
        }
        //si le combattant 2 gagne
        else
        {
            this.combattant1.PerduCombat();
        }
        
    }
}
