import java.util.*;
public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>(Arrays.asList("banana", "Apple", "cherry", "date", "Elderberry"));
        System.out.println("Before sorting: " + items);
        Collections.sort(items, (s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("After sorting: " + items);
    }
}
