package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import software.ulpgc.katas.model.Car;

public class JSONSerializer {

    private final Gson gson;

    public JSONSerializer() {
        this.gson = new Gson();
    }

    public void SerializeCar(Car car) {
        String string = gson.toJson(car);
        System.out.println(string);
    }
}
