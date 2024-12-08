package adventofcode2024.Day07;

import adventofcode2024.EveryDay;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySeven extends EveryDay {
    List<String> input;

    public static void main(String[] args) {
        var daySeven = new DaySeven();
        daySeven.input = readInputFile("DaySeven.txt");
        daySeven.doPart1();
        daySeven.doPart2();
    }

    private void doPart1() {
        BigInteger result = BigInteger.ZERO;
        for (var line: input){
            String[] parts = line.split(":", 2);
            BigInteger goalNumber = new BigInteger(parts[0]);
            List<BigInteger> partsList = Arrays.stream(parts[1].trim().split("\\s+"))
                    .map(BigInteger::new)
                    .toList();
            if (canReachGoal(goalNumber, partsList)){
               result = result.add(goalNumber);
            }
        }
        System.out.println("Part 1: " + result);
    }

    private boolean canReachGoal(BigInteger goalNumber, List<BigInteger> parts) {
        Set<BigInteger> results = new HashSet<>();
        results.add(BigInteger.ZERO);
        for (BigInteger part : parts){
            Set<BigInteger> nextResults = new HashSet<>();

            for (BigInteger result : results){
                nextResults.add(result.add(part));

                if (!BigInteger.ZERO.equals(result)){
                    nextResults.add(result.multiply(part));
                }
            }
            results = nextResults;
        }
        return results.contains(goalNumber);
    }

    private void doPart2() {
        BigInteger result = BigInteger.ZERO;
        for (var line: input){
            String[] parts = line.split(":", 2);
            BigInteger goalNumber = new BigInteger(parts[0]);
            List<BigInteger> partsList = Arrays.stream(parts[1].trim().split("\\s+"))
                    .map(BigInteger::new)
                    .toList();
            if (canReachGoalWithConcatenation(goalNumber, partsList)){
                result = result.add(goalNumber);
            }
        }
        System.out.println("Part 2: " + result);
    }

    private boolean canReachGoalWithConcatenation(BigInteger goalNumber, List<BigInteger> parts) {
        Set<BigInteger> results = new HashSet<>();
        results.add(BigInteger.ZERO);
        for (BigInteger part : parts){
            Set<BigInteger> nextResults = new HashSet<>();

            for (BigInteger result : results){
                nextResults.add(result.add(part));

                if (!BigInteger.ZERO.equals(result)){
                    nextResults.add(result.multiply(part));
                    nextResults.add(new BigInteger(result + String.valueOf(part)));
                }
            }
            results = nextResults;
        }
        return results.contains(goalNumber);
    }
}
