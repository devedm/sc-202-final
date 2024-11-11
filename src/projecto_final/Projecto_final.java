/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecto_final;

/**
 *
 * @author Eddy Mena
 */
public class Projecto_final {

    /**
     * @param args the command line arguments
     */
    public static GestorQuickPass gestorQuickpass = new GestorQuickPass();;
    
    public static void main(String[] args) {
        gestorQuickpass.createQuickpass("B14", "BRC429");
        gestorQuickpass.getActiveQuickpass();
    }
}