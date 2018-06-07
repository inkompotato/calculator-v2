/**
 * The main class of a simple calculator. Create one of these and you'll get the
 * calculator on screen.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Calculator {
	private Engine engine;
	private HexInterface gui;

	/**
	 * Create a new calculator and show it.
	 */
	public Calculator() {
		engine = new Engine();
		gui = new HexInterface(engine);
	}

	/**
	 * In case the window was closed, show it again.
	 */
	public void show() {
		gui.setVisible(true);
	}
}
