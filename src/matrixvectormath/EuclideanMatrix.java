package matrixvectormath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 7/4/17.
 */
public class EuclideanMatrix {

    private static final double EPSILON = 0.000001;

    List<List<Double>> mEntries;

    public EuclideanMatrix(List<Double>... rows) {
        List<List<Double>> entries = new ArrayList();
        for (List<Double> row : rows) {
            entries.add(row);
        }
        mEntries = entries;
    }

    public EuclideanMatrix(List<List<Double>> entries) {
        mEntries = entries;
    }

    public double getEntryAt(int row, int col) {
        return mEntries.get(row).get(col);
    }

    public int getNumRows() {
        return mEntries.size();
    }

    public int getNumCols() {
        return mEntries.get(0).size();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EuclideanMatrix) {
            EuclideanMatrix matrix = (EuclideanMatrix) o;

            if (getNumRows() != matrix.getNumRows() || getNumCols() != matrix.getNumCols()) {
                return false;
            }

            for (int row = 0; row < getNumRows(); row++) {
                for (int col = 0; col < getNumCols(); col++) {
                    if (Math.abs(getEntryAt(row, col) - matrix.getEntryAt(row, col)) > EPSILON) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < getNumRows(); row++) {
            StringBuilder rowBuilder = new StringBuilder();

            rowBuilder.append("[");

            for (int col = 0; col < getNumCols(); col++) {
                rowBuilder.append(getEntryAt(row, col));
                if (col < getNumCols() - 1) {
                    rowBuilder.append(", ");
                }
            }

            rowBuilder.append("]");

            builder.append(rowBuilder.toString());
            if (row < getNumRows() - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
