package principal;

import javax.swing.*;
import java.awt.*;

import matematicas.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Grafica extends JPanel {
	
	double zoomX = 1.0, zoomY = 1.0;
	double posX = 0.0, posY = 0.0; //Con respecto al centro de la grafica, eje +y hacia arriba
	final double UNIT = 10.0; //Cantidad de pixeles iguales a 1 unidad
	Color frente = Color.BLACK, fondo = Color.WHITE;
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(fondo);
		g.fillRect(0, 0, getWidth(), getHeight());
		
	
		//Pintar ejes
		g.setColor(frente);
		//Eje x
		g.drawLine(0, (int) (getHeight() / 2.0 + posY * zoomY * UNIT), 
				getWidth(), (int) (getHeight() / 2.0 + posY * zoomY * UNIT));
		//Eje y
		g.drawLine(0, -20, 0, 20);
		
		
		
		//Fin del codigo para pintar


		

		
		
		
	}

}