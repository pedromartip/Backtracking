
package knighttour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author TOMEU
 */
public class Square {
    private Rectangle2D.Float rec;
    private Color color;
    private int value;
    private boolean free;
    private Piece pesa;
    
    public Square(Rectangle2D.Float rec, Color color, int value, boolean free){//El constructor defineix una casella com un rectangle d'un color i si està                                                                         //ocupada o no, el constructor no posa cap peça a la casella
        this.rec = rec;                                                         //i si està ocupada o no. No posa cap peça a la casella
        this.color = color;
        this.value = value;
        this.free = free;
        this.pesa = null;
    }
    
    public Square(int value, boolean free){
        this.value = value;
        this.free = free;
        this.pesa = null;
    }
    
    public void paintComponent(Graphics g){ //Pinta el rectangle de la casella 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.fill(this.rec);
        if (pesa != null){ 
            this.pesa.paintComponent(g, this.rec.x, this.rec.y); //si la casella està ocupada pinta la pesa cridant al mètode de pintar peces
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.free = false;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public Rectangle2D.Float getRec(){
        return rec;
    }
    
    public void setPesa(Piece p){
        this.pesa = p;
    }
    
    public void setFree(boolean free){
        this.free = free;
    }
       
    public boolean isFree(){
        return free;
    }
    
    public void resetCasella(){
        value = -1;
        free = true;
    }
    
    public void lock(){
        free = false;
    }
    
    public void deletePesa(){
        pesa = null;
    }
    
}


