/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
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
    File archivo = new File(txtPath);
    Date currentDate = new Date();
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
        dateF.setTimeZone(TimeZone.getTimeZone("CST"));
        timestamp = dateF.format(currentDate.getTime());
        return timestamp;
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
            String[] logArray = bigString.split(" - ", -1);
            System.out.println("bigString " + bigString);
            System.out.println("row " + row);
            for (String item : logArray) {
                System.out.println(item);
            }
            log2D = new String[row][col];

            for (int r = 0; r < log2D.length; r++) {
                for (int c = 0; c < log2D[r].length; c++) {
                    log2D[r][c] = logArray[index];
                    index++;
                }
            }

            for (int i = 0; i < log2D.length; i++) {
                for (int j = 0; j < log2D[i].length; j++) {
                    //System.out.print(log2D[i][j] + " ");
                }
                //System.out.print("\n");
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return log2D;
    }

    public void filterByFilial(String filial) {
        String[][] registros = readFile(); // Obtener registros del archivo

        if (registros == null || registros.length == 0) {
            System.out.println("No hay registros para mostrar.");
            return;
        } else {
            StringBuilder resultados = new StringBuilder("Registros para la filial: " + filial + "\n\n");

            // Recorremos el arreglo bidimensional para buscar coincidencias
            for (int i = 0; i < registros.length; i++) {
                if (registros[i][3].contains("Filial: " + filial)) {
                    resultados.append(String.join(" - ", registros[i])).append("\n");
                }
            }

            // Comprobar si se encontraron registros
            if (resultados.length() > 0) {
                JOptionPane.showMessageDialog(null, resultados.toString(), "Registros de Acceso por Filial", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron registros para la filial: " + filial, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

}
