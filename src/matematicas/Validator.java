package matematicas;

public class Validator {

    /*private static final int C0 = 48;
    private static final int C9 = 57;
    private static final int CA = 65;
    private static final int CZ = 90;
    private static final int Ca = 97;
    private static final int Cz = 122;*/

    public static final String[] functions = {
            "sqrt", "sqrt3", "exp", "exp10",
            "log", "log10",
            "sin", "cos", "tan", "sec", "csc", "cot",
            "asin", "acos", "atan"};

    public static boolean validateParentheses(String input) {
        int count = 0;
        for(int i = 0; i < input.length(); i++) {
            char charI = input.charAt(i);
            if(charI == '(')
                count++;
            if(charI == ')')
                count--;
            if(count < 0)
                return false;
        }
        return count == 0 ? true : false;
    }

    public static boolean validateDouble(String input2) {
        if(input2.length() == 0)
            return false;

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        if(input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')')
            input = input.substring(1, input.length() - 1);

        boolean output = true;
        try {
            Double.parseDouble(input);
        } catch(NumberFormatException e) {
            output = false;
        }
        return output;
    }

    public static boolean validatePrimitive(String input2) {
        if(input2.length() == 0)
            return false;

        String input = "";
        for(int i = 0; i < input2.length(); i++) {
            if(input2.charAt(i) != ' ')
                input += input2.charAt(i);
        }

        boolean isNumber = validateDouble(input);

        if(isNumber)
            return true;

        if(input.charAt(input.length() -1 ) != ')')
            return false;

        int indexParentheses = input.indexOf('(');

        if(indexParentheses == -1)
            return false;

        String function = input.substring(0, indexParentheses);
        boolean functionExist = false;
        for(String function2: functions)
            if(function.equalsIgnoreCase(function2))
                functionExist = true;

        if(!functionExist)
            return false;

        String argument = input.substring(indexParentheses);
        if(!validateDouble(argument))
            return false;

        return true;
    }

    public static boolean validatePowers(String input) {
        if(input.length() == 0)
            return false;

        int posOperand = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ' ')
                continue;

            if(input.charAt(i) == '^') {
                if(i == 0 || input.charAt(i - 1) == '^' || i == input.length() - 1)
                    return false;
                //Verify if the last number is valid
                if(!validatePrimitive(input.substring(posOperand, i)))
                    return false;
                posOperand = i + 1;
            }

            if(i == input.length() - 1) {
                if(!validatePrimitive(input.substring(posOperand)))
                    return false;
            }
        }

        return true;
    }


    //Method tested
    public static boolean validateProducts(String input) {
        if(input.length() == 0)
            return false;

        int posOperand = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ' ')
                continue;

            if((input.charAt(i) == '*' || input.charAt(i) == '/')) {
                if(i == 0 || i == input.length() - 1 || input.charAt(i - 1) == '*'
                || input.charAt(i - 1) == '/')
                    return false;

                if(!validatePowers(input.substring(posOperand, i)))
                    return false;

                posOperand = i + 1;
            }
        }

        if(!validatePowers(input.substring(posOperand)))
            return false;

        return true;
    }

    public static boolean validateSums(String input) {
        if(input.length() == 0)
            return false;

        int posOperand = 0;
        if(input.charAt(0) == '+' || input.charAt(0) == '-')
            posOperand = 1;

        for(int i = posOperand; i < input.length(); i++) {
            if(input.charAt(i) == ' ')
                continue;

            if(input.charAt(i) == '+' || input.charAt(i) == '-') {
                if(i == input.length() - 1 ||
                        input.charAt(i - 1) == '+' || input.charAt(i - 1) == '-')
                    return false;
                if(input.charAt(i - 1) != '(' && input.charAt(i - 1) != 'e' &&
                input.charAt(i - 1) != 'E') {
                    if(!validateProducts(input.substring(posOperand, i)))
                        return false;
                    posOperand = i + 1;
                }

            }
        }

        if(!validateProducts(input.substring(posOperand)))
            return false;

        return true;
    }

    public static boolean validateExpression(String input) {
        if(input.length() == 0)
            return false;

        int sumParentheses = 0;
        int posParentheses = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ' ')
                continue;

            if(input.charAt(i) == '(') {
                if(sumParentheses == 0)
                    posParentheses = i;
                sumParentheses++;
            }

            if(input.charAt(i) == ')') {
                sumParentheses--;

                if (sumParentheses < 0)
                    return false;

                if(sumParentheses == 0) {
                    if(!validateExpression(input.substring(posParentheses + 1, i)))
                        return false;
                    input = input.substring(0, posParentheses + 1) + "0" + input.substring(i);
                    i = posParentheses + 2;
                }

            }
        }

        return validateSums(input);
    }

}
