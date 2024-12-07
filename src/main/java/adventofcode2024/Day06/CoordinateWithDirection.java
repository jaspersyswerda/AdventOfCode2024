package adventofcode2024.Day06;

import java.util.Objects;

public class CoordinateWithDirection extends Coordinate {
    int dRow;
    int dCol;

    public CoordinateWithDirection(int row, int column, int dRow, int dCol) {
        super(row, column);
        this.dCol = dCol;
        this.dRow = dRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateWithDirection that = (CoordinateWithDirection) o;
        return row == that.row && column == that.column && dRow == that.dRow && dCol == that.dCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, dRow, dCol);
    }

    @Override
    public String toString() {
        return "CoordinateWithDirection{" +
                "column=" + column +
                ", row=" + row +
                ", dRow=" + dRow +
                ", dCol=" + dCol +
                '}';
    }

    public CoordinateWithDirection turnRight() {
        int newDRow = dCol;
        int newDCol = -dRow;

        return new CoordinateWithDirection(this.row, this.column, newDRow, newDCol);

    }
}
