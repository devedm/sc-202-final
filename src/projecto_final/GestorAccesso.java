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
 */
public class GestorAccesso {
    // Attributes
    String path = "Archivo.txt";
    File archivo = new File(path);
    Date currentDate = new Date();
    DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
    
    // Constructor
    public GestorAccesso(){    
        createFile();
    }

    // Methods
    public void createFile() {
        try{
            if(archivo.createNewFile()){
                System.out.println("File created: " + archivo.getName());
                FileWriter fw = new FileWriter(path);
                fw.write("        Date        |  Action  | Details \n");
                fw.close();
            } else {
                System.out.println("File already exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    
    public String getTimeStamp(){
        String timestamp;
        dateF.setTimeZone(TimeZone.getTimeZone("CST"));
        timestamp = dateF.format(currentDate.getTime());
        return timestamp;
    }
    
    public void writeFile(String data){
        try {
            FileWriter fw = new FileWriter(this.path, true);
            fw.append(getTimeStamp() + " | " + data + "\n");
            fw.close();
            System.out.println("Wrote some text check it out");
        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }
    
    public void readFile(){
        
    }
}
