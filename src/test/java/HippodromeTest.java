import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class HippodromeTest {

    @Test
    void PassingToTheNullConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be null.", exception.getMessage());
        }

    }

    @Test
    void PassingAnEmptyListToTheConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }

    }
    
    @Test
    void getHorseCheck() {

        ArrayList<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i), i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
        
    }

    @Test
    void checkThatMoveCallsTheMoveMethodOnAllHorses() {

        Horse horse = mock(Horse.class);
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        Mockito.verify(horse, times(50)).move();

    }

    @Test
    void checkingThatGetWinnerReturnsTheHorseWithTheLargestDistanceValue() {

        Horse loseHorse = new Horse("Помидорка", 1, 1);
        Horse winHorse = new Horse("Булочка", 2, 20);

        Hippodrome hippodrome = new Hippodrome(List.of(loseHorse, winHorse));

        assertEquals(winHorse, hippodrome.getWinner());

    }

}