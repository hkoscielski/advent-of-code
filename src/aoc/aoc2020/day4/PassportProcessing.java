package aoc.aoc2020.day4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportProcessing {

    private static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }

    private static class Passport {

        private final Map<String, String> valuesByFieldNames = new HashMap<>();

        public Passport(Set<Pair<String, String>> fieldNamesWithValues) {
            fieldNamesWithValues.forEach(c -> valuesByFieldNames.put(c.getFirst(), c.getSecond()));
        }

        public Set<String> getFieldNames() {
            return valuesByFieldNames.keySet();
        }

        public String getFieldByName(String name) {
            return valuesByFieldNames.getOrDefault(name, "");
        }
    }

    private static class PassportValidator {

        private static final String BYR = "byr";
        private static final String IYR = "iyr";
        private static final String EYR = "eyr";
        private static final String HGT = "hgt";
        private static final String HCL = "hcl";
        private static final String ECL = "ecl";
        private static final String PID = "pid";

        private static final List<String> REQUIRED_FIELDS = List.of(BYR, IYR, EYR, HGT, HCL, ECL, PID);

        private static final Pattern byrPattern = Pattern.compile("^(19[2-9][0-9]|200[0-2])$");
        private static final Pattern iyrPattern = Pattern.compile("^(201[0-9]|2020)$");
        private static final Pattern eyrPattern = Pattern.compile("^(202[0-9]|2030)$");
        private static final Pattern hgtPattern = Pattern.compile("^((1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in)$");
        private static final Pattern hclPattern = Pattern.compile("^(#([0-9]|[a-f]){6})$");
        private static final Pattern eclPattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
        private static final Pattern pidPattern = Pattern.compile("^([0-9]{9})$");

        public static boolean isValid(Passport passport) {
            return passport.getFieldNames().containsAll(REQUIRED_FIELDS)
                    && byrPattern.matcher(passport.getFieldByName(BYR)).matches()
                    && iyrPattern.matcher(passport.getFieldByName(IYR)).matches()
                    && eyrPattern.matcher(passport.getFieldByName(EYR)).matches()
                    && hgtPattern.matcher(passport.getFieldByName(HGT)).matches()
                    && hclPattern.matcher(passport.getFieldByName(HCL)).matches()
                    && eclPattern.matcher(passport.getFieldByName(ECL)).matches()
                    && pidPattern.matcher(passport.getFieldByName(PID)).matches();
        }
    }

    public static void main(String[] args) throws IOException {
        List<Passport> passports = new ArrayList<>();
        Set<Pair<String, String>> pairs = new HashSet<>();

        try (Stream<String> lines = Files.lines(Paths.get("src/aoc/aoc2020/day4/input.txt"), StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                if (line.isBlank()) {
                    passports.add(new Passport(pairs));
                    pairs.clear();
                } else {
                    pairs.addAll(
                            Arrays.stream(line.split("\\s"))
                                    .map(kv -> kv.split(":"))
                                    .map(parsedKv -> new Pair<>(parsedKv[0], parsedKv[1]))
                                    .collect(Collectors.toSet())
                    );
                }
            });

            if (!pairs.isEmpty()) {
                passports.add(new Passport(pairs));
                pairs.clear();
            }
        }

        int validPasswordCount = 0;

        for (Passport passport : passports) {
            if (PassportValidator.isValid(passport)) {
                validPasswordCount++;
            }
        }

        System.out.println("Valid passports = " + validPasswordCount);
    }
}
