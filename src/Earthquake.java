/**
 * Write a description of class Earthquake here.
 *
 * @author Matthew Cree
 * @version October 2016
 */

public class Earthquake
{
    private double magnitude;
    private double latitude;
    private double longitude;
    private int year;

    public Earthquake(double magnitude, double latitude, double longitude, int year) {
        this.magnitude = magnitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.year = year;
    }

    public double getMagnitude() {
        return this.magnitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int getYear() {
        return this.year;
    }
}
