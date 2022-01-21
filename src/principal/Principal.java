package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import matematicas.*;

public class Principal extends JFrame {

    public static String funcion = "";

    private Grafica grafica;
    private JTextField txtEntrada;
    private JButton btnGraficar;
    
    private JSpinner jpDespX, jpDespY, jpZoom;
    

    public Principal() {
        setTitle("Graficador de funciones");
        setLayout(new BorderLayout());

        grafica = new Grafica();
        add(grafica, BorderLayout.CENTER);

        txtEntrada = new JTextField();
        btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                funcion = txtEntrada.getText();
                grafica.repaint();      
            }
        });

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());

        panelSur.add(txtEntrada, BorderLayout.CENTER);
        panelSur.add(btnGraficar, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);
        
        //Agregar controles para desplazamiento y zoom
        
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Principal();
    }

}