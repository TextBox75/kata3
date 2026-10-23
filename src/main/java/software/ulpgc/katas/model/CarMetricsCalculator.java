package software.ulpgc.katas.model;

import java.util.List;

import static software.ulpgc.katas.model.Fuel.Petrol;
import static software.ulpgc.katas.model.Transmission.Manual;

public class CarMetricsCalculator {

    List<Car> cars;

    public CarMetricsCalculator(List<Car> cars) {
        this.cars = cars;
    }

    public CarMetricsResult calculateMetrics() {
        return new CarMetricsResult(
                CalculateManuals(),
                CalculatePetrolCars(),
                CalculateAverageKilometers()
        );
    }

    private int CalculatePetrolCars() {
        return (int) cars.stream().filter(car -> car.fuelType() == Petrol).count();
    }

    private int CalculateManuals()  {
        return (int) cars.stream().filter(car -> car.transmissionType() == Manual).count();
    }

    private float CalculateAverageKilometers() {
        return (float) cars.stream().mapToInt(Car::kilometers).average().orElse(0);
    }
}
