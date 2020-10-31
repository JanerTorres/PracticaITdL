/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicai.automatas;

import automatas.AutomataAgrupacion;
import automatas.AutomataBoolean;
import automatas.AutomataOperacion;
import automatas.AutomataSeparador;
import automatas.AutomataTipoVariable;
import automatas.Estructuras;
import java.util.Arrays;

/**
 *
 * @author MI AMIX Y YO 
 */
public class LSL {
    
    private Nodo primero;
    private int numElmentos;
    
    public LSL(){
        primero = null;
        numElmentos = 0;
    }
    
    // Por el momento solo necesitamos insertar nodos al final de la lista
    public void insertar(String clase, String elemento){
        Nodo nuevo = new Nodo(clase, elemento);
        if(primero == null){
            primero = nuevo;
        } 
        else{
            Nodo actual = primero;
            while(actual.getLiga() != null){
                actual = actual.getLiga();
            }
            actual.setLiga(nuevo);
        }
        numElmentos++;
    }
    
    public void listar(){
        Nodo actual = primero;
        while(actual != null){
            // Ponga el codigo de la impresión de nodo
            System.out.println("| " + actual.getClase() + " |" + actual.getValor() + " | -> ");
            PracticaIAutomatas.labelTexto += "| " + actual.getClase() + " | " + actual.getValor() + " | ";
            if(actual.getLiga() != null){
                PracticaIAutomatas.labelTexto += "-> ";
            }
            else{
                PracticaIAutomatas.labelTexto += "fin de secuencia ¬";
            }
            actual = actual.getLiga();
        }
        System.out.println("---------------------------------"); 
        PracticaIAutomatas.labelTexto += "<p>----------------------------------------------------------" + "<p>";
    }
    
    public Nodo getPrimero(){
        return primero;
    }
    
    public int getNumElementos(){
        return numElmentos;
    } 
    
    public String archivo;

    public enum EstadoRecorrido {
        letras, numeros, oper, sep, agru, boole, tipoVar, nada, error
    };

    public static EstadoRecorrido estActual;
    
    public LSL stringToLSL(String linea) {
        LSL lista = new LSL();
        int ind = 0;
        char obj;
        String subst;
        estActual = LSL.EstadoRecorrido.nada;
        for (int a = 0; a < linea.length() + 1; a++) {
            if (a < linea.length()) {
                obj = linea.charAt(a);
            }
            else{
                obj = linea.charAt(a - 1);
            }

            // Verifica si el caracter es una letra
            if (Estructuras.isStringOnlyAlphabet(String.valueOf(obj))) {
                if (estActual == LSL.EstadoRecorrido.letras) {
                    if (a == linea.length()) {
                        subst = linea.substring(ind);
                        if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                            lista.insertar("tipo", subst);
                        } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                            lista.insertar("bool", subst);
                        } else {
                            lista.insertar("var", subst);
                        }
                    }
                    continue;
                }
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.letras;
                    ind = a;
                    continue;
                }

            } // Verifica si el caracter es un operador
            else if (searchList(AutomataOperacion.tiposOper, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.oper;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.oper;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.oper;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.oper;
                    ind = a;
                    continue;

                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.oper;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.oper;
                    ind = a;
                    continue;
                }
            } // Verifica si el caracter es una separador
            else if (searchList(AutomataSeparador.separadores, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.sep;
                    ind = a;
                    continue;
                }

            } // Verifica si el caracter es una signo de agrupación
            else if (searchList(AutomataAgrupacion.signosAgru, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                }else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.agru;
                    ind = a;
                    continue;
                }
            } // Verifica si el caracter es un número
            else if (Estructuras.isConstante(String.valueOf(obj))) {
                if (estActual == LSL.EstadoRecorrido.numeros) {
                    if(a == linea.length()){
                        subst = linea.substring(ind);
                        lista.insertar("constante", subst);
                    }
                    continue;
                }
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                }else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                }
                
            }

            if (linea.charAt(a) == ' ') {
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    ind = a;
                    continue;
                }else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.nada;
                    ind = a;
                    continue;
                }
                
            } else {
                subst = linea.substring(ind, a);
                if (estActual == LSL.EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(AutomataTipoVariable.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(AutomataBoolean.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == LSL.EstadoRecorrido.nada) {
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                }else if (estActual == LSL.EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = LSL.EstadoRecorrido.error;
                    ind = a;
                    continue;
                }
            }
        }
        return lista;
    }

    // Método para buscar un string dentro de un arreglo de strings
    public static boolean searchList(String[] strings, String searchString) {
        return Arrays.asList(strings).contains(searchString);
    }
}
