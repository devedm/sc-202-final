/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecto_final;

import javax.swing.JOptionPane;

/**
 *
 * @author Eddy Mena
 */
public class Projecto_final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int randInt = (int)(Math.random()*1000000);
        JOptionPane.showMessageDialog(null, args, Integer.toString(randInt));
    }
    
}
