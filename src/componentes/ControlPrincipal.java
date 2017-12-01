
package componentes;

import javax.swing.JFrame;

/**
 *
 * @author dabetm
 */
public class ControlPrincipal extends JFrame {
    //Atributos
    private Tablero tab;
    
    public ControlPrincipal() {
        this.setTitle("Tetris");
        this.setSize(300, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
         
        tab = new Tablero(this);
//        tab.setSize(100, 100);
//        tab.setVisible(true);
        this.add(tab);
        this.addKeyListener(tab);
    }
    
    public static void main(String []args) {
        ControlPrincipal contrl = new ControlPrincipal();
        contrl.setVisible(true);
    }
    
    public void mostrarGameOver() {
        JDialogGameOver go = new JDialogGameOver(this, "Has pérdido", true);
        go.setSize(300, 200);
        go.setLocationRelativeTo(this);
        go.setVisible(true);
    }
}
