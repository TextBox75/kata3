package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.CarSerializer;
import software.ulpgc.katas.adapters.FileCarWriter;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarMetricsCalculator;
import software.ulpgc.katas.model.CarMetricsResult;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarWriter;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        CarReader reader = new FileCarReader(new File("./io/car.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator metricsCalculator = new CarMetricsCalculator(cars);
        CarMetricsResult metricsResult = metricsCalculator.calculateMetrics();

        CarSerializer serializer = new CarSerializer();
        List<String> serializedCars = serializer.SerializeCars(metricsResult, cars);

        CarWriter writer = new FileCarWriter("./io/serialized-cars.txt");
        writer.WriteCars(serializedCars);
    }
}