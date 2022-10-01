/*
    Hexadecimal representation
 */

import java.util.Scanner;
import java.math.BigInteger;

public class Hex{
    String value;
    private int bytesNumber;
    boolean checkValue(String sample){
        if (sample.length() < 3) return false;  // 3 is the minimal possible length: 0x0
        if (sample.length() % 2 == 1) return false; // we can examine only full byte sequences
        if (!(sample.charAt(0) == '0' && sample.charAt(1) == 'x')) return false;  // valid predicate assertion
        try{
            // using built-in tool to make sure it contains only hexadecimal symbols
            BigInteger number = new BigInteger(sample.substring(2), 16);

            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }
    public void calculateBytesNumber(){
        bytesNumber = (value.length()+1) / 2; // each hexadecimal represents 4 digit (half-byte) value
        // + 1 to achieve correctness for odd
    }
    Hex(Scanner scanner) {
        do {
            System.out.println("Enter hexadecimal value:");
            value = scanner.nextLine();
            if (!checkValue(value)) System.out.println("Not a valid input!");
            else {
                value = value.substring(2); // to not store predicate
                value = value.toUpperCase(); // to make hexadecimal translation easier
                break;
            }
        }while(true);
        calculateBytesNumber();
    }
    Hex(){
        value = "";
        bytesNumber = 0;
    }
    @Override
    public String toString(){
        return String.format("Number 0x%s takes %d %s to be stored", value, bytesNumber, (bytesNumber > 1) ? "bytes" : "byte");
    }
}
