package test;

public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) { // error: forget to check
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("rectangle")){
            return new Rectangle(); 
        }
        return null;
    }
}
