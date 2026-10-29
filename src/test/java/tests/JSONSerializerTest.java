package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FilePassengerReader;
import software.ulpgc.katas.adapters.JSONPassengerSerializedReportReader;
import software.ulpgc.katas.adapters.JSONPassengerSerializedReportWriter;
import software.ulpgc.katas.adapters.URLPassengerReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.PassengerReader;
import software.ulpgc.katas.ports.PassengerSerializedReportWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONSerializerTest {
    private static final String REPORT_PATH = "./io/report.json";

    @Test
    public void testJSONSerializer() {
        assertSerializer(new FilePassengerReader(new File("./io/Titanic.csv")));
    }

    @Test
    public void testJSONMalformedRow() {
        assertSerializer(new FilePassengerReader(new File("./io/Titanic-malformed-row.csv")));
    }

    @Test
    public void testJSONEmptyDataset() {
        assertSerializer(new FilePassengerReader(new File("./io/empty-file.csv")));
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        assertSerializer(new URLPassengerReader(new URL(
                "https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/Titanic.csv"
        )));
    }

    private void assertSerializer(PassengerReader reader) {
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator calculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult result = calculator.calculateMetrics();

        PassengerReport report = new PassengerReport(result, Passengers);
        PassengerReportSerializer serializer = new PassengerReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        PassengerSerializedReport serializedReport = new PassengerSerializedReport(REPORT_PATH, serializedString);

        PassengerSerializedReportWriter writer = new JSONPassengerSerializedReportWriter(serializedReport);
        writer.WritePassengerSerializedReport();

        JSONPassengerSerializedReportReader jsonReader = new JSONPassengerSerializedReportReader(REPORT_PATH);
        PassengerReport readPassengers = jsonReader.ReadPassengerSerializedReport();

        assertEquals(Passengers.size(), readPassengers.passengers().size());
        assertEquals(result.survived(), readPassengers.metrics().survived());
        assertEquals(result.averageAge(), readPassengers.metrics().averageAge());
    }
}