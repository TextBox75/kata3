package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.model.CarMetricsResult;

import java.util.ArrayList;
import java.util.List;

public class CarSerializer {

    private final Gson gson;

    public CarSerializer() {
        this.gson = new Gson();
    }

    public List<String> SerializeCars(CarMetricsResult result, List<Car> cars) {
        List<String> serializedCars = new ArrayList<>();
        serializedCars.add(SerializeCars(result));

        for (Car car : cars) {
            serializedCars.add(SerializeCars(car));
        }

        return serializedCars;
    }


    public String SerializeCars(Object object) {
        return gson.toJson(object);
    }
}
