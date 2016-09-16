/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combat;

import java.awt.Color;

/**
 *
 * @author andriot
 */
public interface Combattant {
    
    void PerduCombat();
    String GetColor();
    Thread GetHisThread();
    
}
