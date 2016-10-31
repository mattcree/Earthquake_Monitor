/**
 * Write a description of class Monitoring here.
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

    //Adds an Observatory to the observatories list.
    public void addObservatory(Observatory observatory) {
        this.observatories.put(observatory.getName(), observatory);
    }

    public void addEarthquake(String name, Earthquake earthquake) {
        if(this.observatories.containsKey(name)){
            this.observatories.get(name).addEarthquake(earthquake);
        }
    }

    public Set<String> getObservatoryNames() {
        return this.observatories.keySet();
    }

    //Returns the magnitude of the largest earthquake recorded at any Observatory in the list.
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

    //Returns the Observatory with the largest average magnitude of earthquake.
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

    //Returns a list of all recorded earthquakes with magnitudes larger than a given number.
    public ArrayList<Earthquake> getAllEarthQuakesLargerThanGivenNumber(double number) {
        ArrayList<Earthquake> answer = new ArrayList<Earthquake>();
        for (String key : this.observatories.keySet()) {
            answer.addAll(observatories.get(key).getEarthquakesWithMagnitudeGreaterThan(number));
        }
        return answer;
    }
}
