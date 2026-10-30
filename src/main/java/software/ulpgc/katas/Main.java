package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarSerializedReportReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.adapters.JSONCarSerializedReportWriter;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarSerializedReportWriter;

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
        String serializedReportString = serializer.SerializeReport();

        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedReportString);

        CarSerializedReportWriter writer = new JSONCarSerializedReportWriter(serializedReport);
        writer.WriteCarSerializedReport();

        JSONCarSerializedReportReader jsonReader = new JSONCarSerializedReportReader("./io/report.json");
        CarReport jsonCars = jsonReader.ReadCarSerializedReport();
        System.out.println(jsonCars.cars().size());

        CarMetricsCalculator jsonMetricsCalculator = new CarMetricsCalculator(cars);
        CarMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averageKilometers());
    }
}