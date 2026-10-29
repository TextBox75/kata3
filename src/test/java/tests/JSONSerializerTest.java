package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarSerializedReportReader;
import software.ulpgc.katas.adapters.JSONCarSerializedReportWriter;
import software.ulpgc.katas.adapters.URLCarReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarSerializedReportWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONSerializerTest {
    private static final String REPORT_PATH = "./io/report.json";

    @Test
    public void testJSONSerializer() {
        assertSerializer(new FileCarReader(new File("./io/car.csv")));
    }

    @Test
    public void testJSONMalformedRow() {
        assertSerializer(new FileCarReader(new File("./io/malformed-row.csv")));
    }

    @Test
    public void testJSONEmptyDataset() {
        assertSerializer(new FileCarReader(new File("./io/empty-file.csv")));
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        assertSerializer(new URLCarReader(new URL(
                "https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/car.csv"
        )));
    }

    private void assertSerializer(CarReader reader) {
        List<Car> cars = reader.ReadAllCars();

        CarMetricsCalculator calculator = new CarMetricsCalculator(cars);
        CarMetricsResult result = calculator.calculateMetrics();

        CarReport report = new CarReport(result, cars);
        CarReportSerializer serializer = new CarReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        CarSerializedReport serializedReport = new CarSerializedReport(REPORT_PATH, serializedString);

        CarSerializedReportWriter writer = new JSONCarSerializedReportWriter(serializedReport);
        writer.WriteCarSerializedReport();

        JSONCarSerializedReportReader jsonReader = new JSONCarSerializedReportReader(REPORT_PATH);
        CarReport readCars = jsonReader.ReadCarSerializedReport();

        assertEquals(cars.size(), readCars.cars().size());
        assertEquals(result.manuals(), readCars.metrics().manuals());
        assertEquals(result.averageKilometers(), readCars.metrics().averageKilometers());
        assertEquals(result.petrolCars(), readCars.metrics().petrolCars());
    }
}