package resources.botones;


import Login.Login;
import backend.backendAjustes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class Boton_Atras extends JButton {
    public Boton_Atras(JFrame ventana, String args){
        //Coloco el icono para el boton ajustando sus parametros

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


        URL iconURL = getClass().getResource("/resources/icons/dedo_icon.png");
        ImageIcon icono_atras = new ImageIcon(iconURL);
        //Lo redimensiono
        Image imagenOriginal = icono_atras.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(20, 20, Image.SCALE_DEFAULT);

        //Y lo coloco
        ImageIcon icono_definitivo = new ImageIcon(imagenRedimensionada);
        setIcon(icono_definitivo);
        setPreferredSize(new Dimension(55,30));



        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showOptionDialog(ventana, bundle_text.get().getString("confirmarcionLogout"),
                        bundle_text.get().getString("tituloConfirmarLogout"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{bundle_text.get().getString("Si"), bundle_text.get().getString("No")}, JOptionPane.YES_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Si se ha confirmado, se vuelve atras
                    String[] args = {};
                    ventana.dispose();
                    try {
                        Login.main(args);
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    // Si no se confirma, no ocurre nada
                }

            }
        });

    }
}
