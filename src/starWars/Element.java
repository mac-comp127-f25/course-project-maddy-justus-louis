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


   // Breakout-style collision with a point
   public boolean intersects(double x, double y, double w, double h){
       boolean overlapX = getRight() > x && getLeft() < x + w;
       boolean overlapY = getBottom() > y && getTop() < y + h;
       return overlapX && overlapY;
   }
}


// package starWars;


// import java.awt.Graphics;


// import edu.macalester.graphics.GraphicsGroup;
// import edu.macalester.graphics.Image;


// public class Element extends GraphicsGroup{
//     private String filePath;
//     private double width;
//     private double height;
//     private String type;


//     public Element(String name, String type, double scale, double cellSize){
//         this.filePath = name;
//         this.type = type;


//         Image image = new Image(filePath);
//         image.setScale(scale);
//         image.setMaxHeight(cellSize);
//         image.setMaxWidth(cellSize);
//         image.setAnchor(cellSize/2,cellSize/2);
//         add(image);


//         this.width = cellSize;
//         this.height = cellSize;
//     }


//     /////GETTER METHODS
//     public String getFilePath() {
//         return filePath;
//     }


//     public String getType(){
//         return type;
//     }


//     public double getRight(){
//         return this.getCenter().getX() + this.getWidth()/2;
//     }


//     public double getLeft(){
//         return this.getCenter().getX() - this.getWidth()/2;
//     }


//     public double getTop(){
//         return this.getCenter().getY() - this.getHeight()/2;
//     }


//     public double getBottom(){
//         return this.getCenter().getY() + this.getHeight()/2;
//     }


// }




