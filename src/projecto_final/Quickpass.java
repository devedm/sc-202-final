/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecto_final;

/**
 *
 * @author Eddy Mena
 */
public class Quickpass {
    /**
     * This class will define the Quickpass parameters and methods
     */
    
    // Attributes
    public String filial;
    public int codigo;
    public String placa;
    public enum Estados {ACTIVO, INACTIVO};
    public Estados estado;
    
    // Constructor
    public Quickpass() {
        this.estado = Estados.ACTIVO;      
    }
    
    // Methods

    public boolean setCodigo(int nCodigo) {
        String sCodigo = Integer.toString(nCodigo);
        int sLen = sCodigo.length();
        String desiredPref = "101";
        if(sLen == 10 && sCodigo.startsWith(desiredPref)){
            this.codigo = nCodigo;
            return true;
        } else {
            return false;
        }
    }
    
    
    public void setFilial(String filial) {
        this.filial = filial;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }
    
    public boolean setQuickpass(int codigo, String filial, String placa) {
        if(setCodigo(codigo)){
            setCodigo(codigo);
            setFilial(filial);
            setPlaca(placa);
            return true;
        } else {
            return false;
        }
        
    }

    public String getFilial() {
        return filial;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPlaca() {
        return placa;
    }
    
    public Estados getEstado() {
        return estado;
    }
    
    public String getEstadoString() {
        if(estado == Estados.ACTIVO){
            return "Activo";
        } else {
            return "Inactivo";
        }
        
    }
    
    public String getStringQuickpass(boolean oneLine) {
        String result;
        if(oneLine){
            result = "Quickpass Codigo: " + String.valueOf(getCodigo()) + " Filial: " + getFilial() + " Placa: " + getPlaca() + " Estado: " + getEstadoString();
        } else {
            result = "Quickpass\nCodigo: " + String.valueOf(getCodigo()) + "\nFilial: " + getFilial() + "\nPlaca: " + getPlaca() + "\nEstado: " + getEstadoString();
        }
        
        return result;
    }
    
}
