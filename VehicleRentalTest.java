import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;


public class VehicleRentalTest {

    @Test
    public void testLicensePlateValidation() {
        // Valid plates
        assertDoesNotThrow(() -> {
            Vehicle v1 = new Car("AAA111", "Toyota", "Corolla", 2020);
            Vehicle v2 = new Car("ABC567", "Honda", "Civic", 2021);
            Vehicle v3 = new Car("ZZZ999", "Ford", "Focus", 2022);
        });

        // Invalid plates
        assertThrows(IllegalArgumentException.class, () -> new Car("", "Toyota", "Camry", 2020));
        assertThrows(IllegalArgumentException.class, () -> new Car(null, "Honda", "Accord", 2021));
        assertThrows(IllegalArgumentException.class, () -> new Car("AAA1000", "Nissan", "Altima", 2022));
        assertThrows(IllegalArgumentException.class, () -> new Car("ZZZ99", "Mazda", "3", 2023));
    }
    
    @Test
    public void testRentAndReturnVehicle() {
        RentalSystem system = RentalSystem.getInstance();

        // Arrange
        Vehicle vehicle = new Car("XYZ123", "Mazda", "3", 2022);
        Customer customer = new Customer("001", "Ryan");

        system.addVehicle(vehicle);
        system.addCustomer(customer);

        // Confirm initial status
        assertEquals(Vehicle.VehicleStatus.AVAILABLE, vehicle.getStatus());

        // Act - Rent once
        boolean firstRent = system.rentVehicle(vehicle, customer, java.time.LocalDate.now(), 100.0);
        assertTrue(firstRent, "First rent should succeed");
        assertEquals(Vehicle.VehicleStatus.RENTED, vehicle.getStatus());

        // Act - Rent again (should fail)
        boolean secondRent = system.rentVehicle(vehicle, customer, java.time.LocalDate.now(), 100.0);
        assertFalse(secondRent, "Second rent should fail since it's already rented");

        // Act - Return once
        boolean firstReturn = system.returnVehicle(vehicle, customer, java.time.LocalDate.now(), 0.0);
        assertTrue(firstReturn, "First return should succeed");
        assertEquals(Vehicle.VehicleStatus.AVAILABLE, vehicle.getStatus());

        // Act - Return again (should fail)
        boolean secondReturn = system.returnVehicle(vehicle, customer, java.time.LocalDate.now(), 0.0);
        assertFalse(secondReturn, "Second return should fail since it's already available");
    }
    @Test
    public void testSingletonRentalSystem() throws Exception {
        //  the private constructor
        Constructor<RentalSystem> constructor = RentalSystem.class.getDeclaredConstructor();

        // the constructor is private
        int modifiers = constructor.getModifiers();
        assertTrue(Modifier.isPrivate(modifiers), "Constructor should be private");

        //  getInstance() works and returns an object
        RentalSystem instance = RentalSystem.getInstance();
        assertNotNull(instance, "getInstance() should not return null");
    }


}