package com.apc;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<List<List<Integer>>> parsedInput = InputParser.getInstance().getParsedInput();

        int schuurIndex = 1;

        for (List<List<Integer>> schuurList : parsedInput) {
            System.out.println("schuurs " + schuurIndex);

            int maximumProfit = 0;
            Set<Integer> numberOfflutsToBuy = new HashSet<>();

            for (List<Integer> flutList : schuurList) {
                int maximumProfitForStack = -1;
                Set<Integer> numberOfflutsToBuyForStack = new HashSet<>();
                int currentProfit = 0;
                int flutCount = 0;

                for (int flutPrice : flutList) {
                    flutCount++;
                    currentProfit += 10 - flutPrice;
                    if (currentProfit == maximumProfitForStack) {
                        numberOfflutsToBuyForStack.add(flutCount);
                    } else if (currentProfit >= maximumProfitForStack) {
                        numberOfflutsToBuyForStack.clear();
                        numberOfflutsToBuyForStack.add(flutCount);
                        maximumProfitForStack = currentProfit;
                    }
                }
                if (maximumProfitForStack > 0) {
                    maximumProfit += maximumProfitForStack;
                    if(numberOfflutsToBuy.isEmpty()){
                        numberOfflutsToBuy.addAll(numberOfflutsToBuyForStack);
                    }else {
                        numberOfflutsToBuy = cartesianSumOfLists(numberOfflutsToBuy, numberOfflutsToBuyForStack);
                    }
                }
            }
            schuurIndex++;
            System.out.println("Maximum profit is " + maximumProfit);
            System.out.println("Number of fluts to buy " + numberOfflutsToBuy);
        }
    }

    private static Set<Integer> cartesianSumOfLists(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> result = new HashSet<>();
        for(int i : set1){
            for (int j : set2){
                result.add(i+j);
            }
        }
        return result;
    }
}
