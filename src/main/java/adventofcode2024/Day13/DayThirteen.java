package adventofcode2024.Day13;

import adventofcode2024.EveryDay;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DayThirteen extends EveryDay {

    List<String> input = readInputFile("DayThirteen.txt");

    public static void main(String[] args) {
        var dayThirteen = new DayThirteen();
        dayThirteen.doPart1();
        dayThirteen.doPart2();
    }

    private void doPart1() {
        List<DayThirteenHelper> helpers = createHelperObjects();
        BigInteger result = BigInteger.ZERO;
        for(var helper : helpers){
            BigInteger determinant = getDeterminant(helper);
            if (determinant.equals(BigInteger.ZERO)){
                continue;
            }
            BigInteger numerator_X = helper.getAnswerX().multiply(helper.getbY()).subtract(helper.getAnswerY().multiply(helper.getbX()));

            BigInteger numerator_Y = helper.getAnswerY().multiply(helper.getaX()).subtract(helper.getAnswerX().multiply(helper.getaY()));

            if (isEvenlyDivisible(numerator_X, determinant) && isEvenlyDivisible(numerator_Y,determinant)){
                BigInteger x = numerator_X.divide(determinant);
                BigInteger y = numerator_Y.divide(determinant);
                result = result.add(BigInteger.valueOf(3).multiply(x).add(y));
            }
        }
        System.out.println("Part 1: " + result);
    }

    private void doPart2() {
        List<DayThirteenHelper> helpers = createHelperObjectsPart2();
        BigInteger result = BigInteger.ZERO;
        for(var helper : helpers){
            BigInteger determinant = getDeterminant(helper);
            if (BigInteger.ZERO.equals(determinant)){
                continue;
            }
            BigInteger numerator_X = helper.getAnswerX().multiply(helper.getbY()).subtract(helper.getAnswerY().multiply(helper.getbX()));

            BigInteger numerator_Y = helper.getAnswerY().multiply(helper.getaX()).subtract(helper.getAnswerX().multiply(helper.getaY()));

            if (isEvenlyDivisible(numerator_X, determinant) && isEvenlyDivisible(numerator_Y,determinant)){
                BigInteger x = numerator_X.divide(determinant);
                BigInteger y = numerator_Y.divide(determinant);
                result = result.add(BigInteger.valueOf(3).multiply(x).add(y));
            }
        }
        System.out.println("Part 2: " + result);
    }

    private boolean isEvenlyDivisible(BigInteger a, BigInteger b) {

        double doubleA = Double.parseDouble(a.toString());
        double doubleB = Double.parseDouble(b.toString());
        return doubleA / doubleB % 1 == 0;
    }

    private BigInteger getDeterminant(DayThirteenHelper helper) {
        return helper.getaX().multiply(helper.getbY()).subtract(helper.getbX().multiply(helper.getaY()));
    }

    private List<DayThirteenHelper> createHelperObjects() {
        List<DayThirteenHelper> result = new ArrayList<>();

        BigInteger aX = null;
        BigInteger aY = null;
        BigInteger bX = null;
        BigInteger bY = null;
        BigInteger answerX = null;
        BigInteger answerY = null;
        for (String line : input){
            String[] currentLine = line.split("[:,+=]");
            if (currentLine[0].isEmpty()){
                result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
                aX = null;
                bX = null;
                aY = null;
                bY = null;
                answerX = null;
                answerY = null;
                continue;
            }
            if ("Button A".equals(currentLine[0])){
                aX = new BigInteger(currentLine[2]);
                aY = new BigInteger(currentLine[4]);
            }
            if ("Button B".equals(currentLine[0])){
                bX = new BigInteger(currentLine[2]);
                bY = new BigInteger(currentLine[4]);
            }

            if ("Prize".equals(currentLine[0])){
                answerX = new BigInteger(currentLine[2]);
                answerY = new BigInteger(currentLine[4]);
            }

        }
        result.add(new DayThirteenHelper(aX, aY, bX, bY, answerX, answerY));
        return result;
    }

    private List<DayThirteenHelper> createHelperObjectsPart2() {
        String prefix = "10000000000000";
        List<DayThirteenHelper> result = new ArrayList<>();

        BigInteger aX = null;
        BigInteger aY = null;
        BigInteger bX = null;
        BigInteger bY = null;
        BigInteger answerX = null;
        BigInteger answerY = null;
        for (String line : input){
            String[] currentLine = line.split("[:,+=]");
            if (currentLine[0].isEmpty()){
                result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
                aX = null;
                bX = null;
                aY = null;
                bY = null;
                answerX = null;
                answerY = null;
                continue;
            }
            if ("Button A".equals(currentLine[0])){
                aX = new BigInteger(currentLine[2]);
                aY = new BigInteger(currentLine[4]);
            }
            if ("Button B".equals(currentLine[0])){
                bX = new BigInteger(currentLine[2]);
                bY = new BigInteger(currentLine[4]);
            }

            if ("Prize".equals(currentLine[0])){
                answerX = new BigInteger(prefix + currentLine[2]);
                answerY = new BigInteger(prefix + currentLine[4]);
            }

        }
        result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
        return result;
    }
}
