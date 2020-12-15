package aoc.aoc2020.day12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class RainRisk {

    public static void main(String[] args) throws IOException {
        List<Instruction> instructions = Files.lines(Paths.get("src/aoc/aoc2020/day12/input.txt"), StandardCharsets.UTF_8)
                .map(InstructionParser::parseString)
                .collect(Collectors.toList());

        Ship ship = new Ship();
        ship.executeInstructions(instructions);
        System.out.println("Distance = " + ship.getDistance());

        EnhancedShip enhancedShip = new EnhancedShip(new Position(10, 1));
        enhancedShip.executeInstructions(instructions);
        System.out.println("Distance = " + enhancedShip.getDistance());
    }
}
