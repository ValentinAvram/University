package backend;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class loginBackend {
    public static boolean verificarUsuario(String usuario, String contrasena) {
        try {

            //Si carpeta src no existe, crearla
            File carpetaSrc = new File("src");
            if (!carpetaSrc.exists()) {
                carpetaSrc.mkdir();
            }

            //Si carpeta backend no existe, crearla
            File carpetaBackend = new File("src/backend");
            if (!carpetaBackend.exists()) {
                carpetaBackend.mkdir();
            }

            String rutaArchivo = "src/backend/login.txt";
            File archivoUsuarios = new File(rutaArchivo);

            if (!archivoUsuarios.exists()) {
                archivoUsuarios.createNewFile();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contrasena)) {
                        // Crear carpeta para el usuario dentro de la carpeta de usuarios
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
                        return true;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
