package classes;

public class Antenna {

    private final Location location;
    private double radius;
    private int capacity;
    private int activeCalls;

    // ------------------ const--------------------------
    
    public Antenna(Location location, double radius, int capacity)
    {
        this.location = location;
        this.radius = radius;
        this.capacity = capacity;
        this.activeCalls = 0;
    }

 // G & S --------------------------------------
    
    public Location getLocation() { return location; }
    public double getRadius() { return radius; }
    public int getCapacity() { return capacity; }
    public int getActiveCalls() { return activeCalls; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    // is itt in the range ?? ----------------------------
    
    public boolean isPhoneInRange(Location phoneLocation)
    {
        return location.distanceTo(phoneLocation) <= radius;
    }

    // can accept new call ? ------------------------------
    
    public boolean canAcceptNewCall() {
        return activeCalls < capacity;
    }

    public void incrementActiveCalls() {
        if (activeCalls < capacity)
            activeCalls++;
        else
            System.out.println("Antenna Reached Max Capacity!");
    }

    public void decrementActiveCalls() {
        if (activeCalls > 0)
            activeCalls--;
        else
            System.out.println("Antenna Already have no Active Calls!");
    }
}