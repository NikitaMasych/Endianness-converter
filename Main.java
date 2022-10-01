import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        Hex hex = new Hex(scanner);

        Representation BEForm =  converter.HexToBigEndian(hex);
        Representation LEForm =  converter.HexToLittleEndian(hex);

        System.out.println(BEForm);
        System.out.println(LEForm);

        Representation littleEndian = new Representation("little", scanner);
        Hex hexForm1 = converter.LittleEndianToHex(littleEndian);
        System.out.println(hexForm1);

        Representation bigEndian = new Representation("big", scanner);
        Hex hexForm2 = converter.BigEndianToHex(bigEndian);
        System.out.println(hexForm2);
    }
}


