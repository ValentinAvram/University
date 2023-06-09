package resources.paneles;

import resources.botones.Boton_Atras;

import javax.swing.*;
import java.awt.*;

public class Panel_Titulo extends JPanel {
    public Panel_Titulo(JFrame ventana, String args){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 80));

        //Definimos las dimensiones para cada label
        JLabel label_bandeja = new JLabel(ventana.getTitle());
        label_bandeja.setFont(new Font("Arial", Font.BOLD, 30)); // cambia el tamaño de la fuente
        GridBagConstraints c_bandeja = new GridBagConstraints();
        label_bandeja.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        c_bandeja.gridx = 0; // mueve el JLabel a la izquierda
        c_bandeja.gridy = 0; // centra verticalmente
        c_bandeja.gridwidth = 1;
        c_bandeja.weightx = 0.5; // para expandir horizontalmente
        c_bandeja.weighty = 1; // para expandir verticalmente
        c_bandeja.fill = GridBagConstraints.BOTH;
        c_bandeja.anchor = GridBagConstraints.WEST;

        add(label_bandeja, c_bandeja);



    }
}