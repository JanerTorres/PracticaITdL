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
public class AutomataVariable {
    
    /* Método que recibe el nodo que le sigue al nodo que contiene una variable.
       Verifica que el valor del nodo siguiente sea válido dentro de la estructura.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
    */
    public void variableNextValue (Nodo siguiente){
        String nextClass = null;
        String nextValue = null;
        
        if (siguiente != null){
            nextClass = siguiente.getClase();
            nextValue = siguiente.getValor();
            if((!"operador".equals(nextClass)) && (!"separador".equals(nextClass)) && (!")".equals(nextValue))){
                System.out.println("Dato incorrecto");
                PracticaIAutomatas.labelTexto += "Dato incorrecto <p>";
                PracticaIAutomatas.erroresLinea = true;
            }
            else{
                System.out.println("Dato válido");
            }
        }else{
            System.out.println("Eror: Fata punto y coma (;)");
            PracticaIAutomatas.labelTexto += "Eror: Fata punto y coma (;) <p>";
            PracticaIAutomatas.erroresLinea = true;
        }
    }     
    
}
