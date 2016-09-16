/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Combat.Combattant;
import Fourmi.Fourmi_Reine;
import Terrain.Terrain;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author andriot
 */
public class Main extends Applet  implements Runnable {

    private Image   fourmi1;  //images
    private Image   fourmi2;  //images
    private Image   terrain;  //images
    private Image   nourriture;  //images
    private Image   fourmilliere;  //images
    private Thread  thread;   //thread de controle
    private int xmax;
    private int ymax;
    private Terrain mon_terrain;
    private Fourmi.Fourmi_Reine  reine;
    private Fourmi.Fourmi_Reine  reine2;
    private int proportion;
    
    /* ************************************************ */
    /* Tout ce qui est nécessaire au double buffering 
    * pris sur : http://www.developer.com/repository/softwaredev/content/article/2000/06/20/SDtravisdblbuf/DoubleBufferApplet.java */
    /* ************************************************ */
    private int width=-1;
    private int height=-1;
    // The offscreen image
    private Image offscreen;

    // switch: are we double buffering or not?
    private boolean dbon = true;

    // Use this to turn double buffering on and off
    protected void setDoubleBuffering( boolean dbon ) {
    this.dbon = dbon;
    if (!dbon) {
    offscreen = null;
    }
    }

    // Depending on the value of our switch, we either call our
    // special code, or just call the default code
    public void update( Graphics g ) {
    if (dbon) {
    updateDoubleBufffered( g );
    } else { 
    super.update( g );
    }
    }

    // Do the drawing to an offscreen buffer -- maybe
    private void updateDoubleBufffered( Graphics g ) {

    // Let's make sure we have an offscreen buffer, and that
    // it's the right size.  If the applet has been resized,
    // our buffer will be the wrong size and we need to make
    // a new one
    Dimension d = getSize();
    if (offscreen == null ||
    width!=d.width || height!=d.height || offscreen==null) {
    width = d.width;
    height = d.height;
    if (width>0 || height>0) {
    offscreen = createImage( width, height );
    } else offscreen = null;
    }

    // If we still don't have one, give up
    if (offscreen == null) return;

    // Get the off-screen graphics object
    Graphics gg = offscreen.getGraphics();

    // Clear the off-screen graphics object
    gg.setColor( getBackground() );
    gg.fillRect( 0, 0, width, height );
    gg.setColor( getForeground() );

    // Draw to the off-screen graphics object
    paint( gg );

    // We don't need this Graphics object anymore
    gg.dispose();

    // Finally, we transfer the newly-drawn stuff right to the
    // screen
    g.drawImage( offscreen, 0, 0, null );
    }

    /********************Jpanel Button******/
    ActionListener a = new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent ae) {
           JButton JB = (JButton) ae.getSource();
           if("Reine1 : Eclore".equals(JB.getText())){
               reine.eclore();
           };
           if("Reine2 : Eclore".equals(JB.getText())){
               reine2.eclore();
           };
    
           if("Reine1 : rentrer".equals(JB.getText())){
               reine.rentrer();
           };
            if("Reine2 : rentrer".equals(JB.getText())){
               reine2.rentrer();
                       }}};
    
    
    public void start() 
    {
        //n�cessaire pour d�marrer l'animation
        if (thread==null){
          thread=new Thread(this);
          thread.start();
        }
    }
    
     public void init() 
    {
        
        proportion =80;
        
        /******Button*/////
        this.setLayout(new BorderLayout());
       Panel JP = new Panel();
       JButton JBEclore1 = new JButton("Reine1 : Eclore");
       JButton JBEclore2 = new JButton("Reine2 : Eclore");
       //JButton JBRentrer1 = new JButton("Reine1 : rentrer");
       //JButton JBRentrer2 = new JButton("Reine2 : rentrer");
       JBEclore1.addActionListener(a);
       JBEclore2.addActionListener(a);
       //JBRentrer1.addActionListener(a);
       //JBRentrer2.addActionListener(a);
       this.setSize(new Dimension(800, 800));
       JP.add(JBEclore1);
       JP.add(JBEclore2);
       //JP.add(JBRentrer1);
       //JP.add(JBRentrer2);
       JP.setVisible(true);
       this.add(JP, BorderLayout.NORTH);
       
       
        // initialisations, appel�e automatiquement
        fourmi1=getImage(getCodeBase(),"ressources//f1.png");
        fourmi2=getImage(getCodeBase(),"ressources//f2.png");
        terrain=getImage(getCodeBase(),"ressources//herbe_10.jpg");
        nourriture=getImage(getCodeBase(),"ressources//bigmac.png");
        fourmilliere=getImage(getCodeBase(),"ressources//fourmielliere2.jpg");
              
        //creation d'une reine
        mon_terrain = new Terrain("plage");
        
        xmax = (int) mon_terrain.GetLargeur();
        ymax = (int) mon_terrain.GetLongeur();
        
        reine = new Fourmi_Reine(mon_terrain, 0);
        reine2 = new Fourmi_Reine(mon_terrain, 1);
        
        
        try 
        {
            Thread.sleep(500);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
		  
    public  void  paint(Graphics g) 
    {
        //dessiner le terrain
        g.drawImage(terrain,0,0,xmax*proportion,ymax*proportion,this);
        
        //dessiner la fourmilliere
        g.drawImage(fourmilliere,reine.GetPositionX()*proportion, reine.GetPositionY()*proportion,proportion,proportion,this);
        
        g.drawImage(fourmilliere,reine2.GetPositionX()*proportion, reine2.GetPositionY()*proportion,proportion,proportion,this);
         
         //dessiner les soldats et la bouffes
        for(int i = 0; i<xmax; i++)
        {
            for(int j = 0; j<ymax; j++)
            {   
                //dessiner bouffe
                if (mon_terrain.GetCasePosition(i, j).GetIsFood() == true)
                {
                     g.drawImage(nourriture,i*proportion,j*proportion,proportion,proportion,this);
                }
                
                //dessiner foumri
                if (mon_terrain.GetCasePosition(i, j).GetListeCombattant().size()==1)
                {
                    for ( Combattant liste_combattant : mon_terrain.GetCasePosition(i, j).GetListeCombattant()) 
                    {
                        if (liste_combattant.GetColor() == reine.GetColor() )
                            g.drawImage(fourmi1,i*proportion,j*proportion,proportion,proportion,this);
                        else
                            g.drawImage(fourmi2,i*proportion,j*proportion,proportion,proportion,this);

                    }
                    
                }
                else if (mon_terrain.GetCasePosition(i, j).GetListeCombattant().size()>2)
                {
                    int u = 0;
                    int v = 0;
                    
                    for ( Combattant liste_combattant : mon_terrain.GetCasePosition(i, j).GetListeCombattant()) 
                    {
                        if (liste_combattant.GetColor() == reine.GetColor() )
                            u+=1;
                        else
                            v+=1;

                    }
                    
                    if (u>0)
                        g.drawImage(fourmi1,i*proportion,j*proportion,proportion,proportion,this);
                    if(v>0)
                        g.drawImage(fourmi2,i*proportion,j*proportion,proportion,proportion,this);
                }
               
            }
        }
    }

    @Override
    public void run() {
        
        while(true)
        {
            try 
            {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
             
          repaint();
        }
    }
    
}
