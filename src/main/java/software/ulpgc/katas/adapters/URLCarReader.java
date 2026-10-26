package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.Fuel;
import software.ulpgc.katas.model.Transmission;
import software.ulpgc.katas.ports.CarReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLCarReader implements CarReader {

    URL url;

    public URLCarReader(URL url) {
        this.url = url;
    }

    @Override
    public List<Car> ReadAllCars() {
        try {
            return ReadLines(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Car> ReadLines(InputStream stream) throws IOException {
        try (Reader reader = new InputStreamReader(stream)) {
            return ReadLines(reader);
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