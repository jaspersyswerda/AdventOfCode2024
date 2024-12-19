package adventofcode2024.Day12;

import adventofcode2024.EveryDay;

import java.util.*;

public class DayTwelve extends EveryDay {

    List<String> input = readInputFile("DayTwelve.txt");
    String[][] grid = makeGrid();
    final int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) {
        var dayTwelve = new DayTwelve();

        dayTwelve.doPart1();
    }

    private String[][] makeGrid() {
        String[][] result = new String[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(i).split("");
        }
        return result;
    }


    private void doPart1() {
        int output = 0;

        Set<String> visited = new HashSet<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (!visited.contains(Arrays.toString(new int[]{row, col}))){
                    output += explore(row, col, grid[row][col], visited);
                }
            }
            
        }
        
        System.out.println("Part 1: " + output);
    }

    private int explore(int row, int col, String plant, Set<String> visited) {
        int area = 0;
        int perimeter = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        visited.add(Arrays.toString(new int[]{row, col}));

        while (!queue.isEmpty()){
            var currentPlant = queue.remove();
            area++;

            for (var dir : directions){
                var newRow = currentPlant[0] + dir[0];
                var newCol = currentPlant[1] + dir[1];

                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[row].length){
                    perimeter++;
                    continue;
                }

                if (!grid[newRow][newCol].equals(plant)){
                    perimeter++;
                    continue;
                }

                var newCoordinate = new int[]{newRow, newCol};
                if (!visited.contains(Arrays.toString(newCoordinate))){
                    queue.add(newCoordinate);
                    visited.add(Arrays.toString(newCoordinate));

                }
            }
        }





        return area * perimeter;
    }
}
