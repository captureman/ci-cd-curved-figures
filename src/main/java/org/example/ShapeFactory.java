package org.example;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D.Double;

/**
 * A factory class for creating various geometric shapes (stars, rectangles, triangles, etc.)
 * based on a given shape type.
 */
public class ShapeFactory {

   /**
    * The Shape object that holds the created shape.
    */
   public Shape shape;

   /**
    * The stroke style (thickness) for all shapes by default.
    */
   public BasicStroke stroke = new BasicStroke(3.0F);

   /**
    * The color or gradient paint applied to the shape.
    */
   public Paint paint;

   /**
    * The width of the shape (default is 25).
    */
   public int width = 25;

   /**
    * The height of the shape (default is 25).
    */
   public int height = 25;

   /**
    * Constructor for creating a shape based on the given type.
    *
    * @param shape_type The type of the shape (e.g., 10 for a star with 3 arms, 30 for a star with 5 arms).
    * @throws Error If the type is unsupported.
    */
   public ShapeFactory(int shape_type) {
      switch(shape_type / 10) {
         case 1:
            // Creates a star with 3 arms.
            this.shape = createStar(3, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
            break;
         case 2:
         case 4:
         case 6:
         case 8:
         default:
            // Throws an error if the shape type is unsupported.
            throw new Error("type is unsupported");
         case 3:
            // Creates a star with 5 arms.
            this.shape = createStar(5, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 4.0D);
            break;
         case 5:
            // Creates a rectangle.
            this.shape = new Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height);
            break;
         case 7:
            // Creates a triangle.
            GeneralPath path = new GeneralPath();
            double tmp_height = Math.sqrt(2.0D) / 2.0D * (double)this.height;
            path.moveTo((double)(-this.width / 2), -tmp_height);
            path.lineTo(0.0D, -tmp_height);
            path.lineTo((double)(this.width / 2), tmp_height);
            path.closePath();
            this.shape = path;
            break;
         case 9:
            // Creates an arc.
            this.shape = new java.awt.geom.Arc2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height, 30.0D, 300.0D, 2);
      }

      // Apply stroke or paint based on the shape type.
      switch(shape_type % 10) {
         case 1:
            // Default stroke thickness.
            this.stroke = new BasicStroke(3.0F);
            break;
         case 2:
         case 5:
         case 6:
         default:
            // Throws an error if the stroke type is unsupported.
            throw new Error("type is unsupported");
         case 3:
            break;
         case 4:
            // Stroke thickness 7 for type 4.
            this.stroke = new BasicStroke(7.0F);
            break;
         case 7:
            // Apply a gradient paint.
            this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
            break;
         case 8:
            // Apply a red color for type 8.
            this.paint = Color.red;
      }
   }

   /**
    * Creates a star shape with a specified number of arms.
    *
    * @param arms The number of arms of the star (e.g., 3 for a 3-pointed star).
    * @param center The center point of the star.
    * @param rOuter The radius of the outer points of the star.
    * @param rInner The radius of the inner points of the star.
    * @return The created star shape as a GeneralPath.
    */
   private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
      double angle = Math.PI / (double)arms;
      GeneralPath path = new GeneralPath();

      for(int i = 0; i < 2 * arms; ++i) {
         double r = (i & 1) == 0 ? rOuter : rInner;
         java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
         if (i == 0) {
            path.moveTo(p.getX(), p.getY());
         } else {
            path.lineTo(p.getX(), p.getY());
         }
      }

      path.closePath();
      return path;
   }
}
