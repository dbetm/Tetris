/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author dabetm
 */
public class Tablero extends JPanel implements KeyListener {
   //Atributos
   private final int TAMANIOBLOQUE = 30;
   private final int ANCHO = 10;
   private final int ALTO = 20;
   private BufferedImage bloques;
   private int [][] plot;
   private Figura[] figuras;
   private Figura figuraActual;
   private Timer timer;
    //Constantes
   private final int FPS = 60;
   private final int RETARDO = 1000/FPS; // apróximadamente 17
   
   //Constructor
   public Tablero() {
       plot = new int[ANCHO][ALTO]; 
       figuras = new Figura[7]; //para contener las figuras de cada tipo (I, O, Z, S, T, J, L).
       
       try {
            bloques = ImageIO.read(new File("cuadros.png"));
        } catch (IOException e) {
            e.printStackTrace(); // printStackTrace( ) Se utiliza para imprimir el registro del stack donde se ha iniciado la excepción.
            e.getMessage(); // getMessage( ) Se usa para obtener un mensaje de error asociado con una excepción. 
        }
       
       timer = new Timer(RETARDO, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               actualizar();
               repaint(); //refrescar la vista cada RETARDO transcurrido
           }
       });
       
       timer.start();
       
        generarFiguras();
        figuraActual = figuras[3];
    }
   
   public void actualizar() {
       figuraActual.actualizar();
   }
   
   @Override
   public void paint(Graphics g) {
       super.paintComponent(g);
       
       //Dibujando las filas
       for(int i = 0; i < ALTO; i++) {
           g.drawLine(0, i * TAMANIOBLOQUE, ANCHO * TAMANIOBLOQUE, i * TAMANIOBLOQUE);
       }
       //Dibujando las columnas
       for(int i = 0; i < ANCHO; i++) {
           g.drawLine(i * TAMANIOBLOQUE, 0, i * TAMANIOBLOQUE, ALTO * TAMANIOBLOQUE);
       }
       
       //generando gráficamente la figura actual
        figuraActual.generar(g);

   }
   
   private void generarFiguras() {
       //Mapeamos las figuras con arreglos, un 1 significa que el bloque va relleno
       int[][] I = { {1,1,1,1} }; //I 
       figuras[0] = new Figura(bloques.getSubimage(0, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), I, this);
       
       int[][] Z = { {1,1,0},
                     {0,1,1} }; //Z
       figuras[1] = new Figura(bloques.getSubimage(TAMANIOBLOQUE, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), Z, this);
       
       int[][] S = { {0,1,1},
                     {1,1,0} }; //S
       figuras[2] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 2, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), S, this);
       
       int[][] T = { {1,1,1},
                     {0,1,0} }; //T
       figuras[3] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 3, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), T, this);
       
       int[][] J = { {1,1,1},
                      {0,0,1} }; //J
       figuras[4] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 4, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), J, this);
       
       int[][] L = { {1,1,1},
                      {1,0,0} }; //L
       figuras[5] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 5, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), L, this);
       
       int[][] O = { {1,1},
                     {1,1} }; //0
       figuras[6] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 6, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), O, this);
   }
   
   
   //Métodos de acceso
   public int getTAMANIOBLOQUE() {
        return TAMANIOBLOQUE;
    }
   
   
    //Métodos abstractos de keyListener
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            figuraActual.setCambioX(-1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            figuraActual.setCambioX(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


