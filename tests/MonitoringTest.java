/**
 * Created by Cree on 29/10/2016.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MonitoringTest {


    @Test
    public void shouldStoreObservatoryAndProvideAccessToNames(){
        Monitoring mon = new Monitoring();
        String expectedName = "Observatory of the Great Leader";
        mon.addObservatory(new Observatory(expectedName, "North Korea", 2000, 400));
        Assert.assertTrue(mon.getObservatoryNames().contains(expectedName));
    }

    @Test
    public void shouldAddEarthquakeToObservatoryWhenObservatoryPresent(){
        Monitoring mon = new Monitoring();
        String obName = "Observatory of the Great Leader";
        Observatory ob = new Observatory(obName, "North Korea", 2000, 400);
        mon.addObservatory(ob);
        Earthquake eq = new Earthquake(2, 1.1, 1.1, 2016);

        mon.addEarthquake(obName, eq);

        Assert.assertTrue(ob.getEarthquakes().contains(eq));
    }

    @Test
    public void shouldDoNothingWhenAddingEarthquakeToObservatoryThatIsNotFound(){
        Monitoring mon = new Monitoring();
        String obName = "Observatory of the Great Leader";
        Earthquake eq = new Earthquake(2, 1.1, 1.1, 2016);
        mon.addEarthquake(obName, eq);
    }

    @Test
    public void getObservatoryWithLargestAverageMagnitudeShouldReturnObservatoryWithLargestAverage(){
        Monitoring mon = new Monitoring();

        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Earthquake eq1 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(3, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob1.addEarthquake(eq3);

        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Earthquake eq4 = new Earthquake(4, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(5, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(6, 1.1, 1.1, 2016);
        ob2.addEarthquake(eq4);
        ob2.addEarthquake(eq5);
        ob2.addEarthquake(eq6);

        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq7 = new Earthquake(7, 1.1, 1.1, 2016);
        Earthquake eq8 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq9 = new Earthquake(9, 1.1, 1.1, 2016);
        ob3.addEarthquake(eq7);
        ob3.addEarthquake(eq8);
        ob3.addEarthquake(eq9);

        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);

        Assert.assertSame(ob3, mon.getObservatoryWithLargestAverageMagnitude());
    }

    @Test
    public void getLargestRecordedEarthquakeShouldReturnLargestEarthquakeFromAllObservatories(){
        Monitoring mon = new Monitoring();

        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Earthquake eq1 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(3, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob1.addEarthquake(eq3);

        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Earthquake eq4 = new Earthquake(4, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(5, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(6, 1.1, 1.1, 2016);
        ob2.addEarthquake(eq4);
        ob2.addEarthquake(eq5);
        ob2.addEarthquake(eq6);

        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq7 = new Earthquake(7, 1.1, 1.1, 2016);
        Earthquake eq8 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq9 = new Earthquake(9, 1.1, 1.1, 2016);
        ob3.addEarthquake(eq7);
        ob3.addEarthquake(eq8);
        ob3.addEarthquake(eq9);

        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);

        Assert.assertSame(eq9, mon.getLargestRecordedEarthquake());
    }

    @Test
    public void getAllEarthquakesRecordedWithMagnitudeGreaterThanGivenNumber(){
        Monitoring mon = new Monitoring();

        Observatory ob1 = new Observatory("Obs1", "Taiwan", 2005, 600);
        Earthquake eq1 = new Earthquake(1, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(3, 1.1, 1.1, 2016);
        ob1.addEarthquake(eq1);
        ob1.addEarthquake(eq2);
        ob1.addEarthquake(eq3);

        Observatory ob2 = new Observatory("Obs2", "Australia", 1995, 1000);
        Earthquake eq4 = new Earthquake(4, 1.1, 1.1, 2016);
        Earthquake eq5 = new Earthquake(5, 1.1, 1.1, 2016);
        Earthquake eq6 = new Earthquake(6, 1.1, 1.1, 2016);
        ob2.addEarthquake(eq4);
        ob2.addEarthquake(eq5);
        ob2.addEarthquake(eq6);

        Observatory ob3 = new Observatory("Obs3", "Israel", 2015, 300);
        Earthquake eq7 = new Earthquake(7, 1.1, 1.1, 2016);
        Earthquake eq8 = new Earthquake(8, 1.1, 1.1, 2016);
        Earthquake eq9 = new Earthquake(9, 1.1, 1.1, 2016);
        ob3.addEarthquake(eq7);
        ob3.addEarthquake(eq8);
        ob3.addEarthquake(eq9);

        mon.addObservatory(ob1);
        mon.addObservatory(ob2);
        mon.addObservatory(ob3);

        ArrayList<Earthquake> eqList = new ArrayList<>();
        eqList.add(eq8);
        eqList.add(eq9);

        Assert.assertTrue(eqList.containsAll(mon.getAllEarthQuakesLargerThanGivenNumber(7)));
    }

}
