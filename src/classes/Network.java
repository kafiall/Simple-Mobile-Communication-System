package classes;
import java.util.ArrayList;

public class Network {
    private ArrayList<Antenna> antennas = new ArrayList<>();

    public boolean addAntenna(Antenna newAntenna) {
        // When Network is Empty
        //   Adding The New Antenna as The First in Network
        if (antennas.isEmpty()) {
            antennas.add(newAntenna);
            return true;
        }

        for (Antenna currentAntenna : antennas) {
            double distance = currentAntenna.getLocation().distanceTo(newAntenna.getLocation());
            // If Distance Between Current and New Antenna is smaller the the Sum of their Radiuses,
            //   Then add the New Antenna to the list of Antennas
            if (distance <= (currentAntenna.getRadius() + newAntenna.getRadius())) {
                antennas.add(newAntenna);
                System.out.println("The Antenna is Added Successfully!");
                return true;
            }
        }
        System.out.println("Error: Antenna is isolated and cannot be added!");
        return false;
    }

    public Antenna findNearestAntenna(Location phoneLocation) {
        Antenna nearestAntenna = null; // No Available Antenna => return NULL
        double minDistance = Double.MAX_VALUE; // initialize with Maximum Possible Distance,
        //                                          to verify it surely will reduce to the minimum available distance 

        for (Antenna currentAntenna : antennas) {
            // Checking if Antenna Can Accept New Call & if the Phone is inside the Range of current Antenna
            if (currentAntenna.isPhoneInRange(phoneLocation) && currentAntenna.canAcceptNewCall()) {
                double distance = currentAntenna.getLocation().distanceTo(phoneLocation);
                if (distance <= currentAntenna.getRadius() && distance < minDistance) {
                        minDistance = distance;
                        nearestAntenna = currentAntenna;
                    }
            }
        }
        return nearestAntenna;
    }
}
