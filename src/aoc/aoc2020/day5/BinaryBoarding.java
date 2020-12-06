package aoc.aoc2020.day5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding {

    private static class Pair<F, S> {
        private F first;
        private S second;

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

        public void setFirst(F first) {
            this.first = first;
        }

        public void setSecond(S second) {
            this.second = second;
        }
    }

    private static class Seat {

        private final int row;
        private final int column;

        public Seat(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }

    public static int decodeCoord(String encodedCoord, char lowerChar) {
        Pair<Integer, Integer> range = new Pair<>(0, (int) Math.pow(2, encodedCoord.length()) - 1);

        encodedCoord.chars().forEach(c -> {
            int bound = (range.getFirst() + range.getSecond()) / 2;
            if (c == lowerChar) {
                range.setSecond(bound);
            } else {
                range.setFirst(bound + 1);
            }
        });

        return range.getFirst();
    }

    public static void main(String[] args) throws IOException {
        List<Integer> seatIds = Files.lines(Paths.get("src/aoc/aoc2020/day5/input.txt"), StandardCharsets.UTF_8)
                .map(line -> new Seat(decodeCoord(line.substring(0, 7), 'F'), decodeCoord(line.substring(7, 10), 'L')))
                .map(s -> s.getRow() * 8 + s.getColumn())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Max ID = " + seatIds.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax());

        int mySeatId = 0;
        boolean found = false;

        for (int i = 0; i < seatIds.size() && !found; i++) {
            if (i + 1 == seatIds.size()) {
                mySeatId = seatIds.get(i);
                found = true;
            } else if (seatIds.get(i) + 1 != seatIds.get(i + 1)) {
                mySeatId = seatIds.get(i) + 1;
                found = true;
            }
        }

        System.out.println("MySeatId = " + mySeatId);
    }
}
