package adventofcode2024.Day10;

import adventofcode2024.EveryDay;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;


public class DayTen extends EveryDay {

    List<String> input = readInputFile("DayTen.txt");
    Integer[][] grid = createMatrix();
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    List<int[]> directions = makeDirections();

    private List<int[]> makeDirections() {
        return Arrays.asList(DIRECTIONS);
    }

    public static void main(String[] args) {
        DayTen dayTen = new DayTen();
        dayTen.doPart1();
        dayTen.doPart2();
    }

    private void doPart1() {
        int result = 0;
        List<int[]> zeros = findZeroLocations();
        for (int[] zero : zeros){
            Set<String> reachableNines = new HashSet<>();
            findAdjacent(zero[0], zero[1], 0, reachableNines);
            result += reachableNines.size();
        }

        System.out.println("Part 1: " + result);


    }

    private void findAdjacent(int row, int col, int height, Set<String> reachableNines){
        //buiten de grenzen van de grid
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length){
            return;
        }
        // niet het juiste nummer
        if (grid[row][col] != height){
            return;
        }

        String currentPosition = Arrays.toString(new int[]{row, col});

        // als 9 dan top bereikt
        if (height == 9){
            reachableNines.add(currentPosition);
            return;
        }

        // ga kijken in alle richtingen
        for(int[] dir : directions){
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            findAdjacent(nextRow, nextCol, height + 1, reachableNines);
        }
    }

    private List<int[]> findZeroLocations() {
        List<int[]> zeros = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0){
                    zeros.add(new int[]{row, col});
                }
            }
        }
        return zeros;
    }

    private Integer[][] createMatrix() {
        // Maak een String[][] van de lijst
        Integer[][] array = new Integer[input.size()][];

        // Vul de array met gesplitste strings
        for (int i = 0; i < input.size(); i++) {
            String[] arrayString = input.get(i).split("");
            array[i] = new Integer[arrayString.length];
            for (int j = 0; j < arrayString.length; j++) {
                array[i][j] = Integer.parseInt(arrayString[j]);
            }
        }

        return array;

    }

    private void doPart2() {
        int result = 0;

        List<int[]> zeros = findZeroLocations();
        for (int[] zero : zeros){
            result += findAdjacent(zero[0], zero[1], 0);
        }

        System.out.println("Part 2: " + result);
    }

    private int findAdjacent(int row, int col, int height) {
        //buiten de grenzen van de grid
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length){
            return 0;
        }
        // niet het juiste nummer
        if (grid[row][col] != height){
            return 0;
        }

        // als 9 dan top bereikt
        if (height == 9){
            return 1;
        }

        int trails = 0;
        // ga kijken in alle richtingen
        for(int[] dir : directions){
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            trails += findAdjacent(nextRow, nextCol, height + 1);
        }
        return trails;
    }

}
