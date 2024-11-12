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
    /**
     * This class will manage the creation, deletion, query and storage of Quickpass objects
     */
    
    public Quickpass quickpassEnServicio[];
    public Quickpass quickpassEliminados[];
    GestorAccesso gestorAcceso = null;
    public int arrayLengh;
    

    public GestorQuickPass() {
        this.arrayLengh = 10;
        this.quickpassEnServicio = new Quickpass[arrayLengh];
        this.quickpassEliminados = new Quickpass[arrayLengh];
        this.gestorAcceso = new GestorAccesso();
    }
    
    // Methods 
    public int nullIndexFinder( Quickpass aquickpass[], boolean isNull ){
        int index = -1;
        for (int i = 0; i < aquickpass.length ; i++) {
            if (isNull) {
                if(aquickpass[i] == null){
                    index = i;
                }
            } else {
                if(aquickpass[i] != null){
                    index = i;
                }
            }
        }
        return (index != -1) ? index:null;
    }
    
    // ----- SETTERS -----
    // Create Quickpass
    public void createQuickpass(int codigo, String filial, String placa) {
        int availableIndex = nullIndexFinder(quickpassEnServicio,true);
        this.quickpassEnServicio[availableIndex] = new Quickpass();
        this.quickpassEnServicio[availableIndex].setQuickpass(codigo,filial, placa);
        String details = this.quickpassEnServicio[availableIndex].getStringQuickpass(true);
        this.gestorAcceso.writeFile("Crear_QuickPass" + " | " + details);
    }
    
    // Delete by codigo Quickpass
    public void deleteQuickPassCodigo(int codigo){
        int availableIndex = nullIndexFinder(quickpassEliminados,true);
        for(int i = 0; i < quickpassEnServicio.length; i++) {
            if(this.quickpassEnServicio[i] != null && this.quickpassEnServicio[i].getCodigo() == codigo) {
                this.quickpassEliminados[availableIndex] = this.quickpassEnServicio[i];
                System.out.println(this.quickpassEliminados[availableIndex].toString());
                this.quickpassEnServicio[i] = null;
            }
        }
    }
    
    // Delete by placa Quickpass
    public void deleteQuickPassPlaca(String placa){
        int availableIndex = nullIndexFinder(quickpassEliminados,true);
        for(int i = 0; i < quickpassEnServicio.length; i++) {
            if(this.quickpassEnServicio[i] != null && this.quickpassEnServicio[i].getPlaca()== placa) {
                this.quickpassEliminados[availableIndex] = this.quickpassEnServicio[i];
                System.out.println(this.quickpassEliminados[availableIndex].toString());
                this.quickpassEnServicio[i] = null;
            }
        }
    }
    // ----- GETTERS -----
    // Get Active quickpass from quickpassEnServicio array
    public void getActiveQuickpass(){
        for (int i = 0; i < quickpassEnServicio.length; i++) {
            if (this.quickpassEnServicio[i] != null && this.quickpassEnServicio[i].estado == Quickpass.Estados.ACTIVO) {
                System.out.println(this.quickpassEnServicio[i].getStringQuickpass(false));
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
