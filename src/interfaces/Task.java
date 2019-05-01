package interfaces;

/**
 * .
 * interface for single task with generic value
 *
 * @param <T> - the generic value it gets
 */
public interface Task<T> {
    /**
     * .
     * runs the Task (used as anonymous class
     *
     * @return - Generic (T) value
     */
    T run();
}
