/**
 * Created by Cree on 29/10/2016.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MonitoringTest {

    @Test
    public void getObservatoryWithLargestAverageQuakeMagnitude(){
        Monitoring mon = new Monitoring();
        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq1 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq4 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq7 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq8 = new Earthquake(9, 1.1, 1.1, 2016);
        Earthquake eq9 = new Earthquake(10, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob1.addEarthquake(eq3);
        ob2.addEarthquake(eq4);
        ob2.addEarthquake(eq5);
        ob2.addEarthquake(eq6);
        ob3.addEarthquake(eq7);
        ob3.addEarthquake(eq8);
        ob3.addEarthquake(eq9);
        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);
        Assert.assertSame(ob2, mon.getObservatoryWithLargestAverage());
    }

    @Test
    public void getLargestMagnitudeEarthquakeEverRecorded(){
        Monitoring mon = new Monitoring();
        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq1 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(3, 1.1, 1.1, 2016);
        Earthquake eq4 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(6, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(7, 1.1, 1.1, 2016);
        Earthquake eq7 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq8 = new Earthquake(9, 1.1, 1.1, 2016);
        Earthquake eq9 = new Earthquake(1, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob1.addEarthquake(eq3);
        ob2.addEarthquake(eq4);
        ob2.addEarthquake(eq5);
        ob2.addEarthquake(eq6);
        ob3.addEarthquake(eq7);
        ob3.addEarthquake(eq8);
        ob3.addEarthquake(eq9);
        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);
        Assert.assertSame(mon.getLargestEverEarthquake(), eq4);
    }

    @Test
    public void getAllEarthquakesRecordedWithMagnitudeGreaterThanGivenNumber(){
        Monitoring mon = new Monitoring();
        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq1 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(3, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(6, 1.1, 1.1, 2016);
        Earthquake eq4 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(6, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(8, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob2.addEarthquake(eq3);
        ob2.addEarthquake(eq4);
        ob3.addEarthquake(eq5);
        ob3.addEarthquake(eq6);
        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);
        ArrayList<Earthquake> eqList = new ArrayList<>();
        eqList.add(eq6);
        eqList.add(eq4);
        eqList.add(eq1);
        Assert.assertTrue(eqList.containsAll(mon.getAllEarthQuakesLargerThanGivenNumber(7)));
    }

    //Private test helpers
    private Observatory createValidObservatory() {
        String expectedName = "Observatory of the Great Leader";
        String expectedCountryName = "North Korea";
        int expectedFromYear = 2000;
        double expectedAreaCovered = 400;
        return new Observatory(expectedName, expectedCountryName, expectedFromYear, expectedAreaCovered);
    }

    private Earthquake createEarthquake() {
        double expectedMagnitude = 9.7;
        double expectedLatitude = 100.10;
        double expectedLongitude = 99.9;
        int expectedYear = 1987;
        return new Earthquake(expectedMagnitude, expectedLatitude, expectedLongitude, expectedYear);
    }
}
