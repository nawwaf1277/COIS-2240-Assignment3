import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentalSystem {
    private static RentalSystem instance = null;  // ✅ Singleton instance

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory();

    // ✅ Private constructor to enforce Singleton
    private RentalSystem() {
        // Optional: loadData(); if used
    }

    // ✅ Singleton access method
    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    // ✅ Add vehicle
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // ✅ Add customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // ✅ Rent a vehicle
    public boolean rentVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.AVAILABLE) {
            vehicle.setStatus(Vehicle.VehicleStatus.RENTED);
            RentalRecord record = new RentalRecord(vehicle, customer, date, amount, "RENT");
            rentalHistory.addRecord(record);
            saveRecord(record);
            System.out.println("Vehicle rented to " + customer.getCustomerName());
            return true;
        } else {
            System.out.println("Vehicle is not available.");
            return false;
        }
    }

    // ✅ Return a vehicle
    public boolean returnVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.RENTED) {
            vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
            RentalRecord record = new RentalRecord(vehicle, customer, date, amount, "RETURN");
            rentalHistory.addRecord(record);
            saveRecord(record);
            System.out.println("Vehicle returned by " + customer.getCustomerName());
            return true;
        } else {
            System.out.println("Vehicle was not rented.");
            return false;
        }
    }

    // ✅ Display all vehicles (filtered)
    public void displayVehicles(boolean onlyAvailable) {
        System.out.println("|     Type         |\tPlate\t|\tMake\t|\tModel\t|\tYear\t|");
        System.out.println("---------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            if (!onlyAvailable || v.getStatus() == Vehicle.VehicleStatus.AVAILABLE) {
                System.out.println("|     " + (v instanceof Car ? "Car          " : "Motorcycle   ") +
                        "|\t" + v.getLicensePlate() +
                        "\t|\t" + v.getMake() +
                        "\t|\t" + v.getModel() +
                        "\t|\t" + v.getYear() + "\t|");
            }
        }
        System.out.println();
    }

    // ✅ Display all customers
    public void displayAllCustomers() {
        for (Customer c : customers) {
            System.out.println("  " + c.toString());
        }
    }

    // ✅ Display rental history
    public void displayRentalHistory() {
        for (RentalRecord record : rentalHistory.getRentalHistory()) {
            System.out.println(record.toString());
        }
    }

    // ✅ Find a vehicle by plate
    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(plate)) {
                return v;
            }
        }
        return null;
    }

    // ✅ Find customer by ID (String-safe)
    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // ✅ Placeholder save method (can log or skip for now)
    private void saveRecord(RentalRecord record) {
        // Optional: Write to file or simulate saving
    }
}
