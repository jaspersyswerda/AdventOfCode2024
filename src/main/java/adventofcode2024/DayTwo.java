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

        boolean allIncreasing = determineAllIncreasing(numbers);
        boolean allDecreasing = determineAllDecreasing(numbers);
        if (!allDecreasing && !allIncreasing){
            return false;
        }
        return adjacentLevelsSafe(numbers, allIncreasing);
    }

    private static boolean adjacentLevelsSafe(List<Integer> numbers, boolean allIncreasing) {
        int i = 0;
        while (i < numbers.size() - 1){
            int difference = numbers.get(i+1) - numbers.get(i);
            if (allIncreasing && difference > 3){
                return false;
            }
            if (!allIncreasing && difference < -3){
                return false;
            }
            i++;

        }
        return true;


    }

    private static boolean determineAllDecreasing(List<Integer> numbers) {
        int i = 0;
        while (i < numbers.size() - 1){
            if (numbers.get(i + 1) >= numbers.get(i)){
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean determineAllIncreasing(List<Integer> numbers) {
       int i = 0;
        while (i < numbers.size() - 1){
            if (numbers.get(i + 1) <= numbers.get(i)){
                return false;
            }
            i++;
        }
        return true;
    }
}
