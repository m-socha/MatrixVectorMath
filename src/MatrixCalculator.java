import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 7/4/17.
 */
public class MatrixCalculator {

    public enum MatrixOperation {
        ADDITION, MINUS, DETERMINANT, TRACE
    }

    public static class MatrixUnequalSizeException extends Exception {
        private EuclideanMatrix mMatrix1;
        private EuclideanMatrix mMatrix2;
        private MatrixOperation mOperation;

        public MatrixUnequalSizeException(EuclideanMatrix matrix1, EuclideanMatrix matrix2, MatrixOperation operation) {
            super();
            mMatrix1 = matrix1;
            mMatrix2 = matrix2;
            mOperation = operation;
        }

        public EuclideanMatrix getMatrix1() {
            return mMatrix1;
        }

        public EuclideanMatrix getMatrix2() {
            return mMatrix2;
        }

        public MatrixOperation getOperation() {
            return mOperation;
        }
    }

    public static class MatrixSizesIllegibleForMultiplicationException extends Exception {
        private EuclideanMatrix mMatrix1;
        private EuclideanMatrix mMatrix2;

        public MatrixSizesIllegibleForMultiplicationException(EuclideanMatrix matrix1, EuclideanMatrix matrix2) {
            super();
            mMatrix1 = matrix1;
            mMatrix2 = matrix2;
        }

        public EuclideanMatrix getMatrix1() {
            return mMatrix1;
        }

        public EuclideanMatrix getMatrix2() {
            return mMatrix2;
        }
    }

    public static class MatrixNotSquareException extends Exception {
        private EuclideanMatrix mMatrix;
        private MatrixOperation mOperation;

        public MatrixNotSquareException(EuclideanMatrix matrix, MatrixOperation operation) {
            super();
            mMatrix = matrix;
            mOperation = operation;
        }

        public EuclideanMatrix getMatrix() {
            return mMatrix;
        }

        public MatrixOperation getOperation() {
            return mOperation;
        }
    }

    public static EuclideanMatrix add(EuclideanMatrix m1, EuclideanMatrix m2) throws MatrixUnequalSizeException {
        if (m1.getNumRows() != m2.getNumRows() || m1.getNumCols() != m2.getNumCols()) {
            throw new MatrixUnequalSizeException(m1, m2, MatrixOperation.ADDITION);
        }

        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, m1.getNumRows());

        for (int row = 0; row < m1.getNumRows(); row++) {
            for (int col = 0; col < m1.getNumCols(); col++) {
                double sum = m1.getEntryAt(row, col) + m2.getEntryAt(row, col);
                entries.get(row).add(col, sum);
            }
        }

        return new EuclideanMatrix(entries);
    }

    public static EuclideanMatrix subtract(EuclideanMatrix m1, EuclideanMatrix m2) throws MatrixUnequalSizeException {
        if (m1.getNumRows() != m2.getNumRows() || m1.getNumCols() != m2.getNumCols()) {
            throw new MatrixUnequalSizeException(m1, m2, MatrixOperation.MINUS);
        }

        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, m1.getNumRows());

        for (int row = 0; row < m1.getNumRows(); row++) {
            for (int col = 0; col < m1.getNumCols(); col++) {
                double sum = m1.getEntryAt(row, col) - m2.getEntryAt(row, col);
                entries.get(row).add(col, sum);
            }
        }

        return new EuclideanMatrix(entries);
    }

    public static EuclideanMatrix multiply(EuclideanMatrix m1, EuclideanMatrix m2) throws MatrixSizesIllegibleForMultiplicationException {
        if (m1.getNumCols() != m2.getNumRows()) {
            throw new MatrixSizesIllegibleForMultiplicationException(m1, m2);
        }

        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, m1.getNumRows());

        for (int row = 0; row < m1.getNumRows(); row++) {
            for (int col = 0; col < m2.getNumCols(); col++) {
                double sum = 0;
                for (int i = 0; i < m1.getNumCols(); i++) {
                    sum += m1.getEntryAt(row, i) * m2.getEntryAt(i, col);
                }
                entries.get(row).add(col, sum);
            }
        }

        return new EuclideanMatrix(entries);
    }

    public static EuclideanMatrix exponentiate(EuclideanMatrix matrix, int exponent) throws MatrixSizesIllegibleForMultiplicationException {
        if (matrix.getNumCols() != matrix.getNumRows()) {
            throw new MatrixSizesIllegibleForMultiplicationException(matrix, matrix);
        }

        EuclideanMatrix result = matrix;
        for (int i = 1; i < exponent; i++) {
            result = MatrixCalculator.multiply(result, matrix);
        }

        return result;
    }

    public static EuclideanMatrix scale(EuclideanMatrix matrix, double scaleFactor) {
        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, matrix.getNumRows());

        for (int row = 0; row < matrix.getNumRows(); row++) {
            for (int col = 0; col < matrix.getNumCols(); col++) {
                entries.get(row).add(col, matrix.getEntryAt(row, col) * scaleFactor);
            }
        }

        return new EuclideanMatrix(entries);
    }

    public static double determinant(EuclideanMatrix matrix) throws MatrixNotSquareException {
        if (matrix.getNumRows() != matrix.getNumCols()) {
            throw new MatrixNotSquareException(matrix, MatrixOperation.DETERMINANT);
        }

        int dim = matrix.getNumRows();

        switch (dim) {
            case 1:
                return matrix.getEntryAt(0, 0);
            case 2:
                return matrix.getEntryAt(0, 0)*matrix.getEntryAt(1, 1) - matrix.getEntryAt(1, 0)*matrix.getEntryAt(0, 1);
            default:
                EuclideanMatrix matrixWithoutTopRow = matrixWithoutRow(matrix, 0);
                double result = 0;
                for (int i = 0; i < matrixWithoutTopRow.getNumCols(); i++) {
                    EuclideanMatrix matrixWithoutCol = matrixWithoutCol(matrixWithoutTopRow, i);
                    result += Math.pow(-1, i) * matrix.getEntryAt(0, i) * determinant(matrixWithoutCol);
                }
                return result;
        }
    }

    public static double trace(EuclideanMatrix matrix) throws MatrixNotSquareException {
        if (matrix.getNumRows() != matrix.getNumCols()) {
            throw new MatrixNotSquareException(matrix, MatrixOperation.TRACE);
        }

        int dim = matrix.getNumRows();
        double sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += matrix.getEntryAt(i, i);
        }
        return sum;
    }

    public static EuclideanMatrix transpose(EuclideanMatrix matrix) {
        List<List<Double>> transposedEntries = new ArrayList();
        populateEntriesWithEmptyRows(transposedEntries, matrix.getNumCols());

        for (int row = 0; row < matrix.getNumRows(); row++) {
            for (int col = 0; col < matrix.getNumCols(); col++) {
                transposedEntries.get(col).add(row, matrix.getEntryAt(row, col));
            }
        }

        return new EuclideanMatrix(transposedEntries);
    }

    private static EuclideanMatrix matrixWithoutRow(EuclideanMatrix matrix, int rowToRemove) {
        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, matrix.getNumRows() - 1);

        for (int row = 0; row < matrix.getNumRows(); row++) {
            if (row == rowToRemove) {
                continue;
            }

            for (int col = 0; col < matrix.getNumCols(); col++) {
                int rowToSet = (row < rowToRemove) ? row : row - 1;
                entries.get(rowToSet).add(col, matrix.getEntryAt(row, col));
            }
        }

        return new EuclideanMatrix(entries);
    }

    private static EuclideanMatrix matrixWithoutCol(EuclideanMatrix matrix, int colToRemove) {
        List<List<Double>> entries = new ArrayList();
        populateEntriesWithEmptyRows(entries, matrix.getNumRows());

        for (int row = 0; row < matrix.getNumRows(); row++) {
            for (int col = 0; col < matrix.getNumCols(); col++) {
                if (col == colToRemove) {
                    continue;
                }

                int colToSet = (col < colToRemove) ? col : col - 1;
                entries.get(row).add(colToSet, matrix.getEntryAt(row, col));
            }
        }

        return new EuclideanMatrix(entries);
    }

    private static void populateEntriesWithEmptyRows(List<List<Double>> entries, int numRows) {
        for (int i = 0; i < numRows; i++) {
            entries.add(i, new ArrayList());
        }
    }

}
