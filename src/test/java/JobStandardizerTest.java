
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standardizingjobtitle.JobStandardizer;

import static org.junit.jupiter.api.Assertions.*;


class JobStandardizerTest {

    JobStandardizer s;

    @BeforeEach
    void setUp() {
        s = new JobStandardizer();
    }

    @AfterEach
    void tearDown() {
        s = null;
    }

    @Test
    void standardizeTest() {
        assertAll(
                /*

                Basic tests -------------------------------------------------------------------------------

                */
                () -> assertEquals("Architect", s.standardize("Something fun")),
                () -> assertEquals("Accountant", s.standardize("Anything but accountancy")),
                () -> assertEquals("Quantity surveyor", s.standardize("Quantatty sirveyer")),
                () -> assertEquals("Architect", s.standardize("SOFTWEAR")),
                () -> assertEquals("Architect", s.standardize("AArchtiect")),
                () -> assertEquals("Software engineer", s.standardize("Accountant Engineer")),
                () -> assertEquals("Quantity surveyor", s.standardize("Quantity Engineer")),
                () -> assertEquals("Accountant", s.standardize("Astronaut")),
                () -> assertEquals("Architect", s.standardize("234234234234"))
        );
    }


}