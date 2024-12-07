package adventofcode2024.Day06;

import adventofcode2024.EveryDay;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySix extends EveryDay {
    List<String> input;
    int dRow;
    int dCol;

    public static void main(String[] args) {
        var daySix = new DaySix();
        daySix.input = readInputFile("DaySix.txt");
        daySix.doPart1();
    }

    private void doPart1() {
        Set<Coordinate> positions = new HashSet<>();
        Coordinate currentPosition = getStartPosition();
        System.out.println(currentPosition);
        positions.add(currentPosition);
        dRow = -1;
        dCol = 0;
        while(isInsideBoundaries(currentPosition)){
            if (getNext(currentPosition) == '#'){
                rotate90DegRight();
            }
            else {
                currentPosition = new Coordinate(currentPosition.getRow() + dRow, currentPosition.getColumn() + dCol);
                System.out.println(currentPosition);
                positions.add(currentPosition);
            }
        }
        System.out.println(positions.stream().distinct().count());
    }

    private void rotate90DegRight() {
        int newDRow = dCol;
        int newDCol = -dRow;

        dRow = newDRow;
        dCol = newDCol;
    }

    private char getNext(Coordinate currentPosition) {
        return input.get(currentPosition.getRow() + dRow).charAt(currentPosition.getColumn() + dCol);
    }

    private boolean isInsideBoundaries(Coordinate currentPosition) {
        return currentPosition.getRow() + dRow >= 0
                && currentPosition.getRow() + dRow < input.size()
                && currentPosition.getColumn() + dCol >= 0
                && currentPosition.getColumn() + dCol < input.size();
    }

    private Coordinate getStartPosition() {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '^'){
                    return new Coordinate(i, j);
                }

            }
        }
        throw new RuntimeException("No start mark found!");
    }

}
