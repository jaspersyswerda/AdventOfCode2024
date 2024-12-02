package adventofcode2024;

import java.util.Arrays;
import java.util.List;

public class DayTwo extends EveryDay{
    public static void main(String[] args) {
        List<String> input = readInputFile("DayTwo.txt");
       int result = 0;
        for (String line: input){
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            if (isSafe(numbers)){
                result++;
            }
        }
        System.out.println("Part 1: "  + result);
    }

    private static boolean isSafe(List<Integer> numbers) {

        // all increasing or all decreasing
        // adjacent levels differ by between 1 and 3
        boolean allIncreasing = true;
        boolean allDecreasing = true;
        for (int i = 0; i<numbers.size() - 1; i++){
            int difference = numbers.get(i+1) - numbers.get(i);
            if (difference < 1 || difference > 3){
                allDecreasing = false;
            }
            if (difference > -1 || difference < -3){
                allIncreasing = false;
            }
            if (!allDecreasing && !allIncreasing){
                return false;
            }

        }
        return true;
    }
}
