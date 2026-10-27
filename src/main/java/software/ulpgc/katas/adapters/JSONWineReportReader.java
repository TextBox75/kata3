package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.model.Wine;
import software.ulpgc.katas.model.WineMetricsResult;
import software.ulpgc.katas.model.WineReport;
import software.ulpgc.katas.ports.WineReader;
import software.ulpgc.katas.ports.WineReportReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record JSONWineReportReader(String fileName) implements WineReportReader {

    @Override
    public WineReport ReadWineReport() {
        try (FileInputStream is = new FileInputStream(fileName)) {
            WineReport report = ReadWineReport(is);
            is.close();
            return report;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private WineReport ReadWineReport(FileInputStream is) {
        try {
            return ReadWineReport(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private WineReport ReadWineReport(byte[] bytes) {
        return ReadWineReport(new String(bytes));
    }

    private WineReport ReadWineReport(String string) {
        return new WineReport(
                ReadWineReport(new Gson().fromJson(string, JsonObject.class).get("metrics").getAsJsonObject()),
                ReadWineReport(new Gson().fromJson(string, JsonObject.class).get("Wines").getAsJsonArray())
        );
    }

    private WineMetricsResult ReadWineReport(JsonObject object) {
        return new Gson().fromJson(object, WineMetricsResult.class);
    }

    private List<Wine> ReadWineReport(JsonArray jsonArray) {
        List<Wine> Wines = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            Wines.add(ReadWineReport(jsonElement));
        }
        return Wines;
    }

    private Wine ReadWineReport(JsonElement jsonElement) {
        return new Gson().fromJson(jsonElement, Wine.class);
    }
}
