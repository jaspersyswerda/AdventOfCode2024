package adventofcode2024.Day09;

import adventofcode2024.EveryDay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DayNine extends EveryDay {

    String input;

    public static void main(String[] args) {
        var dayNine = new DayNine();
        dayNine.input = readInputFile("DayNine.txt").get(0).trim();
        dayNine.doPart1();
        dayNine.doPart2();
    }

    private void doPart1() {
        List<Integer> diskMap = createDiskMap();
        List<Integer> layoutOfSpace = createLayoutOfSpace(diskMap);
        //System.out.println(layoutOfSpace);
        List<Integer> reorderedLayout = reorderLayout(layoutOfSpace);
        System.out.println("Part 1: " + getChecksum(reorderedLayout));


    }

    private BigInteger getChecksum(List<Integer> reorderedLayout) {
        return IntStream.range(0,reorderedLayout.size())
                .filter(i-> reorderedLayout.get(i) != -1)
                .mapToObj(i-> BigInteger.valueOf(reorderedLayout.get(i)).multiply(BigInteger.valueOf(i)))
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private List<Integer> reorderLayout(List<Integer> layoutOfSpace) {
        ArrayList<Integer> newLayout = new ArrayList<>(layoutOfSpace);
        int leftPointer = findLeftPointer(layoutOfSpace);
        int rightPointer = newLayout.size() - 1;
        while (leftPointer < rightPointer){
            while (newLayout.get(leftPointer) != -1){
                leftPointer++;
            }
            while (newLayout.get(rightPointer) == -1){
                rightPointer--;
            }
            swap(newLayout, leftPointer, rightPointer);
            leftPointer++;
            rightPointer--;

        }
        return newLayout;
    }

    private void swap(List<Integer> layout, int leftPointer, int rightPointer) {
        int temp = layout.get(rightPointer);
        layout.set(rightPointer, layout.get(leftPointer));
        layout.set(leftPointer, temp);
    }

    private int findLeftPointer(List<Integer> layoutOfSpace) {
        return IntStream.range(0, layoutOfSpace.size())
                .filter(i -> layoutOfSpace.get(i) == -1)
                .findFirst()
                .orElseThrow();
    }

    private List<Integer> createDiskMap() {
        return input.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .boxed()
                .toList();
    }

    private List<Integer> createLayoutOfSpace(List<Integer> diskMap) {
        List<Integer> layout = new ArrayList<>();
        for (int i = 0, id = 0; i < diskMap.size() && id<diskMap.size(); i = i + 2, id++) {
            layout.addAll(Collections.nCopies(diskMap.get(i), id));
            if (i + 1 < diskMap.size()){
                layout.addAll(Collections.nCopies(diskMap.get(i+1), -1));
            }
        }
        return layout;
    }

    private void doPart2() {
        List<Integer> diskMap = createDiskMap();
        List<Integer> layoutOfSpace = createLayoutOfSpace(diskMap);
        List<Integer> reorderedLayout = reorderLayoutPart2(layoutOfSpace);
        System.out.println("Part 2: " + getChecksum(reorderedLayout));
    }

    private List<Integer> reorderLayoutPart2(List<Integer> layoutOfSpace) {
        ArrayList<Integer> newLayout = new ArrayList<>(layoutOfSpace);
        int rightPointerDigit = Collections.max(newLayout);
        for (int currentRightPointerDigit = rightPointerDigit; currentRightPointerDigit >= 0; currentRightPointerDigit--){
            int rightPointer = newLayout.indexOf(currentRightPointerDigit);
            int rightPointerLength = findRightPointerLength(rightPointer, newLayout);
            List<Integer> emptyIndices = findEmptyIndices(newLayout, rightPointer);
            for (int leftPointer : emptyIndices) {
                int leftPointerLength = findLeftPointerLength(leftPointer, newLayout);
                if (leftPointerLength >= rightPointerLength){
                    for (int i = leftPointer, j = rightPointer; j < rightPointer + rightPointerLength; i++, j++) {
                        swap(newLayout, i, j);
                    }
                    break;
                }
            }

        }
        return newLayout;
    }

    private List<Integer> findEmptyIndices(ArrayList<Integer> layout, int constraint) {
        List<Integer> result = new ArrayList<>();
        int previousNumber = 0;
        for (int i = 0; i < layout.size() ; i++) {
            int number = layout.get(i);
            if (number == -1 && previousNumber != -1 && i < constraint){
                result.add(i);
            }
            previousNumber = number;

        }
        return result;
    }

    private int findRightPointerLength(int rightPointer, ArrayList<Integer> newLayout) {
        int length = 1;
        int id = newLayout.get(rightPointer);
        while ((rightPointer + length) < newLayout.size() && newLayout.get(rightPointer + length) == id){
            length++;
        }
        return length;
    }

    private int findLeftPointerLength(int leftPointer, ArrayList<Integer> newLayout) {
        int length = 1;
        while ((leftPointer + length) < newLayout.size() && newLayout.get(leftPointer + length) == -1){
            length++;
        }
        return length;
    }

}
