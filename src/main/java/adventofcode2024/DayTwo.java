package adventofcode2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwo extends EveryDay{
    public static void main(String[] args) {
        List<String> input = readInputFile("DayTwo.txt");
       int result_Part1 = 0;
       int result_Part2 = 0;
        for (String line: input){
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            //Part 1
            if (isSafe(numbers)){
                result_Part1++;
            }
            //Part 2
            if (isSafePart2(numbers)){
                result_Part2++;
            }
        }
        System.out.println("Part 1: "  + result_Part1);
        System.out.println("Part 2: "  + result_Part2);
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

    private static boolean isSafePart2(List<Integer> numbers) {

        for (int i = 0; i<numbers.size();i++){
            List<Integer> copy = new ArrayList<>(numbers);
            copy.remove(i);
            if (isSafe(copy)){
                return true;
            }

        }
        return false;
    }


}
