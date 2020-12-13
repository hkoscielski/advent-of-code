package aoc.aoc2020.day11;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SeatingSystem {

    public static void main(String[] args) throws IOException {
        char[][] seatLayout = Files.lines(Paths.get("src/aoc/aoc2020/day11/input.txt"), StandardCharsets.UTF_8)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        SeatingAreaSimulator seatingAreaSimulator = new SeatingAreaSimulator(seatLayout);
        seatingAreaSimulator.simulate();
        System.out.println("Occupied seats = " + seatingAreaSimulator.getOccupiedSeatsCount());

        SeatingAreaEnhancedSimulator seatingAreaEnhancedSimulator = new SeatingAreaEnhancedSimulator(seatLayout);
        seatingAreaEnhancedSimulator.simulate();
        System.out.println("Occupied seats = " + seatingAreaEnhancedSimulator.getOccupiedSeatsCount());
    }
}
