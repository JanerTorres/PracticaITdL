/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.Stack;
import practicai.automatas.Nodo;
import practicai.automatas.PracticaIAutomatas;

/**
 *
 * @author stefa
 */
public class AutomataAgrupacion {

    public static String[] signosAgru = {"\"", "(", ")"};

    /* Método que recibe un nodo (que contiene parentesis) y una pila. Básicamente se encarga de 
       apilar o desapilar en la pila los parentesis cada vez que se llame al método.
       Este método retorna la pila para no perder los datos contenidos en ella cada que se llame
       al método.
     */
    public Stack equilibrarParentesis(Nodo actual, Stack pila) {
        String actualValue = actual.getValor();

        if (AutomataOperacion.entroIgual) {
            if ("(".equals(actualValue)) {
                pila.push(actualValue);
            } else if (")".equals(actualValue)) {
                if (!pila.empty() && !")".equals(pila.peek())) {
                    pila.pop();
                } else {
                    pila.push(actualValue);
                }
            }
        }
        else{
            System.out.println("Error: Paréntesis ubicado en un lugar inválido");
            PracticaIAutomatas.labelTexto += "Error: Paréntesis ubicado en un lugar inválido <p>";
            PracticaIAutomatas.erroresLinea = true;
        }

        return pila;
    }

    /* Método que recibe un nodo (que contiene comillas) y una pila. Básicamente se encarga de 
       apilar o desapilar en la pila las comillas cada vez que se llame al método.
       Este método retorna la pila para no perder los datos contenidos en ella cada que se llame
       al método.
     */
    public Stack equilibrarComillas(Nodo actual, Stack pila) {
        String actualValue = actual.getValor();

        if (AutomataOperacion.entroIgual) {
            if ("\"".equals(actualValue) && pila.empty()) {
                pila.push(actualValue);
            } else {
                pila.pop();
            }

        } else {
            System.out.println("Error: Comillas ubicadas en un lugar inválido");
            PracticaIAutomatas.labelTexto += "Error: Comillas ubicadas en un lugar inválido <p>";
            PracticaIAutomatas.erroresLinea = true;
        }

        return pila;
    }

    /* Método que recibe una pila y nos devuelve un booleano indicando si se encuentra vacía o no.
       Esto con la finalidad de saber al final de la secuencia si los parentesis o comillas están 
       equilibradas.
     */
    public boolean validar(Stack pila) {
        return pila.empty();
    }
}
