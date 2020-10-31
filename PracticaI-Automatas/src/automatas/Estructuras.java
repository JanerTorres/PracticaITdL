/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author MI AMIX Y YO
 */
public class Estructuras {
    
    public static String[] palReservadas = {"boolean","String","int","double","true","false"};
    /*public static String[] tiposVar = {"boolean","String","int","double"};*/
    
    /*public static String[] tiposBool = {"true","false"};*/
    
    //public static String[] tiposOper = {"=","+","-","*","/","&","|"};*/
    
    /*public static String[] separadores = {",",";"};*/

    /*public static String[] signosAgru = {"\"","(",")"};*/
    
    public static boolean isStringOnlyAlphabet(String str) {
        return (str.matches("^[a-zA-Z]*$") || str.equals("_"));
    }
    
    public static boolean isConstante(String str){
        return(str.matches("[0-9]*") || str.equals("."));
    }
    
    
}
