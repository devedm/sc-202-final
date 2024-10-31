/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

/**
 *
 * @author minio
 */
public class GestorQuickPass {
    
    public Quickpass quickpassEnServicio[];
    public Object quickpassEliminados[];

    public GestorQuickPass() {
        this.quickpassEnServicio = new Quickpass[10];
    }
    
    
    
    public void createQuickpass(String filial, String placa) {
        int index = this.quickpassEnServicio.length;
        quickpassEliminados[index] = new Quickpass(filial, placa);
    }
    
    public void getActiveQuickpass(){
        for (Quickpass quickpass : quickpassEnServicio) {
            if (quickpass.estado == Quickpass.Estados.ACTIVO) {
                System.out.println(Integer.toString(quickpass.getCodigo()));
                System.out.println(quickpass.getFilial());
                System.out.println(quickpass.getPlaca());
            }
        }
    }
}
