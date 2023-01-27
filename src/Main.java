import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));

    }

    public static String calc(String input) {
        String answer1 = "";

        int j = 0;
        String[] splitByRegex = input.split("([+]|-|[*]|/|^)");
        String[] splitBySpace = input.split(" ");
        if (input.length() < 2)
            throw new IllegalArgumentException("String is not a mathematical operation"); //строка не является математической операцией
        if (splitBySpace.length > 3 || splitByRegex.length == 1)
            throw new IllegalArgumentException("Wrong format - two operands and one operator (+, -, /, *)"); //формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)
        String operation = validatedOperation(splitBySpace);
        if ((validRome(splitByRegex[j]) && validRome(splitByRegex[j + 1]) || (validArabian(splitByRegex[j]) && validArabian(splitByRegex[j + 1])))) {
            if (validArabian(splitByRegex[j])) {
                Map<String, Integer> arabMap = createArabMap();
                if (operation.equals("+")) {
                    try {
                        Integer integer = arabMap.get(splitByRegex[j].trim());
                        Integer integer1 = arabMap.get(splitByRegex[j + 1].trim());
                        int answer = integer + integer1;
                        answer1 += Integer.toString(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }

                } else if (operation.equals("-")) {
                    try {
                        Integer integer = arabMap.get(splitByRegex[j].trim());
                        Integer integer1 = arabMap.get(splitByRegex[j + 1].trim());
                        int answer = integer - integer1;
                        answer1 += Integer.toString(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                } else if (operation.equals("*")) {
                    try {
                        Integer integer = arabMap.get(splitByRegex[j].trim());
                        Integer integer1 = arabMap.get(splitByRegex[j + 1].trim());
                        int answer = integer * integer1;
                        answer1 += Integer.toString(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                } else if (operation.equals("/")) {
                    try {
                        Integer integer = arabMap.get(splitByRegex[j].trim());
                        Integer integer1 = arabMap.get(splitByRegex[j + 1].trim());
                        int answer = integer / integer1;
                        answer1 += Integer.toString(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                }
            } else if (validRome(splitByRegex[j])) {
                Map<String, Integer> romeMap = createRomeMap();
                if (operation.equals("+")) {
                    try {
                        Integer integer = romeMap.get(splitByRegex[j].trim());
                        Integer integer1 = romeMap.get(splitByRegex[j + 1].trim());
                        int answer = integer + integer1;
                        answer1 += convertArabToRoman(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                } else if (operation.equals("*")) {
                    try {
                        Integer integer = romeMap.get(splitByRegex[j].trim());
                        Integer integer1 = romeMap.get(splitByRegex[j + 1].trim());
                        int answer = integer * integer1;
                        answer1 += convertArabToRoman(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                } else if (operation.equals("/")) {
                    try {
                        Integer integer = romeMap.get(splitByRegex[j].trim());
                        Integer integer1 = romeMap.get(splitByRegex[j + 1].trim());
                        int answer = integer / integer1;
                        answer1 += convertArabToRoman(answer);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                } else if (operation.equals("-")) {
                    Integer integer;
                    Integer integer1;
                    try {
                        integer = romeMap.get(splitByRegex[j].trim());
                        integer1 = romeMap.get(splitByRegex[j + 1].trim());
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("wrong numbers");
                    }
                    if (integer > integer1) {
                        int answer = integer - integer1;
                        answer1 += convertArabToRoman(answer);
                    } else
                        throw new IllegalArgumentException("there are no negative numbers in the Roman system");//в римской системе нет отрицательных чисел
                }
            }
        } else
            throw new IllegalArgumentException("different number systems are used simultaneously");//используются одновременно разные системы счисления

        return answer1;
    }

    public static boolean validRome(String str) {
        str = str.trim();
        return str.contains("I") || str.contains("II") || str.contains("III")
                || str.contains("IV") || str.contains("V") || str.contains("VI")
                || str.contains("VII") || str.contains("VIII") || str.contains("IX") || str.contains("X");
    }

    public static boolean validArabian(String str) {
        str = str.trim();
        return str.contains("1") || str.contains("2") || str.contains("3")
                || str.contains("4") || str.contains("5") || str.contains("6")
                || str.contains("7") || str.contains("8") || str.contains("9") || str.contains("10");
    }

    public static String validatedOperation(String[] splitBySpace) {
        StringBuilder someStr = new StringBuilder();
        for (String s : splitBySpace) {
            if (s.contains("+") || s.contains("-") || s.contains("*") || s.contains("/")) {
                someStr.append(s);
            }
        }
        return someStr.toString();
    }

    public static Map<String, Integer> createRomeMap() {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("I", 1);
        myMap.put("II", 2);
        myMap.put("III", 3);
        myMap.put("IV", 4);
        myMap.put("V", 5);
        myMap.put("VI", 6);
        myMap.put("VII", 7);
        myMap.put("VIII", 8);
        myMap.put("IX", 9);
        myMap.put("X", 10);
        return myMap;
    }

    public static Map<String, Integer> createArabMap() {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("1", 1);
        myMap.put("2", 2);
        myMap.put("3", 3);
        myMap.put("4", 4);
        myMap.put("5", 5);
        myMap.put("6", 6);
        myMap.put("7", 7);
        myMap.put("8", 8);
        myMap.put("9", 9);
        myMap.put("10", 10);
        return myMap;
    }

    public static String convertArabToRoman(int number) {
        List<RomanNumbers> romanNumerals = RomanNumbers.getValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumbers currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}


