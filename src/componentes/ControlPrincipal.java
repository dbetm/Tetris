
package componentes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControlPrincipal extends JFrame {
    //Atributos
    private Tablero tab;
    private Menu m;
    private Score puntuacion;
    //private JPanel next;
    
    public ControlPrincipal() {
        this.setTitle("Tetris");
        this.setSize(305, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
                
        m = new Menu(this, "Menú", true, this);
        m.setSize(635, 396);
        m.setLocationRelativeTo(this);
        m.setVisible(true);
    }
    
    public static void main(String []args) {
        ControlPrincipal contrl = new ControlPrincipal();
    }
    
    public void mostrarGameOver() {
        JOptionPane.showMessageDialog(null,"Game Over");
        this.dispose();
        puntuacion.dispose();
        ControlPrincipal.main(null);
        
    }
    
    public void crearTablero() { // y genera el diálogo score
        
        puntuacion = new Score(null, false);
        puntuacion.setVisible(true);
        puntuacion.setLocation(870, 110);
        
        this.setVisible(true); //se hace visible el frame ControlPrincipal que contiene al tablero
        tab = new Tablero(this, m);
        this.add(tab);
        this.addKeyListener(tab);

    }
    
    public void actualizarNext(int index) {

        switch (index) {
            case 0:
                puntuacion.actualizarNext('i');
                break;
            case 1:
                puntuacion.actualizarNext('z');
                break;
            case 2:
                puntuacion.actualizarNext('s');
                break;
            case 3:
                puntuacion.actualizarNext('t');
                break;
            case 4:
                puntuacion.actualizarNext('j');
                break;
            case 5:
                puntuacion.actualizarNext('l');
                break;
            default:
                puntuacion.actualizarNext('o');
                break;
        }
    }
    
    public void actualizarScore(String puntaje) {
        puntuacion.actualizarScore(puntaje);
    }
    
    public void actualizadNoLineas(String lineas) {
        puntuacion.actualizadNoLineas(lineas);
    }
    
    public void mostrarPausa() {
        puntuacion.mostrarPausa();
    }
}
