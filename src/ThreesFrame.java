/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import threes.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import threes.model.Direction;
import threes.model.GameModel;
import threes.model.MoveResult;
/**
 * Fenêtre principale du jeu Threes
 */
public class ThreesFrame extends javax.swing.JFrame {
    private GameModel model;
    private JButton[][] cells;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ThreesFrame.class.getName());
   
   
     public ThreesFrame() {
    initComponents();   // NetBeans
    initGame();
    initGrid();
    initKeyBindings();  // ← maintenant la méthode existe
    setLayout(new BorderLayout());
add(infoLabel, BorderLayout.NORTH); // message en haut
add(gridPanel, BorderLayout.CENTER); // grille au centre

    updateView();
}


         private void initGame() {
    model = new GameModel();
    model.reset();
    cells = new JButton[4][4];

    gridPanel = jPanel1; // ← IMPORTANT
    gridPanel.setLayout(new GridLayout(4, 4, 5, 5));
}

         
       private void resetGame() {
    model.reset();
    infoLabel.setText("Nouvelle partie");
    updateView();
}

        private void initGrid() {
    gridPanel.removeAll();

    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 4; c++) {
            JButton btn = new JButton();
            btn.setEnabled(false);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setBackground(Color.LIGHT_GRAY);
            cells[r][c] = btn;
            gridPanel.add(btn);
        }
    }
    infoLabel = new JLabel("Nouvelle partie");
infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
getContentPane().add(infoLabel, BorderLayout.NORTH); // met le label en haut

    gridPanel.revalidate();
    gridPanel.repaint();
   

}
    {

        JComponent root = getRootPane();
        InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = root.getActionMap();

        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play(Direction.UP);
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play(Direction.DOWN);
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play(Direction.LEFT);
            }
        });

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play(Direction.RIGHT);
            }
        });
    }

    // ===== JOUER =====
   private void play(Direction dir) {
    if (model.isGameOver()) {
        infoLabel.setText("GAME OVER !");
        return;
    }

    MoveResult res = model.move(dir);
    if (!res.moved) return;

    model.spawnOnBorder(dir);
    updateView();

    // Vérifie si le jeu est terminé après le déplacement
    if (model.isGameOver()) {
        infoLabel.setText("GAME OVER !");
    } else {
        infoLabel.setText("Continuez à jouer");
    }
}


    private JLabel infoLabel2;

    // ===== AFFICHAGE =====
    private void updateView() {
    int[][] grid = model.getGrid();

    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 4; c++) {
            int v = grid[r][c];
            JButton btn = cells[r][c];
            btn.setText(v == 0 ? "" : String.valueOf(v));
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
     private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        resetGame() ;
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ThreesFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

private JPanel gridPanel;
private JLabel infoLabel;
private JLabel movesLabel;

private void initKeyBindings() {
    JComponent root = getRootPane(); // la racine pour capter les touches
    InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = root.getActionMap();

    // Définir les touches
    im.put(KeyStroke.getKeyStroke("UP"), "up");
    im.put(KeyStroke.getKeyStroke("DOWN"), "down");
    im.put(KeyStroke.getKeyStroke("LEFT"), "left");
    im.put(KeyStroke.getKeyStroke("RIGHT"), "right");

    // Associer chaque touche à une action
    am.put("up", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            play(Direction.UP);
        }
    });

    am.put("down", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            play(Direction.DOWN);
        }
    });

    am.put("left", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            play(Direction.LEFT);
        }
    });

    am.put("right", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            play(Direction.RIGHT);
        }
    });
}

}