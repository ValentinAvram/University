package bandejaEntrada;

import backend.backendAjustes;
import backend.backendBorradores;
import leerMensajes.leerMensajes;
import redactar.redactar;
import resources.botones.*;
import resources.paneles.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static backend.backendBandeja.*;
import static javax.swing.JOptionPane.showMessageDialog;


public class bandejaEntrada {

    static public class Panel_Principal extends JPanel {
        public Panel_Principal(JFrame ventana, String args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
            String valorSeleccionado = backendAjustes.obtenerTema(args);
            System.out.println(valorSeleccionado);
            switch (valorSeleccionado){
                case "Metal":
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(ventana);
                    break;
                case "Windows Classic":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(ventana); // frame es el JFrame principal de la aplicación
                    break;
                case "Nimbus":
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(ventana); // frame es el JFrame principal de la aplicación
                    break;
                case "Windows":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(ventana);
                    break;
                case "CDE/Motif":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(ventana);
                    break;


            }

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


            ventana.setTitle(bundle_text.get().getString("tituloBandejaEntrada"));
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
            label_Mensajes.setHorizontalAlignment(SwingConstants.LEFT);
            Border border_Mensajes = BorderFactory.createMatteBorder(0,0, 1, 0, Color.BLACK);
            GridBagConstraints c_label_mensajes = new GridBagConstraints();
            c_label_mensajes.weightx = 0.04;

            JLabel label_espaciado00 = new JLabel("");
            GridBagConstraints c_label_espaciado00 = new GridBagConstraints();
            c_label_espaciado00.weightx = 0.94;

            GridBagConstraints c_mensajes = new GridBagConstraints();
            c_mensajes.anchor = GridBagConstraints.WEST;

            //Panel blanco:

            JPanel mensaje00 = new JPanel(new GridBagLayout());
            GridBagConstraints c_mensaje00 = new GridBagConstraints();

            c_mensaje00.gridx = 0;
            c_mensaje00.gridy = 0;
            c_mensaje00.weightx = 1;
            c_mensaje00.weighty = 0.05;
            c_mensaje00.fill = GridBagConstraints.BOTH;

            mensaje00.setBackground(Color.LIGHT_GRAY);
            mensaje00.setBorder(border_Mensajes);

            mensaje00.add(label_Mensajes, c_label_mensajes);
            mensaje00.add(label_espaciado00, c_label_espaciado00);

            JPanel contenido = new JPanel(new GridBagLayout());
            GridBagConstraints c_contenido = new GridBagConstraints();
            c_contenido.gridx = 0;
            c_contenido.gridy = 2;
            c_contenido.weightx = 1;
            c_contenido.weighty = 0.9;
            c_contenido.fill = GridBagConstraints.BOTH;
            c_contenido.anchor = GridBagConstraints.WEST;

            JPanel mensaje0 = new JPanel(new GridLayout(1,4));
            mensaje0.setPreferredSize(new Dimension(panel4.getWidth(),60));
            GridBagConstraints c_mensaje0 = new GridBagConstraints();
            c_mensaje0.gridx = 0;
            c_mensaje0.gridy = 0;
            c_mensaje0.weightx = 1;
            c_mensaje0.weighty = 0;
            c_mensaje0.fill = GridBagConstraints.BOTH;

            mensaje0.setBorder(border_Mensajes);

            //Aqui necesito tres labels, Remitente, Asunto, Fecha y Eliminar

            JLabel label_Remitente = new JLabel(bundle_text.get().getString("remitente"));
            JLabel label_Asunto = new JLabel(bundle_text.get().getString("asunto"));
            JLabel label_Fecha = new JLabel(bundle_text.get().getString("fecha"));
            JLabel label_Eliminar = new JLabel(bundle_text.get().getString("eliminar"));

            label_Remitente.setHorizontalAlignment(SwingConstants.CENTER);
            label_Asunto.setHorizontalAlignment(SwingConstants.CENTER);
            label_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
            label_Eliminar.setHorizontalAlignment(SwingConstants.CENTER);

            //Le pongo un borde a los labels necesarios

            Border borde_derecha = BorderFactory.createMatteBorder(0,0, 0, 1, Color.BLACK);

            label_Asunto.setBorder(borde_derecha);
            label_Remitente.setBorder(borde_derecha);
            label_Fecha.setBorder(borde_derecha);
            label_Eliminar.setBorder(borde_derecha);

            //Los centro y añado a su panel:

            mensaje0.add(label_Remitente);
            mensaje0.add(label_Asunto);
            mensaje0.add(label_Fecha);
            mensaje0.add(label_Eliminar);

            //Añado el label al panel y despues añadire el panel al panel4

            JPanel Aqui_van_los_mensajes = new JPanel();
            Aqui_van_los_mensajes.setLayout(new BoxLayout(Aqui_van_los_mensajes, BoxLayout.Y_AXIS));
            GridBagConstraints c_Aqui_van_los_mensajes = new GridBagConstraints();
            c_Aqui_van_los_mensajes.gridx = 0;
            c_Aqui_van_los_mensajes.gridy = 1;
            c_Aqui_van_los_mensajes.weightx = 1;
            c_Aqui_van_los_mensajes.weighty = 0.9;
            c_Aqui_van_los_mensajes.fill = GridBagConstraints.HORIZONTAL;
            c_Aqui_van_los_mensajes.anchor = GridBagConstraints.NORTH;

            //Añadimos los titulos de cada panel
            panel3.add(Menu_Gris);

            //Añadimos al panel 4:

            JScrollPane Scroll = new JScrollPane(contenido);
            Scroll.setPreferredSize(new Dimension(300,200));
            Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            Scroll.setBackground(Color.white);

            contenido.add(mensaje0, c_mensaje0);
            contenido.add(Aqui_van_los_mensajes, c_Aqui_van_los_mensajes);
            panel4.add(mensaje00, c_mensaje00);
            panel4.add(Scroll, c_contenido);

            //Creo los botones de Bandeja de entrada (Redactar, eleminar mensajes y atrás)

            JButton boton_redactar = new Boton_Redactar(ventana, args);
            GridBagConstraints b_redactar = new GridBagConstraints();
            b_redactar.gridy = 0;
            b_redactar.gridx = 1;
            b_redactar.insets.right = 20;

            String usuario = args;
            Integer[] seleccionados = new Integer[8];

            Boton_Eliminar boton_eliminar = new Boton_Eliminar(ventana, args, seleccionados);
            GridBagConstraints b_eliminar = new GridBagConstraints();
            b_eliminar.gridy = 0;
            b_eliminar.gridx = 2;
            b_eliminar.insets.right = 20;

            //Los añado a sus respectivos paneles

            panel2.add(boton_redactar, b_redactar);
            panel2.add(boton_eliminar, b_eliminar);

            //Creo los botones del Menú: Enviados, Recibidos, Papelera, Contactos y Ajustes

            JButton boton_recibidos = new Boton_Recibidos(ventana, args);
            GridBagConstraints b_recibidos = new GridBagConstraints();
            b_recibidos.gridx = 0;
            b_recibidos.gridy = 2;

            boton_recibidos.setBackground(Color.gray);

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

            int numeroMails = numeroMails(usuario);
            /*int numeroMax = backendAjustes.obtenerTamanoPag(usuario);
            if(numeroMails > numeroMax)
                numeroMails = numeroMax;

             */

            for(int i = 0; i < numeroMails; i++){ //Bucle que lee el numero de mails y los muestra
                JPanel mensaje = new JPanel(new GridLayout(1,4));
                GridBagConstraints c_mensaje = new GridBagConstraints();
                c_mensaje.gridx = 0;
                c_mensaje.gridy = i;
                c_mensaje.weightx = 1;
                c_mensaje.weighty = 0.05;
                c_mensaje.fill = GridBagConstraints.BOTH;

                mensaje.setBorder(border_Mensajes);

                String[] partes = mailCompleto(usuario, i);

                String remitente = partes[0];
                String asunto = partes[2];
                String fecha = partes[1];

                JLabel label_Remitente1 = new JLabel(remitente);
                JLabel label_Asunto1 = new JLabel(asunto);
                JLabel label_Fecha1 = new JLabel(fecha);
                JCheckBox checkBox_eliminar = new JCheckBox("");

                mensaje.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                mensaje.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mensaje.setBackground(Color.LIGHT_GRAY);
                        mensaje.setOpaque(true);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mensaje.setBackground(Color.WHITE);
                        mensaje.setOpaque(true);

                    }
                });

                label_Remitente1.setHorizontalAlignment(SwingConstants.CENTER);
                label_Asunto1.setHorizontalAlignment(SwingConstants.CENTER);
                label_Fecha1.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox_eliminar.setHorizontalAlignment(SwingConstants.CENTER);

                Border borde_derecha1 = BorderFactory.createMatteBorder(0,0, 0, 1, Color.BLACK);

                label_Asunto1.setBorder(borde_derecha1);
                label_Remitente1.setBorder(borde_derecha1);
                label_Fecha1.setBorder(borde_derecha1);
                checkBox_eliminar.setBorder(borde_derecha1);

                mensaje.add(label_Remitente1);
                mensaje.add(label_Asunto1);
                mensaje.add(label_Fecha1);
                mensaje.add(checkBox_eliminar);
                String ruta = "/bandejaEntrada";

                int finalI = i;
                mensaje.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] datosMail = mailCompleto(usuario, finalI);
                        String remitente = datosMail[0];
                        String asunto = datosMail[2];
                        String texto = datosMail[3];

                        ventana.setContentPane(new leerMensajes.Panel_Principal(ventana, usuario, remitente, asunto, texto, ruta));
                        ventana.revalidate();;
                    }
                });
                mensaje.setPreferredSize(new Dimension(Aqui_van_los_mensajes.getWidth(),60));
                mensaje.setBackground(Color.white);
                GridBagConstraints c_mensaje_aqui = new GridBagConstraints();
                c_mensaje_aqui.fill = GridBagConstraints.BOTH;
                Aqui_van_los_mensajes.add(mensaje,c_mensaje_aqui);
            }

            // ActionListener del botonEliminar
            boton_eliminar.addActionListener(e->{
                ArrayList<Integer> mensajes_a_eliminar = new ArrayList<>();
                // Recorrer todos los mensajes del panel Contenido
                int cont = -1;
                for (Component comp : Aqui_van_los_mensajes.getComponents()) {
                    if (comp instanceof JPanel) {
                        JPanel panelMensaje = (JPanel) comp;
                        for (Component compMensaje : panelMensaje.getComponents()) {
                            if (compMensaje instanceof JCheckBox) {
                                cont++;
                                JCheckBox checkBoxMensaje = (JCheckBox) compMensaje;
                                if (checkBoxMensaje.isSelected()) {
                                    // Agrega el índice del mensaje a un arreglo de mensajes a eliminar
                                    // Por ejemplo, si el índice del mensaje está en el nombre del panel de mensaje:
                                    mensajes_a_eliminar.add(cont);
                                }
                            }
                        }
                    }
                }

                System.out.println(mensajes_a_eliminar);

                // Eliminar valores nulos del array
                ArrayList<Integer> finalMensajes_a_eliminar = new ArrayList<>();
                for (Integer mensaje_a_eliminar : mensajes_a_eliminar) {
                    if (mensaje_a_eliminar != null) {
                        finalMensajes_a_eliminar.add(mensaje_a_eliminar);
                    }
                }

                int option = JOptionPane.showOptionDialog(ventana, bundle_text.get().getString("confirmarEliminar"),
                        bundle_text.get().getString("tituloConfirmar"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{bundle_text.get().getString("Si"), bundle_text.get().getString("No")}, JOptionPane.YES_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    eliminarMensajes(usuario, finalMensajes_a_eliminar);
                    // Refrescar la bandeja de entrada
                    try {
                        ventana.setContentPane(new Panel_Principal(ventana, args));
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    ventana.revalidate();
                }
            });
        }
    }

    public static void main(String args) {
        // TODO code application logic here

        //JFrame ventana = new JFrame("Bandeja de Entrada");
        //Bandeja va a ser el panel principal al que le añadiremos 4 paneles hijos
        //Panel_Principal Bandeja = new Panel_Principal();

        //Container contenedor_Bandeja_De_Entrada = new JPanel();
        //Container contenedor_Bandeja_De_Entrada = ventana.getContentPane();



    }

}
