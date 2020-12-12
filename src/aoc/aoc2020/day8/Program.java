package aoc.aoc2020.day8;

public class Program {

    private final Instruction[] instructions;
    private int acc;
    private int currentInstructionIndex;

    public Program(Instruction[] instructions) {
        this.instructions = instructions;
        this.acc = 0;
        this.currentInstructionIndex = 0;
    }

    public synchronized boolean executeUntilRepeat() {
        while (currentInstructionIndex < instructions.length) {
            Instruction currentInstruction = instructions[currentInstructionIndex];

            if (currentInstruction.isExecuted()) {
                reset();
                return true;
            }

            executeInstruction(currentInstruction);
        }
        reset();
        return false;
    }

    public int getAcc() {
        return acc;
    }

    private void reset() {
        for (Instruction instruction : instructions) {
            instruction.setExecuted(false);
        }
    }

    private void executeInstruction(Instruction instruction) {
        switch (instruction.getOperation()) {
            case ACC:
                acc += instruction.getArgument();
                currentInstructionIndex++;
                break;
            case JMP:
                currentInstructionIndex += instruction.getArgument();
                break;
            case NOP:
                currentInstructionIndex++;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation in program - " + instruction.getOperation().name());
        }

        instruction.setExecuted(true);
    }
}
