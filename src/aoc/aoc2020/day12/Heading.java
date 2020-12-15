package aoc.aoc2020.day12;

import java.util.List;

public class Heading {

    private static final List<Direction> DIRECTIONS = List.of(Direction.E, Direction.S, Direction.W, Direction.N);

    private Direction currentDirection;

    public Heading(Direction startDirection) {
        this.currentDirection = startDirection;
    }

    public void changeDirectionLeft(int degrees) {
        changeDirection(-degrees);
    }

    public void changeDirectionRight(int degrees) {
        changeDirection(degrees);
    }

    private void changeDirection(int degrees) {
        assert degrees % 90 == 0;

        int currentDirectionIndex = DIRECTIONS.indexOf(currentDirection);
        int newDirectionIndex = (DIRECTIONS.size() + currentDirectionIndex + (degrees % 360) / 90) % DIRECTIONS.size();

        this.currentDirection = DIRECTIONS.get(newDirectionIndex);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
