public class ClassandObjectCreation {
    static class Car {
        String make;
        String model;
        int year;
        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }
        void displayInfo() {
            System.out.println("Car Make: " + make);
            System.out.println("Car Model: " + model);
            System.out.println("Car Year: " + year);
        }
    }
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Camry", 2020);
        myCar.displayInfo();
    }

}
