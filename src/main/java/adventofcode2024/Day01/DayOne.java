package adventofcode2024.Day01;

import adventofcode2024.EveryDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayOne extends EveryDay {

    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = readInputFile("DayOne.txt");
        DayOne dayOne = new DayOne();
        dayOne.fillLists(input);
        //Part1
        dayOne.calculateSums_Part1();
        //Part2
        dayOne.calculateSimilarityScores_Part2();

    }

    private void calculateSimilarityScores_Part2() {
        int result = 0;
        for (int number : leftList){
            int count = (int) rightList.stream().filter(r -> r.equals(number)).count();
            result += count*number;
        }
        System.out.println("Part2: " + result);

    }

    private void calculateSums_Part1() {
        int result = 0;
        for (int i = 0; i < leftList.size(); i++){
            int leftNumber = leftList.get(i);
            int rightNumber = rightList.get(i);
            result += Math.abs(leftNumber - rightNumber);
        }
        System.out.println("Part1: " + result);
    }

    void fillLists(List<String> input) {
        for (String line : input){
            String[] numbersAsString = line.split("\\s+");
            leftList.add(Integer.parseInt(numbersAsString[0]));
            rightList.add(Integer.parseInt(numbersAsString[1]));
        }
        Collections.sort(leftList);
        Collections.sort(rightList);
    }


}
