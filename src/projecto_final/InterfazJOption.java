/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author minio
 */
public class InterfazJOption {

    public String mainMenuMsj, gestionMenuMsj, accesoMenuMsj, reportesMenuMsj, invalidMenuOptionMsj, inputCodigoMsj, inputFilialMsj, inputPlacaMsj;
    public int optionMain, optionGestion, optionAcceso, optionReportes;
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

    public void mainMenu() {
        do {
            try {
                optionMain = Integer.parseInt(JOptionPane.showInputDialog(null, mainMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionMain) {
                case 1 ->
                    gestionMenu();
                case 2 ->
                    accesoMenu();
                case 3 ->
                    reportesMenu();
                case 0 ->
                    JOptionPane.showMessageDialog(null, "Saliendo");
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (optionMain != 0);
    }

    public void gestionMenu() {
        do {
            try {
                optionGestion = Integer.parseInt(JOptionPane.showInputDialog(null, gestionMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionGestion) {
                case 1 ->
                    createQuickpass();
                case 2 ->
                    queryQuickpass();
                case 3 ->
                    deleteQuickpass();
                case 4 ->
                    changeEstadoQuickpass();
                case 5 ->
                    inQuickpass();
                case 6 ->
                    outQuickpass();
                case 0 ->
                    JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (optionGestion != 0);
    }

    public void createQuickpass() {
        int ncodigo;
        String nFilial;
        String nplaca;

        try {
            ncodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
            if (gestorQuickpass.codigoValidator(ncodigo)) {
                nFilial = JOptionPane.showInputDialog(null, inputFilialMsj);
                nplaca = JOptionPane.showInputDialog(null, inputPlacaMsj);
                gestorQuickpass.createQuickpass(ncodigo, nFilial, nplaca);
                JOptionPane.showMessageDialog(null, "Quickpass creado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryQuickpass() {
        int opcion = 0;
        int nCodigo = 0;
        String nPlaca = null;
        String nFilial = null;
        String result = null;

        if (gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Elija una opcion:\n1)Busqueda por Placa\n2)Busqueda por Codigo\n3)Busqueda por Filial\n4)Mostrar todos"));
            switch (opcion) {
                case 1 -> {
                    try {
                        nPlaca = JOptionPane.showInputDialog(null, inputPlacaMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    try {
                        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 3 -> {
                    try {
                        nFilial = JOptionPane.showInputDialog(null, inputFilialMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getFilialQuickpass(nFilial);
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 4 -> {
                    result = gestorQuickpass.getExistingQuickpass();
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, "-> Resultado todos los quickpass:\n" + result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun Quickpass creado");
        }
    }

    public void deleteQuickpass() {
        int opcion = 0;
        String nPlaca = null;
        int nCodigo = 0;
        String result = null;
        if (gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Elija una option:\n1)Eliminar por Placa\n2)Eliminar por Codigo"));
            switch (opcion) {
                case 1 -> {
                    nPlaca = JOptionPane.showInputDialog(null, inputPlacaMsj);
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if (result != null) {
                        if (gestorQuickpass.deleteQuickPassPlaca(nPlaca)) {
                            JOptionPane.showMessageDialog(null, "Se ha eliminado\n" + result);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se han podido borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if (result != null) {
                        if (gestorQuickpass.deleteQuickPassCodigo(nCodigo)) {
                            JOptionPane.showMessageDialog(null, "Se ha eliminado\n" + result);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se han podido borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }

                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun Quickpass creado");
        }

    }

    public void changeEstadoQuickpass() {
        int opcion = 0;
        String nPlaca = null;
        int nCodigo = 0;
        String result = null;

        if (gestorQuickpass.nullIndexFinder(gestorQuickpass.quickpassEnServicio, false) != -1) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Elija una option:\n1)Cambiar estado por Placa\n2)Cambiar estado por Codigo"));
            switch (opcion) {
                case 1 -> {
                    try {
                        nPlaca = JOptionPane.showInputDialog(null, inputPlacaMsj);
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getPlacaQuickpass(nPlaca);
                    if (result != null && gestorQuickpass.setEstadoQuickpassByPlaca(nPlaca)) {
                        JOptionPane.showMessageDialog(null, gestorQuickpass.getPlacaQuickpass(nPlaca));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                case 2 -> {
                    try {
                        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
                    } catch (NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                    result = gestorQuickpass.getCodigoQuickpass(nCodigo);
                    if (result != null && gestorQuickpass.setEstadoQuickpassByCodigo(nCodigo)) {
                        JOptionPane.showMessageDialog(null, gestorQuickpass.getCodigoQuickpass(nCodigo));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado resultados");
                    }
                }
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, noExisteQP);
        }
    }

    public void inQuickpass() {
        int nCodigo;
        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
        String permiso = gestorQuickpass.useQuickpass(nCodigo, true);
        if (permiso == null) {
            JOptionPane.showMessageDialog(null, noExisteQP);
        } else {
            if (permiso.contentEquals("Aceptado")) {
                // Aceptado
                JOptionPane.showMessageDialog(null, "Entrada Aceptada para quickpass codigo: " + nCodigo);
            } else {
                // Rechazado
                JOptionPane.showMessageDialog(null, "Entrada Rechazada para quickpass codigo: " + nCodigo);
            }
        }
    }

    public void outQuickpass() {
        int nCodigo;
        nCodigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
        String permiso = gestorQuickpass.useQuickpass(nCodigo, true);
        if (permiso == null) {
            JOptionPane.showMessageDialog(null, noExisteQP);
        } else {
            if (permiso.contentEquals("Aceptado")) {
                // Aceptado
                JOptionPane.showMessageDialog(null, "Salida Aceptada para quickpass codigo: " + nCodigo);
            } else {
                // Rechazado
                JOptionPane.showMessageDialog(null, "Salida Rechazada para quickpass codigo: " + nCodigo);
            }
        }
    }

    public void accesoMenu() {
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
            try {
                optionAcceso = Integer.parseInt(JOptionPane.showInputDialog(null, accesoMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionAcceso) {
                case 1 ->
                    accesoPorFilial();
                case 2 ->
                    accesoPorFechas();
                case 3 ->
                    accesoPorPlaca();
                case 4 ->
                    accesoPorCodigo();
                case 0 ->
                    JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (optionAcceso != 0);
    }

    public static boolean fechaValida(String fecha, String format) {
        DateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            df.parse(fecha);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error, entrada invalida" + e);
            return false;
        }
        return true;
    }

    public void accesoPorFechas() {
        String inicio = JOptionPane.showInputDialog(null, "Ingrese una fecha de inicio en formato dd/mm/aaaa por ejemplo 25/12/2024:");
        String fin = JOptionPane.showInputDialog(null, "Ingrese una fecha de fin en formato dd/mm/aaaa por ejemplo 25/12/2024:");
        if (fechaValida(inicio, "dd/MM/yyyy") && fechaValida(fin, "dd/MM/yyyy")) {
            String[] resultados = gestorQuickpass.gestorAcceso.getFechasQuickpass(inicio, fin);
            if (!resultados[0].contentEquals("0")) {
                JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
            } else {
                JOptionPane.showMessageDialog(null, resultados[1]);
            }
        }
    }

    public void accesoPorPlaca() {
        String placa = JOptionPane.showInputDialog(null, inputPlacaMsj);
        String[] resultados = gestorQuickpass.gestorAcceso.getPlacasQuickpass(placa);
        if (!resultados[0].contentEquals("0")) {
            JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            JOptionPane.showMessageDialog(null, resultados[1]);
        }
    }

    public void accesoPorCodigo() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, inputCodigoMsj));
        String[] resultados = gestorQuickpass.gestorAcceso.getCodigosQuickpass(codigo);
        if (!resultados[0].contentEquals("0")) {
            JOptionPane.showMessageDialog(null, "Se han encontrado " + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            JOptionPane.showMessageDialog(null, resultados[1]);
        }
    }

    public void accesoPorFilial() {

        // Solicitar al usuario la filial a consultar
        String filial = JOptionPane.showInputDialog(null,
                "Ingrese la filial que desea consultar:",
                "Consulta por Filial",
                JOptionPane.QUESTION_MESSAGE);

        // Validar la entrada del usuario
        if (filial == null || filial.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar una filial válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Salir si no hay entrada válida
        }

        // Llamar al método para filtrar los registros por filial
        gestorQuickpass.gestorAcceso.filterByFilial(filial);
    }

    public void reportesMenu() {
        do {
            try {
                optionReportes = Integer.parseInt(JOptionPane.showInputDialog(null, reportesMenuMsj));
            } catch (NumberFormatException e) {
                System.out.println("Error:" + e);
            }
            switch (optionReportes) {
                case 1 ->
                    getAllAccesos();
                case 2 ->
                    getAllFilial();
                case 3 ->
                    getAllCreatedQuickPass();
                case 4 ->
                    getAllQuickpassStatus();
                case 5 ->
                    getAllDeletedQuickpass();
                case 0 ->
                    JOptionPane.showMessageDialog(null, "Volviendo al menu anterior");
                default ->
                    JOptionPane.showMessageDialog(null, invalidMenuOptionMsj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (optionReportes != 0);
    }
    
    public void getAllAccesos(){
        // Variables
        String[] resultados = gestorQuickpass.gestorAcceso.getAllAccess();
        if(resultados != null){
            JOptionPane.showMessageDialog(null, "Total de accesos: " + String.valueOf(resultados.length)+ "\n" + printStringArrayWithLineBreak(resultados));
        }
    }
    
    public void getAllFilial(){
        // Variables
        String[] filiales = new String[10];
        int filialIndex = 0;
        String resultados = "";
        
        
        // Crear lista de fililaes con repetidas
        for (Quickpass quickpass : gestorQuickpass.quickpassEnServicio) {
            if( quickpass != null){
                filiales[filialIndex] = quickpass.getFilial();
                filialIndex ++;
            }
        }
        System.out.println("Antes de Sort:\n" + Arrays.toString(filiales));
        // se ordena el array para organizar los items iguales juntos
        Arrays.sort(filiales, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
        System.out.println("Despues de Sort:\n" + Arrays.toString(filiales));
        // Filtrar fililales repetidas
        for (int i = 0; i < filiales.length; i++) {
            if(filiales[i] != null && i > 0) {
                if(filiales[i] == filiales[i-1]){
                    filiales[i-1] = null; 
                    filialIndex --;
                }
            }
        } 
        
        // se crea un nuevo array con cada Filial que existe sin repetir
        String[] filialesFiltradas = new String[filialIndex];
        
        for (int i = 0; i < filialesFiltradas.length; i++) {
            filialesFiltradas[i] = filiales[i];
        }
        System.out.println("Filtradas:\n" + Arrays.toString(filialesFiltradas));
        // Se buscan los resgistros de los accessos y guardan en el string de resultado
        for (String filial : filialesFiltradas) {
            String[] accessosFilial = gestorQuickpass.gestorAcceso.getFilialAccessByFilial(filial);
            resultados = resultados + "-----------\nFilial:" + filial + "Accessos: " + String.valueOf(accessosFilial.length) + "\n" + printStringArrayWithLineBreak(accessosFilial) + "\n";
        }
        System.out.println("Resultados:\n" + String.valueOf(resultados.length()));
        
        if(filialesFiltradas.length > 0){
            JOptionPane.showMessageDialog(null, resultados);
        } else {
            JOptionPane.showMessageDialog(null, "No hay accessos registrados", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String printStringArrayWithLineBreak(String[] array){
        String result = "";
        for (String string : array) {
            result = result + string + "\n";
        }
        return result;
    }

    public void getAllAccesos2() {
        String resultados = gestorQuickpass.getExistingQuickpass();
        if (!resultados.isBlank() | !resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se han encontrado " + String.valueOf(resultados.split("----------").length - 1) + " resultados:\n" + resultados);
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun quickpass");
        }

    }

    public void getAllFilial2() {
        /*   Este método se usa el método de la clase GestorQuickPass para traer el total de accesos 
        agrupados por filial.
        
        Parámetros: ninguno
        Retorno: void */

        // Llamamos al método que obtiene los accesos por filial
        String[] resultados = gestorQuickpass.gestorAcceso.getFilialAccessByFilial(inputFilialMsj);

        // Verificamos si hay resultados
        if (resultados != null && !resultados[0].equals("0")) {
            // Si hay resultados, mostramos el número de accesos encontrados y los detalles
            JOptionPane.showMessageDialog(null, "Se han encontrado "
                    + resultados[0] + " resultados:\n" + resultados[1]);
        } else {
            // Si no hay resultados, mostramos un mensaje indicando que no se han encontrado accesos
            JOptionPane.showMessageDialog(null, "No se han encontrado accesos por filial.");
        }
    }

    public void getAllCreatedQuickPass() {
        /*
            Este metodo usa el metodo de la clase GestorQuickPass llamado 
        getExistingQuickpass() para traer un String con la informacion de los 
        quickpass que se han creado por ende estan en la lista de 
        quickpassEnServicio.
        
       
        Parameter: none
        Return: void
         */
        String resultados = gestorQuickpass.getExistingQuickpass();
        if (!resultados.isBlank() | !resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se han encontrado " + String.valueOf(resultados.split("----------").length - 1) + " resultados:\n" + resultados);
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun quickpass");
        }
    }

    public void getAllQuickpassStatus() {
        String resultado = gestorQuickpass.contarQuickpassEstados();
        JOptionPane.showMessageDialog(null, resultado, "Totales de Quickpass", JOptionPane.INFORMATION_MESSAGE);
    }

    public void getAllDeletedQuickpass() {
        /*
            Este metodo usa el metodo de la clase GestorQuickPass llamado 
        getDeletedQuickpass() para traer un String con la informacion de los 
        quickpass que se han eliminado por ende estan en la lista de 
        quickpassEliminados.
        
       
        Parameter: none
        Return: void
         */
        // crea string con lista de quickpass eliminados
        String resultados = gestorQuickpass.getDeletedQuickpass();

        if (!resultados.isBlank() | !resultados.isEmpty()) {
            // filtra si la lista no esta en blanco entonces imprime el contenido
            JOptionPane.showMessageDialog(null, "Se han encontrado " + String.valueOf(resultados.split("----------").length - 1) + " resultados:\n" + resultados);
        } else {
            // si esta vacio entonces muestra un mensaje de que no hay items
            JOptionPane.showMessageDialog(null, "No hay quickpass eliminados");
        }
    }

}
