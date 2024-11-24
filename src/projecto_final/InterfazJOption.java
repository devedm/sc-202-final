/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

import javax.swing.JOptionPane;

/**
 *
 * @author minio
 */

public class InterfazJOption {
    
    public String mainMenuMsj, gestionMenuMsj, accesoMenuMsj, reportesMenuMsj, invalidMenuOptionMsj,inputCodigoMsj,inputFilialMsj,inputPlacaMsj;
    public int optionMain,optionGestion,optionAcceso,optionReportes;
    public GestorQuickPass gestorQuickpass;
    public String noExisteQP;

    public InterfazJOption() {
        this.gestorQuickpass = new GestorQuickPass();
        // text messages
        this.mainMenuMsj = " ******** MENU ******** \n1) Gestión Quickpass.\n2) Acceso Quickpass.\n3) Reportes.\n0) Salir.";
        this.gestionMenuMsj = " *** Gestión Quickpass *** \n1) Registrar Quickpass.\n2) Consultar Quickpass.\n3) Eliminar Quickpass.\n4) Desactivar o activar Quickpass.\n5) Entrar con quickpass.\n6) Salir con quickpass.\n0) Volver al menu principal";
        this.accesoMenuMsj = " *** Accesos Quickpass *** \n1) Consultar por filial.\n2) Consultar por fechas.\n3) Consultar por placa.\n4) Consultar por Código\n0) Volver al menu principal";
        this.reportesMenuMsj = " *** Reportes Quickpass *** \n1) Reporte de todos los accesos.\n2) Reporte accesos por filial\n3) Reporte quickpass registrados.\n4) Reporte quickpass activos e inactivos.\n5) Reporte quickpass eliminados.\n0) Volver al menu principal";
        this.inputCodigoMsj = "Ingrese un Codigo: Numero de 10 digitos que tiene que iniciar con 101, por ejemplo 1018275625";
        this.inputFilialMsj = "Ingrese un Filial: El numero de apartamento del inquilino dueño del Quickpass";
        this.inputPlacaMsj = "Ingrese un Placa: El numero de placa del vehiculo";
        this.invalidMenuOptionMsj = "Opcion no valida, intenta nuevamente";
        
        // option integers
        this.optionMain = -1;
        this.optionGestion = -1;
        this.optionAcceso = -1;
        this.optionReportes = -1;
        
        // Errores
        this.noExisteQP = "No hay ningun Quickpass creado";
    }
    public void mainMenu(){
        do {   
            try{
                optionMain = Integer.parseInt(JOptionPane.showInputDialog(null, mainMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionMain) {
                case 1 -> gestionMenu();
                case 2 -> accesoMenu();
                case 3 -> reportesMenu();
                case 0 -> JOptionPane.showMessageDialog(null, "Saliendo");
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (optionMain != 0);       
    }
    public void gestionMenu(){
        do {
            try{
                optionGestion = Integer.parseInt(JOptionPane.showInputDialog(null, gestionMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionGestion) {
                case 1 -> createQuickpass();
                case 2 -> queryQuickpass();
                case 3 -> deleteQuickpass();
                case 4 -> changeEstadoQuickpass();
                case 5 -> inQuickpass();
                case 6 -> outQuickpass();
                case 0 -> JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (optionGestion != 0);
    }

    public void createQuickpass(){
        int ncodigo;
        String nFilial;
        String nplaca;
        
        try{
            ncodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
            if(gestorQuickpass.codigoValidator(ncodigo)){
                nFilial = JOptionPane.showInputDialog(null,inputFilialMsj);
                nplaca = JOptionPane.showInputDialog(null,inputPlacaMsj);
                gestorQuickpass.createQuickpass(ncodigo, nFilial, nplaca);
                JOptionPane.showMessageDialog(null, "Quickpass creado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            } 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
        }    
    } 
    public void queryQuickpass(){
        int opcion = 0;
        int nCodigo = 0;
        String nPlaca = null;
        String nFilial = null;
        String result = null;
        
        
        if(gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Elija una opcion:\n1)Busqueda por Placa\n2)Busqueda por Codigo\n3)Busqueda por Filial\n4)Mostrar todos"));
            switch (opcion) {
                case 1 -> {
                    try{
                        nPlaca = JOptionPane.showInputDialog(null,inputPlacaMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if(result!= null){
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    try{
                        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    } 
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if(result!= null){
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 3 -> {
                    try{
                        nFilial = JOptionPane.showInputDialog(null,inputFilialMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    } 
                    result = gestorQuickpass.getFilialQuickpass(nFilial);
                    if(result!= null){
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 4 -> {
                    result = gestorQuickpass.getExistingQuickpass();
                    if(result!= null){
                        JOptionPane.showMessageDialog(null, "-> Resultado todos los quickpass:\n" + result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun Quickpass creado");
        }
    }
    public void deleteQuickpass(){
        int opcion = 0;
        String nPlaca = null;
        int nCodigo = 0;
        String result = null;
        if(gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Elija una option:\n1)Eliminar por Placa\n2)Eliminar por Codigo"));
            switch (opcion) {
                case 1 -> {
                    nPlaca = JOptionPane.showInputDialog(null,inputPlacaMsj);
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if(result!= null){
                        if(gestorQuickpass.deleteQuickPassPlaca(nPlaca)){
                            JOptionPane.showMessageDialog(null, "Se ha eliminado\n" + result);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se han podido borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if(result!= null){
                        if(gestorQuickpass.deleteQuickPassCodigo(nCodigo)){
                            JOptionPane.showMessageDialog(null, "Se ha eliminado\n" + result);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se han podido borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }

                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun Quickpass creado");
        }
        
    }
    
    public void changeEstadoQuickpass(){
        int opcion = 0;
        String nPlaca = null;
        int nCodigo = 0;
        String result = null;
        
        if(gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Elija una option:\n1)Cambiar estado por Placa\n2)Cambiar estado por Codigo"));
            switch (opcion) {
                case 1 -> {
                    try{
                        nPlaca = JOptionPane.showInputDialog(null,inputPlacaMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if(result!= null && gestorQuickpass.setEstadoQuickpassByPlaca(nPlaca)){
                        JOptionPane.showMessageDialog(null, gestorQuickpass.getPlacaQuickpass(nPlaca));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    try{
                        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    } 
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if(result!= null && gestorQuickpass.setEstadoQuickpassByCodigo(nCodigo)){
                        JOptionPane.showMessageDialog(null, gestorQuickpass.getCodigoQuickpass(nCodigo));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, noExisteQP);
        }
    }
    
    public void inQuickpass(){
        int nCodigo;
        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
        String permiso = gestorQuickpass.useQuickpass(nCodigo, true);
        if(permiso == null){
            JOptionPane.showMessageDialog(null, noExisteQP);
        } else {
            if(permiso.contentEquals("Aceptado")){
                // Aceptado
                JOptionPane.showMessageDialog(null, "Entrada Aceptada para quickpass codigo: " + nCodigo);
            } else {
                // Rechazado
                JOptionPane.showMessageDialog(null, "Entrada Rechazada para quickpass codigo: " + nCodigo);
            }
        }
    }
    
    public void outQuickpass(){
        int nCodigo;
        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null,inputCodigoMsj));
        String permiso = gestorQuickpass.useQuickpass(nCodigo, true);
        if(permiso == null){
            JOptionPane.showMessageDialog(null, noExisteQP);
        } else {
            if(permiso.contentEquals("Aceptado")){
                // Aceptado
                JOptionPane.showMessageDialog(null, "Salida Aceptada para quickpass codigo: " + nCodigo);
            } else {
                // Rechazado
                JOptionPane.showMessageDialog(null, "Salida Rechazada para quickpass codigo: " + nCodigo);
            }
        }
    }

    public void accesoMenu(){
        /*
            Metodo que gestiona la interfaz de opciones para el modulo 2 de 
        gestion de accesso, el cual va mostrar 4 opciones para mostrar el 
        
        1) Consultar por filial
        2) Consultar por fechas
        3) Consultar por placa
        4) Consultar por Código
        0) Volver al menu principal
       
        Parameter: none
        Return: void
        */
        do {
            try{
                optionAcceso = Integer.parseInt(JOptionPane.showInputDialog(null, accesoMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionAcceso) {
                case 1 -> accesoPorFilial();
                case 2 -> accesoPorFechas();
                case 3 -> accesoPorPlaca();
                case 4 -> accesoPorCodigo();
                case 0 -> JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (optionAcceso != 0);
    }
    
    public void accesoPorFilial(){
        String filial = JOptionPane.showInputDialog(null, inputFilialMsj);
        String [] resultados = gestorQuickpass.gestorAcceso.getFilialsQuickpass(filial);
        if(!resultados[0].contentEquals("0")){
            JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            JOptionPane.showMessageDialog(null, resultados[1]);
        }
    }
    
    public void accesoPorFechas(){
        JOptionPane.showMessageDialog(null, "Resultados");
    }
    
    public void accesoPorPlaca(){
        String placa = JOptionPane.showInputDialog(null, inputPlacaMsj);
        String [] resultados = gestorQuickpass.gestorAcceso.getPlacasQuickpass(placa);
        if(!resultados[0].contentEquals("0")){
            JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            JOptionPane.showMessageDialog(null, resultados[1]);
        }
    }

    public void accesoPorCodigo(){
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
        String [] resultados = gestorQuickpass.gestorAcceso.getCodigosQuickpass(codigo);
        if(!resultados[0].contentEquals("0")){
            JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            JOptionPane.showMessageDialog(null, resultados[1]);
        }
    }
    
    public void reportesMenu(){
        do {
            try{
                optionGestion = Integer.parseInt(JOptionPane.showInputDialog(null, reportesMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionGestion) {
                case 1 -> createQuickpass();
                case 2 -> queryQuickpass();
                case 3 -> deleteQuickpass();
                case 4 -> changeEstadoQuickpass();
                case 0 -> JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default -> JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (optionGestion != 0);
    }
}
