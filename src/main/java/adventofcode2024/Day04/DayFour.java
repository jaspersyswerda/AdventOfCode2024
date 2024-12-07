package adventofcode2024.Day04;

import adventofcode2024.EveryDay;

import java.util.List;

public class DayFour extends EveryDay {

    public static void main(String[] args) {
        List<String> input = readInputFile("DayFour.txt");
        input.forEach(System.out::println);
        System.out.println();
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int total = 0;
        String pattern = "XMAS";
        total += countHorizontal(input, pattern);
        total += countVertical(input, pattern);
        total += countDiagonalNorthWestSouthEast(input, pattern);
        total += countDiagonalNorthEastSouthWest(input, pattern);
        System.out.println("Part 1: " + total);
    }

    private static void part2(List<String> input) {
        int total = 0;
        char pattern = 'A';
        for (int i = 1; i < input.size() - 1; i++) {
           for (int j = 1; j<input.get(0).length() - 1;j++){
               if (input.get(i).charAt(j) == pattern && checkAround(input, i, j)){
                   total++;
               }
           }
        }
        System.out.println("Part 2: " + total);
    }

    private static boolean checkAround(List<String> input, int i, int j) {
        // M . M   S . S  M . S  S . M
        // . A .   . A .  . A .  . A .
        // S . S   M . M  M . S  S . M
        return checkAround1(input, i, j)
                || checkAround2(input, i, j)
                || checkAround3(input, i, j)
                || checkAround4(input, i, j);
    }

    private static boolean checkAround3(List<String> input, int i, int j) {
        return input.get(i - 1).charAt(j - 1) == 'M'
                && input.get(i + 1).charAt(j + 1) == 'S'
                && input.get(i + 1).charAt(j - 1) == 'S'
                && input.get(i - 1).charAt(j + 1) == 'M';
    }

    private static boolean checkAround4(List<String> input, int i, int j) {
        return input.get(i - 1).charAt(j - 1) == 'S'
                && input.get(i + 1).charAt(j + 1) == 'M'
                && input.get(i + 1).charAt(j - 1) == 'M'
                && input.get(i - 1).charAt(j + 1) == 'S';
    }

    private static boolean checkAround1(List<String> input, int i, int j) {
        // M . M
        // . A .
        // S . S
        return input.get(i - 1).charAt(j - 1) == 'M'
                && input.get(i + 1).charAt(j + 1) == 'S'
                && input.get(i + 1).charAt(j - 1) == 'M'
                && input.get(i - 1).charAt(j + 1) == 'S';
    }

    private static boolean checkAround2(List<String> input, int i, int j) {
        // S . S
        // . A .
        // M . M
        return input.get(i - 1).charAt(j - 1) == 'S'
                && input.get(i + 1).charAt(j + 1) == 'M'
                && input.get(i + 1).charAt(j - 1) == 'S'
                && input.get(i - 1).charAt(j + 1) == 'M';
    }

    private static int countDiagonalNorthEastSouthWest(List<String> input, String pattern) {
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
            count += count(diagonal.toString(), pattern);
            count += count(diagonal.reverse().toString(), pattern);

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
            count += count(diagonal.toString(), pattern);
            count += count(diagonal.reverse().toString(), pattern);

        }

        return count;
    }

    private static int countDiagonalNorthWestSouthEast(List<String> input, String pattern) {
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
            count += count(diagonal.toString(), pattern);
            count += count(diagonal.reverse().toString(), pattern);

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
            count += count(diagonal.toString(), pattern);
            count += count(diagonal.reverse().toString(), pattern);

        }
        return count;
    }

    private static int countVertical(List<String> input, String pattern) {
        System.out.println("verticaal:");
        int count = 0;
        for (int col = 0; col < input.get(0).length(); col++) {
            StringBuilder vertical = new StringBuilder();
            for (String line : input){
                vertical.append(line.charAt(col));
            }
           System.out.println(vertical);
           count += count(vertical.toString(), pattern);
           count += count(vertical.reverse().toString(), pattern);

        }
        return count;
    }

    private static int countHorizontal(List<String> input, String pattern) {
        System.out.println("Horizontalen: ");
        int count = 0;
        for (String line : input){
            System.out.println(line);
            count += count(line, pattern);
            count += count(new StringBuilder(line).reverse().toString(), pattern);
        }
        return count;
    }

    private static int count(String line, String pattern){
        int count = 0;
        int index = line.indexOf(pattern);
        while (index != -1){
            count++;
            index = line.indexOf(pattern, index + 1);
        }
        return count;
    }
}
