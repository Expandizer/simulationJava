package projetPOO;

import java.awt.Color;
import java.awt.Graphics;

public class ControlPanel {

	private ChampGraphique grid;
	
	public ControlPanel(ChampGraphique grid) {
		
		this.grid = grid;
		
	}

	
	/**
	 *  @description	Generation de la population initale depuis les entrees utilisateur
	 *  @return void	
	 */
	public void generatePopulation() {
		
		grid.ajouterCase(10, 10,Color.ORANGE);
		grid.ajouterCase(10, 11,Color.ORANGE);
		grid.ajouterCase(10, 13,Color.ORANGE);
		grid.ajouterCase(2, 5,Color.ORANGE);
		grid.ajouterCase(1, 8,Color.YELLOW);
		grid.ajouterCase(6, 12,Color.YELLOW);
		grid.ajouterCase(10, 20,Color.YELLOW);
		grid.ajouterCase(10, 1,Color.GREEN);
		grid.ajouterCase(13, 12,Color.GREEN);
		grid.ajouterCase(1, 1,Color.GREEN);
		grid.ajouterCase(1, 2,Color.GREEN);
		grid.ajouterCase(2, 1,Color.GREEN);
		grid.ajouterCase(2, 2,Color.GREEN);
		
	}
	
}
