package software.ulpgc.katas.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CarReportSerializer {

    private final CarReport report;
    private final Gson gson;

    public CarReportSerializer(CarReport report) {
        this.report = report;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String SerializeReport() {
        return gson.toJson(this.report);
    }
}
