package adventofcode2024;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySeven extends EveryDay{
    List<String> input;

    public static void main(String[] args) {
        var daySeven = new DaySeven();
        daySeven.input = readInputFile("DaySeven.txt");
        daySeven.doPart1();
    }

    private void doPart1() {
        BigInteger result = BigInteger.ZERO;
        for (var line: input){
            String[] parts = line.split(":", 2);
            String goalNumberAsString = parts[0];
            String partsAsString = parts[1];
            BigInteger goalNumber = new BigInteger(goalNumberAsString);
            List<BigInteger> partsList = Arrays.stream(partsAsString.trim().split("\\s+"))
                    .map(BigInteger::new)
                    .toList();
            if (canReachGoal(goalNumber, partsList)){
               result = result.add(goalNumber);
            }
        }
        System.out.println(result);
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

            if (results.contains(goalNumber)){
                return true;
            }
        }
        return false;
    }
}
