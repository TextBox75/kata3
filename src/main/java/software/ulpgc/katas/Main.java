package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONFileCarReader;
import software.ulpgc.katas.model.CarReportSerializer;
import software.ulpgc.katas.adapters.FileCarReportWriter;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarMetricsCalculator;
import software.ulpgc.katas.model.CarMetricsResult;
import software.ulpgc.katas.model.CarReport;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarReportWriter;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        CarReader reader = new FileCarReader(new File("./io/car.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator metricsCalculator = new CarMetricsCalculator(cars);
        CarMetricsResult metricsResult = metricsCalculator.calculateMetrics();

        CarReport report = new CarReport(metricsResult, cars);

        CarReportSerializer serializer = new CarReportSerializer();
        String serializedReports = serializer.SerializeReport(report);

        CarReportWriter writer = new FileCarReportWriter("./io/report.json");
        writer.WriteCars(serializedReports);

        // =============================================================

        JSONFileCarReader jsonReader = new JSONFileCarReader("./io/report.json");
        List<Car> jsonCars = jsonReader.ReadAllCars();
        System.out.println(jsonCars.size());

        CarMetricsCalculator jsonMetricsCalculator = new CarMetricsCalculator(jsonCars);
        CarMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averageKilometers());
    }
}