/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author minio
 *
 */
public class GestorAccesso {

    /**
     * This class will include the access task and file storage methods
     */
    // Attributes
    String txtPath = "Historial.txt";
    String created = "created.ser";
    String deleted = "deleted.ser";
    File archivo = new File(txtPath);
    Date currentDate;
    DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");

    // Constructor
    public GestorAccesso() {
        createFile();
    }

    // Methods
    public void createFile() {
        /*
            Este método se encarga de crear un nuevo archivo de nombre "txtPath"
        de extension txt en la carpeta base del proyecto el cual almacena el 
        historial de acceso de los quickpass agregando una hora y descripcion 
        de la interaccion.

            - Parameter -> none
            - Return -> void
         */
        try {
            if (archivo.createNewFile()) {
                System.out.println("File created: " + archivo.getName());
                FileWriter fw = new FileWriter(txtPath);
                fw.write("Fecha - Accion - Detalles - Quickpass - \n");
                fw.close();
            } else {
                //System.out.println("File already exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public String getTimeStamp() {
        /*
            Este método se encarga de consultar la hora actual en la region 
        "CST" y genera un string que tiene un formato de (dd/MM/yyyy_HH:mm:ss)
        definido en la variable "dateF".

            - Parameter -> none
            - Return -> String: es una hora en formato dd/MM/yyyy_HH:mm:ss //https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
         */
        String timestamp;
        currentDate = new Date();
        dateF.setTimeZone(TimeZone.getTimeZone("CST"));
        timestamp = dateF.format(currentDate.getTime());
        return timestamp;
    }
    
    public void writeCreatedQuickpass(Quickpass[] quickpassArray){
        try (FileOutputStream fs = new FileOutputStream(created)){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(quickpassArray);
            os.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void writeDeletedQuickpass(Quickpass[] quickpassArray){
        try (FileOutputStream fs = new FileOutputStream(deleted)){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(quickpassArray);
            os.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public Quickpass[] readCreatedQuickpass(){
        Quickpass[] quickpassCreated = null;
        try (FileInputStream fs = new FileInputStream(created); ObjectInputStream os = new ObjectInputStream(fs)){
            quickpassCreated = (Quickpass[])os.readObject();
            
            System.out.println("Created Quickpass");
            for (Quickpass quickpass : quickpassCreated) {
                if(quickpass != null){
                    System.out.println(quickpass.toString());
                }
            }
            
            
        } catch (IOException | ClassNotFoundException  e){
            System.out.println("Error: " + e);
        }
        return quickpassCreated;
    }
    
    public Quickpass[] readDeletedQuickpass(){
        Quickpass[] quickpassDeleted = null;
        try (FileInputStream fs = new FileInputStream(deleted); ObjectInputStream os = new ObjectInputStream(fs)){
            quickpassDeleted = (Quickpass[])os.readObject();
            
            System.out.println("Deleted Quickpass");
            for (Quickpass quickpass : quickpassDeleted) {
                if(quickpass != null){
                    System.out.println(quickpass.toString());
                }
            }
            
        } catch (IOException | ClassNotFoundException  e){
            System.out.println("Error: " + e);
        }
        return quickpassDeleted;
    }

    public void writeFile(String data) {
        /*
            Este método escribe en el archivo anteriormente generado, este 
        agrega automaticamente el string de fecha de getTimeStamp() y luego 
        mediante un separador | agrega la data que se le pasa como parametro, 
        se pide que no tenga salto de linea ya que este lo agrega en final para 
        lograr el formato de log.
        
            - Parameter -> String data: un string que tenga un formato de accion
        realizada luego agregue un separador "|" y mas detalles por ejemplo, 
        "Aceptado | Ingreso | codigo:1011234567,filial:b15,placa:bsd-546"
            - Return -> void
         */
        try {
            FileWriter fw = new FileWriter(txtPath, true);
            fw.append(getTimeStamp() + " - " + data + " - \n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }

    public String[][] readFile() {
        /*
            Este lee el archivo Historial.txt y convierte el contenido en un 
        array bidimensional y lo retorna.
        
            - Parameter -> none
            - Return -> Bidimentional String Array [row][col]
        
         */
        String line = "";
        String bigString = "";
        int row = 0;
        int col = 4;
        int index = 0;
        String[][] log2D = null;

        try {
            FileReader fileReader = new FileReader(txtPath);
            BufferedReader br = new BufferedReader(fileReader);

            while ((line = br.readLine()) != null) {
                row++;
                bigString += line;
            }
            String[] logArray = bigString.split(" - ",-1);
            //System.out.println("bigString " + bigString);
            //System.out.println("row " + row);

            log2D = new String[row][col];

            for (int r = 0; r < log2D.length; r++) {
                for (int c = 0; c < log2D[r].length; c++) {
                    log2D[r][c] = logArray[index];
                    index++;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.print(e);
        }
        return log2D;
    }
    
    public String[] getPlacasQuickpass(String placa){
        String[][] biDimArr = readFile();
        String[] arrayResultado = new String[2];
        String resultados = "";
        int index = 0;
        
        // loop por todos las filas del historial en la ubicacion index 1 donde esta la accion
        for (int row = 0; row < biDimArr.length; row++) {      
            // Limpiar de espacios el valor de cada item
            String item = biDimArr[row][1].replaceAll("\\s", "");
            // filtrar si dice entrada o salida
            if(item.contentEquals("Entrada") || item.contentEquals("Salida")){
                // crear array con elementos del quickpass
                String[] quickpass = biDimArr[row][3].split(",");
                // filtrar para quitar el text " Placa: " y que quede solo el valor de placa
                String selectedItem = quickpass[2].replaceFirst(" Placa: ", "");
                // evaluar si contiene el valor de placa ingresado
                if(selectedItem.contentEquals(placa)){
                    index ++;
                    String fila = String.join(" - ",biDimArr[row][0], biDimArr[row][1], biDimArr[row][2], biDimArr[row][3]);
                    resultados = resultados.concat(fila + "\n");
                    System.out.print(biDimArr[row][3]);
                }
            }
        }
        // crea un array de String vacio si no se encontro o con los detalles encontrados
        if(index > 0){
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = resultados;
            this.writeFile("Consulta - " + "AccesoPorPlaca_" + placa + " - " + arrayResultado[0] + "_resultados" );
        } else {
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = "Sin Resultados";
            this.writeFile("Consulta - " + "AccesoPorPlaca_" + placa + " - " + arrayResultado[0] + "_resultados" );
        }
        
        return arrayResultado;
    }
    
    public String[] getCodigosQuickpass(int ncodigo){
        String codigo = String.valueOf(ncodigo);
        String[][] biDimArr = readFile();
        String[] arrayResultado = new String[2];
        String resultados = "";
        int index = 0;
        
        for (int row = 0; row < biDimArr.length; row++) {            
            String item = biDimArr[row][1].replaceAll("\\s", "");
            if(item.contentEquals("Entrada") || item.contentEquals("Salida")){
                String[] quickpass = biDimArr[row][3].split(",");
                String selectedItem = quickpass[0].replaceFirst("Codigo: ", "");
                if(selectedItem.contentEquals(codigo)){
                    index ++;
                    String fila = String.join(" - ",biDimArr[row][0], biDimArr[row][1], biDimArr[row][2], biDimArr[row][3]);
                    resultados = resultados.concat(fila + "\n");
                    System.out.print(biDimArr[row][3]);
                }
            }
        }
        if(index > 0){
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = resultados;
            this.writeFile("Consulta - " + "AccesoPorCodigo_" + codigo + " - " + arrayResultado[0] + "_resultados" );
        } else {
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = "Sin Resultados";
            this.writeFile("Consulta - " + "AccesoPorCodigo_" + codigo + " - " + arrayResultado[0] + "_resultados" );
        }
        return arrayResultado;
    }

    public void filterByFilial(String filial) {
        String[][] registros = readFile(); // Obtener registros del archivo
        int index = 0;

        if (registros == null || registros.length == 0) {
            System.out.println("No hay registros para mostrar.");
            return;
        } else {
            StringBuilder resultados = new StringBuilder("Registros para la filial: " + filial + "\n\n");

            // Recorremos el arreglo bidimensional para buscar coincidencias
            for (int i = 0; i < registros.length; i++) {
                if (registros[i][3].contains("Filial: " + filial) && (registros[i][1].trim().contentEquals("Entrada") || registros[i][1].trim().contentEquals("Salida"))) {
                    resultados.append(String.join(" - ", registros[i])).append("\n");
                    index ++;
                }
            }

            // Comprobar si se encontraron registros
            if (resultados.length() > 0) {
                JOptionPane.showMessageDialog(null, resultados.toString(), "Registros de Acceso por Filial", JOptionPane.INFORMATION_MESSAGE);
                this.writeFile("Consulta - " + "AccesoPorFilial_" + filial + " - " + String.valueOf(index) + "_resultados" );
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron registros para la filial: " + filial, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                this.writeFile("Consulta - " + "AccesoPorFilial_" + filial + " - " + String.valueOf(index) + "_resultados" );;
            }
        }

    }
    
    public String[] getFilialAccessByFilial(String filial) {
        String[][] registros = readFile(); // Obtener registros del archivo
        int index = 0;
        String[] tempArray = new String[registros.length];
        int index2 = 0;

        if (registros == null || registros.length == 0) {
            System.out.println("No hay registros para mostrar.");
            return null;
        } else {
            // Recorremos el arreglo bidimensional para buscar coincidencias
            for (int i = 0; i < registros.length; i++) {
                if (registros[i][3].contains("Filial: " + filial) && (registros[i][1].trim().contentEquals("Entrada") || registros[i][1].trim().contentEquals("Salida"))) {
                    tempArray[i] = registros[i][0] + registros[i][1] + registros[i][2] + registros[i][3];
                    index ++;
                }
            }
            String[] resultados = new String[index];
            
            for (int i = 0; i < tempArray.length; i++) {
                if(tempArray[i] != null){
                    resultados[index2] = tempArray[i];
                    index2 ++;
                }
            }
            
            // Comprobar si se encontraron registros
            if (tempArray.length > 0) {
                return resultados;
            } else {
                return resultados;
            }
        }

    }
    
    public String[] getFechasQuickpass(String inicio, String fin){
        String[][] biDimArr = readFile();
        String[] arrayResultado = new String[2];
        String resultados = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        
        for (int row = 0; row < biDimArr.length; row++) {            
            String item = biDimArr[row][1].replaceAll("\\s", "");
            if(item.contentEquals("Entrada") || item.contentEquals("Salida")){
                try{
                    Date fechaItem = format.parse(biDimArr[row][0].split("_")[0]);
                    Date fechaInicio = format.parse(inicio);
                    Date fechaFinal = format.parse(fin);

                    if(fechaItem.compareTo(fechaInicio) >= 0 && fechaItem.compareTo(fechaFinal) <= 0){
                        index ++;
                        String fila = String.join(" - ",biDimArr[row][0], biDimArr[row][1], biDimArr[row][2], biDimArr[row][3]);
                        resultados = resultados.concat(fila + "\n");
                        System.out.print(biDimArr[row][3]);
                    }
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Error, entrada invalida" + e);
                }
            }
        }
        if(index > 0){
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = resultados;
            this.writeFile("Consulta - " + "AccesoPorFecha_Inicio:" + inicio + "_Fin:" + fin + " - " + arrayResultado[0] + "_resultados" );
        } else {
            arrayResultado[0] = String.valueOf(index);
            arrayResultado[1] = "Sin Resultados";
            this.writeFile("Consulta - " + "AccesoPorFecha_Inicio:" + inicio + "_Fin:" + fin + " - " + arrayResultado[0] + "_resultados" );
        }
        return arrayResultado;
    }
    
    public String[] getAllAccess() {
        String[][] registros = readFile(); // Obtener registros del archivo
        int index = 0;
        String[] tempArray = new String[registros.length];
        int index2 = 0;

        if (registros == null || registros.length == 0) {
            System.out.println("No hay registros para mostrar.");
            return null;
        } else {
            // Recorremos el arreglo bidimensional para buscar coincidencias
            for (int i = 0; i < registros.length; i++) {
                if ((registros[i][1].trim().contentEquals("Entrada") || registros[i][1].trim().contentEquals("Salida"))) {
                    tempArray[i] = registros[i][0] + registros[i][1] + registros[i][2] + registros[i][3];
                    index ++;
                }
            }
            String[] resultados = new String[index];
            
            for (int i = 0; i < tempArray.length; i++) {
                if(tempArray[i] != null){
                    resultados[index2] = tempArray[i];
                    index2 ++;
                }
            }
            
            // Comprobar si se encontraron registros
            if (tempArray.length > 0) {
                return resultados;
            } else {
                return resultados;
            }
        }

    }
}
