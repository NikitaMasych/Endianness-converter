/*
    Big and little endian representation
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Representation {
    BigInteger value;
    private String type; // contains type of endian

    Representation(String type, Scanner scanner){
        while(true){
            try{
                System.out.println("Enter " + type + " endian represented value:");
                value = new BigInteger(scanner.nextLine());
                break;
            }
            catch(Exception exp){
                System.out.println("Not a valid input!");
            }
        };
        this.type = type;
    }
    Representation(String type){
        this.type = type;
        value = BigInteger.valueOf(0);
    }
    @Override
    public String toString(){

        // to get first letter capitalized:
        String typeCap = Character.toString(type.charAt(0)).toUpperCase() + type.substring(1);

        return String.format("%s endian representation:\n%d", typeCap, value);
    }
}
