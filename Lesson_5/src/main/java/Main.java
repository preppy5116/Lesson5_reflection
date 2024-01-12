

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Задание 1,5
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(), new MyInvocationHandler(delegate));
        run(calculator);


        //Задание 2,3,4
        MyInterfaceImpl m = new MyInterfaceImpl();
        m.getAllMethodsForClass(Child.class);
        System.out.println();
        m.getAllGetters(Child.class);
        System.out.println();
        m.getAllFields(Child.class);

        //Задание 6
        Calculator calc = new CalculatorImpl();
        Calculator c = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                calc.getClass().getInterfaces(), new PerformanceProxy(calc));
        run(c);
        System.out.println(c.calc(3));


        //Задание 7
        ObjectFrom objectFrom = new ObjectFrom("From", 1);
        ObjectTo objectTo = new ObjectTo("To", 2);
        BeanUtils.assign(objectFrom, objectTo);
        BeanUtils.assign(objectTo, objectFrom);
        System.out.println(objectFrom.getName() + objectTo.getName());
        System.out.println(objectFrom.getNumber() + " " + objectTo.getNumber());
        assertEquals(objectFrom.getName(), objectTo.getName());
        assertEquals(objectFrom.getNumber(), objectTo.getNumber());

    }

    private static void run(Calculator calculator) throws InterruptedException {
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(2));
        System.out.println(calculator.calc(3));
        System.out.println(calculator.calc(4));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(2));
        System.out.println(calculator.calc(3));
        System.out.println(calculator.calc(4));
    }

}