import java.util.Scanner;
import java.util.Stack;
/**
 * UserInterface
 * @author Jan Schelhaas, Pascal Polchow, Larissa Wagnerberger
 * @version 2018.06.08
 */

public class Postfix {


    public void input() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {

                System.out.println("enter infix expression:");
                String line = sc.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                }
                else {
                    try {
                        System.out.println(evaluate2(infixToPostfix(line),false));
                    } catch (UnknownCharacterException e) {
                }
            }
        }
    }

    private Stack<Integer> s = new Stack<>();

    /**
     * method that evaluates a postfix expression
     * @param pfx postfix expression
     * @return result
     */
    public int evaluate(String pfx) {

        pfx = pfx.trim().replaceAll("\\s", "");
        char[] expr = pfx.toCharArray();
        for (int i = 0; i < expr.length; i++) {
            if (expr[i] >= 48 && expr[i] <= 57)
                s.push(Character.getNumericValue(expr[i]));
            if (expr[i] == '*')
                multiply();
            if (expr[i] == '-') {
                subtract();
            }
            if (expr[i] == '+')
                add();
            if (expr[i] == '/')
                divide();
            if (expr[i] == '^')
                power();
        }

        return s.peek();
    }

    /**
     * improved version of the evaluate- method, it now works with multi-digit numbers
     * calculates a result out of a postfix expression
     * @param pfx postfix expression
     * @return result
     */
    public int evaluate2(String pfx, boolean hexmode) throws UnknownCharacterException{
        String[] expr = pfx.split("\\s");
        for (String st : expr) {

            char[] ch = st.toCharArray();
            int numberLength = 0;
            for (int i = 0; i < ch.length; i++) {

                if (ch[i] == '*') {
                    multiply();
                    numberLength = 0;
                }
                else if (ch[i] == '-') {
                    subtract();
                    numberLength = 0;
                }
                else if (ch[i] == '+') {
                    add();
                    numberLength = 0;
                }
                else if (ch[i] == '/') {
                    divide();
                    numberLength = 0;
                }
                else if (ch[i] == '^') {
                    power();
                    numberLength = 0;
                }
                else if (ch[i] >= 48 && ch[i] <= 57) {
                    s.push(Character.getNumericValue(ch[i]));
                    if (numberLength > 0) {
                        int second = (int) s.peek();
                        s.pop();
                        int first = (int) s.peek();
                        s.pop();
                        if (!hexmode)
                            s.push(first * 10 + second);
                        else s.push (first * 16 + second);
                    }
                    numberLength++;
                }
                else if (ch[i]>= 65 && ch[i]<= 70) {
                    String hex = "0x" + ch[i];
                    int number = Integer.decode(hex);
                    s.push(number);
                    if (numberLength > 0) {
                        int second = (int) s.peek();
                        s.pop();
                        int first = (int) s.peek();
                        s.pop();
                        s.push(first * 16 + second);
                    }
                    numberLength++;
                }

                else throw new UnknownCharacterException(ch[i]);

            }

        }
        return s.peek();
    }

    /**
     * takes an infix expression String and generates a postfix expression String out of it
     * @param ifx infix expression
     * @return postfix expression
     */
    public String infixToPostfix(String ifx) throws UnknownCharacterException{
        ifx = ifx.trim().replaceAll("\\s", "");
        char[] expr = ifx.toCharArray();
        String result = "";
        Stack<Character> p = new Stack<>();
        for (int i = 0; i < expr.length; i++) {
            if ((expr[i] >= 48 && expr[i] <= 57) )
                result += (Character.getNumericValue(expr[i]));
            else if (expr[i] >= 65 && expr[i] <= 70 ) {
                result += expr[i];
            }
            else if (expr[i] == '(')
                p.push(expr[i]);
            else if (expr[i] == ')') {
                try {
                    while (!p.peek().equals('(')) {
                        result += " " + p.peek();
                        p.pop();
                    }
                    p.pop();
                } catch (NullPointerException e) {
                    System.out.println("Warning while parsing expression: Empty Stack, too many closing parentheses");
                }
            }
            else if (expr[i] == '*' || expr[i] == '-' || expr[i] == '+' || expr[i] == '/' || expr[i] == '^') {
                result += " ";
                while (!p.isEmpty() && !(checkPrecedence(p.peek(), expr[i]) || (expr[i] == '^' && p.peek().equals('^')))) {
                    result += p.peek() + " ";
                    p.pop();
                }
                p.push(expr[i]);
            }
            else throw new UnknownCharacterException(expr[i]);
        }

        while (!p.isEmpty()) {
            result += " " + p.peek();
            p.pop();
        }


        return result;

    }

    private boolean checkPrecedence(char top, char next) {
        return getPrec(top) < getPrec(next);
    }

    private int getPrec(char operator) {
        switch (operator) {
            case '(':
                return 0;
            case ')':
                return 0;
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    private void power() {
        int op2 = (int) s.peek();
        s.pop();
        int op1 = (int) s.peek();
        s.pop();
        int result = (int) Math.pow(op1, op2);
        s.push(result);
    }

    private void divide() {
        int op2 = (int) s.peek();
        s.pop();
        int op1 = (int) s.peek();
        s.pop();
        int result = Math.round((float)op1/(float)op2);
        s.push(result);
    }

    private void add() {
        int op2 = (int) s.peek();
        s.pop();
        int op1 = (int) s.peek();
        s.pop();
        int result = op1 + op2;
        s.push(result);
    }

    private void subtract() {
        int op2 = (int) s.peek();
        s.pop();
        int op1 = (int) s.peek();
        s.pop();
        int result = op1 - op2;
        s.push(result);
    }

    private void multiply() {
        int op2 = (int) s.peek();
        s.pop();
        int op1 = (int) s.peek();
        s.pop();
        int result = op1 * op2;
        s.push(result);
    }

    private class UnknownCharacterException extends RuntimeException {
        public UnknownCharacterException(char c) {
            System.out.println("Error while parsing expression: Illegal Character: "+c);
            //printStackTrace();
        }
        public UnknownCharacterException() {
            System.out.println("Error while parsing expression: Illegal Character");
        }
    }

}

