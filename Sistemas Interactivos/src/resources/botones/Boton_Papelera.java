package resources.botones;

import backend.backendAjustes;
import bandejaPapelera.bandejaPapelera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class Boton_Papelera extends JButton {
    public Boton_Papelera(JFrame ventana, String args){

        //Idioma
        AtomicReference<Locale> currentLocale = new AtomicReference<>(Locale.getDefault());
        String lang = backendAjustes.obtenerIdioma(args);
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


        setText(bundle_text.get().getString("botonPapelera"));

        //Coloco el icono para el boton ajustando sus parametros
        URL iconURL = getClass().getResource("/resources/icons/papelera_icon.png");
        ImageIcon icono = new ImageIcon(iconURL);

        //Coloco el icono redimensionado
        icono.setImage(icono.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
        setIcon(icono);

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.setContentPane(new bandejaPapelera.Panel_Principal(ventana, args));
                ventana.validate();

            }
        });

    }
}
