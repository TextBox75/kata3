package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.Wine;
import software.ulpgc.katas.model.WineQuality;
import software.ulpgc.katas.model.WineType;
import software.ulpgc.katas.ports.WineReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLWineReader implements WineReader {

    URL url;

    public URLWineReader(URL url) {
        this.url = url;
    }

    @Override
    public List<Wine> ReadAllWines() {
        try {
            return ReadLines(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Wine> ReadLines(InputStream stream) throws IOException {
        try (Reader reader = new InputStreamReader(stream)) {
            return ReadLines(reader);
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