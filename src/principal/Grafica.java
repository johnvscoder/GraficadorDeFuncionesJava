package principal;

import javax.swing.*;
import java.awt.*;

import matematicas.*;

import java.awt.event.*;

public class Grafica extends JPanel {

    public static final double DELTA = 1.0;

    //Es la cantidad de pixeles a la cual es equivalente la unidad de longitud
    //del sistemas de coordenadas
    //En otras palabras, una unidad de longitud es igual a 20 pixeles
    public static final double ESCALA = 20.0;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Eje x
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); 
        //Eje y
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        for(int i = (int) (-getWidth() / 2.0 / 20.0); i <= (int) (getWidth() / 2.0 / 20.0); i += 1) {
            g.drawLine(getWidth() / 2 + i * 20, getHeight() / 2 - 3, getWidth() / 2 + i * 20, getHeight() / 2 + 3);
            if(i != 0)
                g.drawString(Integer.toString(i), getWidth() / 2 + i * 20 - 3, getHeight() / 2 - 9);
        }

        for(int i = (int) (-getHeight() / 2.0 / 20.0); i <= (int) (getHeight() / 2.0 / 20.0); i += 1) {
            g.drawLine(getWidth() / 2 - 3, getHeight() / 2 + (-i * 20), getWidth() / 2 + 3, getHeight() / 2 + (-i * 20));
            if(i != 0)
                g.drawString(Integer.toString(i), getWidth() / 2 + 12, getHeight() / 2 + (-i * 20));
        }

        if(Principal.funcion.equals(""))
            return;

        g.setColor(Color.BLUE);
        
        for(double x = -getWidth() / 2.0; x <= getWidth() / 2.0; x += DELTA) {
            try {
                double xPrevio = x - DELTA;
                double fx = Calculator.f(Principal.funcion, x);
                double fxPrevio = Calculator.f(Principal.funcion, xPrevio);

                if(Double.isFinite(fx) && Double.isFinite(fxPrevio)) {
                    g.drawLine((int) (getWidth() / 2.0  + (xPrevio) * 20.0), 
                    (int) (getHeight() / 2.0 - fxPrevio * 20.0), (int) (getWidth() / 2.0 + x * 20.0), 
                   (int) (getHeight() / 2.0 - fx * 20.0));
                }
            } catch(RuntimeException e) {
                
            }
        }
    }

}