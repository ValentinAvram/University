package backend;

import java.io.*;

public class backendRegister
{
    public static boolean checkUsuario(String usuario)
    {
        String rutaArchivo = new File("src/backend/login.txt").getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].equals(usuario))
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return false;
    }

    public static void newUser(String usuario, String password)
    {
        String rutaArchivo = new File("src/backend/login.txt").getAbsolutePath();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true));
            bw.write(usuario + "," + password);
            bw.newLine();
            bw.close();
            File carpetaUsuario = new File("src/backend/" + usuario);
            if (!carpetaUsuario.exists()) {
                carpetaUsuario.mkdir();
                // Crear carpeta para la bandeja de entrada dentro de la carpeta del usuario
                File carpetaBandejaEntrada = new File("src/backend/" + usuario + "/bandejaEntrada");
                carpetaBandejaEntrada.mkdir();
                File carpetaEnviados = new File("src/backend/" + usuario + "/enviados");
                carpetaEnviados.mkdir();
                File carpetaContactos = new File("src/backend/" + usuario + "/contactos");
                carpetaContactos.mkdir();
                File carpetaPapelera = new File("src/backend/" + usuario + "/papelera");
                carpetaPapelera.mkdir();
                File carpetaBorradores = new File("src/backend/" + usuario + "/borradores");
                carpetaBorradores.mkdir();

                String rutaArchivoSet = "src/backend/" + usuario + "/settings.txt";
                File archivo = new File(rutaArchivoSet);
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
                    escribir.write("Español" + "\n");
                    escribir.write("0" + "\n");
                    escribir.write("" + "\n");
                    escribir.write("Nimbus" + "\n");
                    escribir.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
