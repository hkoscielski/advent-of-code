package aoc.aoc2020.day12;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveNorth(int units) {
        this.y += units;
    }

    public void moveSouth(int units) {
        this.y -= units;
    }

    public void moveEast(int units) {
        this.x += units;
    }

    public void moveWest(int units) {
        this.x -= units;
    }

    public void moveMultipliedPosition(Position position, int multiplier) {
        this.x += position.getX() * multiplier;
        this.y += position.getY() * multiplier;
    }

    public void changeQuarter(int times) {
        for (int i = 0; i < times; i++) {
            int temp = this.x;
            this.x = this.y;
            this.y = -temp;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
