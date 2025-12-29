package classes;

public class Location {
    double x, y;
    public Location(double x, double y) { this.x = x; this.y = y; }

    public double distanceTo(Location location2) {
        return Math.sqrt(Math.pow(this.x - location2.x, 2) + Math.pow(this.y - location2.y, 2));
    }

    public double getX() { return x; }
    public double getY() { return y; }
}