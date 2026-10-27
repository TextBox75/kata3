package software.ulpgc.katas.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WineReportSerializer {

    private final WineReport report;
    private final Gson gson;

    public WineReportSerializer(WineReport report) {
        this.report = report;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String SerializeReport() {
        return gson.toJson(this.report);
    }
}
