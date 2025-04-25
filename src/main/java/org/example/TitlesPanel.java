package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

/**
 * A JPanel that displays a grid of rotating geometric shapes (as defined by {@link ShapeFactory}).
 * The shapes are animated and their rotation is controlled by a timer.
 * The animation runs at a fixed rate and the shapes are drawn at various positions on the panel.
 */
public class TitlesPanel extends JPanel implements ActionListener {

   /**
    * Graphics2D object used to perform rendering.
    */
   private Graphics2D g2d;

   /**
    * Timer responsible for triggering the animation.
    */
   private Timer animation;

   /**
    * Flag to track the drawing state.
    */
   private boolean isDone = true;

   /**
    * The current angle of rotation for the shapes.
    */
   private int startAngle = 0;

   /**
    * Shape type that defines the kind of shape to be drawn.
    */
   private int shape;

   /**
    * Constructor to initialize the TitlesPanel with a specific shape type.
    *
    * @param shapeType The type of shape to display (e.g., star, triangle, etc.).
    */
   public TitlesPanel(int shapeType) {
      this.shape = shapeType;
      this.animation = new Timer(50, this);  // Set timer interval to 50ms
      this.animation.setInitialDelay(50);    // Initial delay of 50ms before starting animation
      this.animation.start();                // Start the animation
   }

   /**
    * Called whenever the timer triggers an event. It repaints the panel to update the animation.
    *
    * @param arg0 The ActionEvent triggered by the timer.
    */
   @Override
   public void actionPerformed(ActionEvent arg0) {
      if (isDone) {
         repaint();  // Repaint the panel to animate the shapes
      }
   }

   /**
    * Handles the drawing logic for the panel. Shapes are drawn and rotated on the panel at various positions.
    *
    * @param g The Graphics object used for drawing on the panel.
    */
   private void doDrawing(Graphics g) {
      isDone = false;
      g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Get panel size and adjust drawing area for insets
      Dimension size = getSize();
      Insets insets = getInsets();
      int w = size.width - insets.left - insets.right;
      int h = size.height - insets.top - insets.bottom;

      // Create shape using ShapeFactory
      ShapeFactory shapeFactory = new ShapeFactory(shape);
      g2d.setStroke(shapeFactory.getStroke());
      g2d.setPaint(shapeFactory.getPaint());

      // Increment and reset the rotation angle
      double angle = startAngle++;
      if (startAngle > 360) {
         startAngle = 0;
      }

      // Adjust rotation speed based on the width of the panel
      double dr = 90.0D / ((double) w / (shapeFactory.getWidth() * 1.5D));

      // Draw shapes in a grid pattern
      for (int j = shapeFactory.getHeight(); j < h; j += (int) (shapeFactory.getHeight() * 1.5D)) {
         for (int i = shapeFactory.getWidth(); i < w; i += (int) (shapeFactory.getWidth() * 1.5D)) {
            angle = angle > 360.0D ? 0.0D : angle + dr;
            AffineTransform transform = new AffineTransform();
            transform.translate(i, j);  // Translate shape to grid position
            transform.rotate(Math.toRadians(angle));  // Rotate shape
            g2d.draw(transform.createTransformedShape(shapeFactory.getShape()));  // Draw the shape
         }
      }
      isDone = true;
   }

   /**
    * Paints the component by calling the custom drawing logic.
    *
    * @param g The Graphics object to paint with.
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);  // Call superclass method to handle basic painting
      doDrawing(g);  // Custom drawing logic
   }
}
