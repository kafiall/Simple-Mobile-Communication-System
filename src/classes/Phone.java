package classes;

public class Phone {

    //==================== Attributes =================================

    private String phoneOwnerName;
    private int batteryLevel;
    private Location location;
    private SimCard sim;
    private boolean isOnCall = false;
    
    //=================== Constructor ==================================
    
    public Phone(String phoneOwnerName, int batteryLevel, Location location) {
        this.phoneOwnerName = phoneOwnerName;
        this.batteryLevel = batteryLevel;
        this.location = location;
    }

    //============= Getters & Setters ==================================
    
    public String getPhoneOwnerName() { return this.phoneOwnerName; }
    public SimCard getSimCard() { return this.sim; }
    public Location getLocation() { return this.location; }
    public boolean isOnCall() { return isOnCall;}
    public int getBatteryLevel() { return batteryLevel; }
    
    public void setPhoneOwnerName(String phoneOwnerName) { this.phoneOwnerName = phoneOwnerName; }
    public void setSimCard(SimCard sim) { this.sim = sim; }
    public void setLocation(Location location) { this.location = location; }
    public void setOnCall(boolean status) { this.isOnCall = status; }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        }
    }

    //======================== Methods =================================

    // Reducing Battery Level and Credit Balance

    public void consumeResources() {
        if (this.sim != null) 
            this.sim.deductCredit(10); // if there is Sim Card, reduce credit by 10 DZD per call
        this.batteryLevel -= 2; // Battery drops by 2% per call
    }

    // Check Making a Call

    public boolean canMakeCall() {
        if (this.batteryLevel > 0)
            if (this.sim != null && this.sim.isActive() && this.sim.isEnoughCredit(10))
                    return true;
        return false;
    }

    // Check the possibility of Receiving a Call

    public boolean canReceiveCall() {
        // if there is Sim Card AND it is activated 
        if (this.sim != null && this.sim.isActive())
            return true;
        return false;
    }

    // Update the Phone Location while Moving 

    public void move(Location newLocation, Network net) {
        this.location = newLocation; // Update current location
        System.out.printf("Phone moved to: (%.2f, %.2f)%n", location.getX(), location.getY());

        // If the phone is currently on a call, check if it's still in range
        if (this.isOnCall) {
            Antenna currentCoverage = net.findNearestAntenna(this.location);
            if (currentCoverage == null) {
                // Disconnect if out of range
                this.isOnCall = false;
                System.out.println("! Call Dropped: Out of network coverage range");
            } else
            {
                double x = currentCoverage.getLocation().getX();
                double y = currentCoverage.getLocation().getY();
                System.out.printf("Call continues using antenna at: (%.2f, %.2f)%n", x, y);
            }
        }
    }

    // Make Call

    public void call(Phone receiver, Network net) {
        // Case 1: Caller Cannot Make Call
        if (!this.canMakeCall()) {
            System.out.println("Caller Cannot make call (No Battery/Credit/Active SIM)");
            return;
        }

        // Case 2: Receiver Cannot Receive Call
        if (!receiver.canReceiveCall()) {
            System.out.println("Receiver cannot receive the call (Off or No SIM).");
        return;
        }

        // Case 3: Receiver is Already on Active Call
        if (receiver.isOnCall) {
                System.out.println("Receiver is currently busy.");
                return;
            }

        //Case 4: Caller is Out of Network Coverage Range
        Antenna callerAntenna = net.findNearestAntenna(this.location);
        if (callerAntenna == null) {
            System.out.println("Caller is out of range or antenna is full");
            return;
        }

        //Case 4: Receiver is Out of Network Coverage Range
        Antenna receiverAntenna = net.findNearestAntenna(receiver.getLocation());
        if (receiverAntenna == null) {
            System.out.println("Receiver is out of range or antenna is full");
            return;
        }

        // Caller and Receiver are in Active Call
        this.isOnCall = true;
        receiver.setOnCall(true);

        // Increment Active Calls Number of each Antenna
        callerAntenna.incrementActiveCalls();
        receiverAntenna.incrementActiveCalls();

        // Reduce Resources
        this.consumeResources();
        receiver.setBatteryLevel(receiver.getBatteryLevel() - 2);
        System.out.println("Starting a Call between You and " + receiver.getPhoneOwnerName());
    }

    public void endCall() {
        isOnCall = false;
    }
}

