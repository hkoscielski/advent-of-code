package aoc.aoc2020.day3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TobogganTrajectory {

    private static final char TREE_SLOPE = '#';

    private static class MoveVariant {
        private final int right;
        private final int down;

        public MoveVariant(int right, int down) {
            this.right = right;
            this.down = down;
        }

        public int getRight() {
            return right;
        }

        public int getDown() {
            return down;
        }
    }

    public static void main(String[] args) throws IOException {
        char[][] grid = Files.lines(Paths.get("src/aoc/aoc2020/day3/input.txt"), StandardCharsets.UTF_8)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        MoveVariant[] moveVariants = {
                new MoveVariant(1, 1),
                new MoveVariant(3, 1),
                new MoveVariant(5, 1),
                new MoveVariant(7, 1),
                new MoveVariant(1, 2)
        };

        long product = 1;

        for (MoveVariant moveVariant : moveVariants) {
            int x = 0;
            int y = 0;
            int treeCount = 0;

            while (y < grid.length) {
                x += moveVariant.getRight();
                y += moveVariant.getDown();

                if (y < grid.length) {
                    if (grid[y][x % grid[y].length] == TREE_SLOPE) {
                        treeCount++;
                    }
                }
            }

            product *= treeCount;
            System.out.printf("MoveVariant(%d, %d) - treeCount = %d\n", moveVariant.getRight(), moveVariant.getDown(), treeCount);
        }

        System.out.println("Product of tree counts = " + product);
    }
}
