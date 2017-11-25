package test;

import java.util.*;
public class Flatten2DVector {

    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;
    private boolean canRemove;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
        canRemove = false;
    }

    public int next() {
        //hasNext();
        canRemove = true;
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }
    
    // As per Java official doc for Iterator: https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
    // Removes from the underlying collection the last element returned by this iterator (optional operation).
    // This method can be called only once per call to next()
    public void remove() {
        if (canRemove) {
            
            j.remove();
            canRemove = false;
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> vec2d = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1); vec2d.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3); vec2d.add(list2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(4); list3.add(5); list3.add(6); vec2d.add(list3);
        
//        Flatten2DVector f1 = new Flatten2DVector(vec2d);
//        while (f1.hasNext()) {
//            int i = f1.next();
//            System.out.print(i + " ");
//            if (i == 2) {
//                f1.remove();
//            }
//        }
//        
//        System.out.println();
//        Flatten2DVector f2 = new Flatten2DVector(vec2d);
//        while (f2.hasNext()) {
//            System.out.print(f2.next() + " ");
//        }

        Flatten2DVector f3 = new Flatten2DVector(vec2d);
        while (f3.hasNext()) {
//            int i = f3.next();
//            System.out.print(i + " ");
//            if (i == 6) {
                f3.remove();
//            }
              int i = f3.next();
              System.out.println(i + " ");
        }
        
        Flatten2DVector f2 = new Flatten2DVector(vec2d);
        while (f2.hasNext()) {
            System.out.print(f2.next() + " ");
        }
    
    }

}
