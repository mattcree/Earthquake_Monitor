/**
 * Created by Cree on 29/10/2016.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ObservatoryTest {

    @Test
    public void createObservatory() {
        Observatory ob = createValidObservatory();
        Assert.assertNotNull(ob);
    }

    @Test
    public void getNameOfObservatory() {
        Observatory ob = createValidObservatory();
        Assert.assertEquals(expectedName, ob.getName());
    }

    @Test
    public void updateNameOfObservatory() {
        Observatory ob = createValidObservatory();
        String newName = "Pyongyang Observatory";
        ob.setName(newName);
        Assert.assertEquals(newName, ob.getName());
    }

    @Test
    public void getNameOfCountry() {
        Observatory ob = createValidObservatory();
        Assert.assertEquals(expectedCountryName, ob.getCountry());
    }

    @Test
    public void updateNameOfCountry() {
        Observatory ob = createValidObservatory();
        String newCountry = "Korea";
        ob.setCountry(newCountry);
        Assert.assertEquals(newCountry, ob.getCountry());
    }

    @Test
    public void getYearObservationsStarted() {
        Observatory ob = createValidObservatory();
        Assert.assertEquals(expectedFromYear, ob.getStartYear());
    }

    @Test
    public void updateYearObservationsStarted() {
        Observatory ob = createValidObservatory();
        int newYear = 1987;
        ob.setStartYear(newYear);
        Assert.assertEquals(newYear, ob.getStartYear());
    }

    @Test
    public void getAreaCovered() {
        Observatory ob = createValidObservatory();
        Assert.assertEquals(expectedAreaCovered, ob.getArea(), 0);
    }

    @Test
    public void updateAreaCovered() {
        Observatory ob = createValidObservatory();
        double newAreaCovered = 1000.10;
        ob.setArea(newAreaCovered);
        Assert.assertEquals(newAreaCovered, ob.getArea(), 0);
    }

    @Test
    public void shouldInitializeEmptyEarthquakeList() {
        Observatory ob = createValidObservatory();
        Assert.assertNotNull(ob.getEarthquakes());
        Assert.assertTrue(ob.getEarthquakes().isEmpty());
    }

    @Test
    public void shouldStoreEarthquakeThatWasAdded() {
        Observatory ob = createValidObservatory();
        Earthquake eq = createEarthquake();
        ob.addEarthquake(eq);
        Assert.assertTrue(ob.getEarthquakes().contains(eq));
    }

    @Test
    public void getLargestMagnitudeEarthquakeShouldReturnNullWhenNoEarthquakeRecorded() {
        Observatory ob = createValidObservatory();
        Earthquake largestEarthquake = ob.getLargestMagnitudeEarthquake();
        Assert.assertNull(largestEarthquake);
    }

    @Test
    public void getLargestMagnitudeEarthquakeShouldReturnLargestEarthquakeRecorded() {
        Observatory ob = createValidObservatory();
        Earthquake eq1 = new Earthquake(9.7, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(10, 1.1, 1.1, 2016);
        ob.addEarthquake(eq1);
        ob.addEarthquake(eq2);
        Earthquake largestEarthquake = ob.getLargestMagnitudeEarthquake();
        Assert.assertSame(eq2, largestEarthquake);
    }

    @Test
    public void getLargestMagnitudeEarthquakeShouldReturnTheLargestEarthquakeThatWasFirstRecorded() {
        Observatory ob = createValidObservatory();
        Earthquake eq1 = new Earthquake(9.7, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(10, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(10, 1.1, 1.1, 2016); // Same size as eq2, but first write wins
        ob.addEarthquake(eq1);
        ob.addEarthquake(eq2);
        ob.addEarthquake(eq3);
        Earthquake largestEarthquake = ob.getLargestMagnitudeEarthquake();
        Assert.assertSame(eq2, largestEarthquake);
    }

    @Test
    public void getAverageEarthquakeMagnitudeShouldReturnZeroWhenNoEarthquakesRecord() {
        Observatory ob = createValidObservatory();
        double expectedAverage = 0;
        Assert.assertEquals(expectedAverage, ob.getAverageEarthquakeMagnitude(), 0);
    }

    @Test
    public void getAverageEarthquakeMagnitudeShouldReturnAverageMagnitudeForRecordedEarthquakes() {
        Observatory ob = createValidObservatory();
        Earthquake eq1 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(4, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(6, 1.1, 1.1, 2016);
        ob.addEarthquake(eq1);
        ob.addEarthquake(eq2);
        ob.addEarthquake(eq3);
        double expectedAverage = (2 + 4 + 6) / 3;
        Assert.assertEquals(expectedAverage, ob.getAverageEarthquakeMagnitude(), 0);
    }

    @Test
    public void getEarthquakesWithMagnitudeGreaterThanShouldReturnEmptyListWhenNoEarthquakesRecorded() {
        Observatory ob = createValidObservatory();
        ArrayList<Earthquake> earthquakesLargerThanMag = ob.getEarthquakesWithMagnitudeGreaterThan(2);
        Assert.assertTrue(earthquakesLargerThanMag.isEmpty());
    }


    @Test
    public void getEarthquakesWithMagnitudeGreaterThanShouldReturnEmptyListWhenNoEarthquakesGreaterThan() {
        Observatory ob = createValidObservatory();
        Earthquake eq1 = new Earthquake(2, 1.1, 1.1, 2016);
        ob.addEarthquake(eq1);
        ArrayList<Earthquake> earthquakesLargerThanMag = ob.getEarthquakesWithMagnitudeGreaterThan(2);
        Assert.assertTrue(earthquakesLargerThanMag.isEmpty());
    }

    @Test
    public void getEarthquakesWithMagnitudeGreaterThanMagitudeProvided() {
        Observatory ob = createValidObservatory();
        Earthquake eq1 = new Earthquake(2, 1.1, 1.1, 2016);
        Earthquake eq2 = new Earthquake(4, 1.1, 1.1, 2016);
        Earthquake eq3 = new Earthquake(6, 1.1, 1.1, 2016);
        ob.addEarthquake(eq1);
        ob.addEarthquake(eq2);
        ob.addEarthquake(eq3);
        ArrayList<Earthquake> earthquakesLargerThanMag = ob.getEarthquakesWithMagnitudeGreaterThan(2);
        Assert.assertFalse(earthquakesLargerThanMag.contains(eq1));
        Assert.assertTrue(earthquakesLargerThanMag.contains(eq2));
        Assert.assertTrue(earthquakesLargerThanMag.contains(eq3));
    }


    // Private Test Helpers

    private String expectedName = "Observatory of the Great Leader";
    private String expectedCountryName = "North Korea";
    private int expectedFromYear = 2000;
    private double expectedAreaCovered = 400;

    private Observatory createValidObservatory() {
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
