package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarReportReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.adapters.JSONCarReportWriter;
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

        CarReportSerializer serializer = new CarReportSerializer(report);
        String serializedReportsString = serializer.SerializeReport();

        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedReportsString);

        CarReportWriter writer = new JSONCarReportWriter(serializedReport);
        writer.WriteCars();

        // =============================================================

        JSONCarReportReader jsonReader = new JSONCarReportReader("./io/report.json");
        CarReport jsonCars = jsonReader.ReadAllCars();
        /*System.out.println(jsonCars.size());

        CarMetricsCalculator jsonMetricsCalculator = new CarMetricsCalculator(jsonCars);
        CarMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averageKilometers());*/
    }
}