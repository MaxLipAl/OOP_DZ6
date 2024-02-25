import operation.Operation;
import calculator.Calculator;
import operation.Addition;
import operation.Subtraction;
import operation.Multiplication;
import operation.Division;
import converter.BinaryConverter;
import converter.Converter;

public class Main {
    public static void main(String[] args) {
        // Создание объектов операций
        Operation addition = new Addition();
        Operation subtraction = new Subtraction();
        Operation multiplication = new Multiplication();
        Operation division = new Division();

        // Создание объекта конвертера
        Converter binaryConverter = new BinaryConverter();

        // Создание объекта калькулятора
        Calculator calculator = new Calculator(addition, subtraction, multiplication, division, binaryConverter);

        // Отображение меню
        calculator.showMenu();
    }
}
