/**
 * Observatory Model Object which contains various attributes of an Observatory object, and related behavior.
 *
 * @author Matthew Cree
 * @version October 2016
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Monitoring
{
    private Map<String, Observatory> observatories = new HashMap<>();

    /**
     * Adds an Observatory object to the Observatories HashMap.
     * @param observatory Observatory object.
     */
    public void addObservatory(Observatory observatory) {
        this.observatories.put(observatory.getName(), observatory);
    }

    /**
     * Adds an Earthquake object to a particular Observatory's List.
     * @param name Name of Observatory as a string.
     * @param earthquake An Earthquake object.
     */
    public void addEarthquake(String name, Earthquake earthquake) {
        if (observatories.containsKey(name)) {
            this.observatories.get(name).addEarthquake(earthquake);
        }
    }

    /**
     * Gets all keys for the Observatories HashMap.
     * @return A Set of String representing Observatory names.
     */
    public Set<String> getObservatoryNames() {
        return this.observatories.keySet();
    }

    /**
     * Method to return the Largest Recorded Earthquake recorded by any Observatory.
     * @return Earthquake object.
     */
    public Earthquake getLargestRecordedEarthquake() {
        double biggestMag = 0;
        Earthquake answer = null;

        for(String key : this.observatories.keySet()) {
            double check = observatories.get(key).getLargestMagnitudeEarthquake().getMagnitude();
            if(check > biggestMag) {
                biggestMag = check;
                answer = observatories.get(key).getLargestMagnitudeEarthquake();
            }
        }
        return answer;
}

    /**
     * Gets the Observatory whose Earthquakes have the highest average magnitude.
     * @return An Observatory object.
     */
    public Observatory getObservatoryWithLargestAverageMagnitude() {
        double biggestAverage = 0;
        Observatory answer = null;

        for(String key : this.observatories.keySet()) {
            double check = observatories.get(key).getAverageEarthquakeMagnitude();
            if(check > biggestAverage) {
                biggestAverage = check;
                answer = observatories.get(key);
            }
        }
        return answer;
    }

    /**
     * Gets an ArrayList of Earthquake whose magnitude is larger than a given number.
     * @param number A number representing a magnitude.
     * @return ArrayList of Earthquake
     */
    public ArrayList<Earthquake> getAllEarthQuakesLargerThanGivenNumber(double number) {
        ArrayList<Earthquake> answer = new ArrayList<Earthquake>();
        for (String key : this.observatories.keySet()) {
            answer.addAll(observatories.get(key).getEarthquakesWithMagnitudeGreaterThan(number));
        }
        return answer;
    }
}
