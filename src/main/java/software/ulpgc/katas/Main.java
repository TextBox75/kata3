package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileWineReader;
import software.ulpgc.katas.adapters.JSONWineSerializedReportReader;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.adapters.JSONWineSerializedReportWriter;
import software.ulpgc.katas.ports.WineReader;
import software.ulpgc.katas.ports.WineSerializedReportWriter;

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

        WineSerializedReportWriter writer = new JSONWineSerializedReportWriter(serializedReport);
        writer.WriteWineSerializedReport();

        JSONWineSerializedReportReader jsonReader = new JSONWineSerializedReportReader("./io/report.json");
        WineReport jsonWines = jsonReader.ReadWineSerializedReport();
        System.out.println(jsonWines.Wines().size());

        WineMetricsCalculator jsonMetricsCalculator = new WineMetricsCalculator(Wines);
        WineMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averagePH());
    }
}