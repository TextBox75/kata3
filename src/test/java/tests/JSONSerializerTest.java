package tests;

import org.junit.jupiter.api.Test;
import software.ulpgc.katas.adapters.FileWineReader;
import software.ulpgc.katas.adapters.JSONWineReportReader;
import software.ulpgc.katas.adapters.JSONWineReportWriter;
import software.ulpgc.katas.adapters.URLWineReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.WineReader;
import software.ulpgc.katas.ports.WineReportWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONSerializerTest {
    @Test
    public void testJSONSerializer() {
        WineReader reader = new FileWineReader(new File("./io/wine_fraud.csv"));
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator calculator = new WineMetricsCalculator(Wines);
        WineMetricsResult result = calculator.calculateMetrics();

        WineReport report = new WineReport(result, Wines);
        WineReportSerializer serializer = new WineReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        WineSerializedReport serializedReport = new WineSerializedReport("./io/report.json", serializedString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader JSONreader = new JSONWineReportReader("./io/report.json");
        WineReport readWines = JSONreader.ReadWineReport();

        assertEquals(readWines.Wines().size(), Wines.size());
    }

    @Test
    public void testJSONMalformedRow() {
        WineReader reader = new FileWineReader(new File("./io/wine-malformed-row.csv"));
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator calculator = new WineMetricsCalculator(Wines);
        WineMetricsResult result = calculator.calculateMetrics();

        WineReport report = new WineReport(result, Wines);
        WineReportSerializer serializer = new WineReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        WineSerializedReport serializedReport = new WineSerializedReport("./io/report.json", serializedString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader JSONreader = new JSONWineReportReader("./io/report.json");
        WineReport readWines = JSONreader.ReadWineReport();

        assertEquals(readWines.Wines().size(), Wines.size());
    }

    @Test
    public void testJSONEmptyDataset() {
        WineReader reader = new FileWineReader(new File("./io/empty-file.csv"));
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator calculator = new WineMetricsCalculator(Wines);
        WineMetricsResult result = calculator.calculateMetrics();

        WineReport report = new WineReport(result, Wines);
        WineReportSerializer serializer = new WineReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        WineSerializedReport serializedReport = new WineSerializedReport("./io/report.json", serializedString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader JSONreader = new JSONWineReportReader("./io/report.json");
        WineReport readWines = JSONreader.ReadWineReport();

        assertEquals(readWines.Wines().size(), Wines.size());
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        WineReader reader = new URLWineReader(new URL("https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/wine_fraud.csv"));
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator calculator = new WineMetricsCalculator(Wines);
        WineMetricsResult result = calculator.calculateMetrics();

        WineReport report = new WineReport(result, Wines);
        WineReportSerializer serializer = new WineReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        WineSerializedReport serializedReport = new WineSerializedReport("./io/report.json", serializedString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader JSONreader = new JSONWineReportReader("./io/report.json");
        WineReport readWines = JSONreader.ReadWineReport();

        assertEquals(readWines.Wines().size(), Wines.size());
    }
}