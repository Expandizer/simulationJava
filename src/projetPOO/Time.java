package projetPOO;

import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;

public class Time {
	
	final int dayDuration = 24;
	
	private ChampGraphique window;
	private int dPerS;
	private int dayCount;
	
	private Seasons currentSeason;
	
	
	public Time(ChampGraphique window) {
		this.window = window;
		
		/*System.out.println("Animeaux présents : ");
		for(Case c : this.window.getCases()) {
			
			System.out.println( c.getX()+" , "+c.getY());
			
		}*/
	}
	
	
	public void setDperS(int dPerS) {
		this.dPerS = dPerS;
		this.window.DperS = dPerS;
	}
	
	public void initLoop() {
		Boolean stillInLoop = true;
		while(stillInLoop) {
			
			this.dayCount++;
			Seasons s = this.currentSeason;
			updateCurrentSeason();
			
			if(s != this.currentSeason) {
				System.out.println("Saison : "+this.currentSeason);
			}
			
			//======BOUCLE PRINCIPALE======
			
			
			/* 0 - Trier animeaux par ordre croissant : Age */
				
				/* 0 - 1 - Tri */
				ArrayList<Case> cases  = this.window.getCases();
			
			
			// 1 - Detecter proies proches
				for(Case c : cases) {
					
					int[] tmp_neighbors = this.window.getNeighbors(c);
					ArrayList<Integer> neighbors = new ArrayList<>(8);
					
					// Si une case voisine est vide, son offset devient = 0
					int j=0;
					for(int i =0 ;i <tmp_neighbors.length ; i++) {
						tmp_neighbors[i] = (this.window.getCase(tmp_neighbors[i]).getX() == 0) 
										? 0
										: tmp_neighbors[i];
						if(tmp_neighbors[i] != 0) {
							neighbors.add( tmp_neighbors[i] );
							j++;
						}
					}
					
					//Recherche de si oui ou non l'animal a un(des) voisin(s)
					boolean has_neighbors = (neighbors.size() == 0) ? false : true;
					
					
					if(!has_neighbors) {// L'animal n'a pas de voisins , inutile de tenter de detecter
						continue;
					}
					
					System.out.println("has N");
					
					double rand = Math.random()*100;
					double rand2 = Math.random()*100;
	
					
					if(rand <= c.getAnimal().getProb_detect() || rand2 <= c.getAnimal().getProb_detect()) {// Si detection (1ere et 2e tentatives)
						
						
						double rand_eat = Math.random() * (c.getAnimal().getBonus_atk());
						rand_eat += c.getAnimal().getAtk();
						
						int rand_neighbor_offset =  (int)Math.floor(Math.random() * (neighbors.size()) );
						
						Case rand_neighbor = this.window.getCase( rand_neighbor_offset);
						
						if(rand_eat > rand_neighbor.getAnimal().getDef()) {
							System.out.println("Attack");
						}
						
						
						
					}else {
						// Tentative de detection echouée, passons etape suivante
					}
					
					
					
				}
				
			
			// 2 -Reproduction si saison : Printemps
				// Si case voisine contient meme espece
			
			// 3 - Se deplacer
			
			// 4 -Mourir si conditions
			
				stillInLoop = false;
			
			//=============================
			
			this.window.repaint();
			this.window.setCurrentDay(this.dayCount);
			try 
			{
	            Thread.sleep(1000/dPerS);
	        } 
			catch (InterruptedException e) 
			{
	            e.printStackTrace();
	        }
		}
		
	}
	
	
	/**
	 * @description Definit la saison actuelle (une saison = 30 jours),
	 * 				Nous supposons que l'annee commence le jour 1 à la saison Hiver
	 */
	private Seasons updateCurrentSeason() {
		
		int seasonPeriod = 30;
		
		double div = ((double) this.dayCount%365) / seasonPeriod  ;
		
		switch((int) Math.floor(div)) {
			
			case 0:	// Hiver
				this.currentSeason = Seasons.Hiver;
				break;
				
			case 1:	// Hiver
				this.currentSeason = Seasons.Printemps;
				break;
				
			case 2:	// Hiver
				this.currentSeason = Seasons.Ete;
				break;
				
			case 3:	// Hiver
				this.currentSeason = Seasons.Automne;
				break;
			
			default:
				break;
		
		}
		
		this.window.changeCurrentSeason(this.currentSeason);
		
		return this.currentSeason;
		
	}
	

}
