package projetPOO;

import java.awt.Color;

public class Animal {

	private Color color;
	
	private int 
			prob_detect,
			prob_repro,
			bonus_atk,
			atk,
			def,
			speed,
			max_hunger,
			life_span,
			health,
			age;
	
	public int lastAttacked, lastMated;// Dernier tour ou animal est attaqué , s'est reproduit
	
	private Case position;
	private Case[] voisins;
	
	private Color target; // Pour cette simulation, une seule proie (cible) est necessaire
	
	public Animal(Color color,Case position) {
		
		this.color = color;
		this.position = position;
		init(color);
		
	}
	
	public void init(Color c) {
		
		//Par peur de non compatibilite des couleurs entre OS (differentes valeures RGB),
		//Nous n'utiliserons par cette formule ...
		
		//String cToStr = c.getRed()+","+c.getGreen()+","+c.getBlue();
		
		
		// ... mais celle-ci 
		String cToStr;
		if(this.color == Color.ORANGE) {
			cToStr = "255,200,0";
		}else if(this.color == Color.YELLOW) {
			cToStr = "255,255,0";
		}else if(this.color == Color.GREEN) {
			cToStr = "0,255,0";
		}else {
			cToStr = "0,255,0"; // Vipere par default
		}
		
		
		
		switch(cToStr) {
		
			case "255,200,0":// Orange : Renard

				this.prob_detect 	= 20;
				this.prob_repro 	= 14;
				this.bonus_atk 		= 12;
				this.atk 			= 20;
				this.def 			= 7;
				this.speed 			= 3;
				this.max_hunger 	= 8;
				this.life_span 		= 250;
				
				this.age 			= 5;
				
				
				break;
				
			case "255,255,0":// Jaune : Poule
				
				this.prob_detect 	= 10;
				this.prob_repro 	= 40;
				this.bonus_atk 		= 6;
				this.atk 			= 15;
				this.def 			= 23;
				this.speed 			= 2;
				this.max_hunger 	= 20;
				this.life_span 		= 150;
				
				this.age 			= 0;
				
				break;
				
			case "0,255,0"://Vert : Vipere
				
				this.prob_detect 	= 50;
				this.prob_repro 	= 50;
				this.bonus_atk 		= 8;
				this.atk 			= 5;
				this.def 			= 17;
				this.speed 			= 1;
				this.max_hunger 	= 35;
				this.life_span 		= 120;
				
				this.age 			= 10;
				
				break;
				
				
			default:
				System.out.println("========================================");
				System.out.println("/!\\ Animal Initialisé incorrectement /!\\");
				System.out.println("=========================================");
				break;
		}
		
		
	}
	
	public int getAge() {
		return this.age;
	}
	

	public void setAge(int age) {
		this.age = age;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getProb_detect() {
		return prob_detect;
	}

	public void setProb_detect(int prob_detect) {
		this.prob_detect = prob_detect;
	}

	public int getProb_repro() {
		return prob_repro;
	}

	public void setProb_repro(int prob_repro) {
		this.prob_repro = prob_repro;
	}

	public int getBonus_atk() {
		return bonus_atk;
	}

	public void setBonus_atk(int bonus_atk) {
		this.bonus_atk = bonus_atk;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMax_hunger() {
		return max_hunger;
	}

	public void setMax_hunger(int max_hunger) {
		this.max_hunger = max_hunger;
	}

	public int getLife_span() {
		return life_span;
	}

	public void setLife_span(int life_span) {
		this.life_span = life_span;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Case getPosition() {
		return position;
	}

	public void setPosition(Case position) {
		this.position = position;
	}

	public Case[] getVoisins() {
		return voisins;
	}

	public void setVoisins(Case[] voisins) {
		this.voisins = voisins;
	}

	public Color getTarget() {
		return target;
	}

	public void setTarget(Color target) {
		this.target = target;
	}

}
