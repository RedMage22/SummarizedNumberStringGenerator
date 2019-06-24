import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SummarizedNumberStringGeneratorTest {
    private SummarizedNumberStringGenerator numberStringGenerator;

    private String inputString = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
    private ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31));
    private String expectedString = "1, 3, 6-8, 12-15, 21-24, 31";

    @BeforeEach
    public void setup() {
        numberStringGenerator = new SummarizedNumberStringGenerator();
    }

    @Test
    void collectShouldReturnIntegerCollection() {
        assumeTrue(inputString != null,
                "The input string should NOT be null");
        assumeTrue(!inputString.isEmpty(), "The input string should NOT be empty");
        assumeTrue(inputString.matches("[\\d,]*"), "The input string format is NOT correct");
        assertIterableEquals(integerList, numberStringGenerator.collect(inputString));
    }

    @Test
    void summarizeCollectionShouldReturnSummarizedString() {
        assumeTrue(integerList != null,
                "The integer collection should NOT be null");
        assumeTrue(!integerList.isEmpty(), "The integer collection should NOT be empty");
        assertEquals(expectedString, numberStringGenerator.summarizeCollection(integerList));
    }
}