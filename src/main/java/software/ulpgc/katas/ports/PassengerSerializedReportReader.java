package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.PassengerReport;

public interface PassengerSerializedReportReader {
    PassengerReport ReadPassengerSerializedReport();
}
