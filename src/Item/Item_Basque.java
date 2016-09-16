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
public abstract class Item_Basque {
    
    protected int position_X;
    protected int position_Y;
    
    public Item_Basque()
    {
        
    }
    
    public int GetPositionX()
    {
        return this.position_X;
    }
    
    public int GetPositionY()
    {
        return this.position_Y;
    }
}
