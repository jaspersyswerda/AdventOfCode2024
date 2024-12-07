package adventofcode2024.Day06;

import adventofcode2024.EveryDay;

import java.util.*;

public class DaySix extends EveryDay {
    List<String> input;
    int dRow;
    int dCol;

    public static void main(String[] args) {
        var daySix = new DaySix();
        daySix.input = readInputFile("DaySix.txt");
        daySix.doPart1();
        daySix.doPart2();
    }

    private void doPart1() {
        Set<Coordinate> positions = new HashSet<>();
        Coordinate currentPosition = getStartPosition();
        System.out.println(currentPosition);
        positions.add(currentPosition);
        dRow = -1;
        dCol = 0;
        while(isInsideBoundaries(currentPosition)){
            if (getNext(currentPosition, input) == '#'){
                rotate90DegRight();
            }
            else {
                currentPosition = new Coordinate(currentPosition.getRow() + dRow, currentPosition.getColumn() + dCol);
              //  System.out.println(currentPosition);
                positions.add(currentPosition);
            }
        }
        System.out.println("Part 1: " + positions.size());
    }

    private void rotate90DegRight() {
        int newDRow = dCol;
        int newDCol = -dRow;

        dRow = newDRow;
        dCol = newDCol;
    }

    private char getNext(Coordinate currentPosition, List<String> input) {
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

    private void doPart2() {
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {

                if (input.get(i).charAt(j) == '.'){
                    List<String> tempInput = replaceWithHashTag(i, j);
                    Set<CoordinateWithDirection> positions = new HashSet<>();

                    CoordinateWithDirection currentPosition = getStartPosition_Part2();
                    positions.add(currentPosition);
                    dRow = -1;
                    dCol = 0;
                    while(isInsideBoundaries(currentPosition)){
                        if (getNext(currentPosition, tempInput) == '#'){
                            rotate90DegRight();
                            currentPosition = currentPosition.turnRight();
                            positions.add(currentPosition);
                        }
                        else {
                            currentPosition = new CoordinateWithDirection(currentPosition.getRow() + dRow, currentPosition.getColumn() + dCol, dRow, dCol);
                            if (!positions.add(currentPosition)){
                                result++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Part 2: " + result);
    }

    private List<String> replaceWithHashTag(int i, int j) {
        List<String> tempInput = new ArrayList<>(input);
        StringBuilder row = new StringBuilder(tempInput.get(i));
        row.replace(j, j + 1, "#");
        tempInput.set(i, row.toString());
        return tempInput;
    }

    private CoordinateWithDirection getStartPosition_Part2() {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '^'){
                    return new CoordinateWithDirection(i, j, -1, 0);
                }

            }
        }
        throw new RuntimeException("No start mark found!");
    }

}
