import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.List;

@SuppressWarnings("ALL")
public class MyInterfaceImpl implements MyInterface {

    /*Вывести на консоль все методы класса, включая все родительские методы*/
    @Override
    public void getAllMethodsForClass(Class<?> classFrom) {

        List publicList = new ArrayList();
        List privateList = new ArrayList();
        Class<?> cl = classFrom;


        while (classFrom != null) {
            Method[] arrMetods = classFrom.getDeclaredMethods();
            for (int i = 0; i < arrMetods.length; i++) {
                if (arrMetods[i].getModifiers() == Modifier.PUBLIC) {
                    publicList.add(arrMetods[i]);
                } else privateList.add(arrMetods[i]);
            }
            classFrom = classFrom.getSuperclass();
        }

        System.out.println("Список публичных методов для класса  "
                + cl.getSimpleName() + " и его предков");
        for (Object m : publicList) {
            System.out.println(m);
        }
        System.out.println();
        System.out.println("Список закрытых методов для класса "
                + cl.getSimpleName() + " и его предков");
        for (Object m : privateList) {
            System.out.println(m);
        }
    }

    @Override
    public void getAllGetters(Class<?> classFrom) {

        List publicList = new ArrayList();
        List privateList = new ArrayList();

        Method[] methods = classFrom.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
            if (isGetter(methods[i])) publicList.add(methods[i]);

        System.out.println("Геттеры класса " + classFrom.getSimpleName());
        for (Object o : publicList) {
            System.out.println(o);
        }
    }
    @Override
    public void getAllFields(Class<?> classFrom) {
        Object value = null;
        Class c = null;
        Object obj = null;
        Class<?> cl = classFrom;
        while (classFrom != null) {
            try {
                c = Class.forName(classFrom.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                obj = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Field[] declaredFields = classFrom.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                try {
                    value = (declaredFields[i].get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (Modifier.isFinal(declaredFields[i].getModifiers())) {
                    if (declaredFields[i].getType().getSimpleName().equals("String")) {
                        if (declaredFields[i].getName() == value.toString()) {
                            System.out.println("Поле константы " + declaredFields[i].getName()
                                    + " совпадает и имеет значение " + value.toString());
                        } else {
                            System.out.println("Поле константы  " + declaredFields[i].getName()
                                    + " не совпадает и имеет значение " + value.toString());
                        }
                    }
                }
            }
            classFrom = classFrom.getSuperclass();
        }
    }

    @Override
    public boolean isGetter(Method method) {
        return method.getName().startsWith("get") || method.getName().startsWith("is") ;
    }
}
