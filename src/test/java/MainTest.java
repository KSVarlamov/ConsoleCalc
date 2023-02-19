import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void sum() {
        assertEquals("3", Main.calc("1 + 2"));
    }

    @Test
    void sumRome() {
        assertEquals("III", Main.calc("I + II"));
    }

    @Test
    void divRome() {
        assertEquals("II", Main.calc("VI / III"));
    }
    @Test
    void div() {
        assertEquals("4", Main.calc("8 / 2"));
        assertEquals("1", Main.calc("4 / 3"));
    }

    @Test
    void sub() {
        assertEquals("4", Main.calc("5 - 1"));
        assertEquals("-4", Main.calc("1 - 5"));
    }

    @Test
    void subRome() {
        assertEquals("IV", Main.calc("IX - V"));
    }

    @Test
    void mult() {
        assertEquals("15", Main.calc("5 * 3"));
        assertEquals("81", Main.calc("9 * 9"));
    }

    @Test
    void multRome() {
        assertEquals("C", Main.calc("X * X"));
        assertEquals("LXXXI", Main.calc("IX * IX"));
    }

    @Test
    void negativeRomeResult() {
        Exception thrown = assertThrows(
                ArithmeticException.class,
                () -> Main.calc("I - II"),
                "Expected exception for negative"
        );
        assertTrue(thrown.getMessage().contains("Rome can't be negative or 0"));
        thrown = assertThrows(
                ArithmeticException.class,
                () -> Main.calc("V - V"),
                "Expected exception to 0"
        );
        assertTrue(thrown.getMessage().contains("Rome can't be negative or 0"));
    }

    @Test
    void mixedArgs() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("I + 1"),
                "Expected exception to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Only Roman or Arabic numerals at the same time"));
    }

    @Test
    void incorrectOperation() {
        Exception thrown = assertThrows(
                UnsupportedOperationException.class,
                () -> Main.calc("3 % 1"),
                "Expected exception to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Incorrect operation "));
    }

    @Test
    void noOperations() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("1"),
                "Expected exception to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Is not a math operation"));
    }

    @Test
    void tooManyOperations() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("1 + 2 + 3"),
                "Expected exception to throw, but it didn't"
        );
    }

    @Test
    void onlyWholeNumbers() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("6.0 + 5"),
                "Expected exception to throw, but it didn't"
        );
    }

    @Test
    void testRange() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("11 + 1"),
                "Expected exception to throw, but it didn't (11 + 1)"
        );
        assertTrue(thrown.getMessage().contains("numbers must be in the range 1..10"));

        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("0 + 3"),
                "Expected exception to throw, but it didn't (0 + 3)"
        );
        assertTrue(thrown.getMessage().contains("numbers must be in the range 1..10"));

        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Main.calc("-3 + -3"),
                "Expected exception to throw, but it didn't (-3 + -3)"
        );
        assertTrue(thrown.getMessage().contains("numbers must be in the range 1..10"));
    }


}
