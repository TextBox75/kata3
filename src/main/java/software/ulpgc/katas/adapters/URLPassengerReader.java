package software.ulpgc.katas.io;

import software.ulpgc.katas.model.Gender;
import software.ulpgc.katas.model.Passenger;
import software.ulpgc.katas.model.PassengerClass;
import software.ulpgc.katas.ports.PassengerReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLPassengerReader implements PassengerReader {

    URL url;

    public URLPassengerReader(URL url) {
        this.url = url;
    }

    @Override
    public List<Passenger> ReadAllPassengers() {
        try {
            return ReadLines(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Passenger> ReadLines(InputStream stream) throws IOException {
        try (Reader reader = new InputStreamReader(stream)) {
            return ReadLines(reader);
        }
    }

    private List<Passenger> ReadLines(Reader reader) throws IOException {
        return ReadLines(reader.readAllLines());
    }

    private List<Passenger> ReadLines(List<String> lines) {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            try {
                passengers.add(ReadLines(lines.get(i)));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return passengers;
    }

    private Passenger ReadLines(String line) {
        String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (fields[2].isEmpty()) fields[2] = "0.0";
        return ReadLines(fields);
    }

    private Passenger ReadLines(String[] fields) {
        return new Passenger(
                fields[0],
                PassengerClass.fromString(fields[1]),
                Float.parseFloat(fields[2]),
                Gender.valueOf(fields[3]),
                Integer.parseInt(fields[4])
        );
    }
}