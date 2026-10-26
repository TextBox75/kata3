package software.ulpgc.katas.model;

import java.util.List;

public record PassengerReport(PassengerMetricsResult metrics, List<Passenger> passengers) { }