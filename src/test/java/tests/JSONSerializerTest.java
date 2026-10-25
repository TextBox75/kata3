package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarReportReader;
import software.ulpgc.katas.adapters.JSONCarReportWriter;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarReportWriter;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONSerializerTest {
    @Test
    public void testJSONSerializer() {
        CarReader reader = new FileCarReader(new File("./io/car.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator calculator = new CarMetricsCalculator(cars);
        CarMetricsResult result = calculator.calculateMetrics();

        CarReport report = new CarReport(result, cars);
        CarReportSerializer serializer = new CarReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedString);

        CarReportWriter writer = new JSONCarReportWriter(serializedReport);
        writer.WriteCars();

        CarReader JSONreader = new JSONCarReportReader("./io/report.json");
        CarReport readCars = JSONreader.ReadAllCars();
    }
}