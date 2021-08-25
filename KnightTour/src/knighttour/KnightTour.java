
package knighttour;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author TOMEU
 */

public class KnightTour extends JFrame  { //Finestra del joc
    
    private static Grid grid;
    private static int N = Grid.DEFAULT_DIMENSION; //Dimensió i coordenades inicials per defecte
    private static int initX = 0;   
    private static int initY = 0;
    private static int firstStep = 1; //representació de la primera passa al tauler
    private static int step = firstStep; //passa actual
    private boolean firstPlaced, calculated = false; //variables de control per evitar sobreescritura de components
    private JButton jbtFollow;
    private JMenuBar jmb;
    private JTextField jtf;
    private JMenu jmbOptions;
    private JMenuItem jmiInitGame,jmiDimension,jmiInitSquare,jmiLockSquare,jmiAutoTour;
    
    
    public KnightTour(int n){  //constructor d'una finestra de n*n 
        super("Ruta del cavaller");
        jmb = new JMenuBar();
        jtf = new JTextField();
                                            //Diferents opcions de joc
        jmbOptions = new JMenu("Opcions");
        jmiInitGame = new JMenuItem("Calcular Tour");
        jmiInitSquare = new JMenuItem("Posició Inici");
        jmiDimension = new JMenuItem("Dimensió");
        jmiLockSquare = new JMenuItem("Bloquejar Casella");
        jmiAutoTour = new JMenuItem("Tour automàtic");
        jmb.add(jmbOptions);            //Afegir opcions al panell
        jmbOptions.add(jmiInitGame);
        jmbOptions.add(jmiInitSquare);
        jmbOptions.add(jmiDimension);
        jmbOptions.add(jmiLockSquare);
        jmbOptions.add(jmiAutoTour);
        jmbOptions.add(jtf);
        
        grid = new Grid(n, initX, initY);   //Instancia del tauler
        grid.setPiece(Piece.CAVALL, initX, initY);

        jbtFollow= new JButton(); //El botó no s'empra, només la tecla return/espai
        getRootPane().setDefaultButton(jbtFollow);//botó per omisió intro
        getContentPane().add(jbtFollow);
        
        jbtFollow.addActionListener(new ActionListener() {//Escoltador d'acció per controlar la tecla return/espai

            public void actionPerformed(ActionEvent evt) { 
                if (step < Math.pow(grid.DIMENSION, 2)-grid.lockedSquares) {
                    grid.proceed(step);
                    step++;
                }
            }
        });
        
        jmiInitGame.addActionListener(new ActionListener(){ //Escoltador d'acció per l'opció de començar el càlcul
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Calculant...Temps d'execució pertanyent a "
                + "theta de 8^n per a n="+(n*n-grid.lockedSquares)+"("+Math.pow(8, n*n-grid.lockedSquares)+" iteracions en el pitjor cas)");
                    
                long init = System.currentTimeMillis();
                
                if (!grid.backTrackKnight(initX, initY, firstStep)) { 
                    System.out.println("La sol·lució no existeix"); 
                } else {
                    System.out.println("Sol·lució trobada!\n");
                    System.out.println(grid.toString());

                    long time_milis = (System.currentTimeMillis() - init);
                    long time_seconds = time_milis/1000;
                    System.out.println("Tiemps d'execució:\t"+(time_seconds/3600)+":"+
                    ((time_seconds/60)-(60*(time_seconds/3600)))+":"+time_seconds+", "+time_milis+"ms");
                    }
                calculated = true;
                }
            });
        
        
        jmiInitSquare.addActionListener(new ActionListener() { //Escoltador d'acció per l'opció d'indicar casella inicial

            @Override
            public void actionPerformed(ActionEvent evt) {
                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {                   //La interfície MouseListener fa necessària la implementació de tots els seus
                        int x = 0, y = 0, i, j = 0;                             //mètodes abstractes. De totes ells agafarem el mouseReleased que és el que l'
                        if (e.getButton() == MouseEvent.BUTTON1&&!firstPlaced&&!calculated) { //mètodes abstractes. De totes ells agafarem el mouseReleased que és el que l'
                            x = e.getX();                                       //escoltador crida quan detecta que s'amolla el botó esquerra del ratolí
                            y = e.getY();
                            for (i = 0; i < grid.DIMENSION && !firstPlaced; i++) {
                                for (j = 0; j < grid.DIMENSION && !firstPlaced; j++) {
                                    firstPlaced = grid.inSquare(i, j, x, y-50); //y-50 per la longitud del jmenubar
                                }
                            }
                            i--;
                            j--;
                            initX = i;
                            initY = j;
                            grid.changeInitPos(initX, initY);
                            grid.removePiece(0, 0);
                            grid.setPiece(Piece.CAVALL, initX, initY);
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
            });
       
            }

        });
        
        jmiDimension.addActionListener(new ActionListener() { //Escoltador d'acció per l'opció d'indicar la dimensió del tauler
            public void actionPerformed(ActionEvent evt) {
                if (!jtf.getText().isEmpty()){
                    int newDim = Integer.parseInt(jtf.getText());
                    if (newDim >=2 && newDim <=10){
                        disposeFrame();
                        KnightTour kt = new KnightTour(newDim);
                        kt.setVisible(true);
                    }
                }                
            }
        });

        jmiLockSquare.addActionListener(new ActionListener() { //Escoltador d'acció sobre escoltador de ratolí per l'opció
                                                                // de bloquetjar una casella
            @Override
            public void actionPerformed(ActionEvent evt) {
                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {   //La interfície MouseListener fa necessària la implementació de tots els seus
                        int x = 0, y = 0, i, j = 0;             //mètodes abstractes. De totes ells agafarem el mouseReleased que és el que l'
                        if (e.getButton() == MouseEvent.BUTTON1) { //mètodes abstractes. De totes ells agafarem el mouseReleased que és el que l'
                            x = e.getX();                           //escoltador crida quan detecta que s'amolla el botó esquerra del ratolí
                            y = e.getY();
                            boolean found = false;
                            for (i = 0; i < grid.DIMENSION && !found; i++) {
                                for (j = 0; j < grid.DIMENSION && !found; j++) {
                                    found = grid.inSquare(i, j, x, y-50); //y-50 per la longitud del jmenubar
                                }
                            }
                            i--;
                            j--;

                            grid.lockSquare(i, j);
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
            });
        }               
        });

        jmiAutoTour.addActionListener(new ActionListener() { //Escoltador d'acció per automatitzar la ruta del cavall

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    grid.repaintFrameZone();
                    autoTour(100); 
                } catch (InterruptedException ex) {
                    Logger.getLogger(KnightTour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
                                            //Afegim les components a la finestra
        this.getContentPane().add(grid);
        this.getContentPane().add(BorderLayout.NORTH, jmb);
        this.setSize(grid.getPreferredSize());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void autoTour(long ms) throws InterruptedException{ //Ruta del cavall automatitzada, transicions en mil·lisegons per paràmetre
        while ((step < Math.pow(grid.DIMENSION, 2)-grid.lockedSquares)&&calculated){
            grid.proceed(step);
            step++;
            Thread.sleep(ms);
        }
    }
    
    private void disposeFrame(){ //Elimina l'objecte de la finestra actual
        this.dispose();
    }
    
    public static void main(String args[]) throws InterruptedException, IOException {
        KnightTour kt = new KnightTour(N);
        kt.setVisible(true);        
    }   
} 


