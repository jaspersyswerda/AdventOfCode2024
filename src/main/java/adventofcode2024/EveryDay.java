package adventofcode2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EveryDay {

    static List<String> readInputFile(String day) {
        List<String> list = new ArrayList<>();
        try {
            File file = new File("src/main/resources/2024/" + day);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                list.add(line);
            }
            scanner.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
