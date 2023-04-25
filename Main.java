import java.util.Scanner;

/**
 * @author Заварзин Олег : wodeno10@gmail.com
 */

public class Main {
    private static boolean arabic, roman;
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите операцию: ");
        String userInput = console.nextLine();
        String[] objectOperation = userInput.split(" ");
        String oneNumeral = objectOperation[0], operand = objectOperation[1], twoNumeral = objectOperation[2];
        testNumerals(objectOperation, oneNumeral, twoNumeral, operand);
    }
    
    public static int arabicCalculated(int oneNumeral, int twoNumeral, String operand){
        int result = 0;
        switch (operand){
            case "+" :
                result = oneNumeral + twoNumeral;
                break;
            case "-" :
                result = oneNumeral - twoNumeral;
                break;
            case "*" :
                result = oneNumeral * twoNumeral;
                break;
            case "/" :
                result =  (int) (oneNumeral / twoNumeral);
                break;
        }
        return result;
    }

    public static int numberOfOperands(String[] userInput){
        String[] arrayOperand = new String[]{"+", "-", "*", "/"};
        int counter = 0;
        for(String x : userInput){
            for(String y : arrayOperand){
                if (x.equals(y)){
                    counter++;
                }
            }
        }
        return  counter;
    }

    public static String romanCalculated(int oneNumeral, int twoNumeral, String operand){
        int result = 0;
        switch (operand){
            case "+" :
                result = oneNumeral + twoNumeral;
                break;
            case "-" :
                result = oneNumeral - twoNumeral;
                break;
            case "*" :
                result = oneNumeral * twoNumeral;
                break;
            case "/" :
                result = (int) (oneNumeral / twoNumeral);
                break;
        }
        if (result <= 0){
            try{
                throw new Exception();
            }catch (Exception exception){
                System.out.println("Нельзя получать в выражениях с римскими цифрами результаты равные нулю или меньше!");
            }
        }
        return arabicToRoman(result);
    }

    public static int romanToArabic(String input){
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder(input);
        while(!stringBuilder.isEmpty()){
            if (stringBuilder.indexOf("IX") >= 0){
                result += 9;
                stringBuilder.delete(0, 2);
            }else if (stringBuilder.indexOf("X") >= 0){
                result += 10;
                stringBuilder.deleteCharAt(0);
            }else if (stringBuilder.indexOf("VIII") >= 0){
                result += 8;
                stringBuilder.delete(0,4);
            }else if (stringBuilder.indexOf("VII") >= 0){
                result += 7;
                stringBuilder.delete(0,3);
            }else if (stringBuilder.indexOf("VI") >= 0){
                result += 6;
                stringBuilder.delete(0,2);
            }else if (stringBuilder.indexOf("V") >= 0){
                result += 5;
                stringBuilder.deleteCharAt(0);
            }else if (stringBuilder.indexOf("IV") >= 0){
                result += 4;
                stringBuilder.delete(0,2);
            }else if (stringBuilder.indexOf("III") >= 0){
                result += 3;
                stringBuilder.delete(0,3);
            }else if (stringBuilder.indexOf("II") >= 0){
                result += 2;
                stringBuilder.delete(0,2);
            }else if (stringBuilder.indexOf("I") >= 0){
                result += 1;
                stringBuilder.deleteCharAt(0);
            }
        }
        return result;
    }

    public static String arabicToRoman(int input){
        String result = "";
        int x = input;
        while (x > 0){
            if (x >= 1000) {
                result += "M";
                x -= 1000;
            }else if (x >= 500){
                result += "D";
                x -= 500;
            }else if (x >= 100){
                result += "C";
                x -= 100;
            }else if (x >= 50){
                result += "L";
                x -= 50;
            }else if (x >= 40) {
                result += "XL";
                x -= 40;
            }else if (x >= 10){
                result += "X";
                x -= 10;
            }else if (x >= 9){
                result += "IX";
                x -= 9;
            }else if (x >= 8){
                result += "VIII";
                x -= 8;
            }else if (x >= 7){
                result += "VII";
                x -= 7;
            }else if (x >= 6){
                result += "VI";
                x -= 6;
            }else if (x >= 5){
                result += "V";
                x -= 5;
            }else if (x >= 4){
                result += "IV";
                x -= 4;
            }else if (x >= 3){
                result += "III";
                x -= 3;
            }else if (x >= 2){
                result += "II";
                x -= 2;
            }else if (x >= 1){
                result += "I";
                x -= 1;
            }
        }
        return result;
    }
    public static void testNumerals(String[] userInput, String oneNumeral, String twoNumeral, String operand){
        String arabicNumerals = "0123456789", romanNumerals = "IIIIIIIVVVIVIIVIIIIXX";
        char[] arrayOne = oneNumeral.toCharArray(), arrayTwo = twoNumeral.toCharArray();
        String[] arrayOperand = new String[]{"+", "-", "*", "/"};
        boolean trueOperand = false;
        for (String ops : arrayOperand){
            if (ops.equals(operand)){
                trueOperand = true;
            }
        }
        for (char chars : arrayOne){
            if (arabicNumerals.indexOf(chars) > 0) {
                arabic = true;
                break;
            }else if (romanNumerals.indexOf(chars) > 0){
                roman = true;
                break;
            }
        }
        for (char chars : arrayTwo) {
            if (arabicNumerals.indexOf(chars) > 0) {
                arabic = true;
                break;
            }else if (romanNumerals.indexOf(chars) > 0){
                roman = true;
                break;
            }
        }
        if (!trueOperand) {
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("Введен несуществующий в данном калькуляторе операнд!");
            }
        }else if (numberOfOperands(userInput) > 1) {
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("Введено больше одного операнда, что недопустимо в выражениях данного калькулятора!");
            }
        }else if (arabic && roman){
            try{
                throw new Exception();
            }catch (Exception exception){
                System.out.println("Нельзя использовать разные наборы знаков системы счисления!");
            }
        }else if (!arabic && !roman) {
            try{
                throw new Exception();
            }catch (Exception exception){
                System.out.println("Введены неподходящие для калькулятора символы!");
            }
        }else if (roman){
            if (romanToArabic(oneNumeral) > 10 || romanToArabic(twoNumeral) > 10){
                try{
                    throw new Exception();
                }catch (Exception exception){
                    System.out.println("Введено число больше 10 которое недопустимо в данной версии моего калькулятора!");
                }
            }else{
                System.out.print(romanCalculated(romanToArabic(oneNumeral), romanToArabic(twoNumeral), operand));
            }
        }else if (!roman){
            if (Integer.parseInt(oneNumeral) > 10 || Integer.parseInt(twoNumeral) > 10){
                try{
                    throw new Exception();
                }catch (Exception exception){
                    System.out.println("Введено число больше 10 которое недопустимо в данной версии моего калькулятора!");
                }
            }else{
                System.out.print(arabicCalculated(Integer.parseInt(oneNumeral), Integer.parseInt(twoNumeral), operand));
            }
        }
    }

}
