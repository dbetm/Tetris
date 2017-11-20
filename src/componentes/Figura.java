
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
    

    public Figura(BufferedImage minoSurface, int[][] coordenadas, Tablero tab) {
        this.minoSurface = minoSurface;
        this.coordenadas = coordenadas;
        this.tab = tab;
        punto = new Dupla(3,0); //para que aparezca en el centro?
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
        if((punto.getX() + desplazamientoX + coordenadas[0].length) <= tab.getANCHO() && (punto.getX() + desplazamientoX) >= 0)
            punto.setX(punto.getX() + desplazamientoX);
        
        if((punto.getY() + 1 + coordenadas.length) <= 20) {
            if(tiempoTranscurrido > velocidadActual) {
                punto.setY(punto.getY() + 1); // y++ de esta manera se desplaza hacia abajo
                tiempoTranscurrido = 0;
            }
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
    
    //Métodos de acceso
    public void setDesplazamientoX(int deltaX) {
        desplazamientoX = deltaX;
    }

    public void setVelocidadActual(int velocidad) {
        this.velocidadActual = velocidad;
    }
}
