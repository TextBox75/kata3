package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.ports.CarReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record JSONFileCarReader(String fileName) implements CarReader {
    @Override
    public List<Car> ReadAllCars() {
        try (FileInputStream is = new FileInputStream(fileName)) {
            List<Car> cars = ReadAllCars(is);
            is.close();
            return cars;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Car> ReadAllCars(FileInputStream is) {
        try {
            return ReadAllCars(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Car> ReadAllCars(byte[] bytes) {
        return ReadAllCars(new String(bytes));
    }

    private List<Car> ReadAllCars(String string) {
        return ReadAllCars(new Gson().fromJson(string, JsonObject.class).get("cars").getAsJsonArray());
    }

    private List<Car> ReadAllCars(JsonArray jsonArray) {
        List<Car> cars = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            cars.add(ReadAllCars(jsonElement));
        }
        return cars;
    }

    private Car ReadAllCars(JsonElement jsonElement) {
        return new Gson().fromJson(jsonElement, Car.class);
    }
}
