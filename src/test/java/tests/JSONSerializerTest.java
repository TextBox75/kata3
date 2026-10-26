package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarReportReader;
import software.ulpgc.katas.adapters.JSONCarReportWriter;
import software.ulpgc.katas.adapters.URLCarReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarReportWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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

        JSONCarReportReader JSONreader = new JSONCarReportReader("./io/report.json");
        CarReport readCars = JSONreader.ReadCarReport();

        assertEquals(readCars.cars().size(), cars.size());
    }

    @Test
    public void testJSONMalformedRow() {
        CarReader reader = new FileCarReader(new File("./io/malformed-row.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator calculator = new CarMetricsCalculator(cars);
        CarMetricsResult result = calculator.calculateMetrics();

        CarReport report = new CarReport(result, cars);
        CarReportSerializer serializer = new CarReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedString);

        CarReportWriter writer = new JSONCarReportWriter(serializedReport);
        writer.WriteCars();

        JSONCarReportReader JSONreader = new JSONCarReportReader("./io/report.json");
        CarReport readCars = JSONreader.ReadCarReport();

        assertEquals(readCars.cars().size(), cars.size());
    }

    @Test
    public void testJSONEmptyDataset() {
        CarReader reader = new FileCarReader(new File("./io/empty-file.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator calculator = new CarMetricsCalculator(cars);
        CarMetricsResult result = calculator.calculateMetrics();

        CarReport report = new CarReport(result, cars);
        CarReportSerializer serializer = new CarReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedString);

        CarReportWriter writer = new JSONCarReportWriter(serializedReport);
        writer.WriteCars();

        JSONCarReportReader JSONreader = new JSONCarReportReader("./io/report.json");
        CarReport readCars = JSONreader.ReadCarReport();

        assertEquals(readCars.cars().size(), cars.size());
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        CarReader reader = new URLCarReader(new URL("https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/car.csv"));
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator calculator = new CarMetricsCalculator(cars);
        CarMetricsResult result = calculator.calculateMetrics();

        CarReport report = new CarReport(result, cars);
        CarReportSerializer serializer = new CarReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        CarSerializedReport serializedReport = new CarSerializedReport("./io/report.json", serializedString);

        CarReportWriter writer = new JSONCarReportWriter(serializedReport);
        writer.WriteCars();

        JSONCarReportReader JSONreader = new JSONCarReportReader("./io/report.json");
        CarReport readCars = JSONreader.ReadCarReport();

        assertEquals(readCars.cars().size(), cars.size());
    }
}