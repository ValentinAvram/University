package backend;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class backendRedactar {
    public static void guardarEnviarMail(String destinatario, String asunto, String mensaje, String usuario) {

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

        String rutaArchivoEnviado = "src/backend/" + usuario + "/enviados/" + asunto + ".txt";
        String rutaArchivoRecibido = "src/backend/" + destinatario + "/bandejaEntrada/" + asunto + ".txt";

        File archivoEnviado = new File(rutaArchivoEnviado);
        File archivoRecibido = new File(rutaArchivoRecibido);
        System.out.println("Al enviar se envia: " + mensaje);
        String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        // Comprobar si existen las carpetas de enviados y bandeja de entrada
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
        }

        File carpetaDestinatario = new File("src/backend/" + destinatario);
        if (!carpetaDestinatario.exists()) {
            carpetaDestinatario.mkdir();
            // Crear carpeta para la bandeja de entrada dentro de la carpeta del usuario
            File carpetaBandejaEntrada = new File("src/backend/" + destinatario + "/bandejaEntrada");
            carpetaBandejaEntrada.mkdir();
            File carpetaEnviados = new File("src/backend/" + destinatario + "/enviados");
            carpetaEnviados.mkdir();
            File carpetaContactos = new File("src/backend/" + destinatario + "/contactos");
            carpetaContactos.mkdir();
            File carpetaPapelera = new File("src/backend/" + destinatario + "/papelera");
            carpetaPapelera.mkdir();
            File carpetaBorradores = new File("src/backend/" + destinatario + "/borradores");
            carpetaBorradores.mkdir();
        }

        // TODO: Crear archivos en las rutas indicadas
        try {
            // Crear archivo de correo enviado
            archivoEnviado.createNewFile();
            FileWriter writer = new FileWriter(archivoEnviado);
            writer.write(destinatario + " // " + fechaActual + " // " + mensaje);
            writer.close();

            // Crear archivo de correo recibido
            archivoRecibido.createNewFile();
            writer = new FileWriter(archivoRecibido);
            writer.write(usuario + " // " + fechaActual + " // " + mensaje);
            writer.close();

            JOptionPane.showMessageDialog(null, bundle_text.get().getString("correoEnviado"), bundle_text.get().getString("correoEnviadoTitulo"), JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, bundle_text.get().getString("errorEnviarCorreo"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void guardarBorrador(String destinatario, String asunto, String mensaje, String usuario) {

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


        String rutaArchivoEnviado = "src/backend/" + usuario + "/borradores/" + asunto + ".txt";
        // Si existe el borrador, borrarlo
        File archivoBorrador = new File(rutaArchivoEnviado);
        if (archivoBorrador.exists()) {
            archivoBorrador.delete();
        }

        File archivoEnviado = new File(rutaArchivoEnviado);

        String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        // TODO: Crear archivos en las rutas indicadas
        try {
            // Crear archivo de correo enviado
            archivoEnviado.createNewFile();
            FileWriter writer = new FileWriter(archivoEnviado);
            String[] lineasMensaje = mensaje.split(System.getProperty("line.separator"));
            for (String linea : lineasMensaje) {
                writer.write(destinatario + " // " + fechaActual + " // " + linea + System.getProperty("line.separator"));
            }
            writer.close();
            JOptionPane.showMessageDialog(null, bundle_text.get().getString("borradorGuardado"), bundle_text.get().getString("borradorGuardadoTitulo"), JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, bundle_text.get().getString("errorGuardarBorrador"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
        }
    }

}
