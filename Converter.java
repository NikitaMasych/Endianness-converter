import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Converter {
    private static final Map<Character, Integer> TRANSLATOR = new HashMap<Character, Integer>(){{
        put('0', 0);  put('1', 1);  put('2', 2);  put('3', 3);
        put('4', 4);  put('5', 5);  put('6', 6);  put('7', 7);
        put('8', 8);  put('9', 9);  put('A', 10); put('B', 11);
        put('C', 12); put('D', 13); put('E', 14); put('F', 15);
    }};

    public Representation HexToLittleEndian(Hex sample){
        Representation result = new Representation("little");

        // byte consists of two hex values, so we'll divide on pairs,
        // because we are swapping order:
        for (int i = 0; i < sample.value.length(); i += 2){

            BigInteger hex1 = BigInteger.valueOf(TRANSLATOR.get(sample.value.charAt(i)));
            BigInteger baseWeight1 = BigInteger.valueOf(16).pow(i+1);

            BigInteger hex2 = BigInteger.valueOf(TRANSLATOR.get(sample.value.charAt(i+1)));
            BigInteger baseWeight2 = BigInteger.valueOf(16).pow(i);

            BigInteger term1 = hex1.multiply(baseWeight1);
            BigInteger term2 = hex2.multiply(baseWeight2);

            BigInteger hexPair = term1.add(term2); // full byte

            result.value = result.value.add(hexPair);
        }

        return result;
    }
    public Representation HexToBigEndian(Hex sample){
        Representation result = new Representation("big");

        for (int i = 0; i != sample.value.length(); ++i){
            BigInteger term1 = BigInteger.valueOf(TRANSLATOR.get(sample.value.charAt(i)));
            BigInteger term2 = BigInteger.valueOf(16).pow(sample.value.length() - 1 - i);
            result.value = result.value.add(term1.multiply(term2));
        }

        return result;
    }
    public Hex LittleEndianToHex(Representation sample){
        Hex result = new Hex();

        do{
            int remainder = sample.value.mod(BigInteger.valueOf(16)).intValue();
            sample.value = sample.value.divide(BigInteger.valueOf(16));
            String digit;
            digit = (remainder <= 9 ) ? Character.toString((char)(48 + remainder)) // 48 - ascii 0 code
                                      : Character.toString((char)(55 + remainder)); // minimum remainder = 10, ergo 55 + 10 = 65 - ascii A code
            result.value += digit;
        }while(!Objects.equals(sample.value, BigInteger.ZERO));
        result.calculateBytesNumber();

        return result;
    }
    public Hex BigEndianToHex(Representation sample){

        // Due to definitions' reversive mechanism:

        Hex result = LittleEndianToHex(sample);

        result.value = new StringBuilder(result.value).reverse().toString();

        return result;
    }
}
