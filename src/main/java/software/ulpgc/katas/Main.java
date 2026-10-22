package software.ulpgc.katas;

import software.ulpgc.katas.adapters.FileCarReader;
import software.ulpgc.katas.adapters.JSONCarWriter;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarWriter;

import java.io.File;
import java.util.List;

public class Main {
    static void main() {
        CarReader reader = new FileCarReader(new File("./io/car.csv"));
        List<Car> cars = reader.ReadAllCars();
        CarWriter writer = new JSONCarWriter("json");
        writer.WriteCar(cars.get(0));
    }
}