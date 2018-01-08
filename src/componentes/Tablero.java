package componentes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

/**
 *
 * @author dabetm
 */
public class Tablero extends JPanel implements KeyListener {
   //Atributos
   private ControlPrincipal ctrl;
   private Menu partidaActual;
   public static final int TAMANIOBLOQUE = 30;
   public static final int ANCHO = 10;
   public static final int ALTO = 20;
   private BufferedImage bloques;
   private BufferedImage fondo;
   private int [][] plot;
   private Figura[] figuras;
   private Figura figuraActual;
   private Figura siguienteFigura;
   final private Timer timer;
   private int ref;
   
    //Constantes
   private final int FPS = 60;
   private final int RETARDO = 1000/FPS; // apróximadamente 17 milisegundos
   
   //Constructor
   public Tablero(ControlPrincipal ctrl, Menu partidaActual) {
        this.ctrl = ctrl;
        this.partidaActual = partidaActual;
        plot = new int[ALTO][ANCHO]; 
        figuras = new Figura[7]; //para contener las figuras de cada tipo (I, O, Z, S, T, J, L).
        ref = 19;

        try {
            bloques = ImageIO.read(new File("zCuadros.png"));
            fondo = ImageIO.read(new File("zTab.png"));
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
        partidaActual.setPuntos(0);
        partidaActual.guardarScores();
        timer.start();
       
        generarFiguras();
        
        //siguiente figura
        Random r = new Random();
        int index = r.nextInt(7);
        siguienteFigura = new Figura(figuras[index].getMinoSurface(), figuras[index].getCoordenadas(), this, figuras[index].getTipoTextura());
        lanzarSiguienteFigura();     
    }
   
   public void actualizar() {
       figuraActual.actualizar();
   }
   
   @Override
   public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, null);

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
        
        for(int fila = 0; fila < plot.length; fila++) {
            for (int columna = 0; columna < plot[fila].length; columna++) {
                if(plot[fila][columna] != 0) {
                    g.drawImage(bloques.getSubimage((plot[fila][columna]-1)*TAMANIOBLOQUE, 0, TAMANIOBLOQUE, TAMANIOBLOQUE),
                                columna * TAMANIOBLOQUE, fila * TAMANIOBLOQUE, null);
                }
            }
        }
    }

   public int[][] getPlot() {
        return plot;
    }
   
   private void generarFiguras() {
        //Mapeamos las figuras con arreglos, un 1 significa que el bloque va relleno
        int[][] I = { {1,1,1,1} }; //I 
        figuras[0] = new Figura(bloques.getSubimage(0, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), I, this, 1);
       
        int[][] Z = { {1,1,0},
                      {0,1,1} }; //Z
        figuras[1] = new Figura(bloques.getSubimage(TAMANIOBLOQUE, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), Z, this, 2);
       
        int[][] S = { {0,1,1},
                      {1,1,0} }; //S
        figuras[2] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 2, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), S, this, 3);
       
        int[][] T = { {1,1,1},
                      {0,1,0} }; //T
        figuras[3] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 3, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), T, this, 4);
       
        int[][] J = { {1,1,1},
                      {0,0,1} }; //J
        figuras[4] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 4, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), J, this, 5);
       
        int[][] L = { {1,1,1},
                      {1,0,0} }; //L
        figuras[5] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 5, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), L, this, 6);
       
        int[][] O = { {1,1},
                      {1,1} }; //0
        figuras[6] = new Figura(bloques.getSubimage(TAMANIOBLOQUE * 6, 0, TAMANIOBLOQUE, TAMANIOBLOQUE), O, this, 7);
   }
   
    public void lanzarSiguienteFigura() {
        figuraActual = siguienteFigura;
        int index;
        
        Random r = new Random();
        index = r.nextInt(7);
        ctrl.actualizarNext(index);
        System.out.println("La sig: " + index);
        siguienteFigura = new Figura(figuras[index].getMinoSurface(), figuras[index].getCoordenadas(), this, figuras[index].getTipoTextura());
    }
   
    //Métodos de acceso
    public int getTAMANIOBLOQUE() {
        return TAMANIOBLOQUE;
    }
   
    //Implementación de los métodos abstractos de keyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            figuraActual.setDesplazamientoX(-1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            figuraActual.setDesplazamientoX(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            figuraActual.setVelocidadActual(Figura.VELOCIDAD_CAIDA); //al presionar se acelera la caída
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            figuraActual.rotar();
        }
        else if(e.getKeyCode() == KeyEvent.VK_P) {
            if(timer.isRunning()) {
               timer.stop();
               ctrl.mostrarPausa(); 
            }
               
            else {
                timer.restart();
                ctrl.mostrarPausa();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            figuraActual.setVelocidadActual(Figura.VELOCIDAD_NORMAL); //al soltar la tecla se retoma la caída a una velocidad normal
        }
    }
    
    public void comprobarLine() {
        int cont;
        for(int i = 19; i > 0;i--){
            cont = 0;
            for (int j = 0; j < 10; j++) {
                if(plot[i][j] != 0) cont++;
                if(plot[i][j] == 0) break;
            }
            if(cont == 10) {
                eliminarLinea(i);
                i++;
            }
        }
    }
    
    public void eliminarLinea(int fila) {
        for(int j = 0; j < ANCHO; j++) {
           plot[fila][j] = 0; 
        }
        desplazarConjuntoMinos(fila);
        // Incrementa los puntos en la partida actual
        partidaActual.aumentarPuntos();
        partidaActual.aumentarLinea();
        //actualizamos el score
        String puntos = Integer.toString(partidaActual.getPuntos());
        String lineas = Integer.toString(partidaActual.getLineas());
        ctrl.actualizarScore(puntos);
        ctrl.actualizadNoLineas(lineas);
    }
    
    public void desplazarConjuntoMinos(int fila) {
        if(fila == ALTO - 1) fila -= 1;
        for(int i = fila; i >= 0; i--) { //forma vertical
            for(int j = 0; j < ANCHO; j++) { //forma horizontal
                if(plot[i][j] != 0) {
                    plot[i+1][j] = plot[i][j];
                    plot[i][j] = 0;
                }
            }
        }
    }
    
    public void detenerTimer() {
        timer.stop();
        ctrl.mostrarGameOver();
        // Guarda los puntos al terminar una partida
        System.out.println("Puntos de la partida:" + partidaActual.getPuntos());
        partidaActual.guardarScores();
    }
    
    public boolean isGameOver() {
        boolean respuesta = false;
        for(int j = 0; j < ANCHO; j++) {
            if(plot[0][j] != 0) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }
}