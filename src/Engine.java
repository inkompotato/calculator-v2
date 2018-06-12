
/**
 * UserInterface
 * @author Jan Schelhaas and Larissa Wagnerberger
 * @version 2018.06.13
 */
public class Engine {
    private String displayString = "";
    private boolean hexMode = false;
    private String error = "";

    public Engine() {
        clear();
    }

    public void numberPressed(int number) {
        displayString += number;
    }

    public void equals() {
        try {
            Postfix p = new Postfix();
            //System.out.println(hexMode);
            if (!hexMode) {
                displayString = String.valueOf(p.evaluate2(p.infixToPostfix(displayString), false));
            } else {
                int result = p.evaluate2(p.infixToPostfix(displayString), true);
                String sign = (Math.signum(result) < 0) ? "-" : "";
                displayString = sign + Integer.toHexString(Math.abs(result)).toUpperCase();
            }
            error = "";

        } catch (Exception e) {
            error = "Error while parsing expression: Illegal Character";
            clear();
        }
    }

    public boolean getHexMode() {
        return hexMode;
    }

    public void setHexMode(boolean hexMode) {

        if (hexMode) {
            try {
                int number = Integer.parseInt(displayString);
                String sign = (Math.signum(number) < 0) ? "-" : "";
                displayString = sign + Integer.toHexString(Math.abs(number)).toUpperCase();
                error = "";
            } catch (Exception e) {
                if (!displayString.equals(""))
                    error = "Illegal Characters in DEC String";
            }
        } else {
            try {
                String sign = (displayString.matches("^-.*")) ? "-" : "";
                displayString = displayString.replaceAll("^-", "");
                int decoded = Integer.decode("0x" + displayString);
                displayString = sign + String.valueOf(decoded);
                error = "";
            } catch (Exception e) {
                if (!displayString.equals(""))
                    error = "Illegal Characters in HEX String";
            }
        }
        this.hexMode = hexMode;
    }

    public void negate() {
            try {
                String sign = (displayString.matches("^-.*")) ? "" : "-";
                displayString = displayString.replaceAll("^-", "");
                int decoded;
                if (hexMode){
                    decoded = Integer.decode("0x" + displayString);
                    displayString = sign + Integer.toHexString(decoded).toUpperCase();
                }
                else {
                    decoded = Integer.parseInt(displayString);
                    displayString = sign + String.valueOf(decoded);
                }
                error = "";

            } catch (Exception e) {
                error = "Unable to negate this expression";
            }
        }


        public void op (String op){
            displayString += op;
        }

        public String getDisplayString () {
            return displayString;
        }

        public void clear () {
            displayString = "";
        }

        public String getStatus () {
            if (hexMode)
                return "HEX";
            else
                return "DEC";
        }

        public String getError () {
            return error;
        }

}
