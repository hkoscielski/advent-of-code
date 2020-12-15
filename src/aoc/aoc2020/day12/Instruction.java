package aoc.aoc2020.day12;

public class Instruction {

    private final Action action;
    private final int argument;

    public Instruction(Action action, int argument) {
        this.action = action;
        this.argument = argument;
    }

    public Action getAction() {
        return action;
    }

    public int getArgument() {
        return argument;
    }
}
