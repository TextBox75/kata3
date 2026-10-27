package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.WineSerializedReport;
import software.ulpgc.katas.ports.WineReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONWineReportWriter(WineSerializedReport serializedReport) implements WineReportWriter {

    @Override
    public void WriteWines() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}