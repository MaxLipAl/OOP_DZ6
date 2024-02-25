package calculator;

import java.util.Scanner;
import converter.Converter;
import operation.Operation;

public class Calculator {
    private final Operation addition;
    private final Operation subtraction;
    private final Operation multiplication;
    private final Operation division;
    private final Converter binaryConverter;

    public Calculator(Operation addition, Operation subtraction, Operation multiplication, Operation division, Converter binaryConverter) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;
        this.binaryConverter = binaryConverter;
    }

    // Методы для выполнения операций
    public double add(double num1, double num2) {
        return roundToTwoDecimalPlaces(addition.calculate(num1, num2));
    }

    public double subtract(double num1, double num2) {
        return roundToTwoDecimalPlaces(subtraction.calculate(num1, num2));
    }

    public double multiply(double num1, double num2) {
        return roundToTwoDecimalPlaces(multiplication.calculate(num1, num2));
    }

    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно");
        }
        return roundToTwoDecimalPlaces(division.calculate(num1, num2));
    }

    // Методы для работы с бинарными числами
    public String convertToBinary(int number) {
        return binaryConverter.convertToBinary(number);
    }

    public int convertToDecimal(String binary) {
        return binaryConverter.convertToDecimal(binary);
    }

    // Метод для отображения меню
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            // Вывод меню
            System.out.println("Калькулятор:");
            System.out.println("1. Сложение");
            System.out.println("2. Вычитание");
            System.out.println("3. Умножение");
            System.out.println("4. Деление");
            System.out.println("5. Перевод числа из десятичной в двоичную");
            System.out.println("6. Перевод числа из двоичной в десятичную");
            System.out.println("7. Выход");

            int choice = readIntInput(scanner);

            switch (choice) {
                case 1:
                    performOperation("сложение", addition, scanner);
                    break;
                case 2:
                    performOperation("вычитание", subtraction, scanner);
                    break;
                case 3:
                    performOperation("умножение", multiplication, scanner);
                    break;
                case 4:
                    performOperation("деление", division, scanner);
                    break;
                case 5:
                    convertDecimalToBinary(scanner);
                    break;
                case 6:
                    convertBinaryToDecimal(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    // Метод для выполнения операции
    private void performOperation(String operationName, Operation operation, Scanner scanner) {
        System.out.print("Введите первое число: ");
        double num1 = readDoubleInput(scanner);

        System.out.print("Введите второе число: ");
        double num2 = readDoubleInput(scanner);

        double result = operation.calculate(num1, num2);
        System.out.println("Результат " + operationName + ": " + roundToTwoDecimalPlaces(result));
    }

    // Метод для перевода числа из десятичной в двоичную систему
    private void convertDecimalToBinary(Scanner scanner) {
        System.out.print("Введите число для перевода из десятичной в двоичную: ");
        int decimalNumber = readIntInput(scanner);
        String binaryRepresentation = convertToBinary(decimalNumber);
        System.out.println("Бинарное представление числа " + decimalNumber + ": " + binaryRepresentation);
    }

    // Метод для перевода числа из двоичной в десятичную систему
    private void convertBinaryToDecimal(Scanner scanner) {
        System.out.print("Введите число для перевода из двоичной в десятичную: ");
        String binaryNumber = scanner.next();
        int decimalValue = convertToDecimal(binaryNumber);
        System.out.println("Десятичное представление числа " + binaryNumber + ": " + decimalValue);
    }

    // Вспомогательные методы для чтения ввода
    private int readIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double readDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    // Метод для округления до двух знаков после запятой
    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
