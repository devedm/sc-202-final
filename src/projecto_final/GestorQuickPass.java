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
    public Quickpass quickpassEliminados[];
    public int lastIndex;

    public GestorQuickPass() {
        this.quickpassEnServicio = new Quickpass[10];
        this.quickpassEliminados = new Quickpass[10];
        this.lastIndex = 0;
    }
    
    public void createQuickpass(String filial, String placa) {
        this.quickpassEnServicio[this.lastIndex] = new Quickpass();
        this.quickpassEnServicio[this.lastIndex].setQuickpass(filial, placa);
        this.lastIndex += 1;
    }
    
    public void deleteQuickPassCodigo(){
        // delete codigo quickpass pending
    }
    
    public void deleteQuickPassPlaca(){
        // delete placa quickpass pending
    }
    
    public void getActiveQuickpass(){
        for (int i = 0; i < lastIndex; i++) {
            if (this.quickpassEnServicio[i].estado == Quickpass.Estados.ACTIVO) {
                System.out.println(Integer.toString(this.quickpassEnServicio[i].getCodigo()));
                System.out.println(this.quickpassEnServicio[i].getFilial());
                System.out.println(this.quickpassEnServicio[i].getPlaca());
            }
        }
    }
    
    public void getAllFilialQuickpass(){
        // pending visualize ALL filial quickpass
    }
    
    public void getFilialQuickpass(){
        // pending visualize ONE filial quickpass
    }
    
    public void getCodigoActiveQuickpass(){
        // pending visualize ONE codigo quickpass
    }
    
    public void getCodigoInactiveQuickpass(){
        // pending visualize ONE codigo quickpass
    }
    
    public void getPlacaQuickpass(){
        // pending visualize ONE Placa quickpass
    }
    
    
    
}
