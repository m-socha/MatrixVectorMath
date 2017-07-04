import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by michael on 7/4/17.
 */
public class EuclideanVectorTest {

    @Test
    public void testVectorInitializationThroughList() {
        EuclideanVector v = new EuclideanVector(Arrays.asList(5.0, 6.0, 7.0));
        assertEquals(v.getSize(), 3);
        assertEquals(v.getComponentAt(0), 5.0, 0);
        assertEquals(v.getComponentAt(1), 6.0, 0);
        assertEquals(v.getComponentAt(2), 7.0, 0);
    }

    @Test
    public void testVectorInitializationThroughVarArgs() {
        EuclideanVector v = new EuclideanVector(5.0, 6.0, 7.0);
        assertEquals(v.getSize(), 3);
        assertEquals(v.getComponentAt(0), 5, 0);
        assertEquals(v.getComponentAt(1), 6, 0);
        assertEquals(v.getComponentAt(2), 7, 0);
    }

    @Test
    public void testEquals() {
        EuclideanVector v = new EuclideanVector(5.0, 6.0, 7.0);
        assertEquals(new EuclideanVector(5.0, 6.0, 7.0), v);
    }

    @Test
    public void testNotEqualsDifferentSize() {
        EuclideanVector v = new EuclideanVector(5.0, 6.0, 7.0);
        assertNotEquals(new EuclideanVector(5.0, 6.0, 7.0, 8.0), v);
    }

    @Test
    public void testNotEqualsDifferentValues() {
        EuclideanVector v = new EuclideanVector(5.0, 6.0, 7.0);
        assertNotEquals(new EuclideanVector(5.0, 6.0, 7.1), v);
    }
}
