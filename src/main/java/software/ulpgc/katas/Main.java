package software.ulpgc.katas;

import software.ulpgc.katas.adapters.*;
import software.ulpgc.katas.model.CarMetrics;
import software.ulpgc.katas.ports.*;
import software.ulpgc.katas.model.Car;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    static void main() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/sachin365123/CSV-files-for-Data-Science-and-Machine-Learning/refs/heads/main/car.csv");
        CarReader urlReader = new URLCarReader(url);
        CarReader fileReader = new FileCarReader(new File("./io/car.csv"));

        List<Car> urlCars = urlReader.ReadAllCars();
        List<Car> fileCars = fileReader.ReadAllCars();

        CarMetrics urlMetrics = new CarMetrics(urlCars);
        CarMetrics fileMetrics = new CarMetrics(fileCars);

        CarWriter urlConsoleWriter = new ConsoleCarWriter();
        CarWriter urlFileWriter = new FileCarWriter("./io/url-car-output.txt");

        CarWriter fileConsoleWriter = new ConsoleCarWriter();
        CarWriter fileFileWriter = new FileCarWriter("./io/file-car-output.txt");

        int urlManuals = urlMetrics.countManuals();
        int fileManuals = fileMetrics.countManuals();

        urlConsoleWriter.WriteLine(urlManuals + " Manuals");
        fileConsoleWriter.WriteLine(fileManuals + " Manuals");

        urlFileWriter.WriteLine(urlManuals + " Manuals");
        fileFileWriter.WriteLine(fileManuals + " Manuals");
    }
}