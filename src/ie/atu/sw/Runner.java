package ie.atu.sw;

import ie.atu.sw.menu.Menu;

/**
 * Initiates the application
 */
public class Runner {
	/**
	 * Main Method to start the Application
	 *
	 * @author Dylan Boyle
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.heading();
	}
}