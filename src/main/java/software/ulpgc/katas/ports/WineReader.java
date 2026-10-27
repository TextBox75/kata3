package software.ulpgc.katas.ports;

import software.ulpgc.katas.model.Wine;
import software.ulpgc.katas.model.WineReport;

import java.util.List;

public interface WineReader {
    List<Wine> ReadAllWines();
}
