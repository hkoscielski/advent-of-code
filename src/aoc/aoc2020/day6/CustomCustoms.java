package aoc.aoc2020.day6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {

    public static void main(String[] args) throws IOException {
        List<Set<String>> groupedAnswers = new ArrayList<>();
        List<String> answersForGroups = new ArrayList<>();
        List<Set<Character>> uniqueYesAnswersForGroups = new ArrayList<>();
        Set<Character> yesAnswersForGroups = new HashSet<>();

        try (Stream<String> lines = Files.lines(Paths.get("src/aoc/aoc2020/day6/input.txt"), StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                if (line.isBlank()) {
                    uniqueYesAnswersForGroups.add(Set.copyOf(yesAnswersForGroups));
                    groupedAnswers.add(Set.copyOf(answersForGroups));
                    yesAnswersForGroups.clear();
                    answersForGroups.clear();
                } else {
                    line.chars().forEach(c -> yesAnswersForGroups.add((char) c));
                    answersForGroups.add(line);
                }
            });

            if (!yesAnswersForGroups.isEmpty()) {
                uniqueYesAnswersForGroups.add(Set.copyOf(yesAnswersForGroups));
                groupedAnswers.add(Set.copyOf(answersForGroups));
                yesAnswersForGroups.clear();
                answersForGroups.clear();
            }
        }

        long countSum = uniqueYesAnswersForGroups.stream().collect(Collectors.summarizingInt(Set::size)).getSum();
        System.out.println("Count sum = " + countSum);

        int answersAnsweredByAllInGroup = 0;

        for (int i = 0; i < groupedAnswers.size(); i++) {
            for (Character c : uniqueYesAnswersForGroups.get(i)) {
                boolean answeredByAllInGroup = true;

                for (String answer : groupedAnswers.get(i)) {
                    if (!answer.contains(c.toString())) {
                        answeredByAllInGroup = false;
                        break;
                    }
                }

                if (answeredByAllInGroup) {
                    answersAnsweredByAllInGroup++;
                }
            }
        }

        System.out.println("Answers answered by all in group = " + answersAnsweredByAllInGroup);
    }
}
