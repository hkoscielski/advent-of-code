package aoc.aoc2020.day10;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {

    private static int calculateMultiplitedDiffs(List<Integer> joltages) {
        int diffOf1JoltCount = 0;
        int diffOf3JoltsCount = 1;

        for (int i = 1; i < joltages.size(); i++) {
            int prevJoltage = joltages.get(i - 1);
            int currJoltage = joltages.get(i);
            int joltageDiff = currJoltage - prevJoltage;

            if (joltageDiff == 1) {
                diffOf1JoltCount++;
            } else if (joltageDiff == 3) {
                diffOf3JoltsCount++;
            }
        }

        return diffOf1JoltCount * diffOf3JoltsCount;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> joltages = Stream.concat(
                Stream.of(0),
                Files.lines(Paths.get("src/aoc/aoc2020/day10/input.txt"), StandardCharsets.UTF_8)
                        .map(Integer::valueOf)
        ).sorted().collect(Collectors.toList());

        int multipliedDiffs = calculateMultiplitedDiffs(joltages);
        System.out.println("Multiplied differences = " + multipliedDiffs);

        AdapterCombiner adapterCombiner = new AdapterCombiner(joltages);
        System.out.println("Number of combinations = " + adapterCombiner.getNumberOfCombinations());
    }
}
