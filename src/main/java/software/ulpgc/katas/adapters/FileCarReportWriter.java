package software.ulpgc.katas.adapters;

import software.ulpgc.katas.ports.CarReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record FileCarReportWriter(String fileName) implements CarReportWriter {

    @Override
    public void WriteCars(String serializedReport) {
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            fos.write(serializedReport.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}