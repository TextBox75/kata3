package software.ulpgc.katas.adapters;

import software.ulpgc.katas.ports.CarWriter;

public class ConsoleCarWriter implements CarWriter {
    @Override
    public void WriteLine(String line) {
        System.out.println(line);
    }
}
