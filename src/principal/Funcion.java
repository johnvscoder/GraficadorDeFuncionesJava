package principal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Funcion extends JPanel {

    private JTextField funcion;
    private Color color;
    private JButton btnColor, btnRemover;
    private Principal ventana;

    public Funcion(Color color, Principal ventana) {
        this.funcion = new JTextField(10);
        this.color = color;
        btnColor = new JButton(" ");
        btnColor.setBackground(color);
        btnColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorUsuario = JColorChooser.showDialog(null, "Elija un color", Color.BLUE);
                btnColor.setBackground(colorUsuario);
                Funcion.this.color = colorUsuario;
                ventana.getGrafica().repaint();
            }
        });
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jpFunciones = ventana.getListaFunciones();
                jpFunciones.remove(Funcion.this);
                jpFunciones.updateUI();
                jpFunciones.repaint();
                ventana.getGrafica().repaint();
                
            }
        });
        setLayout(new BorderLayout());
        add(this.funcion, BorderLayout.CENTER);

        JPanel panelColores = new JPanel();
        panelColores.add(btnColor);
        panelColores.add(btnRemover);
        add(panelColores, BorderLayout.EAST);
        this.ventana = ventana;
    }

    public void setFuncion(String funcion) {
        this.funcion.setText(funcion);
    }

    public String getFuncion() {
        return funcion.getText();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
