package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarMetricsResult;
import software.ulpgc.katas.model.CarReport;
import software.ulpgc.katas.ports.CarReader;
import software.ulpgc.katas.ports.CarReportReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record JSONCarReportReader(String fileName) implements CarReportReader {

    @Override
    public CarReport ReadCarReport() {
        try (FileInputStream is = new FileInputStream(fileName)) {
            CarReport report = ReadCarReport(is);
            is.close();
            return report;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CarReport ReadCarReport(FileInputStream is) {
        try {
            return ReadCarReport(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CarReport ReadCarReport(byte[] bytes) {
        return ReadCarReport(new String(bytes));
    }

    private CarReport ReadCarReport(String string) {
        return new CarReport(
                ReadCarReport(new Gson().fromJson(string, JsonObject.class).get("metrics").getAsJsonObject()),
                ReadCarReport(new Gson().fromJson(string, JsonObject.class).get("cars").getAsJsonArray())
        );
    }

    private CarMetricsResult ReadCarReport(JsonObject object) {
        return new Gson().fromJson(object, CarMetricsResult.class);
    }

    private List<Car> ReadCarReport(JsonArray jsonArray) {
        List<Car> cars = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            cars.add(ReadCarReport(jsonElement));
        }
        return cars;
    }

    private Car ReadCarReport(JsonElement jsonElement) {
        return new Gson().fromJson(jsonElement, Car.class);
    }
}
