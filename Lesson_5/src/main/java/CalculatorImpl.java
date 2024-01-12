import org.junit.jupiter.api.Test;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) throws InterruptedException {
        if (number <= 1) return 1;
        else return number * calc(number - 1);
    }
}
