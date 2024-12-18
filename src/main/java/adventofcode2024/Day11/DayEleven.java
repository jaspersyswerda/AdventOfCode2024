package adventofcode2024.Day11;

import adventofcode2024.EveryDay;

import java.util.*;
import java.util.stream.Collectors;


public class DayEleven extends EveryDay {

    String input = readInputFile("DayEleven.txt").get(0);

    public static void main(String[] args) {
        var dayEleven = new DayEleven();
        dayEleven.doPart1();
        dayEleven.doPart2();
    }

    private void doPart1() {
        String[] numbersAsString = input.split("\\s+");
        List<Long> numbers = Arrays.stream(numbersAsString).map(Long::valueOf).toList();
        for (int i = 0; i < 25; i++) {
            ArrayList<Long> numbersCopy = new ArrayList<>();
            for (Long number : numbers){
                if (number == 0L){
                    numbersCopy.add(1L);
                } else if (String.valueOf(number).length() % 2 == 0) {
                    String numberAsString = String.valueOf(number);
                    int mid = numberAsString.length() / 2;
                    numbersCopy.add(Long.valueOf(numberAsString.substring(0,mid)));
                    numbersCopy.add(Long.valueOf(numberAsString.substring(mid)));
                }

                else {
                    numbersCopy.add(number * (2024L));
                }
            }
            numbers = numbersCopy;
        }
        System.out.println("Part 1: " + numbers.size());

    }

    private void doPart2() {
        String[] numbersAsString = input.split("\\s+");
        Map<Long, Long> stones = Arrays.stream(numbersAsString).map(Long::valueOf).collect(Collectors.toMap(e -> e, e -> 1L));
        for (int i = 0; i < 75; i++) {
            Map<Long, Long> stonesCopy = new HashMap<>();
            for (var entry : stones.entrySet()){
                if (entry.getKey() == 0L){
                    stonesCopy.compute(1L, (k,v) -> (v == null ? 0L : v) + entry.getValue());
                } else if (String.valueOf(entry.getKey()).length() % 2 == 0) {
                    String numberAsString = String.valueOf(entry.getKey());
                    int mid = numberAsString.length() / 2;
                    stonesCopy.compute(Long.valueOf(numberAsString.substring(0,mid)), (k,v) -> (v == null ? 0L : v) + entry.getValue());
                    stonesCopy.compute(Long.valueOf(numberAsString.substring(mid)), (k,v) -> (v == null ? 0L : v) + entry.getValue());
                }

                else {
                   stonesCopy.compute(entry.getKey() * 2024L, (k,v) -> (v == null ? 0L : v) + entry.getValue());
                }
            }
            stones = stonesCopy;
        }
        long output = 0;
        for (var value : stones.values()){
            output += value;
        }
        System.out.println("Part 2: " + output);

    }
}
