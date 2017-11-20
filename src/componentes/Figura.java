
package componentes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Figura {
    //Atributos
    private BufferedImage minoSurface;
    private int[][] coordenadas;
    private Tablero tab; //para poder usar las coordenadas
    private int desplazamientoX = 0;
    private Dupla punto; //se toma como referencia para pintar las figuras
    public static final int velocidadNormal = 600; //velocidad normal a la que caen las figuras
    public static final int velocidadCaida = 100; //velocidad a la cae una figura al presionar HACIA_ABAJO
    private int velocidadActual; //toma el valor de la velocidad normal o de caida
    private long tiempoTranscurrido; //en milisegundos
    private long referenciaTiempoActual; //en milisegundos
    private boolean llegoAbajo;
    

    public Figura(BufferedImage minoSurface, int[][] coordenadas, Tablero tab) {
        this.minoSurface = minoSurface;
        this.coordenadas = coordenadas;
        this.tab = tab;
        punto = new Dupla(3,0); //para que aparezca en el centro?
        llegoAbajo = false;
        velocidadActual = velocidadNormal;
        tiempoTranscurrido = 0;
        referenciaTiempoActual = System.currentTimeMillis();
        // The java.lang.System.currentTimeMillis() method returns the current time in milliseconds.
        //The unit of time of the return value is a millisecond, the granularity of the value depends on the underlying operating system and may be larger.
    }
   
    public void actualizar() {
        tiempoTranscurrido += System.currentTimeMillis() - referenciaTiempoActual;
        referenciaTiempoActual = System.currentTimeMillis();
        //Gracias al if podemos saber si se efectua la actualización de posición o no, no se hará cuando
        //se salga de los bordes
        if((punto.getX() + desplazamientoX + coordenadas[0].length) <= Tablero.ANCHO && (punto.getX() + desplazamientoX) >= 0)
            punto.setX(punto.getX() + desplazamientoX);
        
        if((punto.getY() + 1 + coordenadas.length) <= Tablero.ALTO) {
            if(tiempoTranscurrido > velocidadActual) {
                punto.setY(punto.getY() + 1); // y++ de esta manera se desplaza hacia abajo
                tiempoTranscurrido = 0;
            }
        }
        else {
            llegoAbajo = true;
        }
        
        desplazamientoX = 0;
    }
    
    public void generar(Graphics g) {
        for(int fila = 0; fila < coordenadas.length; fila++) {
            for(int columna = 0; columna < coordenadas[fila].length; columna++) {
                if(coordenadas[fila][columna] == 1) {
                    g.drawImage(minoSurface, (columna * tab.getTAMANIOBLOQUE()) + (punto.getX() * tab.getTAMANIOBLOQUE()), 
                                             (fila * tab.getTAMANIOBLOQUE() + (punto.getY()) * tab.getTAMANIOBLOQUE()), null);
                }
            }
        }
    }
    
    public void rotar() {
        int nuevasCoordenadas[][];
        nuevasCoordenadas = transponerMatriz(coordenadas);
        nuevasCoordenadas = voltearMatriz(nuevasCoordenadas);
        
        if(punto.getX() + nuevasCoordenadas[0].length <= Tablero.ANCHO && punto.getY() + nuevasCoordenadas.length <= Tablero.ALTO){
            coordenadas = nuevasCoordenadas;
        }
    }
    
    private int[][] transponerMatriz(int[][] matriz) {
        int[][] matrizAux = new int[matriz[0].length][matriz.length]; //new int[columnas][filas]
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                matrizAux[j][i] = matriz[i][j];
            }
        }
        return matrizAux;
    }
    
    private int[][] voltearMatriz(int[][] matriz) {
        int mitad = matriz.length / 2; //se obtiene la fila de la mitad
        for(int i = 0; i < mitad; i++) {
           int[] m = matriz[i]; //guardamos la fila a intercambiar
           //se hace el intercambio
           matriz[i] = matriz[matriz.length - i - 1];
           matriz[matriz.length - i - 1] = m;
        }
        return matriz;
    }
    
    //Métodos de acceso
    public void setDesplazamientoX(int deltaX) {
        desplazamientoX = deltaX;
    }

    public void setVelocidadActual(int velocidad) {
        this.velocidadActual = velocidad;
    }
}
