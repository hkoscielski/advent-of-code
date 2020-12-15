package aoc.aoc2020.day12;

public final class InstructionParser {

    public static Instruction parseString(String line) {
        String actionName = line.substring(0, 1);
        String argumentText = line.substring(1);

        return new Instruction(Action.valueOf(actionName), Integer.parseInt(argumentText));
    }
}
