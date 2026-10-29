package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.WineReport;

public interface WineSerializedReportReader {
    WineReport ReadWineSerializedReport();
}
