/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import practicai.automatas.Nodo;
import practicai.automatas.PracticaIAutomatas;

/**
 *
 * @author stefa
 */
public class AutomataBoolean {
    
    public static String[] tiposBool = {"true","false"};
    
    /* Método que recibe el nodo que le sigue al nodo que contiene un boolean.
       Verifica que el valor del nodo siguiente sea válido dentro de la estructura.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
    */
    public void booleanNextValue (Nodo siguiente){
        String nextValue = null;
        
        if (siguiente != null){
            nextValue = siguiente.getValor();
            if(!";".equals(nextValue) && !"&".equals(nextValue) && !"|".equals(nextValue)){
                System.out.println("Error: Dato inválido después de booleano (" + nextValue + ")");//ERROR: DATO INVALIDO DESPUES DEL BOOLEANO
                PracticaIAutomatas.labelTexto += "Error: Dato inválido después de booleano (" + nextValue + ") <p>";
                PracticaIAutomatas.erroresLinea = true;
            }else{
                System.out.println("Dato válido");
            }
        }else{
            System.out.println("Error: Falta punto y coma (;)");
            PracticaIAutomatas.labelTexto += "Error: Falta punto y coma (;) <p>";
            PracticaIAutomatas.erroresLinea = true;
        }
    }    
}
