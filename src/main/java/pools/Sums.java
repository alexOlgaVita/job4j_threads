package pools;

import java.util.Objects;

public class Sums {

    private int rowSum;
    private int colSum;

    public Sums() {
        new Sums(0, 0);
    }

    public Sums(int rowSum, int colSum) {
        this.rowSum = rowSum;
        this.colSum = colSum;
    }

    public int getRowSum() {
        return rowSum;
    }

    public int getColSum() {
        return colSum;
    }

    public void setRowSum(int rowSum) {
        this.rowSum = rowSum;
    }

    public void setColSum(int colSum) {
        this.colSum = colSum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSum, colSum);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Sums sums = (Sums) obj;
        return (rowSum == sums.rowSum) && (colSum == sums.colSum);
    }
}
