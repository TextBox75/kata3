package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONSerializer;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.ports.CarReader;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        CarReader reader = new FileCarReader(new File("./io/car.csv"));
        List<Car> cars = reader.ReadAllCars();
        JSONSerializer parser = new JSONSerializer();
        parser.SerializeCar(cars.getFirst());
    }
}