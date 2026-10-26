package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.Gender;
import software.ulpgc.katas.model.Passenger;
import software.ulpgc.katas.model.PassengerClass;
import software.ulpgc.katas.ports.PassengerReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePassengerReader implements PassengerReader {

    private final File passengerFile;

    public FilePassengerReader(File passengerFile) {
        this.passengerFile = passengerFile;
    }

    @Override
    public List<Passenger> ReadAllPassengers() {
        try (FileInputStream is = new FileInputStream(passengerFile)) {
            List<Passenger> Passengers = ReadLines(is);
            is.close();
            return Passengers;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Passenger> ReadLines(FileInputStream stream) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(stream)) {
            return ReadLines(new BufferedReader(isr));
        }
    }

    private List<Passenger> ReadLines(Reader reader) throws IOException {
        return ReadLines(reader.readAllLines());
    }

    private List<Passenger> ReadLines(List<String> lines) {
        List<Passenger> Passengers = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Passengers.add(ReadLines(lines.get(i)));
        }

        return Passengers;
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