/**
 * Observatory Model Object which contains various attributes of an Observatory object, and related behavior.
 *
 * @author Matthew Cree
 * @version October 2016
 */

import java.util.ArrayList;

public class Observatory
{
    private String name;
    private String countryName;
    private int fromYear;
    private double areaCovered;
    private ArrayList<Earthquake> earthquakes;
    private Earthquake largestRecordedEarthquake;

    /**
     * Constructor for an Observatory object. All parameters are required to create a valid Observatory object.
     * @param name (required) Name of Observatory as a string.
     * @param countryName (required) Name of the Country the Observatory is in as a string.
     * @param fromYear (required) Date monitoring began as an integer.
     * @param areaCovered (required) Area covered by the Observatory as an integer.
     */
    public Observatory(String name, String countryName, int fromYear, double areaCovered) {
        this.name = name;
        this.countryName = countryName;
        this.fromYear = fromYear;
        this.areaCovered = areaCovered;
        this.earthquakes = new ArrayList<>();
    }

    /**
     * Mutator method for Observatory Name. Sets name field.
     * @param name Name of Observatory as a string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for Observatory Name. Gets name field.
     * @return Name of Observatory as a string.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Mutator method for Country Name. Sets Observatory Name.
     * @param countryName Country Name as a string.
     */
    public void setCountry(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Accessor method for Country Name. Gets Name of Country Observatory is in.
     * @return Name of Country as a string.
     */
    public String getCountry() {
        return this.countryName;
    }

    /**
     * Mutator method for Start Year. Sets name Observatory began recording.
     * @param fromYear Year recording began as an integer.
     */
    public void setStartYear(int fromYear) {
        this.fromYear = fromYear;
    }

    /**
     * Accessor method for Start Year. Gets Year recording began.
     * @return Year recording began as an integer.
     */
    public int getStartYear() {
        return this.fromYear;
    }

    /**
     * Mutator method for Area Covered. Sets Area Covered by the Observatory.
     * @param areaCovered Area Covered as a double.
     */
    public void setArea(double areaCovered) {
        this.areaCovered = areaCovered;
    }

    /**
     * Accessor method for Area Covered. Gets Area Covered by the Observatory.
     * @return Area Covered as double.
     */
    public double getArea() {
        return this.areaCovered;
    }

    /**
     * Accessor method for the ArrayList of Earthquakes
     * @return
     */
    public ArrayList<Earthquake> getEarthquakes() {
        return this.earthquakes;
    }

    /**
     * Method for adding an Earthquake object to the earthquakes list. Also sets the Largest Earthquake if the Earthquake's magnitude is larger than any before recorded.
     * @param earthquake Earthquake object.
     */
    public void addEarthquake(Earthquake earthquake) {
        updateLargestEarthquakeIfLargestRecorded(earthquake);
        this.earthquakes.add(earthquake);
    }

    /**
     * Accessor method for the largest recorded Earthquake.
     * @return Earthquake object.
     */
    public Earthquake getLargestMagnitudeEarthquake() {
        return this.largestRecordedEarthquake;
    }

    /**
     * Gets the average magnitude of Earthquake recorded by the Observatory.
     * @return Average magnitude as a double.
     */
    public double getAverageEarthquakeMagnitude() {
        int recordedEarthquakesCount =  earthquakes.size();
        if(recordedEarthquakesCount == 0) {
            return 0;
        }
        
        double total = 0;
        for(Earthquake quake : this.earthquakes) {
            total += quake.getMagnitude();
        }
        return total / recordedEarthquakesCount;
    }

    /**
     * Method to return all Earthquakes larger than a given number.
     * @param number A double representing an Earthquake's magnitude.
     * @return ArrayList of Earthquake with magnitudes larger than passed in number.
     */
    public ArrayList<Earthquake> getEarthquakesWithMagnitudeGreaterThan(double number) {
        ArrayList<Earthquake> answer = new ArrayList<>();
        for (Earthquake quake : this.earthquakes) {
            double check = quake.getMagnitude();
            if (check > number) {
                answer.add(quake);
            }
        }
        return answer;
    }

    /** Helper method used by Add Earthquake. Compares the magnitude of an Earthquake to the Largest Recorded and sets
     * Largest Recorded if that Earthquake's magnitude is larger than Largest Recorded. If none previously recorded, sets that Earthquake as Largest Recorded.
     * @param earthquake Earthquake object.
     *
     */
    private void updateLargestEarthquakeIfLargestRecorded(Earthquake earthquake) {
        if(this.largestRecordedEarthquake == null) {
            this.largestRecordedEarthquake = earthquake;
        }
        else if (earthquake.getMagnitude() > this.largestRecordedEarthquake.getMagnitude()) {
            this.largestRecordedEarthquake = earthquake;
        }
    }
}
