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
		
		ChampGraphique grid = new ChampGraphique(20, 20);
		
		/*
		Case c = grid.ajouterCase(2, 2);
		int[] voisins = {};
		voisins = grid.getNeighboors(c);
		for(int i = 0; i<8;i++) {
			System.out.println("Voisin trouvé : ("+voisins[i]+")");
		}
		
		Case c2 = grid.ajouterCase(1, 1);
		int[] voisins2 = {};
		voisins2 = grid.getNeighboors(c2);
		for(int i = 0; i<8;i++) {
			System.out.println("Voisin trouvé : ("+voisins2[i]+")");
		}
		*/

		
		
		Time timeMaster = new Time(grid);	//Temps (duree)
		timeMaster.setDperS(1);				//Nombre de jours par seconde
		timeMaster.initLoop();				// Boucle principale
         
        
	}
}
