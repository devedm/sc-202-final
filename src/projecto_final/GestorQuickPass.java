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

        - Parameter -> 
            Quickpass Array[] aquickpass: recibe alguna de las 2 lista de objetos
        [quickpassEnServicio] o [quickpassEliminados].
            boolean isNull: recibe true y retorna el primer valor null de la 
        lista, y si es false entonces retorna el primer valor que no sea null 
        de la lista.

        - Return -> int: retorna un numero de indice donde se encuentra o el 
        primer valor null o no null de la lista seleccionada, si no encuentra 
        el valor indicado retorna -1.
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
        Esta funcion acepta 3 parametros para generar un objeto {Quickpass} 
        nuevo dentro de la lista [quickpassEnServicio] y retorna true o false 
        si fue posible crear el objeto o no.

        - Parameter -> 
            Int codigo: debe ser un valor Int con 10 digitos, ademas los 3 
        primeros dijitos deben ser 101, por ejemplo 1011234567
            String filial: debe ser el numero de apartamento del dueño del 
        quickpass, debe ser un string y no tiene requisitos adicionales.
            String placa: debe ser el numero de placa del auto al que se le va 
        asignar el quickpass debe ser un String.
        
        - Return -> Boolean: si se pudo crear el nuevo quickpass el resultado es True y si no es False
    */
        boolean isSuccessfull = false;
        int availableIndex = nullIndexFinder(quickpassEnServicio,true);
        if(availableIndex != -1) {
            this.quickpassEnServicio[availableIndex] = new Quickpass();
            this.quickpassEnServicio[availableIndex].setQuickpass(codigo,filial, placa);
            this.gestorAcceso.writeFile("Crear_QuickPass" + " - " + "Completado" + " - " + this.quickpassEnServicio[availableIndex].getStringQuickpass(true));
            isSuccessfull = true;
        } else {
            this.gestorAcceso.writeFile("Crear_QuickPass" + " - " + "Error" + " - " + "no se pudo crear quickpass codigo:" + String.valueOf(codigo) + ",filial:" + filial + ",placa:" + placa);
            isSuccessfull = false;
        }
        return isSuccessfull;
    }
    
    // ----- DELETE -----
    // Delete by codigo Quickpass
    public boolean deleteQuickPassCodigo(int codigo){
        /*
            descripcion:
            
            - Parameter -> dataType variableName: description.
            - Return -> dataType variableName: description.
        */
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
    
    
    public String useQuickpass(int codigo, boolean direction){
        /*
            Este metodo revisa si al usar un quickpass es valido para la entrada
        o la salida, ademas de que registra la actividad con el modulo de acceso.
            
            - Parameter -> int codigo: codigo para usar quickpass.
            - Return -> String: el resultado va ser null si no existen quickpass
        para revisar, si existe y esta activo se retorna Aceptado y si no cumple
        alguna de las anteriores retorna Rechazado.
        */
        String sResult = null;
        if(nullIndexFinder(quickpassEnServicio, false) != -1){
            // si el return es de != -1 singifica que hay al menos 1 quickpass registrado
            if(direction){
                // si la direccion es *true* significa que esta entrando
                int index = getIndexByCodigoQuickpass(codigo,quickpassEnServicio);
                if(index != -1 && quickpassEnServicio[index].getEstado() == Quickpass.Estados.ACTIVO){
                    // si encontramos el codigo ingresado && si esta activo
                    sResult = "Aceptado";
                    gestorAcceso.writeFile("Entrada - " + sResult +" - " + quickpassEnServicio[index].getStringQuickpass(true));
                } else {
                    sResult = "Rechazado";
                    gestorAcceso.writeFile("Entrada - " + sResult +" - " + quickpassEnServicio[index].getStringQuickpass(true));
                }
            } else {
                // si la direccion es *false* significa que esta saliendo
                int index = getIndexByCodigoQuickpass(codigo,quickpassEnServicio);
                if(index != -1 && quickpassEnServicio[index].getEstado() == Quickpass.Estados.ACTIVO){
                    // si encontramos el codigo ingresado && si esta activo
                    sResult = "Aceptado";
                    gestorAcceso.writeFile("Salida - " + sResult + " - " + quickpassEnServicio[index].getStringQuickpass(true));
                } else {
                    sResult = "Rechazado";
                    gestorAcceso.writeFile("Salida - " + sResult + " - " + quickpassEnServicio[index].getStringQuickpass(true));
                }
            }
        }
        return sResult;
    }
    
    
    public String getExistingQuickpass(){
        /*
        Esta funcion revisa la lista [quickpassEnServicio] para encontrar objetos
        de tipo quickpass y retornar un string con todos los que encuentre que no 
        sean null.

        - Parameter -> none
        - Return -> String
        */
        String sResult = " ";
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
