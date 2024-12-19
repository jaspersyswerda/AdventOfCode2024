package adventofcode2024.Day13;

import adventofcode2024.EveryDay;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DayThirteen extends EveryDay {

    List<String> input = readInputFile("DayThirteen.txt");

    public static void main(String[] args) {
        var dayThirteen = new DayThirteen();
        dayThirteen.doPart1();

    }

    private void doPart1() {
        List<DayThirteenHelper> helpers = createHelperObjects();
        helpers.forEach(System.out::println);
        long result = 0;
        for(var helper : helpers){
            long determinant = getDeterminant(helper);
            if (determinant == 0){
                continue;
            }
            long numerator_X = helper.getAnswerX() * helper.getbY() - helper.getAnswerY() * helper.getbX();

            long numerator_Y = (helper.getAnswerY() * helper.getaX() - helper.getAnswerX() * helper.getaY());

            if (isEvenlyDivisible(numerator_X, determinant) && isEvenlyDivisible(numerator_Y,determinant)){
                long x = numerator_X/determinant;
                long y = numerator_Y/determinant;
                result += 3*x + y;
            }
        }
        System.out.println("Part 1: " + result);
    }

    private boolean isEvenlyDivisible(long a, long b) {
        return a % b == 0;
    }

    private long getDeterminant(DayThirteenHelper helper) {
        return helper.getaX() * helper.getbY() - helper.getbX() * helper.getaY();
    }

    private List<DayThirteenHelper> createHelperObjects() {
        List<DayThirteenHelper> result = new ArrayList<>();

        long aX = 0;
        long aY = 0;
        long bX = 0;
        long bY = 0;
        long answerX = 0;
        long answerY = 0;
        for (String line : input){
            String[] currentLine = line.split("[:,+=]");
            if (StringUtils.isBlank(currentLine[0])){
                result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
                aX = 0;
                bX = 0;
                aY = 0;
                bY = 0;
                answerX = 0;
                answerY = 0;
                continue;
            }
            if ("Button A".equals(currentLine[0])){
                aX = Long.parseLong(currentLine[2]);
                aY = Long.parseLong(currentLine[4]);
            }
            if ("Button B".equals(currentLine[0])){
                bX = Long.parseLong(currentLine[2]);
                bY = Long.parseLong(currentLine[4]);
            }

            if ("Button B".equals(currentLine[0])){
                bX = Long.parseLong(currentLine[2]);
                bY = Long.parseLong(currentLine[4]);
            }
            if ("Prize".equals(currentLine[0])){
                answerX = Long.parseLong(currentLine[2]);
                answerY = Long.parseLong(currentLine[4]);
            }

        }
        result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
        return result;
    }
}
