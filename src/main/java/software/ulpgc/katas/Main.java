package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileWineReader;
import software.ulpgc.katas.adapters.JSONWineReportReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.adapters.JSONWineReportWriter;
import software.ulpgc.katas.ports.WineReader;
import software.ulpgc.katas.ports.WineReportWriter;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        WineReader reader = new FileWineReader(new File("./io/Wine.csv"));
        List<Wine> Wines = reader.ReadAllWines();

        WineMetricsCalculator metricsCalculator = new WineMetricsCalculator(Wines);
        WineMetricsResult metricsResult = metricsCalculator.calculateMetrics();

        WineReport report = new WineReport(metricsResult, Wines);

        WineReportSerializer serializer = new WineReportSerializer(report);
        String serializedReportsString = serializer.SerializeReport();

        WineSerializedReport serializedReport = new WineSerializedReport("./io/report.json", serializedReportsString);

        WineReportWriter writer = new JSONWineReportWriter(serializedReport);
        writer.WriteWines();

        JSONWineReportReader jsonReader = new JSONWineReportReader("./io/report.json");
        WineReport jsonWines = jsonReader.ReadWineReport();
        System.out.println(jsonWines.Wines().size());

        WineMetricsCalculator jsonMetricsCalculator = new WineMetricsCalculator(Wines);
        WineMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averageKilometers());
    }
}