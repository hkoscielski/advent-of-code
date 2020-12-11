package aoc.aoc2020.day7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class HandyHaversacks {

    private static final Map<String, Bag> bagsByColors = new HashMap<>();

    private static Bag getStoredBagOrDefault(Bag bag) {
        if (!bagsByColors.containsKey(bag.getColor())) {
            bagsByColors.put(bag.getColor(), new Bag(bag.getColor()));
        }

        return bagsByColors.get(bag.getColor());
    }

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("src/aoc/aoc2020/day7/input.txt"), StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                Bag parsedBag = BagParser.parseString(line);
                Bag bagToAdd = getStoredBagOrDefault(parsedBag);
                for (Bag containedBag : parsedBag.getContainedBags()) {
                    bagToAdd.addBag(getStoredBagOrDefault(containedBag));
                }
            });
        }

        long containingBagsCount = bagsByColors.entrySet()
                .stream()
                .filter(entry -> entry.getValue().canContainBag("shiny gold"))
                .count();

        System.out.println("containingBagsCount = " + containingBagsCount);

        System.out.println("count = " + bagsByColors.get("shiny gold").bagsInsideCount());
    }
}
