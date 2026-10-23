package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.CarMetricsResult;
import software.ulpgc.katas.ports.CarWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileCarWriter implements CarWriter {

    private final String fileName;

    public FileCarWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void WriteCars(List<String> serializedCars) {
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            for (String car : serializedCars) {
                fos.write((car + '\n').getBytes());
            }
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}