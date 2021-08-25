
package knighttour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author TOMEU
 */

public class Grid extends JPanel{ //El tauler és un panell
    
    public static final int DEFAULT_DIMENSION = 8;
    public int DIMENSION;
    public int lockedSquares;
    
    private static final int MAX = 800;
    private static final Color RED = Color.RED;
    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = Color.BLACK;
    private static int SIDE;
    private int i_precedent;
    private int j_precedent;
    private Square t[][];
    

    public Grid(){ //constructor per un tour per defecte
        t = new Square[DEFAULT_DIMENSION][DEFAULT_DIMENSION];
        DIMENSION = DEFAULT_DIMENSION;
        SIDE = MAX / DIMENSION;
        init();
        t[0][0].setValue(0);
        lockedSquares = 0;
        setPiece(Piece.CAVALL, 0, 0);
    }
    
    public Grid(int dimension, int initX, int initY){
        t = new Square[dimension][dimension];
        DIMENSION = dimension;
        SIDE = MAX / DIMENSION;
        i_precedent = initX;
        j_precedent = initY;
        init();
        t[initX][initY].setValue(0);
        lockedSquares = 0;   
    }

    private void init(){ //inicialitza les caselles i les seves components gràfiques
        int y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, SIDE, SIDE);
                Color col;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = WHITE;
                } else {
                    col = BLACK;
                }
                t[i][j] = new Square(r, col, -1, true);
                x += SIDE;
            }
            y += SIDE;
        }      
    }
    
    public boolean backTrackKnight(int x, int y, int k) {//Calcula la ruta del cavall
        
        int p, xi, yi; 
        
        if (k == (DIMENSION * DIMENSION)-lockedSquares) return true;  //Cas bàsic
        
        for (p = 0; p < Coordinate.NMOVES; p++) { 
            xi = x + Coordinate.getX(p); 
            yi = y + Coordinate.getY(p); 
            if (isValid(xi, yi)) { 
                t[xi][yi].setValue(k);
                if (backTrackKnight(xi, yi, k + 1)) return true; 
                else    t[xi][yi].resetCasella(); 
            } 
        } 
        return false; 
    } 

    private boolean isValid(int x, int y) { //comprova que una casella no excedeix el rango i es troba lliure 
        return x >= 0 && x < DIMENSION && y >= 0 && y < DIMENSION && t[x][y].isFree(); //per ser ocupada
    }
    
    public void lockSquare(int i, int j){ //bloquetja una casella per posició, incrementa el contador de
        if (t[i][j].isFree()){                  //caselles bloqueades i pinta la casella de color vermell
            t[i][j].lock();
            lockedSquares++;
            t[i][j].setColor(RED);
            paintImmediately(getRectangle(i,j));
        }
    }
    
    public void proceed(int step){  //Avança una passa una per representar el tour una vegada calculat
        for (int i=0; i<DIMENSION; i++){
            for (int j=0; j<DIMENSION; j++){
                if (t[i][j].getValue() == step){
                    removePrevious(i_precedent, j_precedent);
                    setPiece(Piece.CAVALL, i, j);
                    i_precedent = i;
                    j_precedent = j;
                }
            }
        }
    }
    
    public void changeInitPos(int initX, int initY){ //Canvia la posició inicial a on comença el tour
        i_precedent = initX;
        j_precedent = initY;
        t[0][0].resetCasella();
        t[initX][initY].setValue(0);
    }
    public void repaintFrameZone(){ //repinta la zona de JMenuBar per tal de que no surti mentres fa el tour
        paintImmediately(getRectangle(0,0));
        paintImmediately(getRectangle(0,1));
        paintImmediately(getRectangle(1,0));
        paintImmediately(getRectangle(1,1));
    }
    
    @Override
    public Dimension getPreferredSize() { //Retorna la dimensió per a la finestra
        return new Dimension(MAX, MAX);
    }
    
    public void setPiece(String s, int i, int j) { //Col·loca una peça dins una casella
        t[i][j].setPesa(new Piece(s));
        paintImmediately(getRectangle(i_precedent, j_precedent));
        paintImmediately(getRectangle(i, j));
    }
    
    public void removePrevious(int i, int j){ //lleva la peça de la passa anterior
        t[i][j].deletePesa();
        paintImmediately(getRectangle(i,j));
        t[i][j].setColor(new Color(0f,1f,0f,.5f));   
    }
    
    public void removePiece(int i, int j){ //lleva una peça de la casella sel·leccionada
        t[i][j].deletePesa();
        paintImmediately(getRectangle(i,j));  
    }
    
    public boolean inSquare(int i, int j, int x, int y) { //retorna vertader si les coordenades
        return t[i][j].getRec().contains(x, y);         //pas passades per paràmetre es troben dins el rectangle
    }                                                   //que defineix una casella

    public boolean isFree(int i, int j) { //retorna vertader si la posició es pot ocupar
        return t[i][j].isFree();
    }

    public void setEmpty(int i, int j) { //Desocupa una posició del tauler
        t[i][j].setFree(true);
    }

    public Rectangle getRectangle(int i, int j) { //retorna el rectangle d'una posició
        return t[i][j].getRec().getBounds();
    }
    
    
    @Override
    public void paintComponent(Graphics g) { //pinta el tauler
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public String toString() { //treu per consola el tauler
        String s="";
        for (int i = 0; i < t.length; i++) { 
            for (int j = 0; j < t.length; j++){
                s += t[i][j].getValue() + " ";
            }
            s += "\n";
        }
        return s;
    }
	
}
