package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FilePassengerReader;
import software.ulpgc.katas.adapters.JSONPassengerReportReader;
import software.ulpgc.katas.adapters.JSONPassengerReportWriter;
import software.ulpgc.katas.io.URLPassengerReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.PassengerReader;
import software.ulpgc.katas.ports.PassengerWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONSerializerTest {
    @Test
    public void testJSONSerializer() {
        PassengerReader reader = new FilePassengerReader(new File("./io/Titanic.csv"));
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator calculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult result = calculator.calculateMetrics();

        PassengerReport report = new PassengerReport(result, Passengers);
        PassengerReportSerializer serializer = new PassengerReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        PassengerSerializedReport serializedReport = new PassengerSerializedReport("./io/report.json", serializedString);

        PassengerWriter writer = new JSONPassengerReportWriter(serializedReport);
        writer.WritePassengers();

        JSONPassengerReportReader JSONreader = new JSONPassengerReportReader("./io/report.json");
        PassengerReport readPassengers = JSONreader.ReadPassengerReport();

        assertEquals(readPassengers.passengers().size(), Passengers.size());
    }

    @Test
    public void testJSONMalformedRow() {
        PassengerReader reader = new FilePassengerReader(new File("./io/Titanic-malformed-row.csv"));
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator calculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult result = calculator.calculateMetrics();

        PassengerReport report = new PassengerReport(result, Passengers);
        PassengerReportSerializer serializer = new PassengerReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        PassengerSerializedReport serializedReport = new PassengerSerializedReport("./io/report.json", serializedString);

        PassengerWriter writer = new JSONPassengerReportWriter(serializedReport);
        writer.WritePassengers();

        JSONPassengerReportReader JSONreader = new JSONPassengerReportReader("./io/report.json");
        PassengerReport readPassengers = JSONreader.ReadPassengerReport();

        assertEquals(readPassengers.passengers().size(), Passengers.size());
    }

    @Test
    public void testJSONEmptyDataset() {
        PassengerReader reader = new FilePassengerReader(new File("./io/empty-file.csv"));
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator calculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult result = calculator.calculateMetrics();

        PassengerReport report = new PassengerReport(result, Passengers);
        PassengerReportSerializer serializer = new PassengerReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        PassengerSerializedReport serializedReport = new PassengerSerializedReport("./io/report.json", serializedString);

        PassengerWriter writer = new JSONPassengerReportWriter(serializedReport);
        writer.WritePassengers();

        JSONPassengerReportReader JSONreader = new JSONPassengerReportReader("./io/report.json");
        PassengerReport readPassengers = JSONreader.ReadPassengerReport();

        assertEquals(readPassengers.passengers().size(), Passengers.size());
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        PassengerReader reader = new URLPassengerReader(new URL("https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/Titanic.csv"));
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator calculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult result = calculator.calculateMetrics();

        PassengerReport report = new PassengerReport(result, Passengers);
        PassengerReportSerializer serializer = new PassengerReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        PassengerSerializedReport serializedReport = new PassengerSerializedReport("./io/report.json", serializedString);

        PassengerWriter writer = new JSONPassengerReportWriter(serializedReport);
        writer.WritePassengers();

        JSONPassengerReportReader JSONreader = new JSONPassengerReportReader("./io/report.json");
        PassengerReport readPassengers = JSONreader.ReadPassengerReport();

        assertEquals(readPassengers.passengers().size(), Passengers.size());
    }
}