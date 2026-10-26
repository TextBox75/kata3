package software.ulpgc.katas.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PassengerReportSerializer {

    private final PassengerReport report;
    private final Gson gson;

    public PassengerReportSerializer(PassengerReport report) {
        this.report = report;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String SerializeReport() {
        return gson.toJson(this.report);
    }
}