import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyInterfaceImplTest {
    MyInterfaceImpl m;

    public MyInterfaceImplTest() {
        m = new MyInterfaceImpl();
    }

    @Test
    void givenAllMethodsFromClass() {
        m.getAllMethodsForClass(Child.class);
    }

    @Test
    void givenAllGettersFromClass() {
        m.getAllGetters(Child.class);
    }

    @Test
    void givenAllFieldsFromClass() {
        m.getAllFields(Child.class);
    }
}