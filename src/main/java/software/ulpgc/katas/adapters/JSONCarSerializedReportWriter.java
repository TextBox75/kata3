package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.CarSerializedReport;
import software.ulpgc.katas.ports.CarSerializedReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONCarSerializedReportWriter(CarSerializedReport serializedReport) implements CarSerializedReportWriter {

    @Override
    public void WriteCarSerializedReport() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}