import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by michael on 7/4/17.
 */
public class MatrixCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddRowMismatch() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        expectedException.expect(MatrixCalculator.MatrixUnequalSizeException.class);
        MatrixCalculator.add(m1, m2);
    }

    @Test
    public void testAddColMismatch() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0),
                Arrays.asList(1.0, -5.0)
        );

        expectedException.expect(MatrixCalculator.MatrixUnequalSizeException.class);
        MatrixCalculator.add(m1, m2);
    }

    @Test
    public void testAdd() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        EuclideanMatrix result = MatrixCalculator.add(m1, m2);
        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(17.0, 0.0, 14.0),
                Arrays.asList(8.0, -7.0, -8.0)
        );
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testSubtractRowMismatch() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0),
                Arrays.asList(7.0, -2.0, -3.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        expectedException.expect(MatrixCalculator.MatrixUnequalSizeException.class);
        MatrixCalculator.subtract(m1, m2);
    }

    @Test
    public void testSubtractColMismatch() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0, 0.0),
                Arrays.asList(7.0, -2.0, -2.0, 8.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0),
                Arrays.asList(1.0, -5.0)
        );

        expectedException.expect(MatrixCalculator.MatrixUnequalSizeException.class);
        MatrixCalculator.subtract(m1, m2);
    }

    @Test
    public void testSubtract() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        EuclideanMatrix result = MatrixCalculator.subtract(m1, m2);
        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(-7.0, -8.0, -10.0),
                Arrays.asList(6.0, 3.0, 4.0)
        );
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testMultiplyIllegibleSizes() throws MatrixCalculator.MatrixSizesIllegibleForMultiplicationException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        expectedException.expect(MatrixCalculator.MatrixSizesIllegibleForMultiplicationException.class);
        MatrixCalculator.multiply(m1, m2);
    }

    @Test
    public void testMultiply() throws MatrixCalculator.MatrixSizesIllegibleForMultiplicationException {
        EuclideanMatrix m1 = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );
        EuclideanMatrix m2 = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0, 5.0),
                Arrays.asList(1.0, -5.0, -6.0, -2.0),
                Arrays.asList(-1.0, 2.0, -2.0, -1.0)
        );

        EuclideanMatrix result = MatrixCalculator.multiply(m1, m2);
        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(54.0, 44.0, 80.0, 31.0),
                Arrays.asList(84.0, 34.0, 100.0, 41.0)
        );
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testExponentiateIllegibleSizes() throws MatrixCalculator.MatrixSizesIllegibleForMultiplicationException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(-5.0, -4.0, 2.0),
                Arrays.asList(7.0, 12.0, -2.0)
        );

        expectedException.expect(MatrixCalculator.MatrixSizesIllegibleForMultiplicationException.class);
        MatrixCalculator.exponentiate(matrix, 4);
    }

    @Test
    public void testExponentiate() throws MatrixCalculator.MatrixSizesIllegibleForMultiplicationException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(-5.0, -4.0, 2.0),
                Arrays.asList(7.0, 12.0, -2.0),
                Arrays.asList(17.0, -22.0, 7.0)
        );

        EuclideanMatrix result = MatrixCalculator.exponentiate(matrix, 4);
        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(-1559.0, -19584.0, 3624.0),
                Arrays.asList(5745.0, 36184.0, -6708.0),
                Arrays.asList(-26250.0, -130842.0, 26353.0)
        );
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testScale() throws MatrixCalculator.MatrixUnequalSizeException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(5.0, -4.0, 2.0),
                Arrays.asList(7.0, -2.0, -2.0)
        );

        EuclideanMatrix result = MatrixCalculator.scale(matrix, -2.5);
        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(-12.5, 10.0, -5.0),
                Arrays.asList(-17.5, 5.0, 5.0)
        );
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testDeterminantNotSquareMatrix() throws MatrixCalculator.MatrixNotSquareException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(12.0, 4.0, 12.0),
                Arrays.asList(1.0, -5.0, -6.0)
        );

        expectedException.expect(MatrixCalculator.MatrixNotSquareException.class);
        MatrixCalculator.determinant(matrix);
    }

    @Test
    public void testDeterminantSingleEntry() throws MatrixCalculator.MatrixNotSquareException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(12.0)
        );

        double result = MatrixCalculator.determinant(matrix);
        assertEquals(12.0, result, 0);
    }

    @Test
    public void testDeterminantTwoByTwo() throws MatrixCalculator.MatrixNotSquareException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(12.0, -4.0),
                Arrays.asList(1.0, -5.0)
        );

        double result = MatrixCalculator.determinant(matrix);
        assertEquals(-56, result, 0);
    }

    @Test
    public void testDeterminantFourByFour() throws MatrixCalculator.MatrixNotSquareException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(13.0, -4.0, 5.0, 1.0),
                Arrays.asList(2.0, -5.0, 4.0, 2.0),
                Arrays.asList(-1.0, 5.0, 41.0, -3.0),
                Arrays.asList(-11.0, -7.0, 21.0, 2.0)
        );

        double result = MatrixCalculator.determinant(matrix);
        assertEquals(2952, result, 0);
    }
}
