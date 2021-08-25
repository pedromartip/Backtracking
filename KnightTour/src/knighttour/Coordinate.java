
package knighttour;

/**
 *
 * @author TOMEU
 */
public final class Coordinate { //Taula de moviments i les funcions per agafar les seves components 'x' o 'y'
    
    public static final int NMOVES = 8;
    
    private static final int[][] MOV = { {2,1},{1,2},{-1,2},{-2,1},
                                            {-2,-1},{-1,-2},{1,-2},{2,-1} };
    
    public static int getX(int pos){
        return MOV[pos][0];
    }
    
    public static int getY(int pos){
        return MOV[pos][1];
    }
    
}
