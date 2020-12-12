package aoc.aoc2020.day9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class EncodingError {

    private static final int PREAMBLE_SIZE = 25;

    public static boolean existsTwoDifferentNumbersAddingUpToTarget(List<Long> nums, long target) {
        Map<Long, Long> firstSummandsByMissingDiffs = new HashMap<>();

        for (long num : nums) {
            if (firstSummandsByMissingDiffs.containsKey(num) && num != firstSummandsByMissingDiffs.get(num)) {
                return true;
            } else {
                firstSummandsByMissingDiffs.put(target - num, num);
            }
        }

        return false;
    }

    public static Optional<Long> getFirstNumberForWhichThereAreNotExistTwoDifferentNumberFromPrembleAddingUpToTarget(List<Long> numbers) {
        for (int i = PREAMBLE_SIZE; i < numbers.size(); i++) {
            long number = numbers.get(i);
            List<Long> preambleNumbers = numbers.subList(i - PREAMBLE_SIZE, i);

            if (!existsTwoDifferentNumbersAddingUpToTarget(preambleNumbers, number)) {
                return Optional.of(number);
            }
        }

        return Optional.empty();
    }

    public static Optional<Long> calculateEncryptionWeakness(List<Long> numbers, long target) {
        Queue<Long> contiguousRange = new LinkedList<>();

        for (long number : numbers) {
            contiguousRange.add(number);
            while (!contiguousRange.isEmpty()) {
                long sum = contiguousRange.stream().mapToLong(num -> num).sum();
                if (sum == target) {
                    long min = contiguousRange.stream().reduce(Long.MAX_VALUE, (acc, num) -> acc > num ? num : acc);
                    long max = contiguousRange.stream().reduce(Long.MIN_VALUE, (acc, num) -> acc < num ? num : acc);
                    return Optional.of(min + max);
                } else {
                    if (sum > target) {
                        contiguousRange.poll();
                    } else {
                        break;
                    }
                }
            }
        }

        return Optional.empty();
    }

    public static void main(String[] args) throws IOException {
        List<Long> numbers = Files.lines(Paths.get("src/aoc/aoc2020/day9/input.txt"), StandardCharsets.UTF_8)
                .map(Long::valueOf)
                .collect(Collectors.toList());

        long invalidNumber = getFirstNumberForWhichThereAreNotExistTwoDifferentNumberFromPrembleAddingUpToTarget(numbers).orElseThrow(NoSuchElementException::new);
        System.out.println("First number for which there are not exist two number adding up to target = " + invalidNumber);

        long encryptionWeakness = calculateEncryptionWeakness(numbers, invalidNumber).orElseThrow(NoSuchElementException::new);
        System.out.println("Encryption weakness for " + invalidNumber + " = " + encryptionWeakness);
    }
}
