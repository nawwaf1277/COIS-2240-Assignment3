import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
}