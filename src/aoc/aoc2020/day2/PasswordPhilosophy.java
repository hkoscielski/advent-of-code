package aoc.aoc2020.day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordPhilosophy {

    private static class PasswordWithPolicy {

        private final int lowerBound;
        private final int upperBound;
        private final char analyzedChar;
        private final String password;

        private PasswordWithPolicy(int lowerBound, int upperBound, char requiredChar, String password) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.analyzedChar = requiredChar;
            this.password = password;
        }

        public static PasswordWithPolicy parseFromString(String input) {
            String[] sections = input.split("\\s");

            String[] parsedOccurrencesRange = sections[0].split("-");
            int lowerBound = Integer.parseInt(parsedOccurrencesRange[0]);
            int upperBound = Integer.parseInt(parsedOccurrencesRange[1]);

            char analyzedChar = sections[1].charAt(0);
            String password = sections[2];

            return new PasswordWithPolicy(lowerBound, upperBound, analyzedChar, password);
        }

        public int getLowerBound() {
            return lowerBound;
        }

        public int getUpperBound() {
            return upperBound;
        }

        public char getAnalyzedChar() {
            return analyzedChar;
        }

        public String getPassword() {
            return password;
        }
    }

    private static class PasswordWithPolicyNumberOfOccurrencesValidator {

        public static boolean isValid(PasswordWithPolicy passwordWithPolicy) {
            long occurrences = passwordWithPolicy.getPassword().chars().filter(ch -> ch == passwordWithPolicy.getAnalyzedChar()).count();
            return passwordWithPolicy.getLowerBound() <= occurrences && occurrences <= passwordWithPolicy.getUpperBound();
        }
    }

    private static class PasswordWithPolicyPositionOccurrencesValidator {

        public static boolean isValid(PasswordWithPolicy passwordWithPolicy) {

            return (passwordWithPolicy.getPassword().charAt(passwordWithPolicy.getLowerBound() - 1) == passwordWithPolicy.getAnalyzedChar()) ^ (passwordWithPolicy.getPassword().charAt(passwordWithPolicy.getUpperBound() - 1) == passwordWithPolicy.getAnalyzedChar());
        }
    }

    public static void main(String[] args) throws IOException {
        List<PasswordWithPolicy> passwordsWithPolicies = Files.lines(Paths.get("src/aoc/aoc2020/day2/input.txt"), StandardCharsets.UTF_8)
                .map(PasswordWithPolicy::parseFromString)
                .collect(Collectors.toList());

        long validPasswordForPuzzle1Count = passwordsWithPolicies.stream()
                .filter(PasswordWithPolicyNumberOfOccurrencesValidator::isValid)
                .count();

        long validPasswordForPuzzle2Count = passwordsWithPolicies.stream()
                .filter(PasswordWithPolicyPositionOccurrencesValidator::isValid)
                .count();

        System.out.println("Valid password count for puzzle 1 count = " + validPasswordForPuzzle1Count);
        System.out.println("Valid password countfor puzzle 2 count = " + validPasswordForPuzzle2Count);
    }
}
