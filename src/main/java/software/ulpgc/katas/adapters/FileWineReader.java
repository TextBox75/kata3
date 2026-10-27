package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.Wine;
import software.ulpgc.katas.model.WineQuality;
import software.ulpgc.katas.model.WineType;
import software.ulpgc.katas.ports.WineReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record FileWineReader(File WineFile) implements WineReader {

    @Override
    public List<Wine> ReadAllWines() {
        try (FileInputStream is = new FileInputStream(WineFile)) {
            List<Wine> Wines = ReadLines(is);
            is.close();
            return Wines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Wine> ReadLines(FileInputStream stream) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(stream)) {
            return ReadLines(new BufferedReader(isr));
        }
    }

    private List<Wine> ReadLines(Reader reader) throws IOException {
        return ReadLines(reader.readAllLines());
    }

    private List<Wine> ReadLines(List<String> lines) {
        List<Wine> Wines = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            try {
                Wines.add(ReadLines(lines.get(i)));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        return Wines;
    }

    private Wine ReadLines(String line) {
        return ReadLines(line.split(","));
    }

    private Wine ReadLines(String[] fields) {
        return new Wine(
                Float.parseFloat(fields[8]),
                Float.parseFloat(fields[10]),
                WineQuality.valueOf(fields[11]),
                WineType.valueOf(fields[12])
        );
    }
}