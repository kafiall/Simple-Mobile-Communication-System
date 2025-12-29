package classes;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize Network
        Network gsm = new Network();
        gsm.addAntenna(new Antenna(new Location(0, 0), 100, 10));
        gsm.addAntenna(new Antenna(new Location(150, 0), 100, 10));

        // 2. Setup Phones
        Phone caller = new Phone("Caller", 100, new Location(10, 10));
        caller.setSimCard(new SimCard("0661223344", true, 20.0));

        Phone receiver = new Phone("Receiver", 100, new Location(140, 5));
        receiver.setSimCard(new SimCard("0550112233", true, 15.0));

        // 3. Simulate Call Logic
        System.out.println("Attempting to start a call...");
        
        Antenna callerAnt = gsm.findNearestAntenna(caller.getLocation());
        Antenna receiverAnt = gsm.findNearestAntenna(receiver.getLocation());

        if (callerAnt != null && receiverAnt != null && caller.canMakeCall() && receiver.canReceiveCall()) {
            // Success: Connect both to antennas
            callerAnt.incrementActiveCalls();
            receiverAnt.incrementActiveCalls();
            
            caller.setOnCall(true);
            receiver.setOnCall(true);
            
            caller.consumeResources();
            
            System.out.println("Call Connected!");
            System.out.println("Caller Credit: " + caller.getSimCard().getCreditBalance());
        } else {
            System.out.println("Call Failed: Either no coverage, busy, or no credit.");
        }
    }
}