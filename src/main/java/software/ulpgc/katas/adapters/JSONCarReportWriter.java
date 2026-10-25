package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.CarSerializedReport;
import software.ulpgc.katas.ports.CarReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONCarReportWriter(CarSerializedReport serializedReport) implements CarReportWriter {

    @Override
    public void WriteCars() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}