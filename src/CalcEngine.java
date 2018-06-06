/**
 * The main part of the calculator doing the calculations.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class CalcEngine {

	// The current value (to be) shown in the display.
	protected int displayValue;
	protected String displayString = "";
	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
	}

	public int getDisplayValue (){
		return displayValue;
	}

	public void numberPressed(int number) {
		displayString+=number;
	}

	/**
	 * The 'plus' button was pressed.
	 */
	public void plus() {
		displayString+= "+";
	}

	/**
	 * The 'minus' button was pressed.
	 */
	public void minus() {
		displayString+= "-";
	}
	/**
	 * The 'multiply' button was pressed.
	 */
	public void multiply() {
		displayString+= "*";
	}

	public void divide() {
		displayString+= "/";
	}

	public void power() {
		displayString+="^";
	}

	public void openP () {
		displayString+="(";
	}

	public void closeP () {
		displayString+=")";
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals() {
		try {
			// This should completes the building of a second operand,
			// so ensure that we really have a left operand, an operator
			// and a right operand.
			Postfix p = new Postfix();
			displayValue = p.evaluate2(p.infixToPostfix(displayString), false);
			displayString = String.valueOf(displayValue);
		} catch (Exception e) {
			clear();
		}
	}

	public String getDisplayString() {
		return displayString;
	}

	/**
	 * The 'CE' (clear) button was pressed. Reset everything to a starting state.
	 */
	public void clear() {
		displayString = "";
	}


	/**
	 * Report an error in the sequence of keys that was pressed.
	 */
	protected void keySequenceError() {
		System.out.println("A key sequence error has occurred.");
		// Reset everything.
		clear();
	}
}
