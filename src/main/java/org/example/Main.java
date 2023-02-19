package org.example;

import java.util.Scanner;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(calc(line));
    }

    public static String calc(String input) {
        Parser parser = new Parser();
        parser.parse(input);

        return parser.getResult();
    }

    private static class Parser {
        int a;
        int b;
        boolean aIsRome;
        boolean bIsRome;
        char operation;
        int result;

        void parse(String input) {
            String[] args = input.split(" ");
            if (args.length != 3) {
                throw new IllegalArgumentException("Is not a math operation");
            }
            parseOperation(args[1]);
            parseOperands(args[0], args[2]);

            return true;
        }

        private void parseOperands(String arg1, String arg2) {
            try {
                a = Integer.parseInt(arg1);
                if (a <= 0 || a > 10) {
                    throw new IllegalArgumentException("numbers must be in the range 1..10");
                }
                aIsRome = false;
            } catch (NumberFormatException e) {
                a = romeToInt(arg1);
                aIsRome = true;
            }
            try {
                b = Integer.parseInt(arg2);
                if (b <= 0 || b > 10) {
                    throw new IllegalArgumentException("numbers must be in the range 1..10");
                }
                bIsRome = false;
            } catch (NumberFormatException e) {
                b = romeToInt(arg2);
                bIsRome = true;
            }
        }

        private void parseOperation(String operation) {
            switch (operation) {
                case "+" -> this.operation = '+';
                case "-" -> this.operation = '-';
                case "*" -> this.operation = '*';
                case "/" -> this.operation = '/';
                default -> throw new UnsupportedOperationException("Incorrect operation " + operation);
            }
        }

        public String getResult() {
            if (aIsRome != bIsRome) {
                throw new IllegalArgumentException("Only Roman or Arabic numerals at the same time");
            }
            if (!aIsRome && !bIsRome) {
                switch (operation) {
                    case '+':
                        return a + b + "";
                    case '-':
                        return a - b + "";
                    case '/':
                        return a / b + "";
                    case '*':
                        return a * b + "";
                }
            }
            if (aIsRome && bIsRome) {

                switch (operation) {
                    case '+':
                        return intToRome(a + b);
                    case '-':
                        return intToRome(a - b);
                    case '/':
                        return intToRome(a / b);
                    case '*':
                        return intToRome(a * b);
                }
            }
            return "";
        }

        private int romeToInt(String string) {
            //Некрасиво, но из-за условий задачи не хочется заморачиваться
            return switch (string) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new IllegalArgumentException("Is not rome " + string);
            };
        }

        public String intToRome(int number) {
            if (number <= 0) {
                throw new ArithmeticException("Rome can't be negative or 0");
            }
            return join("", nCopies(number, "I")).replace("IIIII", "V").replace("IIII", "IV").replace("VV", "X").replace("VIV", "IX").replace("XXXXX", "L").replace("XXXX", "XL").replace("LL", "C").replace("LXL", "XC").replace("CCCCC", "D").replace("CCCC", "CD").replace("DD", "M").replace("DCD", "CM");
        }

    }

}