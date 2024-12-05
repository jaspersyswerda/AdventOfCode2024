package adventofcode2024;

import java.util.List;

public class DayFour extends EveryDay{
    private static final String PATTERN = "XMAS";

    public static void main(String[] args) {
        List<String> input = readInputFile("DayFour.txt");
        input.forEach(System.out::println);
        System.out.println();
        int total = 0;
        total += countHorizontal(input);
        total += countVertical(input);
        total += countDiagonalNorthWestSouthEast(input);
        total += countDiagonalNorthEastSouthWest(input);
        System.out.println(total);
    }

    private static int countDiagonalNorthEastSouthWest(List<String> input) {
        int count = 0;
        int numberOfRows = input.size();
        int numberOfCols = input.get(0).length();
        //Rechts boven naar links onder, tot aan het midden
        System.out.println("Rechts boven naar links onder, tot aan het midden");
        for (int k = 0; k<numberOfRows;k++){
            StringBuilder diagonal = new StringBuilder();
            int rowIndex = k;
            int colIndex = numberOfCols - 1;
            while (rowIndex >= 0){
                diagonal.append(input.get(rowIndex).charAt(colIndex));
                rowIndex--;
                colIndex--;
            }
            System.out.println(diagonal);
            count += count(diagonal.toString());
            count += count(diagonal.reverse().toString());

        }
        System.out.println("Rechts boven naar links onder, vanaf het midden");
        for (int k = numberOfRows - 2; k>=0;k--){
            StringBuilder diagonal = new StringBuilder();
            int rowIndex = numberOfRows - 1;
            int colIndex = k;
            while (colIndex >= 0){
                diagonal.append(input.get(rowIndex).charAt(colIndex));
                rowIndex--;
                colIndex--;
            }
            System.out.println(diagonal);
            count += count(diagonal.toString());
            count += count(diagonal.reverse().toString());

        }

        return count;
    }

    private static int countDiagonalNorthWestSouthEast(List<String> input) {
        int count = 0;
        int numOfRows = input.size();
        int numOfCols = input.get(0).length();
        // Links boven naar rechts onder, tot aan het midden
        System.out.println("Links boven naar rechts onder, tot aan het midden");
        for (int k = 0; k < numOfRows; k++){
            StringBuilder diagonal = new StringBuilder();
            int rowIndex = k;
            int colIndex = 0;
            while (rowIndex >= 0){
                diagonal.append(input.get(rowIndex).charAt(colIndex));
                rowIndex--;
                colIndex++;
            }
            System.out.println(diagonal);
            count += count(diagonal.toString());
            count += count(diagonal.reverse().toString());

        }

        // Links boven naar rechts onder, vanaf het midden
        System.out.println("Links boven naar rechts onder, vanaf het midden");
        for (int k = 1; k < numOfRows; k++){
            StringBuilder diagonal = new StringBuilder();
            int rowIndex = numOfRows - 1;
            int colIndex = k;
            while (colIndex <= numOfCols - 1){
                diagonal.append(input.get(rowIndex).charAt(colIndex));
                rowIndex--;
                colIndex++;
            }
            System.out.println(diagonal);
            count += count(diagonal.toString());
            count += count(diagonal.reverse().toString());

        }
        return count;
    }

    private static int countVertical(List<String> input) {
        System.out.println("verticaal:");
        int count = 0;
        for (int col = 0; col < input.get(0).length(); col++) {
            StringBuilder vertical = new StringBuilder();
            for (String line : input){
                vertical.append(line.charAt(col));
            }
           System.out.println(vertical);
           count += count(vertical.toString());
           count += count(vertical.reverse().toString());

        }
        return count;
    }

    private static int countHorizontal(List<String> input) {
        System.out.println("Horizontalen: ");
        int count = 0;
        for (String line : input){
            System.out.println(line);
            count += count(line);
            count += count(new StringBuilder(line).reverse().toString());
        }
        return count;
    }

    private static int count(String line){
        int count = 0;
        int index = line.indexOf(PATTERN);
        while (index != -1){
            count++;
            index = line.indexOf(PATTERN, index + 1);
        }
        return count;
    }
}
