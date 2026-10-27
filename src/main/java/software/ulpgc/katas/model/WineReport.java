package software.ulpgc.katas.model;

import java.util.List;

public record WineReport(WineMetricsResult metrics, List<Wine> Wines) { }
