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
	
	private Principal padre;
	
	private int mouseX = 0, mouseY = 0;
	private double delta = 0.2;
	
	public Grafica(Principal padre) {
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
		
		int lonX = (int) (UNIT * zoomX);
		int lonY = (int) (UNIT * zoomY);
		
		int xIzquierda = (int) (-ejeY / (lonX));
		int xDerecha = (int) ((getWidth() - ejeY) / (lonX));
		int yArriba = (int) (ejeX / (lonY));
		int yAbajo = (int) (-(getHeight() - ejeX) / (lonY));

		for(int i = xIzquierda; i <= xDerecha; i++)
			g.drawString(Integer.toString(i), (int) (ejeY + i * lonX), ejeX);
		for(int i = yAbajo; i <= yArriba; i++)
			g.drawString(Integer.toString(i), ejeY, (int) (ejeX - i * lonY));
		
		JPanel listaFunciones = padre.getListaFunciones();
		Component[] funciones = listaFunciones.getComponents();
		for(Component f: funciones) {
			Funcion funcion = (Funcion) f;
			graficarFuncion(funcion, g, xIzquierda, xDerecha);
		}
		
	}

	public void graficarFuncion(Funcion funcion, Graphics g, double xIzquierda, double xDerecha) {
		g.setColor(funcion.getColor());
		String funcionTexto = funcion.getFuncion();
		
		//Graficar la funcion
		for(double xi = xIzquierda; xi <= xDerecha; xi += delta) {
			double xi_1 = xi;
			double xi_2 = xi_1 + delta * zoomX;
			
			try {
				double fxi_1 = Calculator.f(funcionTexto, xi_1);
				double fxi_2 = Calculator.f(funcionTexto, xi_2);
				double calcX = getWidth() / 2.0 - posX * UNIT * zoomX;
				double calcY = getHeight() / 2.0 + posY * UNIT * zoomY;
				
				g.drawLine((int) (calcX + xi_1 * UNIT * zoomX), 
						(int) (calcY - fxi_1 * UNIT * zoomY),
						(int) (calcX + xi_2 * UNIT * zoomX), 
						(int) (calcY - fxi_2 * UNIT * zoomY));
			} catch(Exception e) {
				continue;
			}
		}
	}
	
	public void aumentarZoom(double aumentoZoom) {
		zoomX += aumentoZoom;
		zoomY += aumentoZoom;
		delta = 0.2 / zoomX;
	}

}