package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.Passenger;

import java.util.List;

public interface PassengerReader {
    List<Passenger> ReadAllPassengers();
}
