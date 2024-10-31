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
    // Attributes
    public String filial;
    public int codigo;
    public String placa  ;
    public enum Estados {ACTIVO, INACTIVO};
    public Estados estado;
    
    // Constructor
    public Quickpass() {
        this.codigo = (int) (Math.random() * 1000000) + 1010000000;
        this.estado = Estados.ACTIVO;      
    }
    
    // Methods

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }
    
    public void setQuickpass(String filial, String placa) {
        setFilial(filial);
        setPlaca(placa);
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
    
}
