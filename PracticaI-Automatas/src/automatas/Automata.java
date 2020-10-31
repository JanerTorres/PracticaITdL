/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.Stack;
import practicai.automatas.LSL;
import practicai.automatas.Nodo;
import practicai.automatas.PracticaIAutomatas;

/**
 *
 * @author stefa
 */
public class Automata {

    /* Método que recibe una LSL y la recorre a la vez que hace llamados a los automátas
       para realizar las respectivas validaciones.
     */
    public void recorrido(LSL lista) {

        String claseActual;
        String valorActual;
        Nodo primerNodo;
        Nodo nodoActual;
        Nodo nodoSiguiente;
        primerNodo = lista.getPrimero();
        nodoActual = primerNodo;
        Stack<String> pilaParen = new Stack<>();
        Stack<String> pilaComil = new Stack<>();
        
        PracticaIAutomatas.erroresLinea = false;
        
        while (nodoActual != null) {
            claseActual = nodoActual.getClase();
            valorActual = nodoActual.getValor();
            nodoSiguiente = nodoActual.getLiga();

            if ("constante".equals(claseActual)) {
                AutomataConstante automata = new AutomataConstante();
                automata.verificarConstante(nodoActual);
                automata.constanteNextValue(nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("tipo".equals(claseActual)) {
                AutomataTipoVariable automata = new AutomataTipoVariable();
                automata.tipoNextValue(nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("var".equals(claseActual)) {
                AutomataVariable automata = new AutomataVariable();
                automata.variableNextValue(nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("operador".equals(claseActual)) {
                AutomataOperacion automata = new AutomataOperacion();
                automata.operacionNextValue(nodoActual, nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("agrupacion".equals(claseActual)) {
                AutomataAgrupacion automata = new AutomataAgrupacion();
                if ("(".equals(valorActual) || ")".equals(valorActual)) {
                    automata.equilibrarParentesis(nodoActual, pilaParen);
                    nodoActual = nodoActual.getLiga();
                } else {
                    automata.equilibrarComillas(nodoActual, pilaComil);
                    nodoActual = nodoActual.getLiga();
                }
            } else if ("bool".equals(claseActual)) {
                AutomataBoolean automata = new AutomataBoolean();
                automata.booleanNextValue(nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("separador".equals(claseActual)) {
                AutomataSeparador automata = new AutomataSeparador();
                automata.separadorNextValue(nodoActual, nodoSiguiente);
                nodoActual = nodoActual.getLiga();

            } else if ("error".equals(claseActual)) {
                System.out.println("Error: Dato Invalido" + valorActual);
                PracticaIAutomatas.labelTexto += "Error: Dato Invalido" + valorActual + "<p>";
                PracticaIAutomatas.erroresLinea = true;
                nodoActual = nodoActual.getLiga();
            } 
        }

        // Verificamos los signos de agrupación al final
        AutomataAgrupacion automata = new AutomataAgrupacion();
        boolean validacionP, validacionC;
        validacionP = automata.validar(pilaParen);
        validacionC = automata.validar(pilaComil);
        if (validacionP == false) {
            System.out.println("Error: Parentesis NO equilibrados");
            PracticaIAutomatas.labelTexto += "Error: Parentesis NO equilibrados" + "<p>";
            PracticaIAutomatas.erroresLinea = true;
        }
        if (validacionC == false) {
            System.out.println("Error: Comillas NO equilibradas");
            PracticaIAutomatas.labelTexto += "Error: Comillas NO equilibradas" + "<p>";
            PracticaIAutomatas.erroresLinea = true;
        }
        
        if(PracticaIAutomatas.erroresLinea == false){
            System.out.println("SIN ERRORES");
            PracticaIAutomatas.labelTexto += "LINEA SIN ERRORES <p>";
        }
        
        
        
        
        
    }
}
