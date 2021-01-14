package com.apc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputParser {

    private List<List<List<Integer>>> parsedInput;
    private static InputParser inputParserInstance = null;

    private InputParser() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("resources/input.txt")).getFile());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            parsedInput = new ArrayList<>();
            int pileCount = Integer.parseInt(bufferedReader.readLine());
            while (pileCount != 0) {
                List<List<Integer>> pileContentList = new ArrayList<>();
                for (int pile = 0; pile < pileCount; pile++) {
                    String line = bufferedReader.readLine();
                    List<Integer> pileContents = Arrays.stream(line.split(" "))
                            .skip(1)
                            .map(Integer::valueOf)
                            .collect(Collectors.toList());
                    pileContentList.add(pileContents);
                }
                parsedInput.add(pileContentList);
                pileCount = Integer.parseInt(bufferedReader.readLine());
            }
        } catch (IOException e) {
            parsedInput = new ArrayList<>();
        }
    }

    public static InputParser getInstance() {
        if (inputParserInstance == null) {
            inputParserInstance = new InputParser();
        }

        return inputParserInstance;
    }

    public List<List<List<Integer>>> getParsedInput() {
        return parsedInput;
    }
}
