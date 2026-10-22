package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.Car;

import java.util.List;

public interface CarReader {
    List<Car> ReadAllCars();
}
