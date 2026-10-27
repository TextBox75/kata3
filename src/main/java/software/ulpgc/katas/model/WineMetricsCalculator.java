package software.ulpgc.katas.model;

import java.util.List;

import static software.ulpgc.katas.model.WineQuality.Legit;
import static software.ulpgc.katas.model.WineType.red;

public class WineMetricsCalculator {

    List<Wine> Wines;

    public WineMetricsCalculator(List<Wine> Wines) {
        this.Wines = Wines;
    }

    public WineMetricsResult calculateMetrics() {
        return new WineMetricsResult(
                CalculateRedWines(),
                CalculateRedWines(),
                CalculateAveragePH()
        );
    }

    private int CalculateRedWines() {
        return (int) Wines.stream().filter(Wine -> Wine.type() == red).count();
    }

    private int CalculateManuals()  {
        return (int) Wines.stream().filter(Wine -> Wine.quality() == Legit).count();
    }

    private float CalculateAveragePH() {
        return (float) Wines.stream().mapToDouble(Wine::ph).average().orElse(0);
    }
}
