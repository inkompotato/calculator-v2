
/**
 * UserInterface
 * @author Jan Schelhaas and Larissa Wagnerberger
 * @version 2018.06.08
 */
public class Engine {
    private String displayString = "";
    private boolean hexMode = false;
    private String error ="";

    public Engine() {
        clear();
    }

    public void numberPressed(int number) {
        displayString+=number;
    }

    public void equals() {
        try {
            Postfix p = new Postfix();
            //System.out.println(hexMode);
            if (!hexMode){
                displayString = String.valueOf(p.evaluate2(p.infixToPostfix(displayString), false));
            } else {
                int result = p.evaluate2(p.infixToPostfix(displayString), true);
                String sign = (Math.signum(result) < 0) ? "-" : "";
                displayString = sign+Integer.toHexString(Math.abs(result)).toUpperCase();
            }
            error ="";

        } catch (Exception e) {
            error = "Error while parsing expression: Illegal Character";
            clear();
        }
    }

    public boolean getHexMode() {
        return hexMode;
    }

    public void setHexMode(boolean hexMode){
        this.hexMode = hexMode;
        if (hexMode){
            try {
                displayString = Integer.toHexString(Integer.parseInt(displayString)).toUpperCase();
                error ="";
            } catch (Exception e){
                if (!displayString.equals(""))
                    error = "Illegal Characters in DEC String";
            }
        } else {
            try {
                int decoded = Integer.decode("0x"+displayString);
                displayString = String.valueOf(decoded);
                error ="";
            } catch (Exception e){
                if (!displayString.equals(""))
                    error = "Illegal Characters in HEX String";
            }
        }
    }

    public void op(String op) {
        displayString+= op;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void clear() {
        displayString = "";
    }

    public String getStatus() {
        if (hexMode)
            return "HEX";
        else
            return "DEC";
    }

    public String getError() {
        return error;
    }
}
