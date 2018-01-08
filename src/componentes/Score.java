package componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author dabetm
 */
public class Score extends javax.swing.JDialog {
    //Atributos
    final private ImageIcon i, z, s, t, j, l, o;
  
    public Score(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        agregarFondo();
        
        i = new ImageIcon(getClass().getResource("i.png"));
        z = new ImageIcon(getClass().getResource("z.png"));
        s = new ImageIcon(getClass().getResource("s.png"));
        t = new ImageIcon(getClass().getResource("t.png"));
        j = new ImageIcon(getClass().getResource("j.png"));
        l = new ImageIcon(getClass().getResource("l.png"));
        o = new ImageIcon(getClass().getResource("o.png"));
        
        initComponents();
    }

    private void agregarFondo() {
        try {
            Image fondo = ImageIO.read(new File("f3.png"));
            JPanel p = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
        this.setContentPane(p);
            
        } catch (Exception e) {}
    }
    
    public void actualizarNext(char fig) {
        //System.out.println(fig);
        switch (fig) {
            case 'i':
                next.setIcon(i);
                this.repaint();
                break;
            case 'z':
                next.setIcon(z);
                this.repaint();
                break;
            case 's':                
                next.setIcon(s);
                this.repaint();
                break;
            case 't':
                next.setIcon(t);
                this.repaint();
                break;
            case 'j':
                next.setIcon(j);
                this.repaint();
                break;
            case 'l':
                next.setIcon(l);
                this.repaint();
                break;
            default:
                next.setIcon(o);
                this.repaint();
                break;    
        }
    }
    
    
    public void actualizarScore(String puntaje) {
        score.setText(puntaje);
    }
    
    public void actualizadNoLineas(String lineas) {
        this.lineas.setText(lineas);
    }
    
    public void mostrarPausa() {
        if(pausa.getText().equals(""))
            pausa.setText("Pausado");
        else
            pausa.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        score1 = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        lineas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pausa = new javax.swing.JLabel();

        score1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        score1.setForeground(new java.awt.Color(36, 206, 225));
        score1.setText("0");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 100, 600));
        setModalityType(null);
        setUndecorated(true);
        setResizable(false);

        score.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        score.setForeground(new java.awt.Color(36, 206, 225));
        score.setText("0");

        lineas.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lineas.setForeground(new java.awt.Color(36, 206, 225));
        lineas.setText("0");

        pausa.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        pausa.setForeground(new java.awt.Color(243, 28, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lineas)
                    .addComponent(score)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pausa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138)
                .addComponent(score)
                .addGap(74, 74, 74)
                .addComponent(lineas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(pausa)
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lineas;
    private javax.swing.JLabel next;
    private javax.swing.JLabel pausa;
    private javax.swing.JLabel score;
    private javax.swing.JLabel score1;
    // End of variables declaration//GEN-END:variables
}
