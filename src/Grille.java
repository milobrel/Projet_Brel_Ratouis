/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author milob
 */
import java.util.*;

public class Grille {
    public static final int SIZE = 4;
    private int[][] grid;
    private Random random = new Random();

    public Grille() {
        grid = new int[SIZE][SIZE];
        addRandomTile();
        addRandomTile();
    }

    public int[][] getGrid() {
        return grid;
    }

    public void addRandomTile() {
        List<int[]> empty = new ArrayList<>();

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (grid[i][j] == 0)
                    empty.add(new int[]{i, j});

        if (empty.isEmpty()) return;

        int[] pos = empty.get(random.nextInt(empty.size()));
        grid[pos[0]][pos[1]] = random.nextBoolean() ? 1 : 2;
    }

    private boolean canMerge(int a, int b) {
        return (a == b && a >= 3) ||
               (a == 1 && b == 2) ||
               (a == 2 && b == 1);
    }

    private int merge(int a, int b) {
        if ((a == 1 && b == 2) || (a == 2 && b == 1))
            return 3;
        return a * 2;
    }

    public boolean moveLeft() {
        boolean moved = false;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                if (grid[row][col] != 0) {
                    int c = col;
                    while (c > 0 && grid[row][c - 1] == 0) {
                        grid[row][c - 1] = grid[row][c];
                        grid[row][c] = 0;
                        c--;
                        moved = true;
                    }
                    if (c > 0 && canMerge(grid[row][c - 1], grid[row][c])) {
                        grid[row][c - 1] = merge(grid[row][c - 1], grid[row][c]);
                        grid[row][c] = 0;
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    public boolean moveRight() {
        rotate180();
        boolean moved = moveLeft();
        rotate180();
        return moved;
    }

    public boolean moveUp() {
        rotateLeft();
        boolean moved = moveLeft();
        rotateRight();
        return moved;
    }

    public boolean moveDown() {
        rotateRight();
        boolean moved = moveLeft();
        rotateLeft();
        return moved;
    }

    private void rotateLeft() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newGrid[SIZE - j - 1][i] = grid[i][j];
        grid = newGrid;
    }

    private void rotateRight() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newGrid[j][SIZE - i - 1] = grid[i][j];
        grid = newGrid;
    }

    private void rotate180() {
        rotateLeft();
        rotateLeft();
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (grid[i][j] == 0)
                    return false;
        return true;
    }
}