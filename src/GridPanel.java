/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author milob
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GridPanel extends JPanel {
    private Grille game;

    public GridPanel(Grille game) {
        this.game = game;
        setFocusable(true);
        addKeyListener(new GameKeyListener(game, this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = getWidth() / Grille.SIZE;

        for (int i = 0; i < Grille.SIZE; i++) {
            for (int j = 0; j < Grille.SIZE; j++) {
                int value = game.getGrid()[i][j];

                g.setColor(getColor(value));
                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);

                if (value != 0) {
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    String text = String.valueOf(value);
                    FontMetrics fm = g.getFontMetrics();
                    int x = j * tileSize + (tileSize - fm.stringWidth(text)) / 2;
                    int y = i * tileSize + (tileSize + fm.getAscent()) / 2 - 4;
                    g.drawString(text, x, y);
                }
            }
        }
    }

    private Color getColor(int value) {
        return switch (value) {
            case 0 -> Color.LIGHT_GRAY;
            case 1 -> new Color(200, 220, 255);
            case 2 -> new Color(200, 255, 200);
            case 3 -> new Color(255, 220, 180);
            default -> new Color(255, 180, 180);
        };
    }

    private static class GameKeyListener implements KeyListener {

        public GameKeyListener(Grille game, GridPanel aThis) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}