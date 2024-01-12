import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Instant;
import java.util.*;

public class PerformanceProxy implements InvocationHandler {
    private final Object delegate;
    long start, end;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) return invoke(method, args);
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        start = System.nanoTime();
        try {
            return invoke(method, args);
        } finally {
            end = System.nanoTime();
            System.out.println("Время работы метода " + className + "." + methodName + " :: " + (end - start) + " ns");
        }
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
