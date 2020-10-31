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
 * @author torre
 */
public class AutomataOperacion {
    
    public AutomataOperacion(){}
    
    
    public static boolean entroIgual = false; // Se debe reiniciar a false cada que recorra una nueva línea de código
    public static String[] tiposOper = {"=","+","-","*","/","&","|"};
           
    public boolean operacionNextValue (Nodo actual, Nodo siguiente){
        String actualValue = actual.getValor();
        String nextClass = null;
        String nextValue = null;
        boolean dosOperadores = false;
        //entroIgual = false;

        if(siguiente != null){
            nextClass = siguiente.getClase();
            nextValue = siguiente.getValor();
            if("=".equals(actualValue) && entroIgual == false){
                if(!"(".equals(nextValue) && !"\"".equals(nextValue) && !"bool".equals(nextClass) && !"constante".equals(nextClass)){
                    System.out.println("Error: Dato inválido después de operador = (" + nextValue + ")");//ERROR: DATO INVALIDO DESPUES DEL OPERADOR =
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido después de operador = (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                }else{
                    System.out.println("Dato válido");
                }
                entroIgual = true;
            }
            else if("+".equals(actualValue) || "-".equals(actualValue)){
                if(!"=".equals(nextValue) && !"constante".equals(nextClass) && !"var".equals(nextClass)){
                    System.out.println("Error: Dato inválido despues de operador " + actualValue + "  (" + nextValue + ")");//ERROR: DATO INVALIDO DESPUES DEL OPERADOR + O -
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido despues de operador " + actualValue + "  (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                }
                else if ("=".equals(nextValue)){
                    dosOperadores = true;
                    System.out.println("Dato válido");
                }else{
                    System.out.println("Dato válido");
                }
            }
            else if("&".equals(actualValue)){
                if(!"&".equals(nextValue) && !"bool".equals(nextClass)){
                    System.out.println("Error: Dato inválido después de operador &  (" + nextValue + ")");//ERROR: DATO INVALITO DESPUES DEL OPERADOR &
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido después de operador &  (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                }
                else if ("&".equals(nextValue)){
                    dosOperadores = true;
                    System.out.println("Dato válido");
                }else{
                    System.out.println("Dato válido");
                }
            }
            else if("|".equals(actualValue)){
                if(!"|".equals(nextValue) && !"bool".equals(nextClass)){
                    System.out.println("Error: Dato inválido después de operador |  (" + nextValue + ")");//ERROR: DATO INVALITO DESPUES DEL OPERADOR &
                    PracticaIAutomatas.labelTexto += "Error: Dato inválido después de operador |  (" + nextValue + ") <p>";
                    PracticaIAutomatas.erroresLinea = true;
                }
                else if ("|".equals(nextValue)){
                    dosOperadores = true;
                    System.out.println("Dato válido");
                }else{
                    System.out.println("Dato válido");
                }
            }
            else if("+".equals(actualValue) || "-".equals(actualValue) || "*".equals(actualValue) || "/".equals(actualValue)){
                if("+".equals(nextValue) || "-".equals(nextValue) || "*".equals(nextValue) || "/".equals(nextValue)){
                    System.out.println("Error: No pueden haber dos operadores seguidos");//ERROR: NO PUEDEN HABER DOS OPERADORES SEGUIDOS
                    PracticaIAutomatas.labelTexto += "Error: No pueden haber dos operadores seguidos <p>";
                    PracticaIAutomatas.erroresLinea = true;
                }else{
                    System.out.println("Dato válido");
                }
            }
            else if(entroIgual == true){
                System.out.println("Error: Ya había entrado el signo de asignación (=) ");
                PracticaIAutomatas.labelTexto += "Error: Ya había entrado el signo de asignación (=) <p>";
                PracticaIAutomatas.erroresLinea = true;
            }
        }else{
            System.out.println("Error: Falta punto y coma (;)");
            PracticaIAutomatas.labelTexto += "Error: Falta punto y coma (;) <p>";
            PracticaIAutomatas.erroresLinea = true;
        } 
        return dosOperadores;
    }
}

    
    


    

