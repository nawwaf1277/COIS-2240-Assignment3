public class Car extends Vehicle implements Rentable {
    private int numSeats;

    // Original constructor (keep it)
    public Car(String make, String model, int year, int numSeats) {
        super(make, model, year); // ⚠️ <- This also needs licensePlate if your Vehicle expects it
        this.numSeats = numSeats;
    }

    // New constructor to support file loading
    public Car(String licensePlate, String make, String model, int year) {
        super(licensePlate, make, model, year);
        this.numSeats = 5; // default
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
