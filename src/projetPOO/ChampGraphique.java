package projetPOO;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;



public class ChampGraphique extends JPanel implements ActionListener {
	final int size = 15;	// Taille case
	final int padding = 50;	// Zone vide avant fin fenetre
	final int controlPanelWidth = 170;
	
	private int largeur, hauteur,pixelWidth,pixelHeight;
	
	private List<Point> casesAColorier;
	private ArrayList<Case> cases;

	Seasons currentSeason;
	int currentDay;
	int DperS;
	
	JTextField input_ren, input_pou, input_vip;
	JFrame window = new JFrame();
	
	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur){	
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.pixelWidth = largeur*this.size + this.padding + this.controlPanelWidth;
		this.pixelHeight = hauteur*this.size + this.padding;
		
		casesAColorier = new ArrayList<>(25);
		cases = new ArrayList<>(25);

		Seasons currentSeason;
		
		//	Fenetre principale
		JFrame window = this.window;
		window.setSize(this.pixelWidth,this.pixelHeight);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setTitle("Poules, vipères, et renards -- AQUESBI Adam (11929071)");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		int window_x = window.getX();
		int window_y = window.getY();
		
		
		//	Fenetre d'administration
		JFrame controlWindow = new JFrame();
		controlWindow.setSize(this.pixelWidth,150);
		controlWindow.setResizable(false);
		controlWindow.setLocation(
				window_x,
				window_y+this.pixelHeight);
		controlWindow.setTitle("Panneau de controle");
		controlWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Panel d'administration
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3,4));
		controlPanel.setSize(this.pixelWidth,150);
		
			//	Composantes du panel
		JButton startBtn= new JButton("Démarrer");
		
		JLabel label_ren = new JLabel("<html>Nombre renards</html>");
		JTextField input_ren = new JTextField();
		this.input_ren = input_ren;
		
		JLabel label_pou = new JLabel("<html>Nombre poules</html>");
		JTextField input_pou = new JTextField();
		this.input_pou = input_pou;
		
		JLabel label_vip = new JLabel("<html>Nombre vipères</html>");
		JTextField input_vip = new JTextField();
		this.input_vip = input_vip;
		
		JLabel sig = new JLabel("Panneau de controle");
			
			//	Labels vides pour bien agencer les elements
		JLabel empty1 = new JLabel("");
		JLabel empty2 = new JLabel("");
		JLabel empty3= new JLabel("");
		JLabel empty4 = new JLabel("");
		
		controlPanel.add(label_ren);
		controlPanel.add(input_ren);
		controlPanel.add(empty1);
		controlPanel.add(startBtn);
		
		controlPanel.add(label_pou);
		controlPanel.add(input_pou);
		controlPanel.add(empty2);
		controlPanel.add(empty3);
		
		controlPanel.add(label_vip);
		controlPanel.add(input_vip);
		controlPanel.add(empty4);
		
		sig.setForeground(Color.LIGHT_GRAY);
		controlPanel.add(sig);
		
		
		// Listeners et enregistrements de donnees saisies
		CaretListener listen_ren = getCaretListener();
		CaretListener listen_pou = getCaretListener();
		CaretListener listen_vip = getCaretListener();
		
        input_ren.addCaretListener(listen_ren);
        input_pou.addCaretListener(listen_pou);
        input_vip.addCaretListener(listen_vip);
        
        startBtn.addActionListener(this);
        
		controlWindow.getContentPane().add(controlPanel);
		controlWindow.setVisible(true);
	}
	
	private int checkIsNum(String input) {
		
		int ret;
		
		try {
		     ret = Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
		     ret = 10;
		}
		
		return ret;
		
	}
	
	public void actionPerformed(ActionEvent event) {
		String ren = this.input_ren.getText();
		String pou = this.input_pou.getText();
		String vip = this.input_vip.getText();
		
		int int_ren = checkIsNum(ren);
		int int_pou = checkIsNum(pou);
		int int_vip = checkIsNum(vip);
		
		System.out.println("Nombre renards : "+int_ren);
		System.out.println("Nombre poules : "+int_pou);
		System.out.println("Nombre vipères : "+int_vip);
		

		window.setVisible(true);
	}
	
	
	
	
	// Listener des inputs (JTextField)
	private CaretListener getCaretListener() {
		return new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                //System.out.println(text.getText());
            }
        };
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
		
		
		//Panneau d'administration (a droite fenetre principale)
		String season = "Saison : ";
		if(this.currentSeason != null) {
			season += this.currentSeason;
		}
		g.drawString(season,this.pixelWidth - this.controlPanelWidth + 5,25);
		
		String day = "Jour #";
		if(this.currentSeason != null) {
			day += this.currentDay;
		}
		g.drawString(
				day+"  (1:"+this.DperS+")",
				this.pixelWidth - this.controlPanelWidth + 5,
				45);
		
		
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
			cases.add(cellToAdd);
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
		
		public void removeCell(Case c) {
			cases.remove(c);
		}
	
		
	public ArrayList<Case> getCases() {
		return this.cases;
	}
	
	
	
	public Case getCase(int offset) {
		
		if(offset != 0) {
			
			int x = offset%this.largeur;
			int y = (int)Math.floor(offset/this.largeur) + 1;
			
			for(Case c: this.cases) {
				if(c.getX() == x && c.getY() == y) {
					return c;
				}
			}
			
		}
		
		return new Case(0,0);
	}
		
	/**
	 * @description Retourne les offsets des voisins d'une case s'ils existent
	 * @param case Case dont on recherche les voisins
	 * @return int[] neighboors Offsets des voisins de la case
	 */
	public int[] getNeighbors(Case c) {
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
		
		
		//Recherche des voisins selons les criteres limites
		int[] neighbors = {0,0,0,0,0,0,0,0};
		int count = 0;
		for(int i=max_top ; i<=max_bottom ; i++) {//Recherche
			
			for(int j=max_left ; j<=max_right ; j++) {
				
				int neigh_offset = c.getOffset()+j + i*this.largeur ;
				if(neigh_offset > 0 && neigh_offset <= max_offset && neigh_offset != c.getOffset()) {
					neighbors[count] = neigh_offset;
					//System.out.println("[Count:"+count+"] - Added offset #"+neigh_offset);
					count++;
				}
				
			}
			
			
		}
		
		return neighbors;
	}
	
	
	public void moveCell(Case c, int offset) {
		
		if(offset != 0) {
			
			int x = offset%this.largeur;
			int y = (int)Math.floor(offset/this.largeur) + 1;
			
			removeCell(c);
			ajouterCase(c.getX(),c.getY(),c.getColor());
			
		}
		
		
	}
	
	public void changeCurrentSeason(Seasons saison) {
		this.currentSeason = saison;
	}
	
	public void setCurrentDay(int day) {
		this.currentDay = day;
	}
		
	public void setDperS(int DperS) {
		this.DperS = DperS;
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
