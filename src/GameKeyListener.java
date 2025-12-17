/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



  import java.awt.event.*;
import javax.swing.*;

public class GameKeyListener extends KeyAdapter {
    private Grille game;
    private JPanel panel;

    public GameKeyListener(Grille game, JPanel panel) {
        this.game = game;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> moved = game.moveLeft();
            case KeyEvent.VK_RIGHT -> moved = game.moveRight();
            case KeyEvent.VK_UP -> moved = game.moveUp();
            case KeyEvent.VK_DOWN -> moved = game.moveDown();
        }

        if (moved) {
            game.addRandomTile();
            panel.repaint();

            if (game.isGameOver()) {
                JOptionPane.showMessageDialog(panel, "Partie terminée !");
            }
        }
    }
}
  
