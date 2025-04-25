package org.example;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;

/**
 * A factory class for creating various geometric shapes (stars, rectangles, triangles, arcs, etc.)
 * based on a given shape type. This class allows you to easily create a specific shape with
 * configurable stroke, paint, width, and height.
 */
public class ShapeFactory {

   /**
    * Default width for shapes.
    */
   public static final int DEFAULT_WIDTH = 25;

   /**
    * Default height for shapes.
    */
   public static final int DEFAULT_HEIGHT = 25;

   /**
    * Default stroke for shapes (3.0f thickness).
    */
   public static final BasicStroke DEFAULT_STROKE = new BasicStroke(3.0F);

   /**
    * The shape object created by this factory.
    */
   private final Shape shape;

   /**
    * The stroke style for the shape.
    */
   private BasicStroke stroke;

   /**
    * The paint applied to the shape (color or gradient).
    */
   private Paint paint;

   /**
    * The width of the shape.
    */
   private final int width;

   /**
    * The height of the shape.
    */
   private final int height;

   /**
    * Constructor to create a shape based on the given shape type.
    *
    * The shape type corresponds to different types of geometric shapes.
    * For example, 10 represents a star with 3 arms, 30 represents a star with 5 arms,
    * and 5 represents a rectangle.
    *
    * @param shape_type The type of the shape (e.g., 10 for a star with 3 arms, 30 for a star with 5 arms).
    * @throws IllegalArgumentException If the shape type is unsupported.
    */
   public ShapeFactory(int shape_type) {
      this.width = DEFAULT_WIDTH;
      this.height = DEFAULT_HEIGHT;
      this.stroke = DEFAULT_STROKE;

      // Create the shape based on the type
      switch (shape_type / 10) {
         case 1: // Star with 3 arms
            this.shape = createStar(3, new Point(0, 0), this.width / 2.0, this.width / 2.0);
            break;
         case 3: // Star with 5 arms
            this.shape = createStar(5, new Point(0, 0), this.width / 2.0, this.width / 4.0);
            break;
         case 5: // Rectangle
            this.shape = new Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height);
            break;
         case 7: // Triangle
            this.shape = createTriangle();
            break;
         case 9: // Arc
            this.shape = new Arc2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height, 30.0, 300.0, Arc2D.PIE);
            break;
         default:
            throw new IllegalArgumentException("Shape type is unsupported.");
      }

      // Set stroke and paint based on the shape type
      switch (shape_type % 10) {
         case 1:
            this.stroke = new BasicStroke(3.0F); // Default stroke thickness
            break;
         case 4:
            this.stroke = new BasicStroke(7.0F); // Stroke thickness for type 4
            break;
         case 7:
            this.paint = new GradientPaint(-this.width, -this.height, Color.white, this.width, this.height, Color.gray, true);
            break;
         case 8:
            this.paint = Color.red;
            break;
         default:
            throw new IllegalArgumentException("Unsupported stroke/paint type.");
      }
   }

   /**
    * Creates a star shape with a specified number of arms.
    *
    * @param arms The number of arms for the star (e.g., 3 for a 3-pointed star).
    * @param center The center point of the star.
    * @param rOuter The radius of the outer points of the star.
    * @param rInner The radius of the inner points of the star.
    * @return A GeneralPath representing the star shape.
    */
   private Shape createStar(int arms, Point center, double rOuter, double rInner) {
      double angle = Math.PI / arms;
      GeneralPath path = new GeneralPath();
      for (int i = 0; i < 2 * arms; i++) {
         double r = (i & 1) == 0 ? rOuter : rInner;
         Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
         if (i == 0) {
            path.moveTo(p.getX(), p.getY());
         } else {
            path.lineTo(p.getX(), p.getY());
         }
      }
      path.closePath();
      return path;
   }

   /**
    * Creates a triangle shape based on the width and height.
    * The triangle is an equilateral triangle with base equal to the width.
    *
    * @return A GeneralPath representing the triangle shape.
    */
   private Shape createTriangle() {
      GeneralPath path = new GeneralPath();
      double tmpHeight = Math.sqrt(2.0) / 2.0 * this.height;
      path.moveTo(-this.width / 2, -tmpHeight);
      path.lineTo(0.0, -tmpHeight);
      path.lineTo(this.width / 2, tmpHeight);
      path.closePath();
      return path;
   }

   // Getter methods for the shape properties

   /**
    * Gets the created shape.
    *
    * @return The shape created by the factory.
    */
   public Shape getShape() {
      return shape;
   }

   /**
    * Gets the stroke applied to the shape.
    *
    * @return The stroke applied to the shape.
    */
   public BasicStroke getStroke() {
      return stroke;
   }

   /**
    * Gets the paint applied to the shape (could be color or gradient).
    *
    * @return The paint applied to the shape.
    */
   public Paint getPaint() {
      return paint;
   }

   /**
    * Gets the width of the shape.
    *
    * @return The width of the shape.
    */
   public int getWidth() {
      return width;
   }

   /**
    * Gets the height of the shape.
    *
    * @return The height of the shape.
    */
   public int getHeight() {
      return height;
   }
}
