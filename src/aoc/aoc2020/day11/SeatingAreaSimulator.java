package aoc.aoc2020.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatingAreaSimulator {

    private static final char EMPTY_SEAT = 'L';
    private static final char OCCUPIED_SEAT = '#';

    private char[][] seatLayout;
    private char[][] seatLayoutCopy;

    public SeatingAreaSimulator(char[][] seatLayout) {
        this.seatLayout = copyMatrix(seatLayout);
        this.seatLayoutCopy = copyMatrix(seatLayout);
    }

    public void simulate() {
        boolean seatStateChanged = true;

        while (seatStateChanged) {
            seatStateChanged = false;

            for (int i = 0; i < seatLayout.length; i++) {
                for (int j = 0; j < seatLayout[i].length; j++) {
                    char seat = seatLayout[i][j];
                    if (seat == EMPTY_SEAT && hasEmptyAdjacentSeats(i, j)) {
                        seatLayoutCopy[i][j] = OCCUPIED_SEAT;
                        seatStateChanged = true;
                    } else if (seat == OCCUPIED_SEAT && hasFourOrMoreOccupiedAdjacentSeats(i, j)) {
                        seatLayoutCopy[i][j] = EMPTY_SEAT;
                        seatStateChanged = true;
                    }
                }
            }

            seatLayout = seatLayoutCopy;
            seatLayoutCopy = copyMatrix(seatLayout);
        }
    }

    public int getOccupiedSeatsCount() {
        return Arrays.stream(seatLayout)
                .map(seatRow -> new String(seatRow).chars().filter(seat -> seat == OCCUPIED_SEAT).count())
                .mapToInt(Long::intValue).sum();
    }

    private boolean hasEmptyAdjacentSeats(int row, int column) {
        List<Character> adjacentSeats = getAdjacentSeats(row, column);
        return adjacentSeats.stream().allMatch(seat -> seat != OCCUPIED_SEAT);
    }

    private boolean hasFourOrMoreOccupiedAdjacentSeats(int row, int column) {
        List<Character> adjacentSeats = getAdjacentSeats(row, column);
        return adjacentSeats.stream().filter(seat -> seat == OCCUPIED_SEAT).count() >= 4;
    }

    private List<Character> getAdjacentSeats(int row, int column) {
        List<Character> adjacentSeats = new ArrayList<>(8);

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int adjacentRow = row + dx;
                int adjacentColumn = column + dy;
                if (!(adjacentRow < 0 || adjacentRow >= seatLayout.length || adjacentColumn < 0 || adjacentColumn >= seatLayout[adjacentRow].length) && (dx != 0 || dy != 0)) {
                    adjacentSeats.add(seatLayout[row + dx][column + dy]);
                }
            }
        }

        return adjacentSeats;
    }

    private char[][] copyMatrix(char[][] matrix) {
        return Arrays.stream(matrix).map(char[]::clone).toArray(char[][]::new);
    }
}
