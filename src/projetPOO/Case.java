package projetPOO;

import java.awt.Color;

public class Case {
	
	final static Color DEFAULT_COLOR = Color.BLACK;
	
	private Color color;
	private int x_pos,y_pos;
	
	
	private int offset;			//Position de la case
	
	public Case(int x,int y) {
		this(x, y, DEFAULT_COLOR);
	}
	
		public Case(int x,int y,Color color) {
			
			this.setCoord(x,y);
			this.setColor(color);
		}
		
	/**
	 * @description Setters & Getters
	 */
	
	public void setCoord(int x,int y) {
		this.x_pos = x;
		this.y_pos = y;
	}
	
	public int getX() {
		return this.x_pos;
	}
	
	public int getY() {
		return this.y_pos;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
