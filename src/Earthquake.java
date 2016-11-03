/**
 * Earthquake Model Object
 *
 * Contains various attributes of an Earthquake object, and related behavior.
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

    /**
     *
     * @param magnitude (required) magnitude of an Earthquake as a double
     * @param latitude (required) latitude of an Earthquake as a double
     * @param longitude (required) longitude as an Earthquake as a double
     * @param year (required) year of an Earthquake as an int
     */
    public Earthquake(double magnitude, double latitude, double longitude, int year) {
        this.magnitude = magnitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.year = year;
    }

    /**
     *
     * @return the magnitude as a double
     *
     */
    public double getMagnitude() {
        return this.magnitude;
    }

    /**
     *
     * @return the latitude as a double
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     *
     * @return the longitude as a double
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     *
     * @return the year as an int
     */
    public int getYear() {
        return this.year;
    }
}
