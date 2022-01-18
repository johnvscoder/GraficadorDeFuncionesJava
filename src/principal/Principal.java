package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import matematicas.*;

public class Principal extends JFrame {

    public static String funcion = "";

    private Panel panel;
    private JTextField txtEntrada;
    private JButton btnGraficar;

    private JTextField txtZoom;
    private JButton btnZoom;

    public Principal() {
        setTitle("Graficador de funciones");
        setLayout(new BorderLayout());

        panel = new Panel();
        add(panel, BorderLayout.CENTER);

        txtEntrada = new JTextField();
        btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                funcion = txtEntrada.getText();
                panel.repaint();      
            }
        });

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());

        panelSur.add(txtEntrada, BorderLayout.CENTER);
        panelSur.add(btnGraficar, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Principal();
    }

}