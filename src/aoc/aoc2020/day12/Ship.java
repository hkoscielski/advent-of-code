package aoc.aoc2020.day12;

import java.util.List;

public class Ship {

    private final Heading heading;
    private final Position position;

    public Ship() {
        this.heading = new Heading(Direction.E);
        this.position = new Position(0, 0);
    }

    public int getDistance() {
        return Math.abs(position.getX()) + Math.abs(position.getY());
    }

    public void executeInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            executeInstruction(instruction);
        }
    }

    private void executeInstruction(Instruction instruction) {
        switch (instruction.getAction()) {
            case N:
                position.moveNorth(instruction.getArgument());
                break;
            case S:
                position.moveSouth(instruction.getArgument());
                break;
            case E:
                position.moveEast(instruction.getArgument());
                break;
            case W:
                position.moveWest(instruction.getArgument());
                break;
            case L:
                heading.changeDirectionLeft(instruction.getArgument());
                break;
            case R:
                heading.changeDirectionRight(instruction.getArgument());
                break;
            case F:
                movePositionForward(instruction.getArgument());
                break;
            default:
                throw new IllegalStateException("Action " + instruction.getAction().name() + " is not supported");
        }
    }

    private void movePositionForward(int value) {
        switch (heading.getCurrentDirection()) {
            case N:
                position.moveNorth(value);
                break;
            case S:
                position.moveSouth(value);
                break;
            case E:
                position.moveEast(value);
                break;
            case W:
                position.moveWest(value);
                break;
        }
    }
}
