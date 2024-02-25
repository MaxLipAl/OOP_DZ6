package converter;

public class BinaryConverter implements Converter {
    @Override
    public String convertToBinary(int number) {
        return Integer.toBinaryString(number);
    }

    @Override
    public int convertToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }
}
