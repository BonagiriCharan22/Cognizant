public class InheritanceExample {
    static class Animal {
        void makesound() {
            System.out.println("This animal makes a sound.");
        }
    }
    static class Dog extends Animal {
        void makesound() {
            System.out.println("The dog barks.");
        }
    }
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makesound();
        dog.makesound();
    }

}
