package adventofcode2024.Day13;

import java.math.BigInteger;

public class DayThirteenHelper {
    BigInteger aX;
    BigInteger aY;
    BigInteger bX;
    BigInteger bY;
    BigInteger answerX;
    BigInteger answerY;

    public DayThirteenHelper(BigInteger aX, BigInteger aY, BigInteger bX, BigInteger bY, BigInteger answerX, BigInteger answerY) {
        if (aX == null || bX ==  null || aY ==  null|| bY == null || answerX == null || answerY == null){
            throw new IllegalArgumentException("Er is een waarde 0");
        }
        this.answerX = answerX;
        this.answerY = answerY;
        this.aX = aX;
        this.bX = bX;
        this.aY = aY;
        this.bY = bY;
    }

    public BigInteger getAnswerX() {
        return answerX;
    }

    public BigInteger getAnswerY() {
        return answerY;
    }

    public BigInteger getaX() {
        return aX;
    }

    public BigInteger getbX() {
        return bX;
    }

    public BigInteger getaY() {
        return aY;
    }

    public BigInteger getbY() {
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
