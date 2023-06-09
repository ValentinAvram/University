package backend;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class backendLeer {

    public static void borrarMensaje(String usuario, String remitente, String asunto, String ruta) {

        AtomicReference<Locale> currentLocale = new AtomicReference<>(Locale.getDefault());
        String lang = backendAjustes.obtenerIdioma(usuario);
        if(lang.equals("Español")){
            Locale.setDefault(new Locale("es", "ES"));
        }else if(lang.equals("English")){
            Locale.setDefault(new Locale("en", "GB"));
        }
        else if(lang.equals("Romana")){
            Locale.setDefault(new Locale("ro", "RO"));
        }
        else if(lang.equals("none")){

            //Si el Locale actual no está entre los soportados, asignar por defecto
            if(!((currentLocale.get().getLanguage().equals("es") && currentLocale.get().getCountry().equals("ES")) ||
                    (currentLocale.get().getLanguage().equals("en") && currentLocale.get().getCountry().equals("GB")) ||
                    (currentLocale.get().getLanguage().equals("ro") && currentLocale.get().getCountry().equals("RO"))
            )){
                currentLocale.set(new Locale("en", "GB"));
            }
        }

        AtomicReference<ResourceBundle> bundle_text = new AtomicReference<>(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));

        String rutaCarpeta = "src/backend/" + usuario + ruta;
        File carpeta = new File(rutaCarpeta);
        String[] listaArchivos = carpeta.list();

        String archivoABorrar = null;

        for (int i = 0; i < listaArchivos.length; i++) {
            String rutaArchivo = "src/backend/" + usuario + ruta + "/" + listaArchivos[i];
            File archivo = new File(rutaArchivo);

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(" // ");
                    if (campos[0].equals(remitente)) {
                        archivoABorrar = listaArchivos[i];
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (archivoABorrar != null) {
                break;
            }
        }

        if (archivoABorrar != null) {
            String rutaArchivoABorrar = "src/backend/" + usuario + ruta + "/" + archivoABorrar;
            File archivoABorrarFile = new File(rutaArchivoABorrar);

            // Mover el archivo a la papelera
            String rutaPapelera = "src/backend/" + usuario + "/papelera";
            File papelera = new File(rutaPapelera);
            if (!papelera.exists()) {
                papelera.mkdir();
            }

            String rutaArchivoPapelera = "src/backend/" + usuario + "/papelera/" + archivoABorrar;
            File archivoPapelera = new File(rutaArchivoPapelera);
            archivoABorrarFile.renameTo(archivoPapelera);

            // Eliminar el archivo de la bandeja de entrada
            archivoABorrarFile.delete();

        } else {
            JOptionPane.showMessageDialog(null, bundle_text.get().getString("errorMailNoEncontrado"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
