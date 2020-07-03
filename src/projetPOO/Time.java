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
		
		/*System.out.println("Animeaux presents : ");
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
			
			
			// 1 - Detecter proies proches et 2 - Reproduction si conditions
				for(Case c : cases) { 
					
					int[] tmp_neighbors = this.window.getNeighbors(c);// Liste cases voisines
					ArrayList<Integer> neighbors = new ArrayList<>(8);// Liste qui contiendra les voisins (Animeaux)
					
					int nbr_cases_voisines = tmp_neighbors.length;// Nombre cases voisines 
					
					
					
					// Nous enregistrons les cases non vides dans le teableau "neighbors"
					int j=0;
					for(int i =0 ;i < nbr_cases_voisines; i++) {
						
						if((this.window.getCase(tmp_neighbors[i]).getX() != 0)) {
							neighbors.add( tmp_neighbors[i] );
							j++;
						}
						
					}
					
					
					
					boolean has_neighbors = (neighbors.size() == 0) ? false : true;
					if(!has_neighbors) {// L'animal n'a pas de voisins , inutile de tenter de detecter / se reproduire
						continue;
					}
					
					
					// Choix d'un voisin aleatoire (Attaque / reproduction ) 
					int rand_neighbor_offset =  (int)Math.floor(Math.random() * (neighbors.size()) );
					Case rand_neighbor = this.window.getCase( rand_neighbor_offset);
					
					
					
					// Deux lancers aleatoires pour tentative attaque voisin
					double rand = Math.random()*100;
					double rand2 = Math.random()*100;
					
					
	
					
						//  Si detection (1ere ou 2e tentative gagnante) 
					if(rand <= c.getAnimal().getProb_detect() || rand2 <= c.getAnimal().getProb_detect()) {
						
						// rand_eat : points de dommages (attaque)
						double rand_eat = Math.random() * (c.getAnimal().getBonus_atk());
						rand_eat += c.getAnimal().getAtk();
						
						
						
						/**
						 * OMAR  : ATTACK()
						 * Ne pas oublier de mettre a jour Animal.lastAttacked
						 */
						
						
						
					}else { // Si tentative de detection echouee, passons etape suivante
						
						
						// 2 -Reproduction si saison : Printemps 		
						if(this.currentSeason == Seasons.Printemps) {
						
							
							// et si case voisine contient meme espece
							if(rand_neighbor.getAnimal().getColor() == c.getAnimal().getColor()) {
								
								/**
								 * OMAR  : REPRODUCTION()
								 * Ne pas oublier de mettre a jour Animal.lastMated
								 */
								
							}
							
						}
						
					}
					
					

					// 3 - Se deplacer
					
						//Si animal ne vient pas d'etre attaque/ de s'etre reproduit
					if(c.getAnimal().lastAttacked != this.dayCount && c.getAnimal().lastMated != this.dayCount) {
						
						for(int i=0; i<c.getAnimal().getSpeed() ; i++) {
							
							tmp_neighbors = this.window.getNeighbors(c);// Liste cases voisines							
							nbr_cases_voisines = tmp_neighbors.length;// Nombre cases vides voisines 
							
							ArrayList<Integer> casesVides = new ArrayList<>(8);// Enregistrement des offsets des cases voisines vides
							
							// Nous enregistrons les cases  vides dans le teableau "casesVides"
							j=0;
							for(i =0 ;i < nbr_cases_voisines; i++) {
								
								if((this.window.getCase(tmp_neighbors[i]).getX() == 0)) {
									casesVides.add( tmp_neighbors[i] );
									j++;
								}
								
							}
							
							// Choix d'un voisin aleatoire (Attaque / reproduction ) 
							rand_neighbor_offset =  (int)Math.floor(Math.random() * (casesVides.size()) );
							
							
							if(rand_neighbor_offset != 0) {
								//this.window.moveCell(c,rand_neighbor_offset);
							}
							
							
						}
						
					}
					
					
				}
				
			
			
			
				
				
			// 4 -Mourir si conditions
			
			stillInLoop = true;
			
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
	 * 				Nous supposons que l'annee commence le jour 1 a la saison Hiver
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
