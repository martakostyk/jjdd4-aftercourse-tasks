package martak.jjdd4.aftercourse.model;

public class UnitConverterImpl implements UnitConverter{

    private static final double LENGTH_CONVERTER = 0.62137;

    @Override
    public double celsiusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    @Override
    public double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }

    @Override
    public double milesToKilometers(double m) {
        return m / LENGTH_CONVERTER;
    }

    @Override
    public double kilometersToMiles(double k) {
        return k * LENGTH_CONVERTER;
    }

    @Override
    public double poundsToKilograms(double p) {
        return p * 0.4535;
    }

    @Override
    public double kilogramsToPounds(double k) {
        return k * 2.2046;
    }
}
