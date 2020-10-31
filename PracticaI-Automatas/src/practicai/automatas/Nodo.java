/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicai.automatas;

/**
 *
 * @author MI AMIX Y YO
 */
public class Nodo {
    
    private String clase;
    private String valor;
    private Nodo liga;
    
    public Nodo(String clase, String valor){
        this.clase = clase;
        this.valor = valor;
        this.liga = null;
    }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the liga
     */
    public Nodo getLiga() {
        return liga;
    }

    /**
     * @param liga the liga to set
     */
    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
    
    
    
    
}
