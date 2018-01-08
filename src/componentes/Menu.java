package componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author dabetm
 */
public class Menu extends javax.swing.JDialog {
    //Atributos
    private int puntos, lineas = 0;
    private ArrayList<Integer> scores;
    final private ControlPrincipal ctrl;

    public Menu(java.awt.Frame parent, String titulo, boolean modal, ControlPrincipal ctrl) {
        super(parent, modal);
        this.ctrl = ctrl;
        this.setTitle(titulo);
        agregarFondo();
        initComponents();
    }
    
    public void guardarScores() {
        // Si puntos es 0 solo estamos leyendo el archivo y los guardamos en scores
        if(puntos == 0) {
            BufferedReader br = null;
            scores = new ArrayList<Integer>();
            try {
                File scoresfile = new File("scores.dat");
                scoresfile.createNewFile();
                br = new BufferedReader(new FileReader(scoresfile));
                String str;
                while ((str = br.readLine()) != null) {
                    scores.add(Integer.parseInt(str));
                }
                br.close(); //cerramos el BufferedReader
            } catch (IOException e) {
                System.out.println("No se pudo abrir el archivo de scores!");
            }
        } else {
            // Agrega los puntos obtenidos a scores
            scores.add(puntos);
            // Ordena de mayor a menor
            Collections.sort(scores, Collections.reverseOrder()); 

            // Guarda los primeros 5 elementos del ArrayList
            int i = 0;
            try {
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter("scores.dat"));
                outputWriter.write("");
                for(Integer puntos : scores) {
                    System.out.print(puntos + " ");
                    outputWriter.write(Integer.toString(puntos));
                    outputWriter.newLine();
                    i++;
                    if(i > 5) break;
                }
                outputWriter.flush();
                outputWriter.close();   
            } catch (IOException ex) {
                System.out.println("No se guardo el archivo de scores!");
            }
        }
    }
    
    public void aumentarPuntos() {
        Random r = new Random();
        int aumento = r.nextInt(20);
        this.puntos += aumento;
    }
    
    public void aumentarLinea() {
        this.lineas++;
    }
    
    public int getLineas() {
        return lineas;
    }
    

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    private void agregarFondo() {
        try {
            Image fondo = ImageIO.read(new File("zMenu.png"));
            JPanel p = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
        this.setContentPane(p);
            
        } catch (Exception e) {}
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(635, 396));
        setResizable(false);
        setSize(new java.awt.Dimension(635, 396));

        jButton2.setBackground(new java.awt.Color(127, 30, 18));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(254, 254, 254));
        jButton2.setText("START");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(157, 47, 27));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(254, 254, 254));
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(156, 30, 11));
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(254, 254, 254));
        jButton3.setText("MANUAL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ctrl.crearTablero();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ctrl.dispose();
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Manual
        Manual m1 = new Manual(ctrl, true);
        m1.setLocationRelativeTo(this);
        m1.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
