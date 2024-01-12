import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

class TestMetric {


    @Test
    void givenMetricProxy() throws InterruptedException {
        Calculator calc = new CalculatorImpl();
        Calculator c = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                calc.getClass().getInterfaces(), new PerformanceProxy(calc));
        System.out.println(c.calc(3));
    }

}