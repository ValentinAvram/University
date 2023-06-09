package Añadir_Contacto;

import Login.Login;
import backend.backendAjustes;
import backend.backendContactos;
import bandejaEnviados.bandejaEnviados;
import bandejaPapelera.bandejaPapelera;
import contactos.contactos;
import redactar.redactar;
import resources.botones.*;
import resources.paneles.Panel_Atras;
import resources.paneles.Panel_Titulo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class Añadir_Contacto {

    static public class Panel_Principal extends JPanel {
        public Panel_Principal(JFrame ventana, String args) {

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


            ventana.setTitle(bundle_text.get().getString("tituloAñadirContacto"));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margen de 10px en cada borde
            setBackground(Color.GRAY);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);

            //Un panel para cada parte de la ventana, esta se dividira en un 2x2 donde p1 = Atras, p2 = Bandeja de Entrada, p3 = Menu, p4 = Mensajes
            JPanel panel1 = new Panel_Atras(ventana, args);
            JPanel panel2 = new Panel_Titulo(ventana, args);
            JPanel panel3 = new JPanel(new GridLayout(10, 1));
            JPanel panel4 = new JPanel(new GridBagLayout());

            //"Atras"
            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = 0;
            c1.gridy = 0;
            c1.weightx = 0.1;
            c1.weighty = 0.1;
            c1.fill = GridBagConstraints.BOTH;

            //"Bandeja de Entrada"
            GridBagConstraints c2 = new GridBagConstraints();
            c2.gridx = 1;
            c2.gridy = 0;
            c2.weightx = 0.9;
            c2.weighty = 0.1;
            c2.fill = GridBagConstraints.BOTH;

            //"Menu"
            GridBagConstraints c3 = new GridBagConstraints();
            c3.gridx = 0;
            c3.gridy = 1;
            c3.weightx = 0.1;
            c3.weighty = 0.8;
            c3.fill = GridBagConstraints.BOTH;

            //"Mensajes"
            GridBagConstraints c4 = new GridBagConstraints();
            c4.gridx = 1;
            c4.gridy = 1;
            c4.weightx = 0.9;
            c4.weighty = 0.8;
            c4.fill = GridBagConstraints.BOTH;

            //Añadimos los 4 paneles hijos al padre

            add(panel1, c1);
            add(panel2, c2);
            add(panel3, c3);
            add(panel4, c4);

            panel1.setBackground(Color.LIGHT_GRAY);
            panel2.setBackground(Color.LIGHT_GRAY);
            panel3.setBackground(Color.LIGHT_GRAY);
            panel4.setBackground(Color.LIGHT_GRAY);

            //Defino el borde que va a tener cada panel
            //(arriba, derecha, abajo, izquierda)
            Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);
            Border borde_up_left = BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK);
            Border borde_up_right = BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK);
            Border borde_down_left = BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK);
            Border borde_down_right = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);

            panel1.setBorder(borde_up_left);
            panel2.setBorder(borde_up_right);
            panel3.setBorder(borde_down_left);
            panel4.setBorder(borde_down_right);

            JPanel Menu_Gris = new JPanel(new FlowLayout());

            JLabel label_Menu = new JLabel(bundle_text.get().getString("menu"));
            label_Menu.setFont(new Font("Arial", Font.BOLD, 20)); // cambia el tamaño de la fuente
            Border border_Menu =BorderFactory.createMatteBorder(0,0, 1, 0, Color.BLACK);
            GridBagConstraints c_menu = new GridBagConstraints();
            c_menu.gridx = 0;
            c_menu.gridy = 1;
            c_menu.fill = GridBagConstraints.BOTH;
            c_menu.anchor = GridBagConstraints.CENTER;

            Menu_Gris.setBackground(Color.LIGHT_GRAY);
            Menu_Gris.add(label_Menu, c_menu);
            Menu_Gris.setBorder(border_Menu);

            //Label de "mensajes" y Panel para el propio contenido de los mensajes:

            JLabel label_Mensajes = new JLabel(bundle_text.get().getString("mensajes"));
            label_Mensajes.setFont(new Font("Arial", Font.BOLD, 20)); // cambia el tamaño de la fuente
            label_Mensajes.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            Border border_Mensajes = BorderFactory.createMatteBorder(0,0, 1, 0, Color.BLACK);

            GridBagConstraints c_mensajes = new GridBagConstraints();
            c_mensajes.anchor = GridBagConstraints.WEST;

            //Panel blanco:
            //Panel que muestrará el contenido de los ajustes:

            JPanel contenido = new JPanel(new GridBagLayout());
            GridBagConstraints c_contenido = new GridBagConstraints();
            c_contenido.gridx = 0;
            c_contenido.gridy = 2;
            c_contenido.weightx = 1;
            c_contenido.weighty = 0.9;
            c_contenido.fill = GridBagConstraints.BOTH;
            c_contenido.anchor = GridBagConstraints.WEST;
            //Añado el label al panel y despues añadire el panel al panel4

            //Añadimos los titulos de cada panel
            panel3.add(Menu_Gris);
            panel4.add(contenido, c_contenido);

            //Creo los botones de Bandeja de entrada (Redactar, eleminar mensajes y atrás)

            JLabel label_espaciado = new JLabel((""));
            GridBagConstraints c_espaciado = new GridBagConstraints();
            c_espaciado.gridy = 0;
            c_espaciado.gridx = 1;
            c_espaciado.insets.right = 20;
            label_espaciado.setPreferredSize(new Dimension(250,60));

            JLabel label_espaciado2 = new JLabel((""));
            GridBagConstraints c_espaciado2 = new GridBagConstraints();
            c_espaciado2.gridy = 0;
            c_espaciado2.gridx = 2;
            c_espaciado2.insets.right = 20;
            label_espaciado2.setPreferredSize(new Dimension(250,60));


            //Los añado a sus respectivos paneles

            panel2.add(label_espaciado, c_espaciado);
            panel2.add(label_espaciado2, c_espaciado2);


            //Creo los botones del Menú: Enviados, Recibidos, Papelera, Contactos y Ajustes

            JButton boton_recibidos = new Boton_Recibidos(ventana, args);
            GridBagConstraints b_recibidos = new GridBagConstraints();
            b_recibidos.gridx = 0;
            b_recibidos.gridy = 2;

            JButton boton_enviados = new Boton_Enviados(ventana, args);
            GridBagConstraints b_enviados = new GridBagConstraints();
            b_enviados.gridx = 0;
            b_enviados.gridy = 3;

            JButton boton_borradores = new Boton_Borradores(ventana, args);
            GridBagConstraints b_borradores = new GridBagConstraints();
            b_borradores.gridx = 0;
            b_borradores.gridy = 4;

            JButton boton_papelera = new Boton_Papelera(ventana, args);
            GridBagConstraints b_papelera = new GridBagConstraints();
            b_papelera.gridx = 0;
            b_papelera.gridy = 5;


            JButton boton_contactos = new Boton_Contactos(ventana, args);
            GridBagConstraints b_contactos = new GridBagConstraints();
            b_contactos.gridx = 0;
            b_contactos.gridy = 6;

            boton_contactos.setBackground(Color.gray);


            JButton boton_ajustes = new Boton_Ajustes(ventana, args);
            GridBagConstraints b_ajustes = new GridBagConstraints();
            b_ajustes.gridx = 0;
            b_ajustes.gridy = 7;

            //Los añado al panel del Menú

            panel3.add(boton_recibidos,boton_recibidos);
            panel3.add(boton_enviados,b_enviados);
            panel3.add(boton_borradores, b_borradores);
            panel3.add(boton_papelera,b_papelera);
            panel3.add(boton_contactos,b_contactos);
            panel3.add(boton_ajustes,b_ajustes);

            //Contenido del panel blanco: TODO

            //Hago un par de paneles para que todo quede organizado
            JPanel Panel_Configuraciones = new JPanel(new GridBagLayout());
            Panel_Configuraciones.setBackground(Color.WHITE);
            Panel_Configuraciones.setBorder(BorderFactory.createMatteBorder(1,1, 1, 1, Color.GRAY));
            GridBagConstraints c_panel_configuraciones = new GridBagConstraints();
            c_panel_configuraciones.gridy = 0;
            c_panel_configuraciones.gridx = 0;
            c_panel_configuraciones.weightx = 1;
            c_panel_configuraciones.weighty = 0.85;
            c_panel_configuraciones.fill = GridBagConstraints.BOTH;

            c_panel_configuraciones.insets.right = 60;
            c_panel_configuraciones.insets.left = 60;
            c_panel_configuraciones.insets.top = 60;
            c_panel_configuraciones.insets.bottom = 00;


            JPanel Panel_Boton_Aceptar = new JPanel(new GridBagLayout());
            GridBagConstraints c_panel_aceptar = new GridBagConstraints();
            c_panel_aceptar.gridy = 1;
            c_panel_aceptar.gridx = 0;
            c_panel_aceptar.weightx = 1;
            c_panel_aceptar.weighty = 0.15;
            c_panel_aceptar.fill = GridBagConstraints.BOTH;

            //Añadimos los dos paneles pequeños a CONTENIDO

            contenido.add(Panel_Configuraciones, c_panel_configuraciones);
            contenido.add(Panel_Boton_Aceptar, c_panel_aceptar);

            //Hago una fuente para las labels de los contactos se vean mas grandes

            Font Fuente_Contactos = new Font("Dialog", Font.PLAIN, 15);

            //NOMBRE

            JLabel nombre_label = new JLabel(bundle_text.get().getString("nombre"));
            nombre_label.setFont(Fuente_Contactos);
            GridBagConstraints c_nombre_label = new GridBagConstraints();
            c_nombre_label.gridx = 0;
            c_nombre_label.gridy = 0;
            c_nombre_label.insets.bottom = 20;
            c_nombre_label.anchor = GridBagConstraints.WEST;

            JTextField nombre_field = new JTextField(20);
            GridBagConstraints c_nombre_field = new GridBagConstraints();
            c_nombre_field.gridx = 1;
            c_nombre_field.gridy = 0;
            c_nombre_field.insets.bottom = 20;
            c_nombre_field.anchor = GridBagConstraints.WEST;

            //DIRECCIÓN
            JLabel direccion_label = new JLabel(bundle_text.get().getString("direccionCorreo"));
            direccion_label.setFont(Fuente_Contactos);
            GridBagConstraints c_direccion_label = new GridBagConstraints();
            c_direccion_label.gridx = 0;
            c_direccion_label.gridy = 1;
            c_direccion_label.insets.bottom = 20;
            c_direccion_label.anchor = GridBagConstraints.WEST;


            JTextField direccion_field = new JTextField(20);
            GridBagConstraints c_direccion_field = new GridBagConstraints();
            c_direccion_field.gridx = 1;
            c_direccion_field.gridy = 1;
            c_direccion_field.insets.bottom = 20;
            c_direccion_field.anchor = GridBagConstraints.WEST;


            //NÚMERO

            JLabel numero_label = new JLabel(bundle_text.get().getString("numeroTelefono"));
            numero_label.setFont(Fuente_Contactos);
            GridBagConstraints c_numero_label = new GridBagConstraints();
            c_numero_label.gridx = 0;
            c_numero_label.gridy = 2;
            c_numero_label.anchor = GridBagConstraints.WEST;


            JTextField numero_field = new JTextField(20);
            GridBagConstraints c_numero_field = new GridBagConstraints();
            c_numero_field.gridx = 1;
            c_numero_field.gridy = 2;
            c_numero_field.anchor = GridBagConstraints.WEST;

            //Añadimos estos campos al panel de contenido

            Panel_Configuraciones.add(nombre_label, c_nombre_label);
            Panel_Configuraciones.add(nombre_field, c_nombre_field);
            Panel_Configuraciones.add(direccion_label, c_direccion_label);
            Panel_Configuraciones.add(direccion_field, c_direccion_field);
            Panel_Configuraciones.add(numero_label, c_numero_label);
            Panel_Configuraciones.add(numero_field, c_numero_field);

            //Boton para añadir el contacto del cual hemos rellenado los datos
            JButton Boton_Aceptar = new JButton(bundle_text.get().getString("añadirContacto"));
            GridBagConstraints c_Boton_Aceptar = new GridBagConstraints();
            c_Boton_Aceptar.anchor = GridBagConstraints.CENTER;
            c_Boton_Aceptar.ipadx = 70;
            c_Boton_Aceptar.ipady = 20;
            c_Boton_Aceptar.insets.right = 20;


            Panel_Boton_Aceptar.add(Boton_Aceptar, c_Boton_Aceptar);

            Boton_Aceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nombre_field.getText();
                    String direccion = direccion_field.getText();
                    String numero = numero_field.getText();
                    boolean isNumeric = false;
                    // comprobar si el string numero es un numero
                    if(numero.matches("\\d+")) {
                        isNumeric = true;
                    }
                    boolean formatoMail = false;

                    // Compobar si el string direccion es un mail
                    if(direccion.contains("@")) {
                        formatoMail = true;
                    }

                    boolean checkDireccion = backendContactos.checkDireccion(args, direccion);

                    if (nombre.equals("") || direccion.equals("") || numero.equals("")){
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("camposVacios"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!isNumeric){
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("numeroTelefonoIncorrecto"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!formatoMail){
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("formatoMailIncorrecto"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else if (checkDireccion)
                    {
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("errorDireccion"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try {
                            backendContactos.addContacto(args, nombre, direccion, numero);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("contactoAñadido"), bundle_text.get().getString("tituloAñadido"), JOptionPane.INFORMATION_MESSAGE);
                        ventana.setContentPane(new contactos.Panel_Principal(ventana, args));
                        ventana.revalidate();
                    }
                }
            });

        }
    }

    public static void main(String args) {
        // TODO code application logic here

    }

}

