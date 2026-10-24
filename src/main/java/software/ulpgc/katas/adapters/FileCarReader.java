package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.Fuel;
import software.ulpgc.katas.model.Transmission;
import software.ulpgc.katas.ports.CarReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record FileCarReader(File carFile) implements CarReader {

    @Override
    public List<Car> ReadAllCars() {
        try (FileInputStream is = new FileInputStream(carFile)) {
            List<Car> cars = ReadLines(is);
            is.close();
            return cars;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Car> ReadLines(FileInputStream stream) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(stream)) {
            return ReadLines(new BufferedReader(isr));
        }
    }

    private List<Car> ReadLines(Reader reader) throws IOException {
        return ReadLines(reader.readAllLines());
    }

    private List<Car> ReadLines(List<String> lines) {
        List<Car> cars = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            try {
                cars.add(ReadLines(lines.get(i)));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        return cars;
    }

    private Car ReadLines(String line) {
        return ReadLines(line.split(","));
    }

    private Car ReadLines(String[] fields) {
        return new Car(
                fields[0],
                Integer.parseInt(fields[1]),
                Integer.parseInt(fields[4]),
                Fuel.valueOf(fields[5]),
                Transmission.valueOf(fields[7])
        );
    }
}