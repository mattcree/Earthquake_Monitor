/**
 * Write a description of class Observatory here.
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

    public Observatory(String name, String countryName, int fromYear, double areaCovered) {
        this.name = name;
        this.countryName = countryName;
        this.fromYear = fromYear;
        this.areaCovered = areaCovered;
        this.earthquakes = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCountry(String countryName) {
        this.countryName = countryName;
    }

    public String getCountry() {
        return this.countryName;
    }

    public void setStartYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getStartYear() {
        return this.fromYear;
    }

    public void setArea(double areaCovered) {
        this.areaCovered = areaCovered;
    }

    public double getArea() {
        return this.areaCovered;
    }

    public Earthquake getLargestMagnitudeEarthquake() {
        return this.largestRecordedEarthquake;
    }

    /**
     * @param earthquake
     * @apiNote If it has a bigger magnitude than any recorded before, it records the magnitude and earthquake.
     */
    public void addEarthquake(Earthquake earthquake) {
        updateLargestEarthquakeIfLargestRecorded(earthquake);
        this.earthquakes.add(earthquake);
    }

    public ArrayList<Earthquake> getEarthquakes() {
        return this.earthquakes;
    }

    /**
     * @return The average magnitude the observatory's earthquakes.
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
        return total / earthquakes.size();
    }

    /**
     * @return Earthquakes with magnitude larger than a given number
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

    /**
     * @param earthquake
     * @apiNote Will set earthquake as biggest only if it is larger than
     * previously recorded earthquakes.
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
