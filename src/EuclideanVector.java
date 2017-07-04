import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michael on 7/4/17.
 */
public class EuclideanVector<T extends Number> {

    final private List<T> mComponents;

    public EuclideanVector(T... components) {
        mComponents = Arrays.asList(components);
    }

    public EuclideanVector(List<T> components) {
        mComponents = components;
    }

    public T getComponentAt(int index) {
        return mComponents.get(index);
    }

    public int getSize() {
        return mComponents.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EuclideanVector) {
            EuclideanVector v = (EuclideanVector) o;
            return mComponents.equals(v.mComponents);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mComponents.hashCode();
    }
}
