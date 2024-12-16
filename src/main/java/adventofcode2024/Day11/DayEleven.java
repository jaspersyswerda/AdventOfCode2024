package adventofcode2024.Day11;

import adventofcode2024.EveryDay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class DayEleven extends EveryDay {

    String input = readInputFile("DayEleven.txt").get(0);

    public static void main(String[] args) {
        var dayEleven = new DayEleven();
        dayEleven.doPart1();
        dayEleven.doPart2();
    }

    private void doPart1() {
        String[] numbersAsString = input.split("\\s+");
        List<BigInteger> numbers = Arrays.stream(numbersAsString).map(BigInteger::new).toList();
        for (int i = 0; i < 25; i++) {
            ArrayList<BigInteger> numbersCopy = new ArrayList<>();
            for (BigInteger number : numbers){
                if (BigInteger.ZERO.equals(number)){
                    numbersCopy.add(BigInteger.ONE);
                } else if (String.valueOf(number).length() % 2 == 0) {
                    String numberAsString = String.valueOf(number);
                    int mid = numberAsString.length() / 2;
                    numbersCopy.add(new BigInteger(numberAsString.substring(0,mid)));
                    numbersCopy.add(new BigInteger(numberAsString.substring(mid)));
                }

                else {
                    numbersCopy.add(number.multiply(BigInteger.valueOf(2024)));
                }
            }
            numbers = numbersCopy;
        }
        System.out.println("Part 1: " + numbers.size());

    }

    private void doPart2() {
        String[] numbersAsString = input.split("\\s+");
        LinkedList<BigInteger> numbers = Arrays.stream(numbersAsString).map(BigInteger::new).collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < 75; i++) {
            LinkedList<BigInteger> numbersCopy = new LinkedList<>();
            for (BigInteger number : numbers){
                if (BigInteger.ZERO.equals(number)){
                    numbersCopy.add(BigInteger.ONE);
                } else if (String.valueOf(number).length() % 2 == 0) {
                    String numberAsString = String.valueOf(number);
                    int mid = numberAsString.length() / 2;
                    numbersCopy.add(new BigInteger(numberAsString.substring(0,mid)));
                    numbersCopy.add(new BigInteger(numberAsString.substring(mid)));
                }

                else {
                    numbersCopy.add(number.multiply(BigInteger.valueOf(2024)));
                }
            }
            numbers = numbersCopy;
        }
        System.out.println("Part 2: " + numbers.size());

    }
}
