public class Car extends Vehicle implements Rentable {
    private int numSeats;

    // ✅ Constructor used in tests
    public Car(String licensePlate, String make, String model, int year) {
        super(licensePlate, make, model, year);
        this.numSeats = 5; // default value
    }

    // ✅ Original constructor (still useful)
    public Car(String make, String model, int year, int numSeats) {
        super(make, model, year);  // If your Vehicle class supports this version
        this.numSeats = numSeats;
    }

    public int getNumSeats() {
        return numSeats;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Seats: " + numSeats;
    }

    @Override
    public void rentVehicle() {
        setStatus(VehicleStatus.RENTED);
        System.out.println("Car " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Car " + getLicensePlate() + " has been returned.");
    }
}

