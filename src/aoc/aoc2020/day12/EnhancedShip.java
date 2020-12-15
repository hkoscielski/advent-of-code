package aoc.aoc2020.day12;

import java.util.List;

public class EnhancedShip {

    private final Position position;
    private final Position waypointPosition;

    public EnhancedShip(Position waypointPosition) {
        this.position = new Position(0, 0);
        this.waypointPosition = waypointPosition;
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
                waypointPosition.moveNorth(instruction.getArgument());
                break;
            case S:
                waypointPosition.moveSouth(instruction.getArgument());
                break;
            case E:
                waypointPosition.moveEast(instruction.getArgument());
                break;
            case W:
                waypointPosition.moveWest(instruction.getArgument());
                break;
            case L:
                waypointPosition.changeQuarter((360 - instruction.getArgument()) / 90);
                break;
            case R:
                waypointPosition.changeQuarter(instruction.getArgument() / 90);
                break;
            case F:
                position.moveMultipliedPosition(waypointPosition, instruction.getArgument());
                break;
            default:
                throw new IllegalStateException("Action " + instruction.getAction().name() + " is not supported");
        }
    }
}
