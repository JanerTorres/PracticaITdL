/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicai.automatas;

import Vista.FileChooser;
import Vista.Principal;
import automatas.Automata;
import automatas.AutomataBoolean;
import automatas.AutomataConstante;
import automatas.AutomataOperacion;
import automatas.AutomataSeparador;
import automatas.AutomataTipoVariable;
import automatas.AutomataVariable;
import java.io.IOException;

/**
 *
 * @author Mi AMIX Y YO
 */
public class PracticaIAutomatas {

    
    public static String labelTexto;
    public static String ruta;
    public static boolean erroresLinea;
    
    
    public PracticaIAutomatas(){
        labelTexto = "";
        ruta = "";
        erroresLinea = false;
    }
    
    /**
     * @param args the command line arguments
     */
    public void EjecutarAnalisis() throws IOException{
        // TODO code application logic here
        erroresLinea = false;
        labelTexto = "";
        
        
        
        String linea;
        int numLinea = 1;
        Fichero fichero = new Fichero();
        fichero.leeArchivo(ruta);
        //String prueba = "int int = /39.1ol\"\"(";
        String prueba2 = "int String = (true\"+=false,;";
        LSL listaPrueba = new LSL();
        Automata automataGral = new Automata();
        //listaPrueba = listaPrueba.stringToLSL(prueba2);

        //listaPrueba.listar();

        

        //AutomataOperacion.entroIgual = false;
        //automataGral.recorrido(listaPrueba);

       
        while ((linea=fichero.br.readLine()) != null) { // sea asigna al mismo tiempo que se hace la comparación
            erroresLinea = false;
            System.out.println(" - Línea de código: " + numLinea + " - ");
            labelTexto += " - Línea de código: " + numLinea + " - <p><p>";
            labelTexto += linea + "<p><p>";
            listaPrueba = listaPrueba.stringToLSL(linea);
            AutomataOperacion.entroIgual = false;
            automataGral.recorrido(listaPrueba);
            
            listaPrueba.listar();
            
            numLinea++;
        }

        System.out.println("terminado");
        labelTexto += linea + "<p>";
        Principal.pVista.setSalida(labelTexto);
        
        
    }

    /*public static LSL stringToLSL(String linea) {
        LSL lista = new LSL();
        int ind = 0;
        char obj;
        String subst;
        estActual = EstadoRecorrido.nada;
        for (int a = 0; a < linea.length() + 1; a++) {
            if (a < linea.length()) {
                obj = linea.charAt(a);
            }
            else{
                obj = linea.charAt(a - 1);
            }
/
            // Verifica si el caracter es una letra
            if (Estructuras.isStringOnlyAlphabet(String.valueOf(obj))) {
                if (estActual == EstadoRecorrido.letras) {
                    if (a == linea.length()) {
                        subst = linea.substring(ind);
                        if (searchList(Estructuras.tiposVar, subst)) {
                            lista.insertar("tipo", subst);
                        } else if (searchList(Estructuras.tiposBool, subst)) {
                            lista.insertar("bool", subst);
                        } else {
                            lista.insertar("var", subst);
                        }
                    }
                    continue;
                }
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.letras;
                    ind = a;
                    continue;
                }

            } // Verifica si el caracter es un operador
            else if (searchList(Estructuras.tiposOper, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.oper;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.oper;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.oper;
                    continue;

                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.oper;
                    ind = a;
                    continue;

                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.oper;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.oper;
                    ind = a;
                    continue;
                }
            } // Verifica si el caracter es una separador
            else if (searchList(Estructuras.separadores, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.sep;
                    ind = a;
                    continue;
                }

            } // Verifica si el caracter es una signo de agrupación
            else if (searchList(Estructuras.signosAgru, String.valueOf(obj))) {
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operacion", subst);
                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                }else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.agru;
                    ind = a;
                    continue;
                }
            } // Verifica si el caracter es un número
            else if (Estructuras.isConstante(String.valueOf(obj))) {
                if (estActual == EstadoRecorrido.numeros) {
                    if(a == linea.length()){
                        subst = linea.substring(ind);
                        lista.insertar("constante", subst);
                    }
                    continue;
                }
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                }else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.numeros;
                    ind = a;
                    continue;
                }
                
            }

            if (linea.charAt(a) == ' ') {
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.nada) {
                    ind = a;
                    continue;
                }else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.nada;
                    ind = a;
                    continue;
                }
                
            } else {
                subst = linea.substring(ind, a);
                if (estActual == EstadoRecorrido.letras) {
                    // VERIFICAR PALABRAS ESPECIALES
                    if (searchList(Estructuras.tiposVar, subst)) {
                        lista.insertar("tipo", subst);
                    } else if (searchList(Estructuras.tiposBool, subst)) {
                        lista.insertar("bool", subst);
                    } else {
                        lista.insertar("var", subst);
                    }

                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.numeros) {
                    lista.insertar("constante", subst);
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.oper) {
                    lista.insertar("operador", subst);
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.sep) {
                    lista.insertar("separador", subst);
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.agru) {
                    lista.insertar("agrupacion", subst);
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                } else if (estActual == EstadoRecorrido.nada) {
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                }else if (estActual == EstadoRecorrido.error){
                    lista.insertar("error", String.valueOf(obj));
                    estActual = EstadoRecorrido.error;
                    ind = a;
                    continue;
                }
            }
        }

        /*System.out.println("salí");*/
 /*return lista;
    }

    // Método para buscar un string dentro de un arreglo de strings
    public static boolean searchList(String[] strings, String searchString) {
        return Arrays.asList(strings).contains(searchString);
    }*/
}
