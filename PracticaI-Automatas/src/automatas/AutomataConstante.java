/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import static practicai.automatas.LSL.searchList;
import practicai.automatas.Nodo;
import practicai.automatas.PracticaIAutomatas;

/**
 *
 * @author MI AMIX Y YO
 */
public class AutomataConstante {
    
    public AutomataConstante(){}
    
    public static String[] invalidos = {"=","&","|"}; 
    public static String[] clasesInvalidas = {"tipo", "var", "bool", "constante", "error"};
    
    /* Método que recibe un nodo de clase constante y verifica que el valor corresponda a una constante.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
    */
    public void verificarConstante(Nodo nodoActual){
        char obj;
        boolean punto = false;
        boolean hayNumeros = false;
        String constante;
        constante = nodoActual.getValor();
        
        for (int a = 0; a < constante.length(); a++) {
            obj = constante.charAt(a);
            if(String.valueOf(obj).matches("[0-9]*")){
                hayNumeros = true;
            }
            if (String.valueOf(obj).equals(".") && punto == false) {
                punto = true;
            }else if (String.valueOf(obj).equals(".") && punto == true){
                System.out.println("Constante inválida: " + constante);
                PracticaIAutomatas.labelTexto += "Constante inválida: " + constante + "<p>";
                // ERROR
            }
        }
        if(hayNumeros = false){
            System.out.println("Constante inválida: " + constante);//ERROR: CONSTANTE INVALIDA
            PracticaIAutomatas.labelTexto += "Constante inválida: " + constante + "<p>";
            PracticaIAutomatas.erroresLinea = true;
        }
    }
    
    /* Método que recibe el nodo que le sigue al nodo que contiene una constante.
       Verifica que el valor del nodo siguiente sea válido dentro de la estructura.
       Este método no retorna nada ya que la idea es que únicamente almacene o muestre los errores (si los hay)
    */
    public void constanteNextValue (Nodo siguiente){
        String nextClass = null;
        String nextValue = null;
        
        if (siguiente != null){
            nextClass = siguiente.getClase();
            nextValue = siguiente.getValor();
            if(searchList(clasesInvalidas, nextClass) || searchList(invalidos, nextValue)){
                System.out.println("Error: Dato inválido desúes de constante (" + nextValue + ")");
                PracticaIAutomatas.labelTexto += "Error: Dato inválido desúes de constante (" + nextValue + ") <p>";
                PracticaIAutomatas.erroresLinea = true;
            }else{
                System.out.println("Dato válido luego de constante");
            }
        }else{
            System.out.println("Error: Falta punto y coma (;)");
            PracticaIAutomatas.labelTexto += "Error: Falta punto y coma (;) <p>";
            PracticaIAutomatas.erroresLinea = true;
        }
    }
}
