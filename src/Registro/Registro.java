package Registro;

import Login.Login;

import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import backend.*;
import bandejaEnviados.bandejaEnviados;

import java.lang.String;

public class Registro {
    static public class Panel_Principal extends JPanel{
        public Panel_Principal(JFrame ventana, String[] args, String lang){

            //Idioma

            if(lang.equals("es")){
                Locale.setDefault(new Locale("es", "ES"));
            }else if(lang.equals("en")){
                Locale.setDefault(new Locale("en", "GB"));
            }
            else if(lang.equals("ro")){
                Locale.setDefault(new Locale("ro", "RO"));
            }

            AtomicReference<Locale> currentLocale = new AtomicReference<>(Locale.getDefault());

            currentLocale.set(new Locale(lang));

            AtomicReference<ResourceBundle> bundle_text = new AtomicReference<>(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));


            setLayout(new GridLayout(1,3));

            ventana.setTitle(bundle_text.get().getString("tituloRegistro"));
            //JFrames necesarios
            JFrame frame = new JFrame(bundle_text.get().getString("registro")); // Ventana principal
            frame.setMinimumSize(new Dimension(960,540));
            //Container cp = frame.getContentPane(); // Contenedor de la ventana principal

            //Layouts necesarios
            FlowLayout leftLayout = new FlowLayout(); // Layout para el panel de la izquierda
            leftLayout.setAlignment(FlowLayout.CENTER); // Centramos el contenido del panel de la izquierda

            GridBagLayout centerLayout = new GridBagLayout(); // Layout para el panel del centro
            GridBagConstraints c = new GridBagConstraints(); // Constraints para el panel del centro
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(1, 2, 1, 2); // Espacio entre los elementos del panel del centro

            //Paneles necesarios
            JPanel leftPanel = new JPanel(leftLayout);
            JPanel centerPanel = new JPanel(centerLayout);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Panel de login
            loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JPanel loginContainerPanel = new JPanel(new BorderLayout());

            //Bordes necesarios
            Border loginBorder = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ); // Bordes para el panel de login

            //JLabels necesarios
            JLabel usernameLabel = new JLabel(bundle_text.get().getString("Usuario"));
            JLabel passwordLabel = new JLabel(bundle_text.get().getString("Contraseña"));
            JLabel passwordConfirmLabel = new JLabel(bundle_text.get().getString("repetirContraseña"));

            JLabel titleLabel = new JLabel(bundle_text.get().getString("registro"));
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de la fuente
            titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centramos el texto

            //JButtons necesarios
            JButton loginButton = new JButton(bundle_text.get().getString("iniciarSesion"));
            JButton registerButton = new JButton(bundle_text.get().getString("registrarse"));

            //JTextFields necesarios
            JTextField usernameField = new JTextField(20);

            //JPasswordFields necesarios
            JPasswordField passwordField = new JPasswordField(20);
            // Al pulsar enter en el campo de contraseña, se ejecuta el botón de login
            passwordField.addActionListener(e -> loginButton.doClick());

            JPasswordField password_repetir_Field = new JPasswordField(20);

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean samePass = false;
                    String pass = passwordField.getText();
                    String pass_repetir = password_repetir_Field.getText();
                    String user = usernameField.getText();

                    boolean userExists = backendRegister.checkUsuario(user);

                    // Comprobamos que las contraseñas sean iguales
                    if (pass.equals(pass_repetir))
                    {
                        samePass = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("passNoCoincide"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }

                    if(usernameField.getText().equals("") || passwordField.getText().equals("") || password_repetir_Field.getText().equals("")){

                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("relleneCampos"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else if (userExists == true) {
                        // Mostramos un mensaje de error
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("errorUsuarioYaExiste"), bundle_text.get().getString("tituloError"), JOptionPane.ERROR_MESSAGE);
                    }
                    else if (passwordField.getText() != null  && samePass == true) {
                        JOptionPane.showMessageDialog(null, bundle_text.get().getString("usuarioRegistrado"));
                        backendRegister.newUser(usernameField.getText(), passwordField.getText());
                        ventana.setContentPane(new bandejaEnviados.Panel_Principal(ventana, usernameField.getText()));
                        ventana.validate();
                    }
                }
            });


            // Menu de idiomas
            JButton menuButton = new JButton(bundle_text.get().getString("botonIdiomas"));
            menuButton.setAlignmentX(Component.RIGHT_ALIGNMENT); // Alineamos el botón a la derecha
            JPopupMenu menu = new JPopupMenu(); // Menú desplegable

            // Items del menu
            JMenuItem spanishItem = new JMenuItem("Español");
            JMenuItem englishItem = new JMenuItem("English");
            JMenuItem romanianItem = new JMenuItem("Româna");

            // Cada botón tiene su propia acción
            spanishItem.addActionListener(e -> {
                // Cuando se pulsa "Español", se oculta el menú
                menu.setVisible(false);
                // Cambiar el idioma a español
                currentLocale.set(new Locale("es", "ES"));
                // Actualizar los recursos de texto en el bundle
                bundle_text.set(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));
                // Actualizar los textos de los componentes
                titleLabel.setText(bundle_text.get().getString("registro"));
                usernameLabel.setText(bundle_text.get().getString("Usuario"));
                passwordLabel.setText(bundle_text.get().getString("Contraseña"));
                loginButton.setText(bundle_text.get().getString("iniciarSesion"));
                menuButton.setText(bundle_text.get().getString("botonIdiomas"));
                passwordConfirmLabel.setText(bundle_text.get().getString("repetirContraseña"));
                frame.setTitle(bundle_text.get().getString("tituloRegistro"));
            });

            englishItem.addActionListener(e -> {
                // Cuando se pulsa "English", se oculta el menú
                menu.setVisible(false);
                // Cambiar el idioma a inglés
                currentLocale.set(new Locale("en", "GB"));
                // Actualizar los recursos de texto en el bundle
                bundle_text.set(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));
                // Actualizar los textos de los componentes
                titleLabel.setText(bundle_text.get().getString("registro"));
                usernameLabel.setText(bundle_text.get().getString("Usuario"));
                passwordLabel.setText(bundle_text.get().getString("Contraseña"));
                loginButton.setText(bundle_text.get().getString("iniciarSesion"));
                menuButton.setText(bundle_text.get().getString("botonIdiomas"));
                passwordConfirmLabel.setText(bundle_text.get().getString("repetirContraseña"));
                frame.setTitle(bundle_text.get().getString("tituloRegistro"));
            });

            romanianItem.addActionListener(e -> {
                // Cuando se pulsa "Româna", se oculta el menú
                menu.setVisible(false);
                // Cambiar el idioma a rumano
                currentLocale.set(new Locale("ro", "RO"));
                // Actualizar los recursos de texto en el bundle
                bundle_text.set(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));
                // Actualizar los textos de los componentes
                titleLabel.setText(bundle_text.get().getString("registro"));
                usernameLabel.setText(bundle_text.get().getString("Usuario"));
                passwordLabel.setText(bundle_text.get().getString("Contraseña"));
                loginButton.setText(bundle_text.get().getString("iniciarSesion"));
                menuButton.setText(bundle_text.get().getString("botonIdiomas"));
                passwordConfirmLabel.setText(bundle_text.get().getString("repetirContraseña"));
                frame.setTitle(bundle_text.get().getString("tituloRegistro"));
            });

            URL iconURLSpain = Login.class.getResource("/resources/flags/spain_flag.png");
            ImageIcon bandera_Spain = new ImageIcon(iconURLSpain);
            bandera_Spain.setImage(bandera_Spain.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            spanishItem.setIcon(bandera_Spain);

// Carga del icono para la bandera de Reino Unido (UK)
            URL iconURLUK = Login.class.getResource("/resources/flags/UK_flag.png");
            ImageIcon bandera_UK = new ImageIcon(iconURLUK);
            bandera_UK.setImage(bandera_UK.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            englishItem.setIcon(bandera_UK);

// Carga del icono para la bandera de Rumania
            URL iconURLRomania = Login.class.getResource("/resources/flags/romania_flag.png");
            ImageIcon bandera_Rumania = new ImageIcon(iconURLRomania);
            bandera_Rumania.setImage(bandera_Rumania.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            romanianItem.setIcon(bandera_Rumania);


            // Agregamos un ActionListener al botón para mostrar el menú
            menuButton.addActionListener(e -> {
                menu.show(menuButton, 0, menuButton.getHeight());
            });

            // Check del Login.Login
            loginButton.addActionListener(e -> {
                // Al pulsar enter en el campo de contraseña, se ejecuta el botón de login
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

            });


            // Agregamos cada elemento al panel correspondiente
            rightPanel.add(menuButton);

            loginPanel.add(usernameLabel);
            loginPanel.add(usernameField);
            loginPanel.add(passwordLabel);
            loginPanel.add(passwordField);
            loginPanel.add(passwordConfirmLabel);
            loginPanel.add(password_repetir_Field);
            loginPanel.add(registerButton);
            loginPanel.add(loginButton);

            menu.add(spanishItem);
            menu.add(englishItem);
            menu.add(romanianItem);

            loginContainerPanel.add(titleLabel, BorderLayout.NORTH);
            loginContainerPanel.setBorder(loginBorder); // Agregamos el borde al panel de login

            loginContainerPanel.add(loginPanel, BorderLayout.CENTER);

            centerPanel.add(loginContainerPanel);

            // Agregamos cada panel al panel principal
            add(leftPanel);
            add(centerPanel);
            add(rightPanel);

            // Agregamos el panel principal al contenedor



        }

    }
}