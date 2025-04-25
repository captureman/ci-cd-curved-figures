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
      this.initUI();
   }

   /**
    * Initializes the user interface for the frame.
    * Sets the title, default close operation, panel, window size, and location.
    */
   private void initUI() {
      // Set the title of the window
      this.setTitle("Криві фігури");

      // Set the default close operation (close the application when the window is closed)
      this.setDefaultCloseOperation(3); // 3 corresponds to EXIT_ON_CLOSE

      // Add a custom TitlesPanel with a specific parameter (78)
      this.add(new TitlesPanel(78));

      // Set the window size (350x350 pixels)
      this.setSize(350, 350);

      // Center the window on the screen
      this.setLocationRelativeTo(null);
   }

   /**
    * The entry point for the program. Creates and displays the {@link TitlesFrame} window.
    *
    * @param args Command-line arguments (not used in this program).
    */
   public static void main(String[] args) {
      // Using SwingUtilities.invokeLater to ensure the UI updates are done on the Event Dispatch Thread (EDT)
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            // Create an instance of TitlesFrame and set it visible
            TitlesFrame ps = new TitlesFrame();
            ps.setVisible(true);
         }
      });
   }
}
