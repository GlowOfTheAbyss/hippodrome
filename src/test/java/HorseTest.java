import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void passingNullAsTheFirstArgumentToTheConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));

        try {
            new Horse(null, 0,0);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "    "})
    void passingAnEmptyStringAsTheFirstArgumentToTheConstructor(String text) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(text, 1, 1));

        try {
            new Horse(text, 1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

    }

    @Test
    void passingNegativeNumberAsTheSecondArgumentToTheConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("Булочка", -1, 1));

        try {
            new Horse("name", -1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }

    }

    @Test
    void passingNegativeNumberAsTheThirdArgumentToTheConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("Булочка", 1, -1));

        try {
            new Horse("name", 1, -1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }

    }

    @Test
    void checkThatTheGetNameMethodReturnsTheStringThatWasPassedByTheFirstParameterToTheConstructor() {

        Horse horse = new Horse("Булочка", 2, 3);
        assertEquals("Булочка", horse.getName());

    }

    @Test
    void checkThatTheGetSpeedMethodReturnsWhichWasPassedToTheConstructorByTheSecondParameter() {

        Horse horse = new Horse("Булочка", 2, 3);
        assertEquals(2, horse.getSpeed());

    }

    @Test
    void checkingThatGetDistanceReturnsTheNumberThatWasPassedByTheThirdParameterToTheConstructor() {

        Horse horse = new Horse("Булочка", 2, 3);
        assertEquals(3, horse.getDistance());

    }

    @Test
    void checkingThatTheGetDistanceReturnsZeroIfTheObjectWasCreatedUsingAConstructorWithTwoParameters() {

        Horse horse = new Horse("Булочка", 2);
        assertEquals(0, horse.getDistance());

    }

    @Test
    void CheckingThatMoveCallsTheGetRandomDoubleMethodInside() {

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {

            new Horse("Булочка", 2, 3).move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @CsvSource({
        "0.2, 0.4",
        "0.4, 0.8"
    })
    void checkingTheDistance(double random, double result) {

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Булочка", 2);
            horse.move();

            assertEquals(result, horse.getDistance());
        }

    }

}