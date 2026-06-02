import java.util.*;
import java.util.stream.*;
record Person(String name, int age) {}
public class Records {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob", 17);
        Person p3 = new Person("Charlie", 22);
        System.out.println("Instances:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        List<Person> people = List.of(p1, p2, p3);
        System.out.println("All: " + people);
        List<Person> adults = people.stream()
                                   .filter(p -> p.age() >= 18)
                                   .collect(Collectors.toList());
        System.out.println("Adults (age >= 18): " + adults);
    }
}
