package test;

public class FactoryPattern {
    /*
     * In Factory pattern, we create object without exposing the creation 
     * logic to the client and refer to newly created object using a common 
     * interface.
     */

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = factory.getShape("RECTANGLE");
        shape2.draw();
    }

}
