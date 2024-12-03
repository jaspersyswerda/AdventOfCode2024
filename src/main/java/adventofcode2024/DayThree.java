package adventofcode2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree extends EveryDay{

    public static void main(String[] args) {
        List<String> input = readInputFile("DayThree.txt");
        doPart1(input);
        doPart2(input);

    }

    private static void doPart1(List<String> input) {
        String line = String.join("", input);
        List<Integer> multipliedNumbers = getMultipliedNumbers(line);
        int result = multipliedNumbers.stream().reduce(0, Integer::sum);

        System.out.println("Part 1: " + result);
    }

    private static void doPart2(List<String> input) {
        String line = String.join("", input);
        List<Integer> multipliedNumbers = getMultipliedNumbersPart2(line);
        int result = multipliedNumbers.stream().reduce(0, Integer::sum);

        System.out.println("Part 2: " + result);

    }

    private static List<Integer> getMultipliedNumbers(String line) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()){
            list.add(Integer.parseInt(matcher.group(1))*Integer.parseInt(matcher.group(2)));
        }
        return list;
    }

    private static List<Integer> getMultipliedNumbersPart2(String line) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("don't\\(\\)|do\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(line);
        boolean enabled = true;
        while (matcher.find()){
            if (Objects.equals(matcher.group(), "do()")){
                enabled = true;
            }
            else if (Objects.equals(matcher.group(), "don't()")){
                enabled = false;
            }
            else {
                if (enabled) {
                    list.add(Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
                }
            }
        }
        return list;
    }


}
