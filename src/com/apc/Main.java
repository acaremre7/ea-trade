package com.apc;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<List<List<Integer>>> parsedInput = InputParser.getInstance().getParsedInput();

        int schuurIndex = 1;

        for (List<List<Integer>> schuurList : parsedInput) {
            System.out.println("schuurs " + schuurIndex);

            int maximumProfit = 0;
            Set<Integer> numbersOfFlutsToBuy = new HashSet<>();

            for (List<Integer> flutList : schuurList) {
                int maximumProfitForStack = -1;
                Set<Integer> numbersOfFlutsToBuyForStack = new HashSet<>();
                int currentProfit = 0;
                int flutCount = 0;

                for (int flutPrice : flutList) {
                    flutCount++;
                    currentProfit += 10 - flutPrice;
                    if (currentProfit == maximumProfitForStack) {
                        numbersOfFlutsToBuyForStack.add(flutCount);
                    } else if (currentProfit >= maximumProfitForStack) {
                        numbersOfFlutsToBuyForStack.clear();
                        numbersOfFlutsToBuyForStack.add(flutCount);
                        maximumProfitForStack = currentProfit;
                    }
                }
                if (maximumProfitForStack > 0) {
                    maximumProfit += maximumProfitForStack;
                    if(numbersOfFlutsToBuy.isEmpty()){
                        numbersOfFlutsToBuy.addAll(numbersOfFlutsToBuyForStack);
                    }else {
                        numbersOfFlutsToBuy = cartesianSumOfSets(numbersOfFlutsToBuy, numbersOfFlutsToBuyForStack);
                    }
                }
            }
            schuurIndex++;
            System.out.println("Maximum profit is " + maximumProfit);
            System.out.println("Number of fluts to buy " + numbersOfFlutsToBuy);
        }
    }

    private static Set<Integer> cartesianSumOfSets(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> result = new HashSet<>();
        for(int i : set1){
            for (int j : set2){
                result.add(i+j);
            }
        }
        return result;
    }
}
