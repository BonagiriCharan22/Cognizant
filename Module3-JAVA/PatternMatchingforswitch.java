import java.util.*;
public class PatternMatchingforswitch {
    public static void main(String[] args) {
        describe(null);
        describe(123);
        describe("hello");
        describe(3.14);
        describe(List.of(1, 2, 3));
        describe(true);
    }
    static void describe(Object o) {
        String msg = switch (o) {
            case null -> "Received: null";
            case Integer i -> "Integer with value: " + i;
            case String s -> "String with length " + s.length() + ": \"" + s + "\"";
            case Double d -> "Double with value: " + d;
            case List<?> list -> "List with size " + list.size() + ": " + list;
            case Boolean b -> "Boolean: " + b;
            default -> "Unknown type: " + o.getClass().getName();
        };
        System.out.println(msg);
    }
}
