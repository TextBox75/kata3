package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.WineSerializedReport;
import software.ulpgc.katas.ports.WineSerializedReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONWineSerializedReportWriter(WineSerializedReport serializedReport) implements WineSerializedReportWriter {

    @Override
    public void WriteWineSerializedReport() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}