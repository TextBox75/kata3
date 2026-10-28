package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FilePassengerReader;
import software.ulpgc.katas.adapters.JSONPassengerReportReader;
import software.ulpgc.katas.adapters.JSONPassengerReportWriter;
import software.ulpgc.katas.model.*;
import software.ulpgc.katas.ports.PassengerReader;
import software.ulpgc.katas.ports.PassengerReportWriter;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        PassengerReader reader = new FilePassengerReader(new File("./io/Titanic.csv"));
        List<Passenger> Passengers = reader.ReadAllPassengers();

        PassengerMetricsCalculator metricsCalculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult metricsResult = metricsCalculator.calculateMetrics();

        PassengerReport report = new PassengerReport(metricsResult, Passengers);

        PassengerReportSerializer serializer = new PassengerReportSerializer(report);
        String serializedReportsString = serializer.SerializeReport();

        PassengerSerializedReport serializedReport = new PassengerSerializedReport("./io/report.json", serializedReportsString);

        PassengerReportWriter writer = new JSONPassengerReportWriter(serializedReport);
        writer.WritePassengers();

        JSONPassengerReportReader jsonReader = new JSONPassengerReportReader("./io/report.json");
        PassengerReport jsonPassengers = jsonReader.ReadPassengerReport();
        System.out.println(jsonPassengers.passengers().size());

        PassengerMetricsCalculator jsonMetricsCalculator = new PassengerMetricsCalculator(Passengers);
        PassengerMetricsResult jsonMetricsResult = jsonMetricsCalculator.calculateMetrics();

        System.out.println(jsonMetricsResult.averageAge());
    }
}