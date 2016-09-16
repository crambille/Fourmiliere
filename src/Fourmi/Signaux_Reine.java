/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fourmi;

import java.util.Observable;

/**
 *
 * @author andriot
 */
public class Signaux_Reine extends Observable{
    
     public void signal_pheromone(String nom_signal)
    {
        if ( nom_signal == "eclore")
        {
            System.out.println("Reine: j'éclos tout mes oeufs");
            this.setChanged();
            this.notifyObservers("eclore");
        }
        
        if ( nom_signal == "rentrer")
        {
            System.out.println("Reine: je fait rentrer toutes les fourmis");
            this.setChanged();
            this.notifyObservers("rentrer");
        }
    }
}
