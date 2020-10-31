/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import practicai.automatas.LSL;
import practicai.automatas.Nodo;
import practicai.automatas.PracticaIAutomatas;

/**
 *
 * @author stefa
 */
public class AutomataTipoVariable {
    
    public static String[] tiposVar = {"boolean","String","int","double"};
    
    /* Método que recibe el nodo que le sigue al nodo que contiene un tipo de variable.
       Verifica que el valor del nodo siguiente sea válido dentro de la estructura.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
    */
    public void tipoNextValue (Nodo siguiente){

        if (siguiente != null){
            String nextClass = siguiente.getClase();
            String nextValue = siguiente.getValor();
            if(!"var".equals(nextClass)){
                System.out.println("(ERROR) Dato inválido después de tipo de variable:  (" + siguiente.getValor() + ")");//ERROR: DATO INVALIDO DESPUES DEL TIPO
                PracticaIAutomatas.labelTexto += "(ERROR) Dato inválido después de tipo de variable:  (" + siguiente.getValor() + ") <p>";
                PracticaIAutomatas.erroresLinea = true;
                if(LSL.searchList(Estructuras.palReservadas, nextValue)){
                    System.out.println("No puede usar palabras reservadas para nombrar una variable.");
                    PracticaIAutomatas.labelTexto += "No puede usar palabras reservadas para nombrar una variable. <p>";
                }
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
