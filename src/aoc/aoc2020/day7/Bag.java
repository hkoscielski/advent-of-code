package aoc.aoc2020.day7;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private final String color;
    private final List<Bag> containedBags;

    public Bag(String color) {
        this(color, new ArrayList<>());
    }

    public Bag(String color, List<Bag> containedBags) {
        this.color = color;
        this.containedBags = containedBags;
    }

    public String getColor() {
        return color;
    }

    public List<Bag> getContainedBags() {
        return containedBags;
    }

    public void addBag(Bag bag) {
        containedBags.add(bag);
    }

    public boolean canContainBag(String searchedBagColor) {
        return containedBags.stream().anyMatch(containedBag -> containedBag.getColor().equals(searchedBagColor) || containedBag.canContainBag(searchedBagColor));
    }

    public long bagsInsideCount() {
        return containedBags.stream().mapToLong(bag -> 1 + bag.bagsInsideCount()).sum();
    }
}
