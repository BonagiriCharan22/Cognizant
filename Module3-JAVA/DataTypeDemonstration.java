import java.util.*;
public class DataTypeDemonstration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int intValue = sc.nextInt();
        System.out.println("Enter a float:");
        float floatValue = sc.nextFloat();
        System.out.println("Enter a double:");
        double doubleValue = sc.nextDouble();
        System.out.println("Enter a string:");
        String stringValue = sc.next();
        System.out.println("Enter a character:");
        char charValue = sc.next().charAt(0);
        System.out.println("Enter a boolean:");
        boolean booleanValue = sc.nextBoolean();
        System.out.println("Integer: " + intValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("String: " + stringValue);
        System.out.println("Character: " + charValue);
        System.out.println("Boolean: " + booleanValue);
    }
}
