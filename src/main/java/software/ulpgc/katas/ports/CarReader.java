package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarReport;

import java.util.List;

public interface CarReader {
    List<Car> ReadAllCars();
}
