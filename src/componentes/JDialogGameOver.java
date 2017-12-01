
package componentes;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author dabetm
 */
public class JDialogGameOver extends JDialog {

    public JDialogGameOver(Frame propietario, String titulo, boolean modal) {
        super(propietario, titulo, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        System.out.println("Hola mundo");
        cargarComponentes();
    }
    
    public void cargarComponentes() {
        JButton reiniciar = new JButton("Restart");
        reiniciar.setSize(10, 5);
        reiniciar.setVisible(true);
        this.add(reiniciar);
    }
}
