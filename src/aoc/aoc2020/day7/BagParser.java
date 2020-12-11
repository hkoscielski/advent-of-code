package aoc.aoc2020.day7;

import java.util.ArrayList;
import java.util.List;

public final class BagParser {

    public static Bag parseString(String input) {
        String[] containSplitted = input.split("contain");

        String color = stringToBag(containSplitted[0]).getColor();
        String[] containedBagsSplitted = containSplitted[1].split(",");
        List<Bag> containedBags = new ArrayList<>();

        for (String containedBag : containedBagsSplitted) {
            containedBags.addAll(stringToDuplicatedBags(containedBag));
        }

        return new Bag(color, containedBags);
    }

    private static Bag stringToBag(String bagText) {
        bagText = cleanUpBagText(bagText);
        String[] splittedBag = splitToNumberAndColor(bagText);
        String color = splittedBag[1];

        return new Bag(color);
    }

    private static List<Bag> stringToDuplicatedBags(String bagText) {
        bagText = cleanUpBagText(bagText);
        String[] splittedBag = splitToNumberAndColor(bagText);
        String color = splittedBag[1];
        int numOfBags = Integer.parseInt(splittedBag[0]);
        List<Bag> bags = new ArrayList<>();
        Bag bag = new Bag(color);

        for (int i = 0; i < numOfBags; i++) {
            bags.add(bag);
        }

        return bags;
    }

    private static String cleanUpBagText(String bagText) {
        return bagText.replace("bags", "")
                .replace("bag", "")
                .replace("no other", "")
                .replace(".", "")
                .strip();
    }

    private static String[] splitToNumberAndColor(String bagText) {
        String[] splittedBag = bagText.split("\\s", 2);

        if (!splittedBag[0].matches("^[0-9]+$")) {
            return new String[]{"0", bagText};
        }

        return splittedBag;
    }
}
