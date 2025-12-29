package classes;

//! ++ AirPlane Mode

public class Phone {

    String phoneOwnerName;
    private int batteryLevel;
    private Location location;
    private SimCard sim;
    private boolean isOnCall = false;
    
    //=================================================================
    
    public Phone(String phoneOwnerName, int batteryLevel, Location location) {
        this.phoneOwnerName = phoneOwnerName;
        this.batteryLevel = batteryLevel;
        this.location = location;
    }

    //=================================================================
    
    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        }
    }

    //=================================================================
    
    public String getPhoneOwnerName() { return this.phoneOwnerName; }
    public Location getLocation() { return this.location; }
    public SimCard getSimCard() { return this.sim; }


    public void setLocation(Location location) { this.location = location; }
    public void setSimCard(SimCard sim) { this.sim = sim; }
    public void setOnCall(boolean status) { this.isOnCall = status; }
    public void setPhoneOwnerName(String phoneOwnerName) { this.phoneOwnerName = phoneOwnerName; }

    //=================================================================

    public void consumeResources() {
        if (this.sim != null) 
            this.sim.deductCredit(1);
        this.batteryLevel -= 2; // Battery drops by 2 units per call
    }

    public boolean canMakeCall() {
        return this.batteryLevel > 0 && this.sim != null && this.sim.isActive() && this.sim.isEnoughCredit(1);
    }

    public boolean canReceiveCall() {
        if (this.sim != null && this.sim.isActive())
            return true;
        return false;
    }

    //=================================================================

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

        Antenna callerAntenna = net.findNearestAntenna(this.location);
        if (callerAntenna == null) {
            System.out.println("Caller is out of range or antenna is full");
            return;
        }

        Antenna receiverAntenna = net.findNearestAntenna(receiver.getLocation());
        if (receiverAntenna == null) {
            System.out.println("Receiver is out of range or antenna is full");
            return;
        }

        this.isOnCall = true;
        receiver.setOnCall(true);

        callerAntenna.incrementActiveCalls();
        receiverAntenna.incrementActiveCalls();

        this.consumeResources();
        receiver.setBatteryLevel(batteryLevel-1);
        System.out.println("Starting a Call between You and " + receiver.getPhoneOwnerName());
    }

    public void endCall() { isOnCall = false; }
}