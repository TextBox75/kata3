package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.Car;

import java.util.List;

public interface CarWriter {
    void WriteCars(List<String> serializedCars);
}
