package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

/**
 * A JPanel that displays a grid of rotating geometric shapes (as defined by {@link ShapeFactory}).
 * The shapes are animated and their rotation is controlled by a timer.
 */
public class TitlesPanel extends JPanel implements ActionListener {

   /**
    * Graphics2D object used for rendering shapes.
    */
   private Graphics2D g2d;

   /**
    * Timer object used to control the animation of the shapes.
    */
   private Timer animation;

   /**
    * Flag indicating whether the drawing operation is complete or not.
    */
   private boolean is_done = true;

   /**
    * The starting angle for rotating the shapes.
    */
   private int start_angle = 0;

   /**
    * The type of shape to be drawn, passed as a parameter to the {@link ShapeFactory}.
    */
   private int shape;

   /**
    * Constructs a TitlesPanel with a specific shape type.
    * Initializes the animation timer.
    *
    * @param _shape The type of shape to draw, passed to the {@link ShapeFactory}.
    */
   public TitlesPanel(int _shape) {
      this.shape = _shape;
      // Initializes the Timer to trigger every 50 milliseconds
      (this.animation = new Timer(50, this)).setInitialDelay(50);
      this.animation.start();
   }

   /**
    * Handles the timer events. Repaints the panel when triggered.
    *
    * @param arg0 The ActionEvent triggered by the Timer.
    */
   public void actionPerformed(ActionEvent arg0) {
      if (this.is_done) {
         // Repaint the panel when the timer fires
         this.repaint();
      }
   }

   /**
    * Performs the actual drawing of shapes. This method is called from {@link #paintComponent(Graphics)}.
    * It draws a grid of rotating shapes based on the current angle and shape type.
    *
    * @param g The Graphics object used for rendering the shapes.
    */
   private void doDrawing(Graphics g) {
      this.is_done = false;

      // Set up the Graphics2D context for better rendering quality
      (this.g2d = (Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Get the panel size and insets
      Dimension size = this.getSize();
      Insets insets = this.getInsets();
      int w = size.width - insets.left - insets.right;
      int h = size.height - insets.top - insets.bottom;

      // Create the shape using the provided shape type
      ShapeFactory shape = new ShapeFactory(this.shape);

      // Set the stroke and paint based on the ShapeFactory's properties
      this.g2d.setStroke(shape.stroke);
      this.g2d.setPaint(shape.paint);

      // Calculate the rotation angle for each shape
      double angle = (double) (this.start_angle++);
      if (this.start_angle > 360) {
         this.start_angle = 0;
      }

      // Calculate the angle increment based on panel width and shape width
      double dr = 90.0D / ((double) w / ((double) shape.width * 1.5D));

      // Draw the shapes in a grid pattern
      for (int j = shape.height; j < h; j += (int) ((double) shape.height * 1.5D)) {
         for (int i = shape.width; i < w; i += (int) ((double) shape.width * 1.5D)) {
            angle = angle > 360.0D ? 0.0D : angle + dr;

            // Apply translation and rotation to the shape
            AffineTransform transform = new AffineTransform();
            transform.translate((double) i, (double) j);
            transform.rotate(Math.toRadians(angle));

            // Draw the transformed shape
            this.g2d.draw(transform.createTransformedShape(shape.shape));
         }
      }

      this.is_done = true;
   }

   /**
    * Paints the component by calling {@link #doDrawing(Graphics)} to perform the actual drawing.
    * This method is called automatically by the Swing framework when the component needs to be redrawn.
    *
    * @param g The Graphics object used for painting the component.
    */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.doDrawing(g);
   }
}
