package adventofcode2024;

import java.util.*;
import java.util.stream.IntStream;

public class DayFive extends EveryDay{

    public static void main(String[] args) {
        List<String> input = readInputFile("DayFive.txt");
        List<String> rules = getRules(input);
        List<String> pagesToProduce = getPagesToProduce(input);

        Map<Integer, List<Integer>> orderMap = fillMap(rules);
        int result = 0;
        for (String pagesAsString : pagesToProduce){
            List<Integer> pageNumbers = getPageNumbers(pagesAsString);
            System.out.println(pageNumbers);
            if (isValid(pageNumbers, orderMap)){
                result+=(pageNumbers.get(pageNumbers.size()/2));
            }
        }
        System.out.println(result);

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
        List<Integer> numbers = new ArrayList<>();
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
