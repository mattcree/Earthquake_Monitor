/**
 * Created by Cree on 29/10/2016.
 */
import org.junit.Assert;
import org.junit.Test;

public class EarthquakeTest {

    @Test
    public void createNewEarthquake(){
        Earthquake eq = createValidEarthquake();
        Assert.assertNotNull(eq);
    }

    @Test
    public void getLatitude(){
        Earthquake eq = createValidEarthquake();
        Assert.assertEquals(expectedLatitude, eq.getLatitude(), 0);
    }

    @Test
    public void getLongitude(){
        Earthquake eq = createValidEarthquake();
        Assert.assertEquals(expectedLongitude, eq.getLongitude(), 0);
    }

    @Test
    public void getYear(){
        Earthquake eq = createValidEarthquake();
        Assert.assertEquals(expectedYear, eq.getYear(), 0);
    }

    @Test
    public void getMagnitude(){
        Earthquake eq = createValidEarthquake();
        Assert.assertEquals(expectedMagnitude, eq.getMagnitude(), 0);
    }

    // Private Test Helpers

    private double expectedMagnitude = 9.7;
    private double expectedLatitude = 100.10;
    private double expectedLongitude = 99.9;
    private int expectedYear = 1987;

    private Earthquake createValidEarthquake() {
        return new Earthquake(expectedMagnitude, expectedLatitude, expectedLongitude, expectedYear);
    }


}
