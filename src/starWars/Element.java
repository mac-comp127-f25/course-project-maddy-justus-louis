package starWars;


import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;


public class Element extends GraphicsGroup {
   private String type;
   private double width, height;


   public Element(String imageFile, String type, double scale, double cellSize) {
       this.type = type;


       Image img = new Image(imageFile);
       img.setScale(scale);
       img.setMaxWidth(cellSize);
       img.setMaxHeight(cellSize);
       img.setAnchor(cellSize/2, cellSize/2);
       add(img);


       width = cellSize;
       height = cellSize;
   }

   public String getType() { return type; }

   public double getLeft() { return getCenter().getX() - width/2; }
   public double getRight(){ return getCenter().getX() + width/2; }
   public double getTop()  { return getCenter().getY() - height/2; }
   public double getBottom(){ return getCenter().getY() + height/2; }

   public boolean intersects(double x, double y, double w, double h){
       boolean overlapX = getRight() > x && getLeft() < x + w;
       boolean overlapY = getBottom() > y && getTop() < y + h;
       return overlapX && overlapY;
   }
}





