public abstract class Vehicle {
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private VehicleStatus status;

    public enum VehicleStatus { AVAILABLE, RESERVED, RENTED, MAINTENANCE, OUTOFSERVICE }

    public Vehicle(String make, String model, int year) {
    	if (make == null || make.isEmpty())
    		this.make = null;
    	else
    		this.make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
    	
    	if (model == null || model.isEmpty())
    		this.model = null;
    	else
    		this.model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
    	
        this.year = year;
        this.status = VehicleStatus.AVAILABLE;
        this.licensePlate = null;
    }
    
    public Vehicle(String licensePlate, String make, String model, int year) {
        if (!isValidPlate(licensePlate)) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
        this.licensePlate = licensePlate.toUpperCase();

        if (make == null || make.isEmpty())
            this.make = null;
        else
            this.make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();

        if (model == null || model.isEmpty())
            this.model = null;
        else
            this.model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();

        this.year = year;
        this.status = VehicleStatus.AVAILABLE;
    }

    private boolean isValidPlate(String plate) {
        if (plate == null || plate.trim().isEmpty()) return false;
        return plate.matches("^[A-Z]{3}[0-9]{3}$");
    }


    public Vehicle() {
        this(null, null, 0);
    }

    public void setLicensePlate(String plate) {
        if (!isValidPlate(plate)) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
        this.licensePlate = plate.toUpperCase();
    }

    public void setStatus(VehicleStatus status) {
    	this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }

    public String getMake() { return make; }

    public String getModel() { return model;}

    public int getYear() { return year; }

    public VehicleStatus getStatus() { return status; }

    public String getInfo() {
        return "| " + licensePlate + " | " + make + " | " + model + " | " + year + " | " + status + " |";
    }

}
