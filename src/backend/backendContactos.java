package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class backendContactos
{

    public static int numeroContactos(String usuario)
    {
        int numeroMails = 0;
        //Leer numero de archivos dentro de la carpeta de la bandeja de entrada
        String rutaCarpeta = "src/backend/" + usuario + "/contactos";
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

    public static String[] datosContactos(String usuario, int number) {
        String rutaCarpeta = "src/backend/" + usuario + "/contactos";
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        // De la lista de archivos, obtener el archivo con el numero number
        String rutaArchivo = "src/backend/" + usuario + "/contactos/" + listaArchivos[number];
        File archivo = new File(rutaArchivo);

        String[] partes = new String[3];

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(" // ");
                partes[i++] = campos[0];  // direccion
                partes[i++] = campos[1];  // numero
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        partes[2] = archivo.getName();
        // Eliminar la extension del nombre del archivo
        partes[2] = partes[2].substring(0, partes[2].length() - 4);

        return partes;
    }

    public static void eliminarContactos(String usuario, ArrayList<Integer> mensajesAEliminar) {
        String rutaCarpeta = "src/backend/" + usuario + "/contactos";
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        //Eliminar los valores nulos del array de mensajes a eliminar

        for (int i = 0; i < mensajesAEliminar.size(); i++)
        {
            File archivo = new File(rutaCarpeta + "/" + listaArchivos[mensajesAEliminar.get(i)]);
            archivo.delete();
        }
    }

    public static void addContacto(String usuario, String nombre, String direccion, String numero) throws IOException {
        String rutaArchivo = "src/backend/" + usuario + "/contactos/" + nombre + ".txt";
        File archivo = new File(rutaArchivo);

        // Si el archivo no existe, crearlo
        if (!archivo.exists())
        {
            try
            {
                archivo.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Escribir en el archivo
        FileWriter writer = new FileWriter(archivo);
        writer.write(direccion + " // " + numero);
        writer.close();

    }

    public static boolean checkDireccion(String usuario, String direccion)
    {
        String rutaCarpeta = "src/backend/" + usuario + "/contactos";
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        for (int i = 0; i < listaArchivos.length; i++)
        {
            String rutaArchivo = "src/backend/" + usuario + "/contactos/" + listaArchivos[i];
            File archivo = new File(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(" // ");
                    if (Objects.equals(campos[0], direccion))
                    {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
