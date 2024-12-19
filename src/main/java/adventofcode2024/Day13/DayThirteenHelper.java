package adventofcode2024.Day13;

public class DayThirteenHelper {
    int aX;
    int aY;
    int bX;
    int bY;
    int answerX;
    int answerY;

    public DayThirteenHelper(int aX, int aY, int bX, int bY, int answerX, int answerY) {
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

    public int getAnswerX() {
        return answerX;
    }

    public int getAnswerY() {
        return answerY;
    }

    public int getaX() {
        return aX;
    }

    public int getbX() {
        return bX;
    }

    public int getaY() {
        return aY;
    }

    public int getbY() {
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
