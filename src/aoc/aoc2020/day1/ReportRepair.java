package aoc.aoc2020.day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ReportRepair {

    private static final int CURRENT_YEAR = 2020;

    private int calculateMultiplicationOfTwoExpensesAddingUpToCurrentYearNumber(List<Integer> expenses) {
        int size = expenses.size();

        for (int i = 0; i < size; i++) {
            int expense1 = expenses.get(i);

            for (int j = i + 1; j < size; j++) {
                int expense2 = expenses.get(j);

                if (expense1 + expense2 == CURRENT_YEAR) {
                    return expense1 * expense2;
                }
            }
        }

        throw new NoSuchElementException("Cannot found two elements which adds up to current year number");
    }

    private int calculateMultiplicationOfThreeExpensesAddingUpToCurrentYearNumber(List<Integer> expenses) {
        int size = expenses.size();

        for (int i = 0; i < size; i++) {
            int expense1 = expenses.get(i);

            for (int j = i + 1; j < size; j++) {
                int expense2 = expenses.get(j);

                for (int k = j + 1; k < size; k++) {
                    int expense3 = expenses.get(k);

                    if (expense1 + expense2 + expense3 == CURRENT_YEAR) {
                        return expense1 * expense2 * expense3;
                    }
                }
            }
        }

        throw new NoSuchElementException("Cannot found three elements which adds up to current year number");
    }

    public static void main(String[] args) throws IOException {
        List<Integer> expenses = Files.lines(Paths.get("src/aoc/aoc2020/day1/input.txt"), StandardCharsets.UTF_8)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        ReportRepair reportRepair = new ReportRepair();
        System.out.println("Two numbers = " + reportRepair.calculateMultiplicationOfTwoExpensesAddingUpToCurrentYearNumber(expenses));
        System.out.println("Three numbers = " + reportRepair.calculateMultiplicationOfThreeExpensesAddingUpToCurrentYearNumber(expenses));
    }
}
