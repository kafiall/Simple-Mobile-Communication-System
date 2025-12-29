package classes;
import java.util.ArrayList;

public class Network {
    private ArrayList<Antenna> antennas = new ArrayList<>();

    public boolean addAntenna(Antenna newAntenna) {
        if (antennas.isEmpty()) {
            antennas.add(newAntenna);
            return true;
        }

        for (Antenna currentAntenna : antennas) {
            double distance = currentAntenna.getLocation().distanceTo(newAntenna.getLocation());
            if (distance <= (currentAntenna.getRadius() + newAntenna.getRadius())) {
                antennas.add(newAntenna);
                return true;
            }
        }
        System.out.println("Error: Antenna is isolated and cannot be added!");
        return false;
    }

    public Antenna findNearestAntenna(Location phoneLocation) {
        Antenna nearestAntenna = null;
        double minDistance = Double.MAX_VALUE;

        for (Antenna currentAntenna : antennas) {
            if (currentAntenna.isPhoneInRange(phoneLocation) && currentAntenna.canAcceptNewCall()) {
            double dist = currentAntenna.getLocation().distanceTo(phoneLocation);
            if (dist <= currentAntenna.getRadius() && dist < minDistance) {
                    minDistance = dist;
                    nearestAntenna = currentAntenna;
                }
            }
        }
        return nearestAntenna;
    }
}