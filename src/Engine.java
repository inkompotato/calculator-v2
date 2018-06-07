public class Engine {
    private String displayString = "";
    private boolean hexMode = false;

    public Engine() {
        clear();
    }

    public void numberPressed(int number) {
        displayString+=number;
    }

    public void equals() {
        try {
            Postfix p = new Postfix();
            System.out.println(hexMode);
            if (!hexMode){
                displayString = String.valueOf(p.evaluate2(p.infixToPostfix(displayString), false));
            } else {
                displayString = Integer.toHexString(p.evaluate2(p.infixToPostfix(displayString), true)).toUpperCase();
            }

        } catch (Exception e) {
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
            } catch (Exception e){

            }
        } else {
            try {
                int decoded = Integer.decode("0x"+displayString);
                displayString = String.valueOf(decoded);
            } catch (Exception e){

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

}
