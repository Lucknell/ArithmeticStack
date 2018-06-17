import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Scanner;

class Arithmetic {

    Stack stk;
    String expression, postfix;
    int length;

    Arithmetic(String s) {
        expression = s;
        postfix = "";
        length = expression.length();
        stk = new Stack();

    }

    // Validate the expression - make sure parentheses are balanced
    boolean isBalance() {
        int index = 0;
        boolean fail = false;
                //char letter;

        try {
            while (index < length && !fail) {
                char ch = expression.charAt(index);

                switch (ch) {
                    case Constants.LEFT_NORMAL:
                    case Constants.LEFT_BRACE:
                    case Constants.LEFT_BRACKET:
                        stk.push(ch);
                        break;

                    case Constants.RIGHT_NORMAL:
                       if (ch != ')') {
                            fail = true;
                        }
                    case Constants.RIGHT_BRACE:
                    case Constants.RIGHT_BRACKET:
                        Character x = (Character) stk.pop();
                        char letter = x;
                        System.out.println(ch + "  " + letter);
                        //if (ch != letter) {
                        //    fail = true;
                        //}
                        break;

                    default:
                        break;
                }
                index++;
            }
        } catch (EmptyStackException e) {
            System.out.println(expression + "  " + e.toString());
            fail = true;
        }
        return stk.empty() && !fail;
    }

    // Convert expression to postfix notation
    void postfixExpression() {

        stk.clear();
        Scanner scan = new Scanner(expression);
        char current;

        while (scan.hasNext()) {
            String token = scan.next();

            if (isNumber(token)) {
                postfix = postfix + token + " ";
            } else {
                current = token.charAt(0);

                if (isParentheses(current)) {
                    if (stk.empty() || current == Constants.LEFT_NORMAL) {

                        stk.push(current);
                    } else if (current == Constants.RIGHT_NORMAL) {
                        try {

                            Character ch = (Character) stk.pop();
                            char top = ch;

                            while (top != Constants.LEFT_NORMAL) {
                                postfix = postfix + top + " ";
                                ch = (Character) stk.pop();

                                top = top = ch;
                            }
                        } catch (EmptyStackException e) {

                        }
                    }
                } else if (isOperator(current)) {
                    if (stk.empty()) {
                        stk.push(current);
                    } else {
                        try {
                            char top = (Character) stk.peek();
                            boolean higher = hasHigherPrecedence(top, current);

                            while (top != Constants.LEFT_NORMAL && higher) {
                                postfix = postfix + stk.pop() + " ";
                                top = (Character) stk.peek();
                            }
                            stk.push(current);
                        } catch (EmptyStackException e) {
                            stk.push(current);
                        }
                    }
                }
            }
        }

        try {
            while (!stk.empty()) {
                postfix = postfix + stk.pop() + " ";
            }
        } catch (EmptyStackException e) {

        }
    }

    boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isParentheses(char current) {
        boolean parenthesis;

        switch (current) {
            case Constants.LEFT_NORMAL:
            case Constants.RIGHT_NORMAL:
                parenthesis = true;
                break;
            default:
                parenthesis = false;
                break;
        }
        return parenthesis;
    }

    boolean isOperator(char ch) {
        boolean operator = false;

        switch (ch) {
            case Constants.PLUS:
            case Constants.MINUS:
            case Constants.MULTIPLY:
            case Constants.DIVIDE:
                operator = true;
                break;
            default:
                break;
        }
        return operator;
    }

    boolean hasHigherPrecedence(char top, char current) {
        int topmost = -1;
        int currentValue = -1;

        switch (top) {
            case Constants.PLUS:
            case Constants.MINUS:
                topmost = 0;
                break;

            case Constants.MULTIPLY:
            case Constants.DIVIDE:
                topmost = 1;
                break;
            default:
                break;
        }

        switch (current) {
            case Constants.PLUS:
            case Constants.MINUS:
                currentValue = 0;
                break;

            case Constants.MULTIPLY:
            case Constants.DIVIDE:
                currentValue = 1;
                break;
            default:
                break;
        }
        return topmost >= currentValue;
    }

    String getPostfix() {
        return postfix;
    }

    void evaluateRPN() {

        Scanner scan = new Scanner(getPostfix());
        stk.clear(); 
        while (scan.hasNext()) {
            String s = scan.next();
            int x3;

            if (isNumber(s)) {
                stk.push(Integer.parseInt(s));
            } else {
                try {
                    Integer i = (Integer) stk.pop();
                    int x1 = i;

                    int x2 = (Integer) stk.pop();

                    char x = s.charAt(0);
                      int value;

                    switch (x) {

                        case Constants.PLUS:
                            x3 = x2 + x1;
                            value = (x3);
                            stk.push(value);
                            break;
                        case Constants.MULTIPLY:
                            x3 = x2 * x1;
                            value = (x3);
                            stk.push(value);
                            break;
                        case Constants.DIVIDE:
                            x3 = x2 / x1;
                            value = (x3);
                            stk.push(value);
                            break;
                        case Constants.MINUS:
                            x3 = x2 - x1;
                            value = (x3);
                            stk.push(value);
                            break;
                        default:

                            break;
                    }
                } catch (EmptyStackException e) {

                }
            }
        }//while
    }

    int getSizeOfStack() {
        return stk.size();
    }

    int getResult() {
        try {
            return (Integer) stk.pop();
        } catch (EmptyStackException e) {
            System.out.println(e.toString() + "\nResult might be unreliable");
        }
        return 0;
    }
}
