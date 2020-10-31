/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicai.automatas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author MI AMIX Y YO
 */
public class Fichero {

    public File archivo = null;
    public FileReader fr = null;
    public BufferedReader br = null;

    public Fichero() {

    }

    public void leeArchivo(String url) {

        try {
            //Abrimos el archivo y creamos el BufferedReader para leerlo con el método ReadLine()
            archivo = new File(url);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
