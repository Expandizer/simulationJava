package projetPOO;

public class Time {
	
	final int dayDuration = 24;
	
	private ChampGraphique window;
	private int dPerS;
	private int dayCount;
	
	
	public Time(ChampGraphique window) {
		this.window = window;
	}
	
	
	public void setDperS(int dPerS) {
		this.dPerS = dPerS;
	}
	
	public void initLoop() {
		Boolean stillInLoop = true;
		while(stillInLoop) {
			this.dayCount++;
			
			/*
			 * ICI CODE DE LA BOUCLE PRINCIPALE
			 */
			
			this.window.repaint();
			System.out.println("Refreshed window at t = T+"+this.dayCount);
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
}
