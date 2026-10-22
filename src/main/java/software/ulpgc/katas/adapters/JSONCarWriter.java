package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import software.ulpgc.katas.model.Car;
import software.ulpgc.katas.ports.CarWriter;

public class JSONCarWriter implements CarWriter {

    private final String fileName;
    private final Gson gson;

    public JSONCarWriter(String fileName) {
        this.fileName = fileName;
        this.gson = new Gson();
    }

    @Override
    public void WriteCar(Car car) {
        String string = gson.toJson(car);
        System.out.println(string);
    }
}
