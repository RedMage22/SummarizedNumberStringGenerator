package com.redmage.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummarizedNumberStringGenerator implements NumberRangeSummarizer {
    private String groupedNumberRangeString = null;

    @Override
    public Collection<Integer> collect(String input) {
        return buildIntegerCollection(buildStringCollection(input));
    }

    private Collection<Integer> buildIntegerCollection(Collection<String> strings) {
        return strings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
    }

    private Collection<String> buildStringCollection(String input) {
        return Stream.of(input)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> integerList = (List<Integer>) input;
        List<String> stringList = new ArrayList<>();
        for (int currentIndex = 0; currentIndex < integerList.size(); currentIndex++) {
            if (isPartOfGroupedNumberString(integerList, currentIndex, 2)) {
                currentIndex = buildGroupedNumberRangeString(integerList, currentIndex, 2);
                stringList.add(groupedNumberRangeString);
                groupedNumberRangeString = null;
            } else {
                stringList.add(String.valueOf(integerList.get(currentIndex)));
            }
        }
        return String.join(", ", stringList);
    }

    private boolean isPartOfGroupedNumberString(List<Integer> integerList, int currentIndex, int numSteps) {
        int targetIndex = currentIndex + numSteps;
        IntPredicate isWithinBounds = indexValue -> indexValue < integerList.size();
        IntPredicate isTargetNumber = value ->
                integerList.get(value - numSteps) + numSteps == integerList.get(value);
        return isWithinBounds.and(isTargetNumber).test(targetIndex);
    }

    private int buildGroupedNumberRangeString(List<Integer> integerList, int currentIndex, int numSteps) {
        groupedNumberRangeString = integerList.get(currentIndex) + "-";
        currentIndex += numSteps;
        while (isPartOfGroupedNumberString(integerList, currentIndex, 1)) {
            currentIndex++;
        }
        groupedNumberRangeString += integerList.get(currentIndex);
        return currentIndex;
    }
}
