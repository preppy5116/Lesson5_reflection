import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class BeanUtils {

    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     * <p>
     * param to Object which properties will be set.
     * param from Object which properties will be used to get values.
     */

    public static void assign(Object to, Object from) {
        Method[] methodsFrom = from.getClass().getMethods();
        Method[] methodsTo = to.getClass().getMethods();

        List<Method> getterList = new ArrayList<>();
        List<Method> setterList = new ArrayList<>();

        for (Method method : methodsFrom) {
            if ((method.getName().startsWith("get")) && (method.getParameterTypes().length == 0) &&
                    (!void.class.equals(method.getReturnType()))) {
                getterList.add(method);
            }
        }

        for (Method method : methodsTo) {
            if ((method.getName().startsWith("set")) && (method.getParameterTypes().length == 1)) {
                setterList.add(method);
            }
        }

        for (Method method : getterList) {
            for (Method method1 : setterList) {
                if (isCompatible(method1,method)) {
                    try {
                        method1.invoke(to, method.invoke(from));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static boolean isCompatible(Method setter, Method getter) {
        Class returnType = getter.getReturnType();
        Class parameterType = setter.getParameterTypes()[0];
        return parameterType.isAssignableFrom(returnType);
    }

}
