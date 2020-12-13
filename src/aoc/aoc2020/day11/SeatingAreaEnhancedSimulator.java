package aoc.aoc2020.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatingAreaEnhancedSimulator {

    private static final char EMPTY_SEAT = 'L';
    private static final char OCCUPIED_SEAT = '#';

    private char[][] seatLayout;
    private char[][] seatLayoutCopy;

    public SeatingAreaEnhancedSimulator(char[][] seatLayout) {
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
                    if (seat == EMPTY_SEAT && hasEmptyFirstVisibleSeats(i, j)) {
                        seatLayoutCopy[i][j] = OCCUPIED_SEAT;
                        seatStateChanged = true;
                    } else if (seat == OCCUPIED_SEAT && hasFiveOrMoreOccupiedAdjacentSeats(i, j)) {
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

    private boolean hasEmptyFirstVisibleSeats(int row, int column) {
        List<Character> firstVisibleSeats = getFirstSeatsInEachDirection(row, column);
        return firstVisibleSeats.stream().allMatch(seat -> seat != OCCUPIED_SEAT);
    }

    private boolean hasFiveOrMoreOccupiedAdjacentSeats(int row, int column) {
        List<Character> firstVisibleSeats = getFirstSeatsInEachDirection(row, column);
        return firstVisibleSeats.stream().filter(seat -> seat == OCCUPIED_SEAT).count() >= 5;
    }

    private List<Character> getFirstSeatsInEachDirection(int row, int column) {
        List<Character> adjacentSeats = new ArrayList<>(8);
        int[][] directions = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        int maxDistance = seatLayout.length;

        for (int[] direction : directions) {
            for (int m = 1; m < maxDistance; m++) {
                int adjacentRow = row + direction[0] * m;
                int adjacentColumn = column + direction[1] * m;

                if (adjacentRow < 0 || adjacentRow >= seatLayout.length || adjacentColumn < 0 || adjacentColumn >= seatLayout[adjacentRow].length) {
                    break;
                }

                char seat = seatLayout[adjacentRow][adjacentColumn];
                if (seat == EMPTY_SEAT || seat == OCCUPIED_SEAT) {
                    adjacentSeats.add(seat);
                    break;
                }
            }
        }

        return adjacentSeats;
    }

    private char[][] copyMatrix(char[][] matrix) {
        return Arrays.stream(matrix).map(char[]::clone).toArray(char[][]::new);
    }
}
