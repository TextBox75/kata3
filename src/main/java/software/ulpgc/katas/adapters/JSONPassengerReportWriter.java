package software.ulpgc.katas.adapters;

import software.ulpgc.katas.model.PassengerSerializedReport;
import software.ulpgc.katas.ports.PassengerWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public record JSONPassengerReportWriter(PassengerSerializedReport serializedReport) implements PassengerWriter {

    @Override
    public void WritePassengers() {
        try {
            FileOutputStream fos = new FileOutputStream(serializedReport.fileName());
            fos.write(serializedReport.data().getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}