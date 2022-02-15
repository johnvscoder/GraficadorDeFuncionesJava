package principal;

import javax.swing.*;
import java.awt.*;

import matematicas.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Grafica extends JPanel {
	
	double zoomX = 1.0, zoomY = 1.0;
	double posX = 0.0, posY = 0.0; //Con respecto al centro de la grafica, eje +y hacia arriba
	final double UNIT = 30.0; //Cantidad de pixeles iguales a 1 unidad
	Color frente = Color.BLACK, fondo = Color.WHITE;
	
	private JFrame padre;
	
	public Grafica(JFrame padre) {
		this.padre = padre;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		setSize(padre.getWidth(), padre.getHeight());
		g.setColor(fondo);
		g.fillRect(0, 0, getWidth(), getHeight());
		
	
		//Pintar ejes
		g.setColor(frente);
		
		int ejeX = (int) (getHeight() / 2.0 + posY * zoomY * UNIT);
		int ejeY = (int) (getWidth() / 2.0 - posX * zoomX * UNIT);
		//Eje x
		g.drawLine(0, ejeX, 
				getWidth(), ejeX);
		//Eje y
		g.drawLine(ejeY, 0,
				ejeY, getHeight());
		
		FontMetrics fm = g.getFontMetrics();
		
		int altoTexto = fm.getHeight();
		
		
		int xIzquierda = (int) ((posX - getWidth() / 2.0 / UNIT) / zoomX);
		int xDerecha = (int) ((posX + getWidth() / 2.0 / UNIT) / zoomX);
		int yAbajo = (int) ((posY - getHeight() / 2.0 / UNIT) / zoomY);
		int yArriba = (int) ((posY + getHeight() / 2.0 / UNIT) / zoomY);
		
		for(int i = xIzquierda; i <= xDerecha; i++) {
			g.drawString(Integer.toString(i), (int) (getWidth() / 2.0 + (posX + i) * zoomX * UNIT), ejeX);
		}
		for(int i = yAbajo; i <= yArriba; i++) {
			g.drawString(Integer.toString(i), ejeY, (int) (getHeight() / 2.0 - (posY + i) * zoomY * UNIT));
		}
		
		
//		g.drawRect(0, (int) (getHeight() / 2.0 + posY * zoomY), 
//				getWidth(), (int) (getHeight() / 2.0 + posY * zoomY));
//		//Eje y
//		g.drawRect((int) (getWidth() / 2.0 - posX * zoomX), 0,
//				(int) (getWidth() / 2.0 - posX * zoomX), getHeight());

		

		
		
		
	}

}