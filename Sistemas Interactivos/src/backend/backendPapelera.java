package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class backendPapelera
{

    public static int numeroMails(String usuario)
    {
        int numeroMails = 0;
        //Leer numero de archivos dentro de la carpeta de la bandeja de entrada
        String rutaCarpeta = "src/backend/" + usuario + "/papelera";
        File carpeta = new File(rutaCarpeta);
        if (carpeta.exists())
        {
            numeroMails = carpeta.list().length;
        }
        else
        {
            numeroMails = 0;
        }
        return numeroMails;
    }

    public static void eliminarMensajes(String usuario, ArrayList<Integer> mensajesAEliminar) {
        String rutaCarpeta = "src/backend/" + usuario + "/papelera";
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        for (int i = 0; i < mensajesAEliminar.size(); i++)
        {
            File archivo = new File(rutaCarpeta + "/" + listaArchivos[mensajesAEliminar.get(i)]);
            archivo.delete();
        }
    }

    public static String[] mailCompleto(String usuario, int number) {
        String rutaCarpeta = "src/backend/" + usuario + "/papelera";
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        // De la lista de archivos, obtener el archivo con el numero number
        String nombreArchivo = listaArchivos[number];

        String rutaArchivo = "src/backend/" + usuario + "/papelera/" + nombreArchivo;
        File archivo = new File(rutaArchivo);

        String[] partes = new String[3];

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            // Leer el archivo completo en un String
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append(System.lineSeparator());
            }
            String archivoString = sb.toString();

            // Dividir el archivo en las tres partes usando el separador //
            String[] campos = archivoString.split(" \\/\\/ ");
            partes[0] = campos[0]; // remitente
            partes[1] = campos[1]; // asunto
            partes[2] = campos[2]; // cuerpo del mensaje
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eliminar la extensión del nombre del archivo
        nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 4);

        String[] datos = {partes[0], partes[1], nombreArchivo, partes[2]};

        return datos;
    }
}
