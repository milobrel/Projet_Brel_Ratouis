/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author milob
 */
public class Grille {
    public static final int SIZE = 4;
    private int[][] grid;

    public Grille() {
        grid = new int[SIZE][SIZE];
        addRandomTile();
        addRandomTile();
    }
}
