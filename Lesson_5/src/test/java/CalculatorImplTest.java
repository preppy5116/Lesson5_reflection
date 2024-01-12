import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {
    @Test
    void givenCacheProxy() throws InterruptedException {
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(), new MyInvocationHandler(delegate));

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