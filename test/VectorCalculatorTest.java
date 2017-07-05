import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by michael on 7/4/17.
 */
public class VectorCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void projectionSizeMismatchTest() throws VectorCalculator.VectorUnequalSizeException, VectorCalculator.DivisionByZeroException {
        EuclideanVector base = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector vector = new EuclideanVector(5.0, -2.0, 42.1, 4.1);

        expectedException.expect(VectorCalculator.VectorUnequalSizeException.class);
        VectorCalculator.projection(base, vector);
    }

    @Test
    public void projectionDivideByZeroTest() throws VectorCalculator.VectorUnequalSizeException, VectorCalculator.DivisionByZeroException {
        EuclideanVector base = new EuclideanVector(0.0, 0.0, 0.0);
        EuclideanVector vector = new EuclideanVector(5.0, -2.0, 42.1);

        expectedException.expect(VectorCalculator.DivisionByZeroException.class);
        VectorCalculator.projection(base, vector);
    }

    @Test
    public void projectionTest() throws VectorCalculator.VectorUnequalSizeException, VectorCalculator.DivisionByZeroException {
        EuclideanVector base = new EuclideanVector(5.0, -1.0, 2.0);
        EuclideanVector vector = new EuclideanVector(-24.0, 12.0, 36.0);

        EuclideanVector result = VectorCalculator.projection(base, vector);

        assertEquals(new EuclideanVector(-10.0, 2.0, -4.0), result);
    }

    @Test
    public void addSizeMismatchTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector v2 = new EuclideanVector(5.0, -2.0, 42.1, 4.1);

        expectedException.expect(VectorCalculator.VectorUnequalSizeException.class);
        VectorCalculator.add(v1, v2);
    }

    @Test
    public void addTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 0.2);

        EuclideanVector result = VectorCalculator.add(v1, v2);

        assertEquals(new EuclideanVector(6.0, -3.0, 42.3), result);
    }

    @Test
    public void subtractSizeMismatchTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector v2 = new EuclideanVector(5.0, -2.0, 42.1, 4.1);

        expectedException.expect(VectorCalculator.VectorUnequalSizeException.class);
        VectorCalculator.subtract(v1, v2);
    }

    @Test
    public void subtractTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 0.2);

        EuclideanVector result = VectorCalculator.subtract(v1, v2);

        assertEquals(new EuclideanVector(4.0, -1.0, 41.9), result);
    }

    @Test
    public void crossTestFirstVectorIncorrectSize() throws VectorCalculator.VectorIncorrectSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1, 5.0);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 0.2);

        expectedException.expect(VectorCalculator.VectorIncorrectSizeException.class);
        VectorCalculator.cross(v1, v2);
    }

    @Test
    public void crossTestSecondVectorIncorrectSize() throws VectorCalculator.VectorIncorrectSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, -2.0, 42.1);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 0.2, -4.5);

        expectedException.expect(VectorCalculator.VectorIncorrectSizeException.class);
        VectorCalculator.cross(v1, v2);
    }

    @Test
    public void crossTest() throws VectorCalculator.VectorIncorrectSizeException {
        EuclideanVector v1 = new EuclideanVector(3.0, -2.0, 4.0);
        EuclideanVector v2 = new EuclideanVector(9.0, 5.0, -1.0);

        EuclideanVector result = VectorCalculator.cross(v1, v2);
        assertEquals(new EuclideanVector(-18.0, 39.0, 33.0), result);
    }

    @Test
    public void multiplyWithMatrixSizeMismatchTest() throws VectorCalculator.VectorMatrixMultiplicationIncorrectSizeException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(4.0, 5.0, 4.0, 5.0),
                Arrays.asList(4.0, 5.0, 2.0, 5.0)
        );
        EuclideanVector vector = new EuclideanVector(3.0, -2.0, 4.0);

        expectedException.expect(VectorCalculator.VectorMatrixMultiplicationIncorrectSizeException.class);
        VectorCalculator.multiplyWithMatrix(matrix, vector);
    }

    @Test
    public void multiplyWithMatrixTest() throws VectorCalculator.VectorMatrixMultiplicationIncorrectSizeException {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(4.0, 5.0, -4.0),
                Arrays.asList(14.0, -5.0, 2.0)
        );
        EuclideanVector vector = new EuclideanVector(3.0, -2.0, 4.0);

        EuclideanVector result = VectorCalculator.multiplyWithMatrix(matrix, vector);
        assertEquals(new EuclideanVector(-14.0, 60.0), result);
    }

    @Test
    public void scaleTest() {
        EuclideanVector v = new EuclideanVector(-1.0, -2.0, 2.0);
        EuclideanVector result = VectorCalculator.scale(v, -2.5);
        assertEquals(new EuclideanVector(2.5, 5.0, -5.0), result);
    }

    @Test
    public void dotProductSizeMismatchTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, 3.0, 2.5);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 2.5, 2.5);

        expectedException.expect(VectorCalculator.VectorUnequalSizeException.class);
        VectorCalculator.dotProduct(v1, v2);
    }

    @Test
    public void dotProductSizeTest() throws VectorCalculator.VectorUnequalSizeException {
        EuclideanVector v1 = new EuclideanVector(5.0, 3.0, 2.5);
        EuclideanVector v2 = new EuclideanVector(1.0, -1.0, 2.5);

        double result = VectorCalculator.dotProduct(v1, v2);

        assertEquals(8.25, result, 0);
    }

    @Test
    public void magnitudeTest() {
        EuclideanVector v = new EuclideanVector(-1.0, -2.0, 2.0);
        double result = VectorCalculator.magnitude(v);
        assertEquals(result, 3.0, 0);
    }
}
