package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.CarReport;

public interface CarSerializedReportReader {
    CarReport ReadCarSerializedReport();
}
