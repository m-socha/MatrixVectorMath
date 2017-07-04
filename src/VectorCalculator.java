import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 7/4/17.
 */
public class VectorCalculator {

    private static final int CROSS_PRODUCT_VECTOR_SIZE = 3;

    public enum VectorOperation {
        PROJECTION, ADDITION, MINUS, CROSS_PRODUCT, DOT_PRODUCT
    }

    public static class VectorUnequalSizeException extends Exception {
        private EuclideanVector mVector1;
        private EuclideanVector mVector2;
        private VectorOperation mOperation;

        public VectorUnequalSizeException(EuclideanVector vector1, EuclideanVector vector2, VectorOperation operation) {
            super();
            mVector1 = vector1;
            mVector2 = vector2;
            mOperation = operation;
        }

        public EuclideanVector getVector1() {
            return mVector1;
        }

        public EuclideanVector getVector2() {
            return mVector2;
        }

        public VectorOperation getOperation() {
            return mOperation;
        }
    }

    public static class VectorIncorrectSizeException extends Exception {
        private EuclideanVector mVector;
        private int mIntendedSize;
        private VectorOperation mOperation;

        public VectorIncorrectSizeException(EuclideanVector vector, int intendedSize, VectorOperation operation) {
            super();
            mVector = vector;
            mIntendedSize =  intendedSize;
            mOperation = operation;
        }

        public EuclideanVector getVector() {
            return mVector;
        }

        public int getIntendedSize() {
            return mIntendedSize;
        }

        public VectorOperation getOperation() {
            return mOperation;
        }
    }

    public static class DivisionByZeroException extends Exception {
        private VectorOperation mOperation;

        public DivisionByZeroException(VectorOperation operation) {
            super();
            mOperation = operation;
        }

        public VectorOperation getOperation() {
            return mOperation;
        }
    }

    public static EuclideanVector projection(EuclideanVector base, EuclideanVector vector) throws VectorUnequalSizeException, DivisionByZeroException {
        if (base.getSize() != vector.getSize()) {
            throw new VectorUnequalSizeException(base, vector, VectorOperation.PROJECTION);
        }

        double numerator = VectorCalculator.dotProduct(base, vector);
        double denominator = VectorCalculator.dotProduct(base, base);

        if (denominator == 0) {
            throw new DivisionByZeroException(VectorOperation.PROJECTION);
        }

        EuclideanVector result = VectorCalculator.scale(base, numerator / denominator);

        return result;
    }

    public static EuclideanVector add(EuclideanVector v1, EuclideanVector v2) throws VectorUnequalSizeException {
        if (v1.getSize() != v2.getSize()) {
            throw new VectorUnequalSizeException(v1, v2, VectorOperation.ADDITION);
        }

        List<Double> result = new ArrayList();
        for (int i = 0; i < v1.getSize(); i++) {
            result.add(v1.getComponentAt(i) + v2.getComponentAt(i));
        }
        return new EuclideanVector(result);
    }

    public static EuclideanVector subtract(EuclideanVector v1, EuclideanVector v2) throws VectorUnequalSizeException {
        if (v1.getSize() != v2.getSize()) {
            throw new VectorUnequalSizeException(v1, v2, VectorOperation.MINUS);
        }

        List<Double> result = new ArrayList();
        for (int i = 0; i < v1.getSize(); i++) {
            result.add(v1.getComponentAt(i) - v2.getComponentAt(i));
        }
        return new EuclideanVector(result);
    }

    public static EuclideanVector cross(EuclideanVector v1, EuclideanVector v2) throws VectorIncorrectSizeException {
        for (EuclideanVector v : new EuclideanVector[] {v1, v2}) {
            if (v.getSize() != CROSS_PRODUCT_VECTOR_SIZE) {
                throw new VectorIncorrectSizeException(v, CROSS_PRODUCT_VECTOR_SIZE, VectorOperation.CROSS_PRODUCT);
            }
        }

        List<Double> result = new ArrayList();
        result.add(v1.getComponentAt(1)*v2.getComponentAt(2) - v1.getComponentAt(2)*v2.getComponentAt(1));
        result.add(v1.getComponentAt(2)*v2.getComponentAt(0) - v1.getComponentAt(0)*v2.getComponentAt(2));
        result.add(v1.getComponentAt(0)*v2.getComponentAt(1) - v1.getComponentAt(1)*v2.getComponentAt(0));
        return new EuclideanVector(result);
    }

    public static EuclideanVector scale(EuclideanVector v, Double scaleFactor) {
        List<Double> result = new ArrayList();
        for (int i = 0; i < v.getSize(); i++) {
            result.add(v.getComponentAt(i) * scaleFactor);
        }
        return new EuclideanVector(result);
    }

    public static double dotProduct(EuclideanVector v1, EuclideanVector v2) throws VectorUnequalSizeException {
        if (v1.getSize() != v2.getSize()) {
            throw new VectorUnequalSizeException(v1, v2, VectorOperation.DOT_PRODUCT);
        }

        double result = 0;
        for (int i = 0; i < v1.getSize(); i++) {
            result += (v1.getComponentAt(i) * v2.getComponentAt(i));
        }
        return result;
    }

    public static double magnitude(EuclideanVector v) {
        double sumOfSquares = 0;
        for (int i = 0; i < v.getSize(); i++) {
            sumOfSquares += Math.pow(v.getComponentAt(i), 2);
        }
        return Math.sqrt(sumOfSquares);
    }
}
