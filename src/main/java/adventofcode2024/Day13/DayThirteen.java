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
        int result = 0;
        for(var helper : helpers){
            int determinant = getDeterminant(helper);
            if (determinant == 0){
                continue;
            }
            int numerator_X = helper.getAnswerX() * helper.getbY() - helper.getAnswerY() * helper.getbX();

            int numerator_Y = (helper.getAnswerY() * helper.getaX() - helper.getAnswerX() * helper.getaY());

            if (isEvenlyDivisible(numerator_X, determinant) && isEvenlyDivisible(numerator_Y,determinant)){
                int x = numerator_X/determinant;
                int y = numerator_Y/determinant;
                result += 3*x + y;
            }
        }
        System.out.println(result);
    }

    private boolean isEvenlyDivisible(int a, int b) {
        return a % b == 0;
    }

    private int getDeterminant(DayThirteenHelper helper) {
        return helper.getaX() * helper.getbY() - helper.getbX() * helper.getaY();
    }

    private List<DayThirteenHelper> createHelperObjects() {
        List<DayThirteenHelper> result = new ArrayList<>();

        int aX = 0;
        int aY = 0;
        int bX = 0;
        int bY = 0;
        int answerX = 0;
        int answerY = 0;
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
                aX = Integer.parseInt(currentLine[2]);
                aY = Integer.parseInt(currentLine[4]);
            }
            if ("Button B".equals(currentLine[0])){
                bX = Integer.parseInt(currentLine[2]);
                bY = Integer.parseInt(currentLine[4]);
            }

            if ("Button B".equals(currentLine[0])){
                bX = Integer.parseInt(currentLine[2]);
                bY = Integer.parseInt(currentLine[4]);
            }
            if ("Prize".equals(currentLine[0])){
                answerX = Integer.parseInt(currentLine[2]);
                answerY = Integer.parseInt(currentLine[4]);
            }

        }
        result.add(new DayThirteenHelper(aX, aY, bX, bY,answerX, answerY));
        return result;
    }
}
