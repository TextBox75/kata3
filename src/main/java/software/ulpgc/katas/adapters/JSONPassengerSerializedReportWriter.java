package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.PassengerSerializedReport;
import software.ulpgc.katas.ports.PassengerSerializedReportWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONPassengerSerializedReportWriter(PassengerSerializedReport serializedReport) implements PassengerSerializedReportWriter {

    @Override
    public void WritePassengerSerializedReport() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}