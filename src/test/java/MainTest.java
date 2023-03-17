import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void timeCheck() {

        assertTimeout(Duration.ofSeconds(22),() -> Main.main(new String[]{}));

    }

}