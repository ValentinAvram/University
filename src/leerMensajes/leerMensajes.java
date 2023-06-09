package leerMensajes;

import Login.Login;
import ajustes.ajustes;
import bandejaEntrada.bandejaEntrada;
import bandejaEnviados.bandejaEnviados;
import bandejaPapelera.bandejaPapelera;
import contactos.contactos;
import resources.botones.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import backend.*;
import resources.paneles.Panel_Atras;
import resources.paneles.Panel_Titulo;

public class leerMensajes {

    static public class Panel_Principal extends JPanel {
        public Panel_Principal(JFrame ventana, String args, String remitente, String asunto, String mensaje, String ruta) {

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


            ventana.setTitle(bundle_text.get().getString("tituloVerCorreo"));
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

            //Panel que de contenido, aqui estara lo necesario para escribir un correo
            JPanel contenido = new JPanel(new GridBagLayout());
            GridBagConstraints c_contenido = new GridBagConstraints();
            c_contenido.gridx = 0;
            c_contenido.gridy = 2;
            c_contenido.weightx = 1;
            c_contenido.weighty = 0.9;
            c_contenido.fill = GridBagConstraints.BOTH;
            c_contenido.anchor = GridBagConstraints.WEST;

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

            Integer[] seleccionados = new Integer[1];

            Boton_Eliminar boton_eliminar = new Boton_Eliminar(ventana, args, seleccionados);
            boton_eliminar.setText(bundle_text.get().getString("eliminarCorreo"));
            GridBagConstraints b_eliminar = new GridBagConstraints();
            b_eliminar.gridy = 0;
            b_eliminar.gridx = 2;
            b_eliminar.insets.right = 20;

            //Los añado a sus respectivos paneles
            panel2.add(label_espaciado, c_espaciado);
            panel2.add(boton_eliminar, b_eliminar);

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

            //Haré dos paneles, uno para el destinatario y asunto y otro
            JPanel panel_destino_asunto = new JPanel(new GridBagLayout());
            panel_destino_asunto.setBackground(Color.WHITE);
            GridBagConstraints c_destino_asunto_panel = new GridBagConstraints();
            panel_destino_asunto.setBorder(BorderFactory.createMatteBorder(1,1, 1, 1, Color.GRAY));

            c_destino_asunto_panel.gridx = 0;
            c_destino_asunto_panel.gridy = 0;
            c_destino_asunto_panel.weightx = 1;
            c_destino_asunto_panel.weighty = 0.05;

            c_destino_asunto_panel.insets.right = 30;
            c_destino_asunto_panel.insets.left = 30;
            c_destino_asunto_panel.insets.top = 30;
            c_destino_asunto_panel.insets.bottom = 0;

            c_destino_asunto_panel.fill = GridBagConstraints.BOTH;

            JPanel panel_Escribir_Mensaje = new JPanel(new GridLayout(1,1));
            panel_Escribir_Mensaje.setBackground(Color.WHITE);
            GridBagConstraints c_Escribir_Mensaje_panel = new GridBagConstraints();
            panel_Escribir_Mensaje.setBorder(BorderFactory.createMatteBorder(1,1, 1, 1, Color.GRAY));

            c_Escribir_Mensaje_panel.gridx = 0;
            c_Escribir_Mensaje_panel.gridy = 1;
            c_Escribir_Mensaje_panel.weightx = 1;
            c_Escribir_Mensaje_panel.weighty = 0.95;

            c_Escribir_Mensaje_panel.insets.right = 30;
            c_Escribir_Mensaje_panel.insets.left = 30;
            c_Escribir_Mensaje_panel.insets.top = 30;
            c_Escribir_Mensaje_panel.insets.bottom = 30;

            c_Escribir_Mensaje_panel.fill = GridBagConstraints.BOTH;

            //Añado los dos sub-paneles al panel de contenido
            contenido.add(panel_destino_asunto, c_destino_asunto_panel);
            contenido.add(panel_Escribir_Mensaje,c_Escribir_Mensaje_panel);

            //Campos Destino y Asunto (LABELS)
            JLabel label_remitente = new JLabel(bundle_text.get().getString("remitente"));
            GridBagConstraints c_destino_label = new GridBagConstraints();
            c_destino_label.gridx = 0;
            c_destino_label.gridy = 0;
            c_destino_label.weightx = 0.1;
            c_destino_label.insets.left = 20;
            c_destino_label.ipady = 10;

            JLabel label_asunto = new JLabel(bundle_text.get().getString("asunto"));
            GridBagConstraints c_asunto_label = new GridBagConstraints();
            c_asunto_label.gridx = 0;
            c_asunto_label.gridy = 1;
            c_asunto_label.weightx = 0.1;
            c_asunto_label.ipady = 20;
            c_asunto_label.insets.left = 10;

            //Campos Destino y Asunto (TEXTFIELDS)
            JLabel remitente_campo = new JLabel(remitente);
            GridBagConstraints c_destino_textfield = new GridBagConstraints();
            c_destino_textfield.gridx = 1;
            c_destino_textfield.gridy = 0;
            c_destino_textfield.weightx = 0.9;
            c_destino_textfield.ipadx = 200;
            c_destino_textfield.ipady = 20;
            c_destino_textfield.insets.right = 40;
            c_destino_textfield.anchor = GridBagConstraints.WEST;
            c_destino_textfield.fill = GridBagConstraints.BOTH;

            JLabel asunto_campo = new JLabel(asunto);
            GridBagConstraints c_asunto_textfield = new GridBagConstraints();
            c_asunto_textfield.gridx = 1;
            c_asunto_textfield.gridy = 1;
            c_asunto_textfield.weightx = 0.9;
            c_asunto_textfield.ipadx = 200;
            c_asunto_textfield.ipady = 20;
            c_asunto_textfield.insets.right = 40;
            c_asunto_textfield.anchor = GridBagConstraints.WEST;
            c_asunto_textfield.fill = GridBagConstraints.BOTH;

            panel_destino_asunto.add(label_remitente, c_destino_label);
            panel_destino_asunto.add(remitente_campo, c_destino_textfield);
            panel_destino_asunto.add(label_asunto, c_asunto_label);
            panel_destino_asunto.add(asunto_campo, c_asunto_textfield);

            //Campo textarea para el contenido del correo

            JTextArea contenido_mensaje_texto = new JTextArea("Contenido");
            contenido_mensaje_texto.setBackground(Color.white);
            contenido_mensaje_texto.setEditable(false);
            contenido_mensaje_texto.setText(mensaje);

            JScrollPane contenido_mensaje_Scroll = new JScrollPane(contenido_mensaje_texto);
            contenido_mensaje_Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            contenido_mensaje_Scroll.setBackground(Color.white);

            panel_Escribir_Mensaje.add(contenido_mensaje_Scroll, BorderLayout.CENTER);

            boton_eliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        backendLeer.borrarMensaje(args, remitente, asunto, ruta);

                        JOptionPane.showMessageDialog(ventana, bundle_text.get().getString("mensajeEliminado"), bundle_text.get().getString("tituloMensajeEliminado"), JOptionPane.INFORMATION_MESSAGE);

                        if(ruta.equals("/bandejaEntrada")) {
                            try {
                                ventana.setContentPane(new bandejaEntrada.Panel_Principal(ventana, args));
                            } catch (UnsupportedLookAndFeelException ex) {
                                throw new RuntimeException(ex);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (InstantiationException ex) {
                                throw new RuntimeException(ex);
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else if(ruta.equals("/papelera"))
                        {
                            ventana.setContentPane(new bandejaPapelera.Panel_Principal(ventana, args));
                        }
                        else if(ruta.equals("/enviados")) {
                            ventana.setContentPane(new bandejaEnviados.Panel_Principal(ventana, args));
                        }
                        ventana.revalidate();
                }
            });
        }
    }

    public static void main(String args) {
        // TODO code application logic here




    }

}
