package aoc.aoc2020.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterCombiner {

    private final List<Integer> joltages;
    private final Map<Integer, Long> countsByJoltages;

    public AdapterCombiner(List<Integer> joltages) {
        this.joltages = joltages;
        this.countsByJoltages = new HashMap<>();
    }

    private long getNumberOfCombinations(int fromIndex) {
        if (fromIndex == joltages.size() - 1) {
            return 1;
        }

        if (countsByJoltages.containsKey(fromIndex)) {
            return countsByJoltages.get(fromIndex);
        }

        long combinations = 0;
        for (int i = fromIndex + 1; i < joltages.size(); i++) {
            if (joltages.get(i) - joltages.get(fromIndex) > 3) {
                break;
            }
            combinations += getNumberOfCombinations(i);
        }

        countsByJoltages.put(fromIndex, combinations);
        return combinations;
    }

    public long getNumberOfCombinations() {
        return getNumberOfCombinations(0);
    }
}
