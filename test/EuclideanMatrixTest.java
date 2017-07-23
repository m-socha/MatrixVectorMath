import matrixvectormath.EuclideanMatrix;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by michael on 7/4/17.
 */
public class EuclideanMatrixTest {

    @Test
    public void testInitializationWithListOfLists() {
        EuclideanMatrix matrix = new EuclideanMatrix(Arrays.asList(
                Arrays.asList(5.0, -3.0, 1.0),
                Arrays.asList(-5.0, -5.0, 7.0)
        ));

        assertEquals(2, matrix.getNumRows());
        assertEquals(3, matrix.getNumCols());

        assertEquals(matrix.getEntryAt(0, 0), 5.0, 0);
        assertEquals(matrix.getEntryAt(0, 1), -3.0, 0);
        assertEquals(matrix.getEntryAt(0, 2), 1.0, 0);
        assertEquals(matrix.getEntryAt(1, 0), -5.0, 0);
        assertEquals(matrix.getEntryAt(1, 1), -5.0, 0);
        assertEquals(matrix.getEntryAt(1, 2), 7.0, 0);
    }

    @Test
    public void testInitializationWithVarArgs() {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(5.0, -3.0, 1.0),
                Arrays.asList(-5.0, -5.0, 7.0)
        );

        assertEquals(2, matrix.getNumRows());
        assertEquals(3, matrix.getNumCols());

        assertEquals(matrix.getEntryAt(0, 0), 5.0, 0);
        assertEquals(matrix.getEntryAt(0, 1), -3.0, 0);
        assertEquals(matrix.getEntryAt(0, 2), 1.0, 0);
        assertEquals(matrix.getEntryAt(1, 0), -5.0, 0);
        assertEquals(matrix.getEntryAt(1, 1), -5.0, 0);
        assertEquals(matrix.getEntryAt(1, 2), 7.0, 0);
    }

    @Test
    public void testEquals() {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void testNotEqualsDifferentRowCount() {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0),
                Arrays.asList(4.0, -1.0, 2.0)
        );

        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        assertNotEquals(expectedMatrix, matrix);
    }

    @Test
    public void testNotEqualsDifferentColCount() {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0, 2.0),
                Arrays.asList(5.0, -15.0, 8.0, -3.0)
        );

        assertNotEquals(expectedMatrix, matrix);
    }

    @Test
    public void testNotEqualsDifferentValues() {
        EuclideanMatrix matrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.1, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        EuclideanMatrix expectedMatrix = new EuclideanMatrix(
                Arrays.asList(55.0, -14.0, 11.0),
                Arrays.asList(5.0, -15.0, 8.0)
        );

        assertNotEquals(expectedMatrix, matrix);
    }
}
