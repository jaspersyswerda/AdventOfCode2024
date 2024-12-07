package adventofcode2024.Day05;

import adventofcode2024.EveryDay;

import java.util.*;
import java.util.stream.IntStream;

public class DayFive extends EveryDay {

    public static void main(String[] args) {
        List<String> input = readInputFile("DayFive.txt");
        List<String> rules = getRules(input);
        List<String> pagesToProduce = getPagesToProduce(input);
        Map<Integer, List<Integer>> orderMap = fillMap(rules);

        part1(pagesToProduce, orderMap);
        part2(pagesToProduce, orderMap, rules);

    }

    private static void part2(List<String> pagesToProduce, Map<Integer, List<Integer>> orderMap, List<String> rules) {
        int result = 0;
        for (String pagesAsString: pagesToProduce){
            List<Integer> pageNumbers = getPageNumbers(pagesAsString);
            if (!isValid(pageNumbers, orderMap)){
                result += orderListAndFindMiddle(pageNumbers, orderMap, rules);
            }
        }
        System.out.println("Part 2: " + result);
    }

    private static int orderListAndFindMiddle(List<Integer> pageNumbers, Map<Integer, List<Integer>> orderMap, List<String> rules) {
        pageNumbers = new ArrayList<>(pageNumbers);
        while (!isValid(pageNumbers, orderMap)){
            for (String rule : rules){
                String[] numbersAsString = rule.split("\\|");
                int before = Integer.parseInt(numbersAsString[0]);
                int after = Integer.parseInt(numbersAsString[1]);
                if (!pageNumbers.contains(before) || !pageNumbers.contains(after)){
                    continue;
                }
                int beforeIndex = pageNumbers.indexOf(before);
                int afterIndex = pageNumbers.indexOf(after);
                if (beforeIndex > afterIndex){
                    pageNumbers.set(beforeIndex, after);
                    pageNumbers.set(afterIndex, before);
                }
            }
        }
        return pageNumbers.get(pageNumbers.size()/2);
    }

    private static void part1(List<String> pagesToProduce, Map<Integer, List<Integer>> orderMap) {
        int result = 0;
        for (String pagesAsString : pagesToProduce){
            List<Integer> pageNumbers = getPageNumbers(pagesAsString);
            if (isValid(pageNumbers, orderMap)){
                result += (pageNumbers.get(pageNumbers.size()/2));
            }
        }
        System.out.println("Part 1: " + result);
    }

    private static boolean isValid(List<Integer> pageNumbers, Map<Integer, List<Integer>> orderMap) {
        Set<Integer> seen = new HashSet<>();
        for (int number : pageNumbers){
            seen.add(number);
            if (orderMap.containsKey(number)){
                for (var value : orderMap.get(number)){
                    if (seen.contains(value)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<Integer> getPageNumbers(String pagesAsString) {
        String[] pagesAsArrayString = pagesAsString.split(",");
        return Arrays.stream(pagesAsArrayString).map(Integer::parseInt).toList();
    }

    private static Map<Integer, List<Integer>> fillMap(List<String> rules) {
        var orderMap = new HashMap<Integer,List<Integer>>();
        for (String rule : rules){
            String[] numbersAsString = rule.split("\\|");
            int before = Integer.parseInt(numbersAsString[0]);
            int after = Integer.parseInt(numbersAsString[1]);
            orderMap.computeIfAbsent(before, k -> new ArrayList<>()).add(after);
        }
        return orderMap;
    }

    private static List<String> getRules(List<String> input) {
        int index = IntStream.range(0,input.size()).filter(i -> input.get(i).isEmpty()).findFirst().orElse(-1);

        if (index == -1){
            throw new RuntimeException("");
        }

        return input.subList(0, index);
    }

    private static List<String> getPagesToProduce(List<String> input) {
       int index = IntStream.range(0,input.size()).filter(i -> input.get(i).isEmpty()).findFirst().orElse(-1);

       if (index == -1){
           throw new RuntimeException("");
       }

       return input.subList(index + 1, input.size());
    }


}
