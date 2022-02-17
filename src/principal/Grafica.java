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
	
	private int mouseX = 0, mouseY = 0;
	private double delta = 0.1;
	
	public Grafica(JFrame padre) {
		this.padre = padre;
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				posX -= (e.getX() - mouseX) / UNIT;
				posY += (e.getY() - mouseY) / UNIT;
				mouseX = e.getX();
				mouseY = e.getY();
				repaint();
			}
			
		});
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
		
		int lonX = (int) (UNIT * zoomX);
		int lonY = (int) (UNIT * zoomY);
		
		int posEjeX = (int) (getHeight() / 2.0 + posY * lonY);
		int posEjeY = (int) (getWidth() / 2.0 - posX * lonX);
		
		int xIzquierda = (int) (-posEjeY / (lonX));
		int xDerecha = (int) ((getWidth() - posEjeY) / (lonX));
		int yArriba = (int) (posEjeX / (lonY));
		int yAbajo = (int) (-(getHeight() - posEjeX) / (lonY));

		for(int i = xIzquierda; i <= xDerecha; i++)
			g.drawString(Integer.toString(i), (int) (posEjeY + i * lonX), posEjeX);
		for(int i = yAbajo; i <= yArriba; i++)
			g.drawString(Integer.toString(i), posEjeY, (int) (posEjeX - i * lonY));
		
		
		if(Principal.funcion.equals(""))
			return;
		//Graficar la funcion
		for(double xi = xIzquierda; xi <= xDerecha; xi += delta) {
			double xi_1 = xi;
			double xi_2 = xi_1 + delta * zoomX;
			
			try {
				double fxi_1 = Calculator.f(Principal.funcion, xi_1);
				double fxi_2 = Calculator.f(Principal.funcion, xi_2);
				double calcX = getWidth() / 2.0 - posX * UNIT * zoomX;
				double calcY = getHeight() / 2.0 + posY * UNIT * zoomY;
				
				g.drawLine((int) (calcX + xi_1 * UNIT * zoomX), 
						(int) (calcY - fxi_1 * UNIT * zoomY),
						(int) (calcX + xi_2 * UNIT * zoomX), 
						(int) (calcY - fxi_2 * UNIT * zoomY));
			} catch(Exception e) {
				continue;
			}
			
			
			
//			g.drawLine((int) (getWidth() / 2.0 + (-posX + xi_1) * UNIT * zoomX), 
//					(int) (getHeight() / 2.0 + (posY - fxi_1) * UNIT * zoomY),
//					(int) (getWidth() / 2.0 + (-posX + xi_2) * UNIT * zoomX), 
//					(int) (getHeight() / 2.0 + (posY - fxi_2) * UNIT * zoomY));
		}
		

		
		
		
	}
	
	public void aumentarZoom(double aumentoZoom) {
		zoomX += aumentoZoom;
		zoomY += aumentoZoom;
		delta = 0.1 / zoomX;
	}

}