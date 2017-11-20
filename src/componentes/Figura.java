
package componentes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Figura {
    //Atributos
    private BufferedImage minoSurface;
    private int[][] coordenadas;
    Tablero tab; //para poder usar las coordenadas
    private int cambioX = 0;
    Dupla punto;

    public Figura(BufferedImage minoSurface, int[][] coordenadas, Tablero tab) {
        this.minoSurface = minoSurface;
        this.coordenadas = coordenadas;
        this.tab = tab;
        punto = new Dupla(3,0); //para que aparezca en el centro?
    }
    
    public void actualizar() {
        punto.setX(punto.getX() + cambioX);
        cambioX = 0;
    }
    
    public void generar(Graphics g) {
        for(int fila = 0; fila < coordenadas.length; fila++) {
            for(int columna = 0; columna < coordenadas[fila].length; columna++) {
                if(coordenadas[fila][columna] == 1) {
                    g.drawImage(minoSurface, columna * tab.getTAMANIOBLOQUE() + punto.getX() * tab.getTAMANIOBLOQUE(), 
                    fila * tab.getTAMANIOBLOQUE() + punto.getY() * tab.getTAMANIOBLOQUE(), null);
                }
            }
        }
        
    }
    
    //Métodos de acceso

    public void setCambioX(int cambioX) {
        this.cambioX = cambioX;
    }
    
}
