package adventofcode2024.Day06;

import java.util.Objects;

public class CoordinateWithDirection {
    int row;
    int column;
    int dRow;
    int dCol;

    public CoordinateWithDirection(int column, int dCol, int dRow, int row) {
        this.column = column;
        this.dCol = dCol;
        this.dRow = dRow;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getdCol() {
        return dCol;
    }

    public int getdRow() {
        return dRow;
    }

    public int getRow() {
        return row;
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
}
