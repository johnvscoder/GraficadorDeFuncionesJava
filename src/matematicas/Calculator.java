package matematicas;

public class Calculator {

    public static boolean invalidDouble(double number) {
        return Double.isNaN(number) || number == Double.POSITIVE_INFINITY || number == Double.NEGATIVE_INFINITY;
    }

    public static double calculateDouble(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        if(input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')')
            input = input.substring(1, input.length() - 1);

        //boolean output = true;
        try {
            double number = Double.parseDouble(input);
            if(invalidDouble(number))
                throw new RuntimeException("Error. Invalid argument.");
            return number;
        } catch(NumberFormatException e) {
            throw new RuntimeException("Error. Invalid expression.");
        }
    }

    public static double calculatePrimitive(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        if(Validator.validateDouble(input))
            return calculateDouble(input);

        if(input.charAt(input.length() - 1) != ')')
            throw new RuntimeException("Error. Invalid argument.");

        int indexParentheses = input.indexOf('(');

        if(indexParentheses == -1)
            throw new RuntimeException("Error. Invalid argument.");

        String function = input.substring(0, indexParentheses);
        boolean functionExist = false;
        for(String function2: Validator.functions)
            if(function.equalsIgnoreCase(function2))
                functionExist = true;

        if(!functionExist)
            throw new RuntimeException("Error. Invalid Argument.");

        double argument = calculateDouble(input.substring(indexParentheses));
        double number = 0.0;
        function = function.toLowerCase();
        switch(function) {
            case "sqrt":
                number = Math.sqrt(argument);
                break;
            case "sqrt3":
                number = Math.pow(Math.abs(argument), 1.0 / 3);
                if(argument < 0.0)
                    number *= -1.0;
                break;
            case "exp":
                number = Math.exp(argument);
                break;
            case "exp10":
                number = Math.pow(10.0, argument);
                break;
            case "log":
                number = Math.log(argument);
                break;
            case "log10":
                number = Math.log10(argument);
                break;
            case "sin":
                number = Math.sin(argument);
                break;
            case "cos":
                number = Math.cos(argument);
                break;
            case "tan":
                number = Math.tan(argument);
                break;
            case "sec":
                number = 1.0 / Math.cos(argument);
                break;
            case "csc":
                number = 1.0 / Math.sin(argument);
                break;
            case "cot":
                number = 1.0 / Math.tan(argument);
                break;
            case "asin":
                number = Math.asin(argument);
                break;
            case "acos":
                number = Math.acos(argument);
                break;
            case "atan":
                number = Math.atan(argument);
                break;
        }
        if(invalidDouble(number))
            throw new RuntimeException("Error. Invalid argument.");
        return number;
    }

    public static double calculatePowers(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        int posOperand = 0;
        boolean started = false;
        double number = 0.0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '^') {
                if(i == 0 || input.charAt(i - 1) == '^' || i == input.length() - 1)
                    throw new RuntimeException("Error. Invalid expression.");

                double operand = calculatePrimitive(input.substring(posOperand, i));
                if(invalidDouble(operand))
                    throw new RuntimeException("Error. Invalid operand.");
                if(!started) {
                    number = operand;
                    started = true;
                }
                else
                    number = Math.pow(number, operand);

                if(invalidDouble(number))
                    throw new RuntimeException("Error. Invalid result");
                posOperand = i + 1;
            }
        }

        double operand = calculatePrimitive(input.substring(posOperand));
        if(invalidDouble(operand))
            throw new RuntimeException("Error. Invalid result.");
        if(!started)
            number = operand;
        else
            number = Math.pow(number, operand);

        if(invalidDouble(number))
            throw new RuntimeException("Error. Invalid result.");
        return number;
    }

    public static double calculateProducts(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        int posOperand = 0;
        double number = 1.0;
        char prevOperator = '*';
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '*' || input.charAt(i) == '/') {
                if(i == 0 || i == input.length() - 1 || input.charAt(i - 1) == '*'
                        || input.charAt(i - 1) == '/')
                    throw new RuntimeException("Error. Invalid expression.");

                double operand = calculatePowers(input.substring(posOperand, i));
                if(prevOperator == '*')
                    number *= operand;
                else
                    number /= operand;

                if(invalidDouble(number))
                    throw new RuntimeException("Error. Invalid result.");

                prevOperator = input.charAt(i);

                posOperand = i + 1;
            }
        }

        double operand = calculatePowers(input.substring(posOperand));
        if(prevOperator == '*')
            number *= operand;
        else
            number /= operand;

        if(invalidDouble(number))
            throw new RuntimeException("Error. Invalid number.");

        return number;
    }

    public static double calculateSums(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        int posOperand = 0;
        char prevOperator = '+';
        if(input.charAt(0) == '+' || input.charAt(0) == '-') {
            posOperand = 1;
            if(input.charAt(0) == '-')
                prevOperator = '-';
        }

        double sum = 0.0;
        for(int i = posOperand; i < input.length(); i++) {
            if(input.charAt(i) == '+' || input.charAt(i) == '-') {
                if(i == input.length() - 1 ||
                        input.charAt(i - 1) == '+' || input.charAt(i - 1) == '-')
                    throw new RuntimeException("Error. Invalid expression.");
                if(input.charAt(i - 1) != '(' && input.charAt(i - 1) != 'e' &&
                        input.charAt(i - 1) != 'E') {
                    double operand = calculateProducts(input.substring(posOperand, i));
                    if(prevOperator == '+')
                        sum += operand;
                    else
                        sum -= operand;
                    if(invalidDouble(sum))
                        throw new RuntimeException("Error. Invalid result.");
                    prevOperator = input.charAt(i);
                    posOperand = i + 1;
                }
            }
        }

        double operand = calculateProducts(input.substring(posOperand));
        if(prevOperator == '+')
            sum += operand;
        else
            sum -= operand;
        if(invalidDouble(sum))
            throw new RuntimeException("Error. Invalid result.");
       return sum;
    }

    public static double calculateExpression(String input2) {
        if(input2.length() == 0)
            throw new RuntimeException("Error. Empty string.");

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        int sumParentheses = 0;
        int posParentheses = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '(') {
                if(sumParentheses == 0)
                    posParentheses = i;
                sumParentheses++;
            }

            if(input.charAt(i) == ')') {
                sumParentheses--;

                if (sumParentheses < 0)
                    throw new RuntimeException("Error. Invalid expression.");

                if(sumParentheses == 0) {
                    double number = calculateExpression(input.substring(posParentheses + 1, i));
                    int posEndParentheses =  input.substring(0, posParentheses + 1).length() + Double.toString(number).length();
                    input = input.substring(0, posParentheses + 1) + Double.toString(number) + input.substring(i);
                    i = posEndParentheses;
                }
            }
        }

        return calculateSums(input);
    }

    public static double f(String funcion, double x) {
        String xString = Double.toString(x);
        for(int i = 0; i < funcion.length(); i++)
            if(funcion.charAt(i) == 'x' &&
             (i == funcion.length() - 1 || funcion.charAt(i + 1) != 'p'))
                funcion = funcion.substring(0, i) + '(' + xString + ')'
                    + (i != funcion.length() - 1 ? funcion.substring(i + 1) : "");
        return calculateExpression(funcion);
    }
}
