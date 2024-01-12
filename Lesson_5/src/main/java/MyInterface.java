import java.lang.reflect.Method;

public interface MyInterface {
    boolean isGetter(Method method);

    void getAllMethodsForClass(Class<?> classFrom);
    void getAllGetters(Class<?> classFrom);
    void getAllFields(Class<?> classFrom);
}
