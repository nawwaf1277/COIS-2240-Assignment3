import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentalSystem {
    private static RentalSystem instance = null; 
    
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory();
    
    private RentalSystem() {
        loadData(); // Load saved data at program start
    }

    
    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

 // 🔹 Task 1.2: Add vehicle and save to file
    public boolean addVehicle(Vehicle vehicle) {
        if (findVehicleByPlate(vehicle.getLicensePlate()) != null) {
            System.out.println("Duplicate vehicle: Plate already exists.");
            return false;
        }
        vehicles.add(vehicle);
        saveVehicle(vehicle);  // Save to file
        return true;
    }

    // 🔹 Task 1.2: Add customer and save to file
    public boolean addCustomer(Customer customer) {
        if (findCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Duplicate customer: ID already exists.");
            return false;
        }
        customers.add(customer);
        saveCustomer(customer);  // Save to file
        return true;
    }

    // 🔹 Task 1.2: Rent vehicle and save rental record
    public boolean rentVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.AVAILABLE) {
            vehicle.setStatus(Vehicle.VehicleStatus.RENTED);
            RentalRecord record = new RentalRecord(vehicle, customer, date, amount, "RENT");
            rentalHistory.addRecord(record);
            saveRecord(record);  // Save to file
            System.out.println("Vehicle rented to " + customer.getCustomerName());
            return true;
        } else {
            System.out.println("Vehicle is not available for renting.");
            return false;
        }
    }

    // 🔹 Task 1.2: Return vehicle and save rental record
    public boolean returnVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.RENTED) {
            vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
            RentalRecord record = new RentalRecord(vehicle, customer, date, amount, "RETURN");
            rentalHistory.addRecord(record);
            saveRecord(record);  // Save to file
            System.out.println("Vehicle returned by " + customer.getCustomerName());
            return true;
        } else {
            System.out.println("Vehicle was not rented.");
            return false;
        }
    }

    // 🔹 Task 1.2: Save vehicle to vehicles.txt
    private void saveVehicle(Vehicle vehicle) {
        try (PrintWriter out = new PrintWriter(new FileWriter("vehicles.txt", true))) {
            out.println(vehicle.getLicensePlate() + "," +
                        vehicle.getMake() + "," +
                        vehicle.getModel() + "," +
                        vehicle.getYear());
        } catch (IOException e) {
            System.out.println("Error saving vehicle: " + e.getMessage());
        }
    }

    // 🔹 Task 1.2: Save customer to customers.txt
    private void saveCustomer(Customer customer) {
        try (PrintWriter out = new PrintWriter(new FileWriter("customers.txt", true))) {
            out.println(customer.getCustomerId() + "," + customer.getCustomerName());
        } catch (IOException e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

    // 🔹 Task 1.2: Save rental record to rental_records.txt
    private void saveRecord(RentalRecord record) {
        try (PrintWriter out = new PrintWriter(new FileWriter("rental_records.txt", true))) {
            out.println(record.getVehicle().getLicensePlate() + "," +
                        record.getCustomer().getCustomerId() + "," +
                        record.getDate() + "," +
                        record.getAmount() + "," +
                        record.getAction());
        } catch (IOException e) {
            System.out.println("Error saving record: " + e.getMessage());
        }
    }

    // 🔹 Support methods
    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equals(plate)) return v;
        }
        return null;
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public RentalHistory getRentalHistory() {
        return rentalHistory;
    }
}