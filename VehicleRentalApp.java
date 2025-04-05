import java.util.Scanner;
import java.time.LocalDate;

public class VehicleRentalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalSystem rentalSystem = RentalSystem.getInstance();

        while (true) {
            System.out.println("\n=== Vehicle Rental System ===");
            System.out.println("1: Add Vehicle");
            System.out.println("2: Add Customer");
            System.out.println("3: Rent Vehicle");
            System.out.println("4: Return Vehicle");
            System.out.println("5: Display Available Vehicles");
            System.out.println("6: Show Rental History");
            System.out.println("7: Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // clear buffer

            switch (choice) {
                case 1:
                    System.out.println("  1: Car\n  2: Motorcycle\n  3: Truck");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter license plate: ");
                    String plate = scanner.nextLine().toUpperCase();

                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();

                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();

                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    Vehicle vehicle;
                    if (type == 1) {
                        System.out.print("Enter number of seats: ");
                        int seats = scanner.nextInt();
                        scanner.nextLine();
                        vehicle = new Car(plate, make, model, year);
                        System.out.println("Car added successfully.");
                    } else if (type == 2) {
                        System.out.print("Has sidecar? (true/false): ");
                        boolean sidecar = scanner.nextBoolean();
                        scanner.nextLine();
                        vehicle = new Motorcycle(plate, make, model, year); // Add sidecar support if needed
                        System.out.println("Motorcycle added successfully.");
                    } else if (type == 3) {
                        System.out.print("Enter the cargo capacity: ");
                        double cargoCapacity = scanner.nextDouble();
                        scanner.nextLine();
                        vehicle = new Truck(plate, make, model, year); // Add cargoCapacity if your Truck constructor uses it
                        System.out.println("Truck added successfully.");
                    } else {
                        vehicle = null;
                        System.out.println("Invalid type. Vehicle not added.");
                    }

                    if (vehicle != null) {
                        rentalSystem.addVehicle(vehicle);
                        System.out.println("Vehicle saved.");
                    }
                    break;

                case 2:
                    System.out.print("Enter customer ID (number): ");
                    int cid = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    System.out.print("Enter customer name: ");
                    String cname = scanner.nextLine();

                    Customer customer = new Customer(String.valueOf(cid), cname);
                    rentalSystem.addCustomer(customer);
                    System.out.println("Customer added.");
                    break;

                case 3:
                    System.out.println("Available Vehicles:");
                    rentalSystem.displayAvailableVehicles();

                    System.out.print("Enter license plate to rent: ");
                    String rentPlate = scanner.nextLine().toUpperCase();

                    System.out.println("Registered Customers:");
                    rentalSystem.displayAllCustomers();

                    System.out.print("Enter customer name: ");
                    String cnameRent = scanner.nextLine();

                    System.out.print("Enter rental amount: ");
                    double rentAmount = scanner.nextDouble();
                    scanner.nextLine();

                    Vehicle vehicleToRent = rentalSystem.findVehicleByPlate(rentPlate);
                    Customer customerToRent = rentalSystem.findCustomerByName(cnameRent);

                    if (vehicleToRent == null || customerToRent == null) {
                        System.out.println("Vehicle or customer not found.");
                    } else {
                        rentalSystem.rentVehicle(vehicleToRent, customerToRent, LocalDate.now(), rentAmount);
                    }
                    break;

                case 4:
                    System.out.println("Available Vehicles:");
                    rentalSystem.displayAvailableVehicles();

                    System.out.print("Enter license plate to return: ");
                    String returnPlate = scanner.nextLine().toUpperCase();

                    System.out.println("Registered Customers:");
                    rentalSystem.displayAllCustomers();

                    System.out.print("Enter customer name: ");
                    String cnameReturn = scanner.nextLine();

                    System.out.print("Enter return fees: ");
                    double returnFees = scanner.nextDouble();
                    scanner.nextLine();

                    Vehicle vehicleToReturn = rentalSystem.findVehicleByPlate(returnPlate);
                    Customer customerToReturn = rentalSystem.findCustomerByName(cnameReturn);

                    if (vehicleToReturn == null || customerToReturn == null) {
                        System.out.println("Vehicle or customer not found.");
                    } else {
                        rentalSystem.returnVehicle(vehicleToReturn, customerToReturn, LocalDate.now(), returnFees);
                    }
                    break;

                case 5:
                    rentalSystem.displayAvailableVehicles();
                    break;

                case 6:
                    rentalSystem.displayRentalHistory();
                    break;

                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
