package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.model.Passenger;
import software.ulpgc.katas.model.PassengerMetricsResult;
import software.ulpgc.katas.model.PassengerReport;
import software.ulpgc.katas.ports.PassengerSerializedReportReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record JSONPassengerSerializedReportReader(String fileName) implements PassengerSerializedReportReader {

    @Override
    public PassengerReport ReadPassengerSerializedReport() {
        try (FileInputStream is = new FileInputStream(fileName)) {
            PassengerReport report = ReadPassengerReport(is);
            is.close();
            return report;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PassengerReport ReadPassengerReport(FileInputStream is) {
        try {
            return ReadPassengerReport(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PassengerReport ReadPassengerReport(byte[] bytes) {
        return ReadPassengerReport(new String(bytes));
    }

    private PassengerReport ReadPassengerReport(String string) {
        return new PassengerReport(
                ReadPassengerReport(new Gson().fromJson(string, JsonObject.class).get("metrics").getAsJsonObject()),
                ReadPassengerReport(new Gson().fromJson(string, JsonObject.class).get("passengers").getAsJsonArray())
        );
    }

    private PassengerMetricsResult ReadPassengerReport(JsonObject object) {
        return new Gson().fromJson(object, PassengerMetricsResult.class);
    }

    private List<Passenger> ReadPassengerReport(JsonArray jsonArray) {
        List<Passenger> Passengers = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            Passengers.add(ReadPassengerReport(jsonElement));
        }
        return Passengers;
    }

    private Passenger ReadPassengerReport(JsonElement jsonElement) {
        return new Gson().fromJson(jsonElement, Passenger.class);
    }
}