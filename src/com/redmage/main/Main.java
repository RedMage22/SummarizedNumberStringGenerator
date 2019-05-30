package com.redmage.main;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        NumberRangeSummarizer rangeSummarizer = new SummarizedNumberStringGenerator();
        String inputString = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

        try {
            Collection<Integer> integerCollection = rangeSummarizer.collect(inputString);
            String summarizedCollection = rangeSummarizer.summarizeCollection(integerCollection);
            System.out.println(summarizedCollection);
        } catch (Exception e) {
            System.out.println("Invalid value\n" + e.getMessage());
        }


    }
}
