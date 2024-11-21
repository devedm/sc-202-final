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
    /*
        Esta funcion recibe 2 parametros, uno de lista de objetos quickpass y 
        el otro un booleano que decide el resultado, va a realizar una busqueda 
        en la lista quickpass seleccionada y retornar si tiene o no valores null.

        Parameter -> 
            Quickpass Array[] aquickpass: recibe alguna de las 2 lista de objetos
        [quickpassEnServicio] o [quickpassEliminados]

        Return -> 
    */
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
        return index;
    }
    // Serialization of Array
    
    
    // ----- CREATE -----
    // Create Quickpass
    public boolean createQuickpass(int codigo, String filial, String placa) {
    /*
        Esta funcion acepta 3 parametros para generar un objeto {Quickpass} nuevo dentro de la lista [quickpassEnServicio] este nuevo objeto se 

        Parameter -> 
            Int codigo: debe ser un valor Int con 10 digitos, ademas los 3 
        primeros dijitos deben ser 101, por ejemplo 1011234567
            String filial: debe ser el numero de apartamento del dueño del 
        quickpass, debe ser un string y no tiene requisitos adicionales.
            String placa: debe ser el numero de placa del auto al que se le va 
        asignar el quickpass debe ser un String.
        
        Return -> Boolean: si se pudo crear el nuevo quickpass el resultado es True y si no es False
    */
        boolean isSuccessfull = false;
        int availableIndex = nullIndexFinder(quickpassEnServicio,true);
        if(availableIndex != -1) {
            this.quickpassEnServicio[availableIndex] = new Quickpass();
            this.quickpassEnServicio[availableIndex].setQuickpass(codigo,filial, placa);
            String details = this.quickpassEnServicio[availableIndex].getStringQuickpass(true);
            this.gestorAcceso.writeFile("Crear_QuickPass" + " | " + details);
            isSuccessfull = true;
        } else {
            isSuccessfull = false;
        }
        return isSuccessfull;
    }
    
    // ----- DELETE -----
    // Delete by codigo Quickpass
    public boolean deleteQuickPassCodigo(int codigo){
        boolean isSuccessfull = false;
        int availableIndex = nullIndexFinder(quickpassEliminados,true);
        if(availableIndex != -1){
            for(int i = 0; i < quickpassEnServicio.length; i++) {
                if(this.quickpassEnServicio[i] != null && this.quickpassEnServicio[i].getCodigo() == codigo) {
                    this.quickpassEliminados[availableIndex] = this.quickpassEnServicio[i];
                    this.quickpassEnServicio[i] = null;
                    isSuccessfull = true;
                }
            }
        } else {
            isSuccessfull = false;
        }
        return isSuccessfull;
    }
    
    // Delete by placa Quickpass
    public boolean deleteQuickPassPlaca(String placa){
        boolean isSuccessfull = false;
        int availableIndex = nullIndexFinder(quickpassEliminados,true);
        if(availableIndex != -1) {
            for(int i = 0; i < quickpassEnServicio.length; i++) {
                if(this.quickpassEnServicio[i] != null && this.quickpassEnServicio[i].getPlaca().contentEquals(placa)) {
                    this.quickpassEliminados[availableIndex] = this.quickpassEnServicio[i];
                    this.quickpassEnServicio[i] = null;
                    isSuccessfull = true;
                }
            }
        } else {
            isSuccessfull = false;
        }
        return isSuccessfull;
    }
    // ----- SETTERS -----
    public boolean setEstadoQuickpassByCodigo(int codigo){
        boolean isSuccessfull = false;
        int index = getIndexByCodigoQuickpass(codigo, quickpassEnServicio);
        if(quickpassEnServicio[index].getCodigo() == codigo){
            if(index != -1){
                quickpassEnServicio[index].changeEstado();
                isSuccessfull = true;
            }
        }
        return isSuccessfull;
    }
    
    public boolean setEstadoQuickpassByPlaca(String placa){
        boolean isSuccessfull = false;
        int index = getIndexByPlacaQuickpass(placa, quickpassEnServicio);
        if(quickpassEnServicio[index].getPlaca().contentEquals(placa)){
            if(index != -1){
                quickpassEnServicio[index].changeEstado();
                isSuccessfull = true;
            }
        }
        return isSuccessfull;
    }
    
    // ----- GETTERS -----
    
    
    public String getExistingQuickpass(){
        /*
        Esta funcion revisa la lista [quickpassEnServicio] para encontrar objetos
        de tipo quickpass y retornar un string con todos los que encuentre que no 
        sean null.

        Parameter -> none
        Return -> String
        */
        String sResult = null;
        for (Quickpass qp : this.quickpassEnServicio) {
            if (qp != null) {
                String qpString = qp.getStringQuickpass(false);
                sResult += "----------\n" + qpString;
                //System.out.println(qp.getStringQuickpass(true));
            }
        }
        return sResult;
    }    
    
    
    public String getActiveQuickpass(){
        
        String sResult = "-> Resultado todos los quickpass activos:\n";
        for (Quickpass qp : this.quickpassEnServicio) {
            if (qp != null && qp.getEstado() == Quickpass.Estados.ACTIVO) {
                String qpString = qp.getStringQuickpass(false);
                sResult += "----------\n" + qpString;
            }
        }
        return sResult;
        
    }
    
    public String getFilialQuickpass(String filial){
        String sResult = "-> Resultados filial: " + filial + "\n";
        for (Quickpass qp : this.quickpassEnServicio) {
            if (qp != null && qp.getEstado() == Quickpass.Estados.ACTIVO) {
                String qpString = qp.getStringQuickpass(false);
                sResult += "----------\n" + qpString;
            }
        }
        return sResult;
    }
    
    public String getCodigoQuickpass(int codigo){
        String sResult = null;
        int index = getIndexByCodigoQuickpass(codigo, quickpassEnServicio);
        if(index != -1){
            sResult = quickpassEnServicio[index].getStringQuickpass(false);
        }
        return sResult;
    }
    
    public String getPlacaQuickpass(String placa){
        String sResult = null;
        int index = getIndexByPlacaQuickpass(placa, quickpassEnServicio);
        if(index != -1){
            sResult = quickpassEnServicio[index].getStringQuickpass(false);
        }
        return sResult;
    }
    
    public String getDeletedQuickpass(){
        String sResult = "Deleted Quickpass\n";
        for (Quickpass qp : this.quickpassEliminados) {
            if (qp != null) {
                String qpString = qp.getStringQuickpass(false);
                sResult += "----------\n" + qpString;
            }
        }
        return sResult;
    }
    
    public int getIndexByCodigoQuickpass(int codigo,Quickpass array[]){
        int index = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] != null && array[i].getCodigo() == codigo){
                index = i;
            }
        }
        return index;
    }
    
    public int getIndexByPlacaQuickpass(String placa,Quickpass array[]){
        int index = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] != null && array[i].getPlaca().contentEquals(placa)){
                index = i;
            }
        }
        return index;
    }
    
    public boolean codigoValidator(int codigo){
        boolean isValid = false;
        String sCodigo = Integer.toString(codigo);
        int sLen = sCodigo.length();
        String desiredPref = "101";
        if (sLen == 10 && sCodigo.startsWith(desiredPref)){
            isValid = true;
        }
        return isValid;
    }
}
