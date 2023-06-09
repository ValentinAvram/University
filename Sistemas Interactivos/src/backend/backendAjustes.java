package backend;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class backendAjustes {

    public static void setAjustes(String usuario, String idioma, Integer tamanoPag, String firma, String tema)
    {
        String rutaArchivo = "src/backend/" + usuario + "/settings.txt";
        File archivo = new File(rutaArchivo);
        // Comprobamos si el archivo existe, en caso de que no, lo creamos
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Escribimos en el archivo
        try {
            java.io.FileWriter escribir = new java.io.FileWriter(archivo, false);
            escribir.write(idioma + "\n");
            escribir.write(tamanoPag + "\n");
            escribir.write(firma + "\n");
            escribir.write(tema + "\n");
            escribir.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String obtenerFirma(String usuario)
    {
        String rutaArchivo = "src/backend/" + usuario + "/settings.txt";
        File archivo = new File(rutaArchivo);
        String firma = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            linea = reader.readLine();
            firma = reader.readLine();
            linea = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firma;
    }

    public static Integer obtenerTamanoPag(String usuario)
    {
        String rutaArchivo = "src/backend/" + usuario + "/settings.txt";
        File archivo = new File(rutaArchivo);
        Integer tamanoPag = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            tamanoPag = Integer.parseInt(reader.readLine());
            linea = reader.readLine();
            linea = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tamanoPag;
    }

    public static String obtenerTema(String usuario)
    {
        String rutaArchivo = "src/backend/" + usuario + "/settings.txt";
        File archivo = new File(rutaArchivo);
        String tema = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            linea = reader.readLine();
            linea = reader.readLine();
            tema = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tema;
    }

    public static String obtenerIdioma(String usuario)
    {
        String rutaArchivo = "src/backend/" + usuario + "/settings.txt";
        File archivo = new File(rutaArchivo);
        String idioma = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            idioma = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idioma;
    }
}
