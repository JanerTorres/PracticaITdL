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
 * @author MI AMIX Y YO
 */
public class AutomataSeparador {

    public static String[] separadores = {",", ";"};

    /* Método que recibe un nodo actual (que contiene un separador) y el nodo que le sigue.
       Verifica que el valor del nodo siguiente sea válido dentro de la estructura.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
     */
    public void separadorNextValue(Nodo actual, Nodo siguiente) {
        String actualValue = actual.getValor();
        String nextClass = null;
        String nextValue = null;

        if (siguiente != null) {
            nextClass = siguiente.getClase();
            nextValue = siguiente.getValor();
            if (",".equals(actualValue)) {
                if (!"var".equals(nextClass)) {
                    System.out.println("Error: Dato inválido después de separador ,  (" + nextValue + ")");//ERROR: DATO INVALIDO DESPUES DEL SEPARADOR ,
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido después de separador ,  (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                } else {
                    System.out.println("Dato válido");
                }
            } else {
                if (!"¬".equals(nextValue)/*fin de secuencia*/) {
                    System.out.println("Error: Dato inválido después de ;  (" + nextValue + ")");//ERROR: DATO INVALIDO DESPUES DEL ;
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido después de ;  (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                } else {
                    System.out.println("Dato válido");
                }
            }
        } else {
            /*fin de secuencia*/
            if(!";".equals(actualValue)){
                System.out.println("Error: Falta punto y coma (;)");
                PracticaIAutomatas.labelTexto += "Error: Falta punto y coma (;) <p>";
                PracticaIAutomatas.erroresLinea = true;
            }
            else{
                System.out.println("Dato válido, fin de secuencia");
                PracticaIAutomatas.labelTexto += "Dato válido, fin de secuencia <p>";
            }
            
        }
    }
}
