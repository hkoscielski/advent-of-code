package aoc.aoc2020.day8;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandheldHalting {

    public static void main(String[] args) throws IOException {
        Instruction[] instructions = Files.lines(Paths.get("src/aoc/aoc2020/day8/input.txt"), StandardCharsets.UTF_8)
                .map(InstructionParser::parseString)
                .toArray(Instruction[]::new);

        Program program = new Program(instructions);
        program.executeUntilRepeat();
        System.out.println("acc = " + program.getAcc());

        for (int i = 0; i < instructions.length; i++) {
            Instruction oldInstruction = instructions[i];
            Instruction instruction = new Instruction(instructions[i]).mutateOperation();
            instructions[i] = instruction;
            Program programVariant = new Program(instructions);
            boolean repaired = !programVariant.executeUntilRepeat();
            instructions[i] = oldInstruction;

            if (repaired) {
                System.out.println("acc = " + programVariant.getAcc());
                break;
            }
        }
    }
}
