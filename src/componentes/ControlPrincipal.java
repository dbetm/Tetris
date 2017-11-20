/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
         
        tab=new Tablero();
//        tab.setSize(100, 100);
//        tab.setVisible(true);
        this.add(tab);
        this.addKeyListener(tab);
    }
    
    public static void main(String []args) {
        ControlPrincipal contrl= new ControlPrincipal();
        contrl.setVisible(true);
    }
}
