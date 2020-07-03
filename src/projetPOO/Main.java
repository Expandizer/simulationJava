package projetPOO;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;




public class Main 
{
	public static void main(String[] args)
	{
		int posx, posy;
		
		// Affichage graphique
		ChampGraphique grid = new ChampGraphique(20, 20);
		
		// Panneau d'administration
		ControlPanel controlPanel = new ControlPanel(grid);
		controlPanel.generatePopulation();
		
		

		Time timeMaster = new Time(grid);	//Temps (duree)
		timeMaster.setDperS(20);			//Nombre de jours par seconde
		timeMaster.initLoop();				// Boucle principale
         
        
	}
	
	
}
