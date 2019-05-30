package com.redmage.test;

import com.redmage.main.SummarizedNumberStringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SummarizedNumberStringGeneratorTestParameterized {
    private SummarizedNumberStringGenerator numberStringGenerator;

    @BeforeEach
    public void  setup() {
        numberStringGenerator = new SummarizedNumberStringGenerator();
    }

    @ParameterizedTest(name = "Run {index}: inputString={0}, integerList={1}")
    @MethodSource("collectTestInput_Parameters")
    void collectShouldReturnIntegerCollection(String inputString, Collection<Integer> integerList) {
        assumeTrue(inputString != null,
                "The input string should NOT be null");
        assumeTrue(!inputString.isEmpty(), "The input string should NOT be empty");
        assumeTrue(inputString.matches("[\\d,]*"), "The input string format is NOT correct");
        assertIterableEquals(integerList, numberStringGenerator.collect(inputString));
    }

    @ParameterizedTest(name = "Run {index}: integerList={0}, expectedString={1}")
    @MethodSource("summarizeCollectionTestInput_Parameters")
    void summarizeCollectionShouldReturnSummarizedString(Collection<Integer> integerList, String expectedString) {
        assumeTrue(integerList != null,
                "The integer collection should NOT be null");
        assumeTrue(!integerList.isEmpty(), "The integer collection should NOT be empty");
        assertEquals(expectedString, numberStringGenerator.summarizeCollection(integerList));
    }

    static Stream<Arguments> collectTestInput_Parameters() throws Throwable {
        return Stream.of(
                Arguments.of("1,3,6,7,8,12,13,14,15,21,22,23,24,31",
                        Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31)),
                Arguments.of("1,6,7,8,13,14,15,22,23,31",
                        Arrays.asList(1,6,7,8,13,14,15,22,23,31)),
                Arguments.of("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18," +
                                "19,20,21,22,23,24,25,26,27,28,29,30,31",
                        Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
                                19,20,21,22,23,24,25,26,27,28,29,30,31))

        );
    }

    static Stream<Arguments> summarizeCollectionTestInput_Parameters() throws Throwable {
        return Stream.of(
                Arguments.of(Arrays.asList(1,3,4,6,7,8,12,13,14,15,21,22,23,24,31),
                        "1, 3, 4, 6-8, 12-15, 21-24, 31"),
                Arguments.of(Arrays.asList(1,2,4,5,7,8,13,14,15,22,23,31),
                        "1, 2, 4, 5, 7, 8, 13-15, 22, 23, 31"),
                Arguments.of(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,
                        18,19,20,21,22,23,24,25,26,27,28,29,30,31),
                        "1-31")

        );
    }
}
