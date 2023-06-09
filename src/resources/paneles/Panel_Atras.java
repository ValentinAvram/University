package resources.paneles;

import ajustes.ajustes;
import backend.backendAjustes;
import resources.botones.Boton_Atras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class Panel_Atras extends JPanel {
    public Panel_Atras(JFrame ventana, String args){

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

        setLayout(new GridBagLayout());


        JButton boton_atras = new Boton_Atras(ventana, args);
        GridBagConstraints b_atras = new GridBagConstraints();
        b_atras.gridx = 0; //
        b_atras.gridy = 0; //

        JLabel label_Atras = new JLabel(bundle_text.get().getString("Salir"));
        label_Atras.setFont(new Font("Arial", Font.BOLD, 20)); // cambia el tamaño de la fuente
        GridBagConstraints c_atras = new GridBagConstraints();
        c_atras.gridx = 1; //
        c_atras.gridy = 0; //

        add(boton_atras,b_atras);
        add(label_Atras, c_atras);



    }
}