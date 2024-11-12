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
    public int lastActiveIndex;
    public int lastDeletedIndex;
    GestorAccesso gestorAcceso = null;
    

    public GestorQuickPass() {
        this.quickpassEnServicio = new Quickpass[10];
        this.quickpassEliminados = new Quickpass[10];
        this.lastActiveIndex = 0;
        this.gestorAcceso = new GestorAccesso();
    }
    
    public void createQuickpass(String filial, String placa) {
        this.quickpassEnServicio[this.lastActiveIndex] = new Quickpass();
        this.quickpassEnServicio[this.lastActiveIndex].setQuickpass(filial, placa);
        String details = "codigo: " + Integer.toString(this.quickpassEnServicio[this.lastActiveIndex].getCodigo()) + ", filial: " + this.quickpassEnServicio[this.lastActiveIndex].getFilial() + ", placa: " + this.quickpassEnServicio[this.lastActiveIndex].getPlaca() + ", estado: " + this.quickpassEnServicio[this.lastActiveIndex].getEstadoString();
        this.gestorAcceso.writeFile("Crear_QuickPass" + " | " + details);
        this.lastActiveIndex += 1;
    }
    
    public void deleteQuickPassCodigo(int codigo){
        // delete codigo quickpass pending
        for(int i = 0; i < lastActiveIndex; i++) {
            if(this.quickpassEnServicio[i].getCodigo() == codigo) {
                this.quickpassEliminados[lastDeletedIndex] = this.quickpassEnServicio[i];
                System.out.println(this.quickpassEliminados[lastDeletedIndex].toString());
                this.quickpassEnServicio[i] = null;
            }
        }
    }
    
    public void deleteQuickPassPlaca(){
        // delete placa quickpass pending
    }
    
    public void getActiveQuickpass(){
        for (int i = 0; i < lastActiveIndex; i++) {
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
