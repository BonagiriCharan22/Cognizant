import java.util.*;
import java.io.*;
public class TypeCastingExample {
    public static void main(String[] args) {
        int num1 = 100;
        double num2 = num1; 
        System.out.println("Implicit Type Casting (Widening):");
        System.out.println("Integer value: " + num1);
        System.out.println("Double value: " + num2);
        double num3 = 9.99;
        int num4 = (int) num3;
        System.out.println("\nExplicit Type Casting (Narrowing):");
        System.out.println("Double value: " + num3);
        System.out.println("Integer value: " + num4);
    }
}
