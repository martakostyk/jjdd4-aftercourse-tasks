package martak.jjdd4.aftercourse.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class UnitConverterImplTest {

    private static final double DELTA = 0.05;

    private UnitConverter unitConverter = new UnitConverterImpl();

    @Test
    public void shouldTestCelsiusToFahrenheit() {
        assertEquals(-4.0, unitConverter.celsiusToFahrenheit(-20.0), DELTA);
        assertEquals(32.0, unitConverter.celsiusToFahrenheit(0.0), DELTA);
        assertEquals(86.0, unitConverter.celsiusToFahrenheit(30.0), DELTA);
    }

    @Test
    public void shouldTestFahrenheitToCelsius() {
        assertEquals(-40.0, unitConverter.fahrenheitToCelsius(-40.0), DELTA);
        assertEquals(10.0, unitConverter.fahrenheitToCelsius(50.0), DELTA);
        assertEquals(50.0, unitConverter.fahrenheitToCelsius(122.0), DELTA);
    }

    @Test
    public void shouldTestMilesToKilometers() {
        assertEquals(160.93, unitConverter.milesToKilometers(100.0), DELTA);
        assertEquals(823.98, unitConverter.milesToKilometers(512), DELTA);
        assertEquals(8046.72, unitConverter.milesToKilometers(5000.0), DELTA);
    }

    @Test
    public void shouldTestKilometersToMiles() {
        assertEquals(3.11, unitConverter.kilometersToMiles(5.0), DELTA);
        assertEquals(621.37, unitConverter.kilometersToMiles(1000.0), DELTA);
        assertEquals(1272.57, unitConverter.kilometersToMiles(2048), DELTA);
    }

    @Test
    public void shouldTestPoundsToKilograms() {
        assertEquals(4.54, unitConverter.poundsToKilograms(10.0), DELTA);
        assertEquals(13.61, unitConverter.poundsToKilograms(30.0), DELTA);
        assertEquals(90.72, unitConverter.poundsToKilograms(200.0), DELTA);
    }

    @Test
    public void shouldTestKilogramsToPounds() {
        assertEquals(22.05, unitConverter.kilogramsToPounds(10.0), DELTA);
        assertEquals(198.42, unitConverter.kilogramsToPounds(90.0), DELTA);
        assertEquals(132.28, unitConverter.kilogramsToPounds(60.0), DELTA);
    }

}