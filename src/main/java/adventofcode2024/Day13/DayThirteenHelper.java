package adventofcode2024.Day13;

public class DayThirteenHelper {
    long aX;
    long aY;
    long bX;
    long bY;
    long answerX;
    long answerY;

    public DayThirteenHelper(long aX, long aY, long bX, long bY, long answerX, long answerY) {
        if (aX == 0 || bX == 0 || aY == 0|| bY == 0 || answerX == 0 || answerY == 0){
            throw new IllegalArgumentException("Er is een waarde 0");
        }
        this.answerX = answerX;
        this.answerY = answerY;
        this.aX = aX;
        this.bX = bX;
        this.aY = aY;
        this.bY = bY;
    }

    public long getAnswerX() {
        return answerX;
    }

    public long getAnswerY() {
        return answerY;
    }

    public long getaX() {
        return aX;
    }

    public long getbX() {
        return bX;
    }

    public long getaY() {
        return aY;
    }

    public long getbY() {
        return bY;
    }


    @Override
    public String toString() {
        return "DayThirteenHelper{" +
                "answerX=" + answerX +
                ", aX=" + aX +
                ", aY=" + aY +
                ", bX=" + bX +
                ", bY=" + bY +
                ", answerY=" + answerY +
                '}';
    }
}
