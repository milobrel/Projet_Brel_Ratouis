/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threes.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GameModel {

    public static final int SIZE = 4;

    private final int[][] grid = new int[SIZE][SIZE];
    private final Random rng = new Random();

    private int movesCount = 0;

    public int getMovesCount() {
        return movesCount;
    }

    public int[][] getGrid() {
        return grid;
    }

    // ===== INITIALISATION =====
    public void reset() {
        for (int r = 0; r < SIZE; r++) {
            Arrays.fill(grid[r], 0);
        }
        movesCount = 0;

        // Deux tuiles au départ
        spawnRandomAnywhere();
        spawnRandomAnywhere();
    }

    private void spawnRandomAnywhere() {
        List<int[]> empties = emptyCells();
        if (empties.isEmpty()) return;

        int[] pos = empties.get(rng.nextInt(empties.size()));
        grid[pos[0]][pos[1]] = rng.nextBoolean() ? 1 : 2;
    }

    // ===== SPAWN SUR LE BORD (règle Threes) =====
    public boolean spawnOnBorder(Direction dir) {
        List<int[]> candidates = new ArrayList<>();

        switch (dir) {
            case LEFT -> {
                for (int r = 0; r < SIZE; r++)
                    if (grid[r][SIZE - 1] == 0)
                        candidates.add(new int[]{r, SIZE - 1});
            }

            case RIGHT -> {
                for (int r = 0; r < SIZE; r++)
                    if (grid[r][0] == 0)
                        candidates.add(new int[]{r, 0});
            }

            case UP -> {
                for (int c = 0; c < SIZE; c++)
                    if (grid[SIZE - 1][c] == 0)
                        candidates.add(new int[]{SIZE - 1, c});
            }

            case DOWN -> {
                for (int c = 0; c < SIZE; c++)
                    if (grid[0][c] == 0)
                        candidates.add(new int[]{0, c});
            }
        }

        if (candidates.isEmpty()) return false;

        int[] pos = candidates.get(rng.nextInt(candidates.size()));
        grid[pos[0]][pos[1]] = rng.nextBoolean() ? 1 : 2;
        return true;
    }

    private List<int[]> emptyCells() {
        List<int[]> res = new ArrayList<>();
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (grid[r][c] == 0)
                    res.add(new int[]{r, c});
        return res;
    }

    // ===== FIN DE PARTIE =====
    public boolean isGameOver() {
        if (!emptyCells().isEmpty()) return false;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int v = grid[r][c];
                if (c + 1 < SIZE && canMerge(v, grid[r][c + 1])) return false;
                if (r + 1 < SIZE && canMerge(v, grid[r + 1][c])) return false;
            }
        }
        return true;
    }

    // ===== DEPLACEMENT =====
   public MoveResult move(Direction dir) {

    boolean movedAny = false;
    int merges = 0;

    if (dir == Direction.LEFT) {
        for (int r = 0; r < SIZE; r++) {
            int[] line = Arrays.copyOf(grid[r], SIZE);
            LineResult lr = compressAndMerge(line);
            movedAny |= lr.changed;
            merges += lr.merges;
            grid[r] = lr.out;
        }
    } else if (dir == Direction.RIGHT) {
        for (int r = 0; r < SIZE; r++) {
            int[] line = new int[SIZE];
            for (int c = 0; c < SIZE; c++)
                line[c] = grid[r][SIZE - 1 - c];
            LineResult lr = compressAndMerge(line);
            movedAny |= lr.changed;
            merges += lr.merges;
            for (int c = 0; c < SIZE; c++)
                grid[r][SIZE - 1 - c] = lr.out[c];
        }
    } else if (dir == Direction.UP) {
        for (int c = 0; c < SIZE; c++) {
            int[] line = new int[SIZE];
            for (int r = 0; r < SIZE; r++)
                line[r] = grid[r][c];
            LineResult lr = compressAndMerge(line);
            movedAny |= lr.changed;
            merges += lr.merges;
            for (int r = 0; r < SIZE; r++)
                grid[r][c] = lr.out[r];
        }
    } else if (dir == Direction.DOWN) {
        for (int c = 0; c < SIZE; c++) {
            int[] line = new int[SIZE];
            for (int r = 0; r < SIZE; r++)
                line[r] = grid[SIZE - 1 - r][c];
            LineResult lr = compressAndMerge(line);
            movedAny |= lr.changed;
            merges += lr.merges;
            for (int r = 0; r < SIZE; r++)
                grid[SIZE - 1 - r][c] = lr.out[r];
        }
    }

    if (movedAny) movesCount++;
    return new MoveResult(movedAny, merges);
   
   }
    private boolean canMerge(int v, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LineResult compressAndMerge(int[] line) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private static class LineResult {
    int[] out;      // la ligne après compression/fusion
    boolean changed; // vrai si quelque chose a bougé
    int merges;      // nombre de fusions

    
    LineResult(int[] out, boolean changed, int merges){
        this.out = out;
        this.changed = changed;
        this.merges = merges;
    
    }