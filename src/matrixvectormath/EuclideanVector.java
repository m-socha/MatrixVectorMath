package matrixvectormath;

import java.util.Arrays;
import java.util.List;

/**
 * Created by michael on 7/4/17.
 */
public class EuclideanVector {

    private static final double EPSILON = 0.000001;

    final private List<Double> mComponents;

    public EuclideanVector(Double... components) {
        mComponents = Arrays.asList(components);
    }

    public EuclideanVector(List<Double> components) {
        mComponents = components;
    }

    public double getComponentAt(int index) {
        return mComponents.get(index);
    }

    public int getSize() {
        return mComponents.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EuclideanVector) {
            EuclideanVector v = (EuclideanVector) o;

            if (getSize() != v.getSize()) {
                return false;
            }

            for (int i = 0; i < getSize(); i++) {
                if (Math.abs(getComponentAt(i) - v.getComponentAt(i)) > EPSILON) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mComponents.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < mComponents.size(); i++) {
            builder.append(mComponents.get(i));
            if (i < mComponents.size() - 1) {
                builder.append(", ");
            }
        }

        builder.append("]");

        return builder.toString();
    }
}
