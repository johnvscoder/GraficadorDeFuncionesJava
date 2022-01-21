package principal;

import javax.swing.*;
import java.awt.*;

import matematicas.*;

import java.awt.event.*;
import java.io.*;

public class Grafica extends JPanel {
	
	private Color fondo;
	private Color ejes;
	private Font fuente;
	
	private double zoomX, zoomY;
	private double despX, despY;
	
	//La cantidad de pixeles a que es igual una unidad de longitud
	private static final double UNIT = 10.0;
	
	public Grafica() {
		this(Color.WHITE, Color.GREEN, 1.0, 1.0, 0.0, 0.0);
		
		//Cargar una fuente
		try(BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream("res/GlacialIndifference-Regular.otf"))) {
			fuente = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(Font.PLAIN, 14);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuente);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	public Grafica(Color fondo, Color ejes, double zoomX, double zoomY, double despX, double despY) {
		this.fondo = fondo;
		this.ejes = ejes;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
		this.despX = despX;
		this.despY = despY;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(fuente);
		g.setColor(fondo);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Los ejes
		g.setColor(ejes);
		dibujarLinea(g, -getAncho() / 2 + 1, 0, getAncho() / 2 - 1, 0);
		
		
		
	}

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//        g.setColor(fondo);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        
//        g.setColor(Color.BLACK);
//        
//        //Eje x
//        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); 
//        //Eje y
//        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
//
//        for(int i = (int) (-getWidth() / 2.0 / 20.0); i <= (int) (getWidth() / 2.0 / 20.0); i += 1) {
//            g.drawLine(getWidth() / 2 + i * 20, getHeight() / 2 - 3, getWidth() / 2 + i * 20, getHeight() / 2 + 3);
//            if(i != 0)
//                g.drawString(Integer.toString(i), getWidth() / 2 + i * 20 - 3, getHeight() / 2 - 9);
//        }
//
//        for(int i = (int) (-getHeight() / 2.0 / 20.0); i <= (int) (getHeight() / 2.0 / 20.0); i += 1) {
//            g.drawLine(getWidth() / 2 - 3, getHeight() / 2 + (-i * 20), getWidth() / 2 + 3, getHeight() / 2 + (-i * 20));
//            if(i != 0)
//                g.drawString(Integer.toString(i), getWidth() / 2 + 12, getHeight() / 2 + (-i * 20));
//        }
//
//        if(Principal.funcion.equals(""))
//            return;
//
//        g.setColor(Color.BLUE);
//        
//        for(double x = -getWidth() / 2.0; x <= getWidth() / 2.0; x += 1.0) {
//            try {
//                double xPrevio = x - 1.0;
//                double fx = Calculator.f(Principal.funcion, x);
//                double fxPrevio = Calculator.f(Principal.funcion, xPrevio);
//
//                if(Double.isFinite(fx) && Double.isFinite(fxPrevio)) {
//                    g.drawLine((int) (getWidth() / 2.0  + (xPrevio) * 20.0), 
//                    (int) (getHeight() / 2.0 - fxPrevio * 20.0), (int) (getWidth() / 2.0 + x * 20.0), 
//                   (int) (getHeight() / 2.0 - fx * 20.0));
//                }
//            } catch(RuntimeException e) {
//                
//            }
//        }
//    }
	
	private void dibujarLinea(Graphics g, double x1, double y1, double x2, double y2) {
		g.drawLine((int) (getWidth() / 2.0 + (x1 - despX) * zoomX * UNIT), (int) (getHeight() / 2.0 - (y1 - despY) * zoomY * UNIT),
				(int) (getWidth() / 2.0 + (x2 - despX) * zoomX * UNIT), (int) (getHeight() / 2.0 - (y2 - despY) * zoomY * UNIT));
	}
	
	private double getAncho() {
		return getWidth() / UNIT;
	}
	
	private double getAlto() {
		return getHeight() / UNIT;
	}

}