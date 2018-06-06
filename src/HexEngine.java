public class HexEngine extends CalcEngine {

	protected boolean active;
	protected String hexdisplayValue;

	public HexEngine() {
		super();
	}

	/*@Override
	public void numberPressed(int number) {
		if (!active) {
			super.numberPressed(number);
		} else {
			if (buildingDisplayValue) {
				// Incorporate this digit.
				displayValue = displayValue * 16 + number;
			} else {
				// Start building a new number.
				displayValue = number;
				buildingDisplayValue = true;
			}
			//System.out.println(buildingDisplayValue);
			haveLeftOperand = true;
		}
	}*/

	@Override
	public void equals() {
		try {
			if (!isActive()) super.equals();
				// This should completes the building of a second operand,
				// so ensure that we really have a left operand, an operator
				// and a right operand.
			else {
				Postfix p = new Postfix();
				displayValue = p.evaluate2(p.infixToPostfix(displayString), true);
				displayString = Integer.toHexString(displayValue);
			}
		} catch (Exception e) {
			clear();
		}
	}

	protected void convertoHex() {
		Integer value = super.getDisplayValue();
		// System.out.println(value);
		String hex;
		if (value>0) {
			hex = Integer.toHexString(value).toUpperCase();
		} else {
			hex  = "-"+Integer.toHexString(Math.abs(value)).toUpperCase();
		}
		// System.out.println(hex);
		hexdisplayValue = hex;
	}

	public String getHexDisplayValue() {
		convertoHex();
		hexdisplayValue = displayString;
		return hexdisplayValue;
	}

	public void setActive() {
		active = true;
	}

	public boolean isActive() {
		return active;
	}

	public String getStatus() {
		if (active)
			return "HEX";
		else
			return "DEC";
	}

	public void setInactive() {
		active = false;

	}

}