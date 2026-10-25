package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarMetricsResult;
import software.ulpgc.katas.model.CarReport;
import software.ulpgc.katas.ports.CarReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record JSONCarReportReader(String fileName) implements CarReader {
    @Override
    public CarReport ReadAllCars() {
        try (FileInputStream is = new FileInputStream(fileName)) {
            CarReport report = ReadAllCars(is);
            is.close();
            return report;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CarReport ReadAllCars(FileInputStream is) {
        try {
            return ReadAllCars(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CarReport ReadAllCars(byte[] bytes) {
        return ReadAllCars(new String(bytes));
    }

    private CarReport ReadAllCars(String string) {
        return new CarReport(
                ReadAllCars(new Gson().fromJson(string, JsonObject.class).get("metrics").getAsJsonObject()),
                ReadAllCars(new Gson().fromJson(string, JsonObject.class).get("cars").getAsJsonArray())
        );
    }

    private CarMetricsResult ReadAllCars(JsonObject object) {
        return new Gson().fromJson(object, CarMetricsResult.class);
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
