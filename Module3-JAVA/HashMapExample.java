import java.util.HashMap;
import java.util.*;
public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key-value pairs (type 'exit' to stop):");
        while (true) {
            System.out.print("Key: ");
            String key = sc.nextLine();
            if (key.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("Value: ");
            int value = sc.nextInt();
            sc.nextLine(); 
            map.put(key, value);
        }
        System.out.println("HashMap contents:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
     sc.close();
    }
}
