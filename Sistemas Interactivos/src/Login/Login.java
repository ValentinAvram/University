package Login;
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

import Registro.Registro;
import backend.loginBackend;
import bandejaEntrada.bandejaEntrada;


public class Login {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {


        //Obtener Locale por defecto
        AtomicReference<Locale> currentLocale = new AtomicReference<>(Locale.getDefault());

        //Si el Locale actual no está entre los soportados, asignar por defecto
        if(!((currentLocale.get().getLanguage().equals("es") && currentLocale.get().getCountry().equals("ES")) ||
                (currentLocale.get().getLanguage().equals("en") && currentLocale.get().getCountry().equals("GB")) ||
                (currentLocale.get().getLanguage().equals("ro") && currentLocale.get().getCountry().equals("RO"))
        )){
            currentLocale.set(new Locale("en", "GB"));
        }

        //Obtener el conjunto de cadenas del bundle para el Locale actual
        AtomicReference<ResourceBundle> bundle_text = new AtomicReference<>(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));

        //JFrames necesarios
        JFrame frame = new JFrame(bundle_text.get().getString("tituloLogin")); // Ventana principal
        frame.setMinimumSize(new Dimension(1280,720));
        Container cp = frame.getContentPane(); // Contenedor de la ventana principal

        try {
            // Establecer el aspecto Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Manejo de excepciones si ocurre algún error al establecer el aspecto
            e.printStackTrace();
        }

        //Layouts necesarios
        FlowLayout leftLayout = new FlowLayout(); // Layout para el panel de la izquierda
        leftLayout.setAlignment(FlowLayout.CENTER); // Centramos el contenido del panel de la izquierda

        GridBagLayout centerLayout = new GridBagLayout(); // Layout para el panel del centro
        GridBagConstraints c = new GridBagConstraints(); // Constraints para el panel del centro
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(1, 2, 1, 2); // Espacio entre los elementos del panel del centro

        //Paneles necesarios
        JPanel totalPanel = new JPanel(new GridLayout(1,3)); // Panel principal
        JPanel leftPanel = new JPanel(leftLayout);
        JPanel centerPanel = new JPanel(centerLayout);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Panel de login
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

        JLabel titleLabel = new JLabel(bundle_text.get().getString("bienvenida"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de la fuente
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centramos el texto

        //JButtons necesarios
        JButton loginButton = new JButton(bundle_text.get().getString("iniciarSesion"));
        JButton registerButton = new JButton(bundle_text.get().getString("registrarse"));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lang = currentLocale.get().getLanguage();
                frame.setContentPane(new Registro.Panel_Principal(frame, args, lang));
                frame.validate();
            }
        });

        //JTextFields necesarios
        JTextField usernameField = new JTextField(20);

        //JPasswordFields necesarios
        JPasswordField passwordField = new JPasswordField(20);
        // Al pulsar enter en el campo de contraseña, se ejecuta el botón de login
        passwordField.addActionListener(e -> loginButton.doClick());



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
            titleLabel.setText(bundle_text.get().getString("bienvenida"));
            usernameLabel.setText(bundle_text.get().getString("Usuario"));
            passwordLabel.setText(bundle_text.get().getString("Contraseña"));
            loginButton.setText(bundle_text.get().getString("iniciarSesion"));
            menuButton.setText(bundle_text.get().getString("botonIdiomas"));
            frame.setTitle(bundle_text.get().getString("tituloLogin"));
            registerButton.setText(bundle_text.get().getString("registrarse"));
        });

        englishItem.addActionListener(e -> {
            // Cuando se pulsa "English", se oculta el menú
            menu.setVisible(false);
            // Cambiar el idioma a inglés
            currentLocale.set(new Locale("en", "GB"));
            // Actualizar los recursos de texto en el bundle
            bundle_text.set(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));
            // Actualizar los textos de los componentes
            titleLabel.setText(bundle_text.get().getString("bienvenida"));
            usernameLabel.setText(bundle_text.get().getString("Usuario"));
            passwordLabel.setText(bundle_text.get().getString("Contraseña"));
            loginButton.setText(bundle_text.get().getString("iniciarSesion"));
            menuButton.setText(bundle_text.get().getString("botonIdiomas"));
            frame.setTitle(bundle_text.get().getString("tituloLogin"));
            registerButton.setText(bundle_text.get().getString("registrarse"));
        });

        romanianItem.addActionListener(e -> {
            // Cuando se pulsa "Româna", se oculta el menú
            menu.setVisible(false);
            // Cambiar el idioma a rumano
            currentLocale.set(new Locale("ro", "RO"));
            // Actualizar los recursos de texto en el bundle
            bundle_text.set(ResourceBundle.getBundle("Login.languages.Bundle", currentLocale.get()));
            // Actualizar los textos de los componentes
            titleLabel.setText(bundle_text.get().getString("bienvenida"));
            usernameLabel.setText(bundle_text.get().getString("Usuario"));
            passwordLabel.setText(bundle_text.get().getString("Contraseña"));
            loginButton.setText(bundle_text.get().getString("iniciarSesion"));
            menuButton.setText(bundle_text.get().getString("botonIdiomas"));
            frame.setTitle(bundle_text.get().getString("tituloLogin"));
            registerButton.setText(bundle_text.get().getString("registrarse"));
        });

        //Coloco las banderas:
        // Carga del icono para la bandera de España
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
            passwordField.addActionListener(e1 -> loginButton.doClick());

            // Validar nombre de usuario y contraseña aquí
            if (loginBackend.verificarUsuario(usernameField.getText(), passwordField.getText()) == true) {
                JOptionPane.showMessageDialog(frame, bundle_text.get().getString("inicioSesionExito"), bundle_text.get().getString("tituloLoginExito"), JOptionPane.INFORMATION_MESSAGE);

                String usuario = usernameField.getText();
                try {
                    frame.setContentPane(new bandejaEntrada.Panel_Principal(frame, usuario));
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                frame.validate();
            } else {
                JOptionPane.showMessageDialog(frame, bundle_text.get().getString("errorInicioSesion"));
            }
        });

        //TODO: Completar esto!
        /*
        registerButton.addActionListener(e -> {
            frame.setContentPane(new Registro(frame));
            frame.validate();
        });
        */

        // Agregamos cada elemento al panel correspondiente
        rightPanel.add(menuButton);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
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
        totalPanel.add(leftPanel);
        totalPanel.add(centerPanel);
        totalPanel.add(rightPanel);

        // Agregamos el panel principal al contenedor
        cp.add(totalPanel);

        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}