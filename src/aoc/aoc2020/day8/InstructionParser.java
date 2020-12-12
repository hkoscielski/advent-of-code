package aoc.aoc2020.day8;

import java.util.Map;

public final class InstructionParser {

    private static final Map<String, Operation> operationsByNames = Map.of("acc", Operation.ACC, "jmp", Operation.JMP, "nop", Operation.NOP);

    public static Instruction parseString(String line) {
        String[] fieldsSplitted = line.split("\\s");
        Operation operation = operationsByNames.get(fieldsSplitted[0]);
        int argument = Integer.parseInt(fieldsSplitted[1].replace("+", ""));
        return new Instruction(operation, argument);
    }
}
