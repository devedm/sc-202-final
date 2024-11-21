/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    public GestorAccesso(){    
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
        try{
            if(archivo.createNewFile()){
                System.out.println("File created: " + archivo.getName());
                FileWriter fw = new FileWriter(txtPath);
                fw.write("        Fecha        |  Accion  |  Detalles  |  Quickpass  \n");
                fw.close();
            } else {
                //System.out.println("File already exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    
    public String getTimeStamp(){
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
    
    public void writeFile(String data){
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
            fw.append(getTimeStamp() + " | " + data + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }
    
    public void readFile(){
        
    }
}
