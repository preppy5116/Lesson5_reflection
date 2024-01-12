import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest {

    @Test
    void assignTwoClass() {
        ObjectFrom objectFrom = new ObjectFrom("From", 1);
        ObjectTo objectTo = new ObjectTo("To", 2);
        BeanUtils.assign(objectFrom, objectTo);
        assertEquals(objectFrom.getNumber(), objectTo.getNumber());
    }
}