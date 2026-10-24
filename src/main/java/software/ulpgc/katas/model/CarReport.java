package software.ulpgc.katas.model;

import java.util.List;

public record CarReport(CarMetricsResult metrics, List<Car> cars) { }
