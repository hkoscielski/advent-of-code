package aoc.aoc2020.day8;

public class Instruction {

    private Operation operation;
    private final int argument;
    private boolean executed;

    public Instruction(Operation operation, int argument) {
        this.operation = operation;
        this.argument = argument;
        this.executed = false;
    }

    public Instruction(Instruction instruction) {
        this.operation = instruction.getOperation();
        this.argument = instruction.getArgument();
        this.executed = instruction.isExecuted();
    }

    public Operation getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public Instruction mutateOperation() {
        switch (operation) {
            case JMP:
                operation = Operation.NOP;
                break;
            case NOP:
                operation = Operation.JMP;
                break;
        }

        return this;
    }
}
