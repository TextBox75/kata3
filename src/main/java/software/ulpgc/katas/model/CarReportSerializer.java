package software.ulpgc.katas.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CarReportSerializer {

    private final Gson gson;

    public CarReportSerializer() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String SerializeReport(CarReport carReport) {
        return gson.toJson(carReport);
    }
}
