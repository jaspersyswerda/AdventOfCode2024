package adventofcode2024.Day08;

import adventofcode2024.EveryDay;

import java.util.*;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class DayEight extends EveryDay {

    List<String> input = readInputFile("DayEight.txt");

    public static void main(String[] args) {
        var dayEight = new DayEight();
        dayEight.doPart1();
        dayEight.doPart2();
    }

    private void doPart1() {
        Set<String> uniqueLocations = new HashSet<>();
        Set<Character> distinctCharacters = input.stream()
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .filter(c -> c != '.')
                .collect(Collectors.toSet());

        for (char c : distinctCharacters){
            List<int[]> locations = getLocations(c);
            List<Pair<int[], int[]>> pairs = getPairs(locations);
            for (var pair : pairs){
                int[] antiNode = findAntiNode(pair, 2);
                if (withinBounds(antiNode)){
                    uniqueLocations.add(Arrays.toString(antiNode));
                }
            }
        }
        //uniqueLocations.forEach(System.out::println);
        System.out.println("Part 1: " + uniqueLocations.size());

    }

    private boolean withinBounds(int[] antiNode) {
        return antiNode[0] >= 0 && antiNode[0] < input.size()
                && antiNode[1] >= 0 && antiNode[1] < input.size();
    }

    private int[] findAntiNode(Pair<int[],int[]> pair, int range) {
        int[] firstCoordinate = pair.getKey();
        int[] secondCoordinate = pair.getValue();

        int dX = secondCoordinate[0] - firstCoordinate[0];
        int dY = secondCoordinate[1] - firstCoordinate[1];

        int newX = firstCoordinate[0] + dX * range;
        int newY = firstCoordinate[1] + dY * range;
        return new int[]{newX, newY};
    }

    private List<Pair<int[], int[]>> getPairs(List<int[]> locations) {
        List<Pair<int[], int[]>> pairs = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                if (i != j){
                    int[] first = locations.get(i);
                    int[] second = locations.get(j);
                    pairs.add(new Pair<>(first, second));
                }
            }
        }
        return pairs;
    }

    private List<int[]> getLocations(char c) {
        List<int[]> locations = new ArrayList<>();
        for (int i = 0; i < input.size() ; i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == c){
                    int[] location = new int[]{i, j};
                    locations.add(location);
                }
            }
        }
        return locations;
    }

    private void doPart2() {
        Set<String> uniqueLocations = new HashSet<>();
        Set<Character> distinctCharacters = input.stream()
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .filter(c -> c != '.')
                .collect(Collectors.toSet());

        for (char c : distinctCharacters){
            List<int[]> locations = getLocations(c);
            List<Pair<int[], int[]>> pairs = getPairs(locations);
            for (var pair : pairs){
                int[] antiNode;
                int i = 1;
                do {
                    antiNode = findAntiNode(pair, i);
                    if (withinBounds(antiNode)){
                        uniqueLocations.add(Arrays.toString(antiNode));
                    }

                    i++;
                } while (withinBounds(antiNode));
            }
        }
       // uniqueLocations.forEach(System.out::println);
        System.out.println("Part 2: " + uniqueLocations.size());

    }


}
