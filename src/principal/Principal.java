package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import matematicas.*;

public class Principal extends JFrame {

    public static String funcion = "";

    private Grafica grafica;
    private JButton btnZoomMas, btnZoomMenos;
    private JTextField txtEntrada;
    private JButton btnGraficar;

    public Principal() {
    	setLayout(new BorderLayout());
        setTitle("Graficador de funciones");

        JLayeredPane panel = new JLayeredPane();
        grafica = new Grafica();
        btnZoomMas = new JButton("+");
        btnZoomMenos = new JButton("-");
        
        
        JPanel panelZoom = new JPanel();
        panelZoom.setLayout(new GridLayout(2, 1, 5, 5));
        panelZoom.add(btnZoomMas);
        panelZoom.add(btnZoomMenos);
        panelZoom.setBackground(new Color(0, 0, 0, 0));
        panelZoom.setBounds(5, 5, 50, 100);
       
        panel.add(grafica, JLayeredPane.DEFAULT_LAYER);
        panel.add(panelZoom, JLayeredPane.PALETTE_LAYER);
        
        add(panel, BorderLayout.CENTER);
        
        
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
        
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        grafica.setSize(getWidth(), getHeight());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Principal();
    }

}