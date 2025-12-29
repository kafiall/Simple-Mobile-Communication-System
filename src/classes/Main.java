package classes;

public class Main {
    public static void main(String[] args) {
        // 1. Initializing the Network and Antennas
        Network mobilis = new Network();
        
        // Creating overlapping antennas
        Antenna tower1 = new Antenna(new Location(0, 0), 100, 2);
        Antenna tower2 = new Antenna(new Location(150, 0), 120, 10);
        
        System.out.println("--- Building Network ---");
        mobilis.addAntenna(tower1);
        mobilis.addAntenna(tower2);

        // 2. Setting up Phones and SIM Cards
        // Ahmed: Caller
        SimCard sim1 = new SimCard("0661223344", true, 500.0);
        Phone phone1 = new Phone("Ahmed", 100, new Location(10, 10));
        phone1.setSimCard(sim1);

        // Aymen: Receiver
        SimCard sim2 = new SimCard("0550112233", true, 200.0);
        Phone phone2 = new Phone("Aymen", 90, new Location(140, 5));
        phone2.setSimCard(sim2);

        // ---------------------------------------------------------
        // Test Case 1: Normal Successful Call
        // ---------------------------------------------------------

        System.out.println("\n\n[Test 1] Normal Call Scenario:\n");
        phone1.call(phone2, mobilis);

        // ---------------------------------------------------------
        // Test Case 2: Mobility (Walking out of range)
        // ---------------------------------------------------------

        System.out.println("\n\n[Test 2] Mobility Scenario (Ahmed is walking...):\n");
        // Moving to a safe location first
        phone1.move(new Location(50, 50), mobilis); 
        // Moving out of range (Disconnected)
        phone1.move(new Location(1000, 1000), mobilis); 

        // ---------------------------------------------------------
        // Test Case 3: Insufficient Credit
        // ---------------------------------------------------------

        System.out.println("\n\n[Test 3] Low Credit Scenario:\n");
        SimCard sim3 = new SimCard("0770000000", true, 5.0); // Only 5 DZD (needs 10)
        phone1.setSimCard(sim3);
        phone1.setLocation(new Location(5, 5)); // Back to coverage
        phone1.call(phone2, mobilis);

        // ---------------------------------------------------------
        // Test Case 4: Receiver in Call
        // ---------------------------------------------------------
        
        System.out.println("\n\n[Test 4] Busy Receiver Scenario:\n");
        Phone phone3 = new Phone("Guest", 100, new Location(2, 2));
        phone3.setSimCard(new SimCard("0540000000", true, 100));
        
        phone3.call(phone2, mobilis); // Should fail due to capacity
        phone2.endCall(); // end The Active Call for Phone 2

        // ---------------------------------------------------------
        // Test Case 5: Antenna Capacity Limit
        // ---------------------------------------------------------

        System.out.println("\n\n[Test 5] Max Capacity Scenario:\n");
        // tower1 capacity is 2. Let's fill it.
        tower1.incrementActiveCalls();
        tower1.incrementActiveCalls(); 
        phone3.call(phone2, mobilis); // Should fail due to capacity

        // ---------------------------------------------------------
        // Test Case 6: Isolated Antenna Check
        // ---------------------------------------------------------

        System.out.println("\n\n[Test 6] Adding Isolated Antenna:\n");
        Antenna farTower = new Antenna(new Location(5000, 5000), 100, 5);
        mobilis.addAntenna(farTower);
        System.out.print("\n\n");
    }
}
