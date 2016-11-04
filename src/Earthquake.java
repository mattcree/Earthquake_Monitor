/**
 * Earthquake Model Object which contains various attributes of an Earthquake object, and related behavior.
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
     *  Constructor for an Earthquake. Single constructor requires all parameters to create a valid Earthquake object.
     * @param magnitude (required) Magnitude of an Earthquake as a double.
     * @param latitude (required) Latitude of an Earthquake as a double.
     * @param longitude (required) Longitude as an Earthquake as a double.
     * @param year (required) Year of an Earthquake as an int.
     */
    public Earthquake(double magnitude, double latitude, double longitude, int year) {
        this.magnitude = magnitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.year = year;
    }

    /**
     *  Accessor method for the Magnitude field.
     * @return the magnitude as a double
     *
     */
    public double getMagnitude() {
        return this.magnitude;
    }

    /**
     *  Accessor method for the Latitude field.
     * @return the latitude as a double
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Accessor method for the Longitude field.
     * @return the longitude as a double
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     *  Accessor method for the Year field
     * @return the year as an int
     */
    public int getYear() {
        return this.year;
    }
}
