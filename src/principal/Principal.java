package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame {

    private Grafica grafica;
    private JButton btnZoomMas, btnZoomMenos;
    private JButton btnGraficar;
    
    private JPanel jpFunciones;
    private JButton btnAgregarFuncion;

    public static void main(String[] args) {
        new Principal();
    }

    public Principal() {
    	setLayout(new BorderLayout());
        setTitle("Graficador de funciones");

        JLayeredPane panel = new JLayeredPane();
        grafica = new Grafica(this);
        btnZoomMas = new JButton("+");
        btnZoomMas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				grafica.aumentarZoom(1);
				grafica.repaint();
			}
		});
        btnZoomMenos = new JButton("-");
        btnZoomMenos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				grafica.aumentarZoom(-1);
				grafica.repaint();
			}
		});
        
        
        JPanel panelZoom = new JPanel();
        panelZoom.setLayout(new GridLayout(2, 1, 5, 5));
        panelZoom.add(btnZoomMas);
        panelZoom.add(btnZoomMenos);
        panelZoom.setBackground(new Color(0, 0, 0, 0));
        panelZoom.setBounds(5, 5, 50, 100);
       
        panel.add(grafica, JLayeredPane.DEFAULT_LAYER);
        panel.add(panelZoom, JLayeredPane.PALETTE_LAYER);
        
        add(panel, BorderLayout.CENTER);

        JPanel panelFunciones = new JPanel();
        panelFunciones.setLayout(new BorderLayout());

        jpFunciones = new JPanel();
        jpFunciones.setLayout(new BoxLayout(jpFunciones, BoxLayout.Y_AXIS));
        btnAgregarFuncion = new JButton("Nueva función");
        btnAgregarFuncion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Funcion funcion = new Funcion(Color.BLUE, Principal.this);
                jpFunciones.add(funcion);
                jpFunciones.updateUI();
                jpFunciones.repaint();
            }
        });
        btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                grafica.repaint();      
            }
        });

        JPanel panelBotonesFunciones = new JPanel();
        panelBotonesFunciones.add(btnGraficar);
        panelBotonesFunciones.add(btnAgregarFuncion);


        panelFunciones.add(panelBotonesFunciones, BorderLayout.SOUTH);

        JPanel subpanelListaFunciones = new JPanel();
        subpanelListaFunciones.setLayout(new BorderLayout());
        subpanelListaFunciones.add(jpFunciones, BorderLayout.NORTH);
        JScrollPane scrollListaFunciones = new JScrollPane(subpanelListaFunciones);
        panelFunciones.add(scrollListaFunciones, BorderLayout.CENTER);
        add(panelFunciones, BorderLayout.EAST);
        
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        grafica.setSize(new Dimension(600, 600));
        
        setVisible(true);
    }

    public JPanel getListaFunciones() {
        return jpFunciones;
    }

    public Grafica getGrafica() {
        return grafica;
    }

}