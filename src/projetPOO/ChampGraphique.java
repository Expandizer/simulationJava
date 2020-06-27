package projetPOO;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Merci à StackOverflow pour sa précieuse contribution !


public class ChampGraphique extends JPanel
{
	final int size = 15;	// Taille case
	final int padding = 50;	// Zone vide avant fin fenetre
	final int controlPanelWidth = 170;
	
	private int largeur, hauteur,pixelWidth,pixelHeight;
	
	private List<Point> casesAColorier;
	private ArrayList<Case> cases;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur) 
	{	
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.pixelWidth = largeur*this.size + this.padding + this.controlPanelWidth;
		this.pixelHeight = hauteur*this.size + this.padding;
		
		casesAColorier = new ArrayList<>(25);
		cases = new ArrayList<>(25);

		JFrame window = new JFrame();
		window.setSize(this.pixelWidth,this.pixelHeight);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setTitle("Poules, vipères, et renards -- AQUESBI Adam (11929071)");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);
		int window_x = window.getX();
		int window_y = window.getY();
		
		JFrame controlWindow = new JFrame();
		controlWindow.setSize(this.pixelWidth,150);
		controlWindow.setResizable(false);
		controlWindow.setLocation(
				window_x,
				window_y+this.pixelHeight);
		controlWindow.setTitle("Panneau de controle");
		controlWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2,7));
		controlPanel.setSize(this.pixelWidth,150);
		
		
		JButton startBtn= new JButton("Démarrer");
		
		JLabel empty = new JLabel("<html>Nombre d'animeaux<br> de départ</html>");
		JLabel empty1 = new JLabel("");
		JLabel empty2 = new JLabel("");
		JLabel empty3 = new JLabel("");
		JLabel empty4 = new JLabel("");
		JLabel empty5 = new JLabel("");
		JLabel sig = new JLabel("Panneau de controle");
		controlPanel.add(empty);
		controlPanel.add(empty1);
		controlPanel.add(empty2);
		controlPanel.add(startBtn);
		controlPanel.add(empty3);
		controlPanel.add(empty4);
		controlPanel.add(empty5);
		controlPanel.add(sig);
		
		controlWindow.getContentPane().add(controlPanel);
		controlWindow.setVisible(true);
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		// Cases a colorier
		for (Case cellToFill : cases) 
		{
			int cellX = (cellToFill.getX() * this.size);
			int cellY = (cellToFill.getY() * this.size);
			g.setColor(cellToFill.getColor());
			g.fillRect(cellX, cellY, this.size, this.size);
		}
		
		//Bordures du tableau
		g.setColor(Color.BLACK);
		g.drawRect(this.size, this.size, largeur*this.size, hauteur*this.size);
		
		//lignes et colones
		for (int i = this.size; i <= largeur*this.size; i += this.size) {
			g.drawLine(i, this.size, i, hauteur*this.size+this.size);
		}
		for (int i = this.size; i <= hauteur*this.size; i += this.size) {
			g.drawLine(this.size, i, largeur*this.size+this.size, i);
		}
		

		// Panneau de controle
		g.setColor(Color.GRAY);
		g.drawLine(
					this.pixelWidth - this.controlPanelWidth,this.pixelHeight,
					this.pixelWidth - this.controlPanelWidth,0
				);
		
		
		//details fenetre
		String title = "Poules, vipères, et renards";
		g.drawString(title,this.pixelWidth - this.controlPanelWidth + 5, this.pixelHeight-70);
		g.drawString("Par AQUESBI Adam ",this.pixelWidth - this.controlPanelWidth + 5, this.pixelHeight-50);
		
	}

	/**
	 * Permet d'ajouter une case (en option : d'une couleur)
	 * @param x Abscisse de la case à ajouter 
	 * @param y Ordonnée de la case à ajouter
	 * @param color Couleur.
	 */	
	public Case ajouterCase(int x,int y, Color color) {
		
		Case cellToAdd = new Case(x,y,color);
		cellToAdd.setOffset(x + (y-1)*this.largeur);
		
		if(!cases.contains(cellToAdd)) {
			cases.add(new Case(x,y,color));
		}else {
			cases.set(cellToAdd.getOffset(),cellToAdd);
		}
		
		return cellToAdd;
	}
		
		/**
		 * Permet d'ajouter une case de couleur par defaut
		 * @param x Abscisse de la case à ajouter 
		 * @param y Ordonnée de la case à ajouter
		 */	
		public Case ajouterCase(int x,int y) {
			return ajouterCase(x,y,Case.DEFAULT_COLOR);
		}
	
		
	/**
	 * @description Retourne les offsets des voisins d'une case s'ils existent
	 * @param case Case dont on recherche les voisins
	 * @return int[] neighboors Offsets des voisins de la case
	 */
	public int[] getNeighboors(Case c) {
		// Position de la case
		int x = c.getX();
		int y = c.getY();
		
		int max_offset = this.largeur*this.hauteur;	// Offset max de la case
		
		// Si la case est sur une bordure, on ne cherche pas ses voisins du cote 
		// de ladite bordure
		int max_left = (c.getX() > 1) ? -1 : 0;
		int max_right = (c.getX() < this.largeur ) ? 1 : 0;
		int max_top = (c.getY() > 1) ? -1 : 0;
		int max_bottom = (c.getY() < this.hauteur) ? 1 : 0;
		
		
		System.out.println("Getting neighboors; cell's offset : "+c.getOffset());
		
		//Recherche des voisins selons les criteres limites
		int[] neighboors = {0,0,0,0,0,0,0,0};
		int count = 0;
		for(int i=max_top ; i<=max_bottom ; i++) {//Recherche
			
			for(int j=max_left ; j<=max_right ; j++) {
				
				int neigh_offset = c.getOffset()+j + i*this.largeur ;
				if(neigh_offset > 0 && neigh_offset <= max_offset && neigh_offset != c.getOffset()) {
					neighboors[count] = neigh_offset;
					System.out.println("[Count:"+count+"] - Added offset #"+neigh_offset);
					count++;
				}
				
			}
			
			
		}
		
		return neighboors;
	}
		
	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur()
	{
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur()
	{
		return hauteur;
	}
}
