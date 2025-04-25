package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * A JFrame subclass that creates a window displaying a panel with curved shapes.
 * The window is titled "Криві фігури" (Curved Shapes) and displays a custom JPanel with a shape.
 */
public class TitlesFrame extends JFrame {

   /**
    * Default constructor that initializes the user interface by calling {@link #initUI()}.
    */
   public TitlesFrame() {
      initUI();
   }

   /**
    * Initializes the user interface for the frame.
    * Sets the title, default close operation, panel, window size, and location.
    */
   private void initUI() {
      setTitle("Криві фігури");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      add(new TitlesPanel(78));
      setSize(350, 350);
      setLocationRelativeTo(null);
   }

   /**
    * The entry point for the program. Creates and displays the {@link TitlesFrame} window.
    */
   public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
         TitlesFrame frame = new TitlesFrame();
         frame.setVisible(true);
      });
   }
}
