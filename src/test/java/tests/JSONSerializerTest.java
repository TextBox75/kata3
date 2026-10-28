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
    private static final String REPORT_PATH = "./io/report.json";

    @Test
    public void testJSONSerializer() {
        assertSerializer(new FileWineReader(new File("./io/wine_fraud.csv")));
    }

    @Test
    public void testJSONMalformedRow() {
        assertSerializer(new FileWineReader(new File("./io/wine-malformed-row.csv")));
    }

    @Test
    public void testJSONEmptyDataset() {
        assertSerializer(new FileWineReader(new File("./io/empty-file.csv")));
    }

    @Test
    public void testJSONURLDataset() throws MalformedURLException {
        assertSerializer(new URLWineReader(new URL(
                "https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/wine_fraud.csv"
        )));
    }

    private void assertSerializer(WineReader reader) {
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator calculator = new WineMetricsCalculator(Wines);
        WineMetricsResult result = calculator.calculateMetrics();

        WineReport report = new WineReport(result, Wines);
        WineReportSerializer serializer = new WineReportSerializer(report);

        String serializedString = serializer.SerializeReport();
        WineSerializedReport serializedReport = new WineSerializedReport(REPORT_PATH, serializedString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader jsonReader = new JSONWineReportReader(REPORT_PATH);
        WineReport readWines = jsonReader.ReadWineReport();

        assertEquals(Wines.size(), readWines.Wines().size());
        assertEquals(result.legitWines(), readWines.metrics().legitWines());
        assertEquals(result.averagePH(), readWines.metrics().averagePH());
        assertEquals(result.redWines(), readWines.metrics().redWines());
    }
}