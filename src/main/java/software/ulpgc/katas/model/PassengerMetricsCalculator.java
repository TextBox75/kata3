package software.ulpgc.katas.model;

import java.util.List;

public class PassengerMetricsCalculator {

    List<Passenger> Passengers;

    public PassengerMetricsCalculator(List<Passenger> Passengers) {
        this.Passengers = Passengers;
    }

    public PassengerMetricsResult calculateMetrics() {
        return new PassengerMetricsResult(
                CalculateSurvived(),
                CalculateAverageAge()
        );
    }

    private int CalculateSurvived() {
        return (int) Passengers.stream().filter(Passenger -> Passenger.survived() == 1).count();
    }

    private float CalculateAverageAge() {
        return (float) Passengers.stream().mapToDouble(Passenger::age).average().orElse(0);
    }
}