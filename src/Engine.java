import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static java.awt.Color.*;

public class Engine implements DrawingEngine{
    ArrayList<Shape> shps = new ArrayList<>();
    ArrayList<String> nms = new ArrayList<>();
    ArrayList<Color> clrs = new ArrayList<>();
    Gui gui;
    int counter = 0;

//    colors = {"GREEN","RED","WHITE","BLACK","BLUE","CYAN","DARK_GRAY","GRAY","MAGENTA","ORANGE","PINK","YELLOW"}

    public Engine() {
        clrs.add(GREEN);
        clrs.add(RED);
        clrs.add(WHITE);
        clrs.add(BLACK);
        clrs.add(BLUE);
        clrs.add(CYAN);
        clrs.add(DARK_GRAY);
        clrs.add(GRAY);
        clrs.add(MAGENTA);
        clrs.add(ORANGE);
        clrs.add(PINK);
        clrs.add(YELLOW);
    }

    @Override
    public void addShape(Shape shape) {
        counter++;
        if (Circle.class.isAssignableFrom(shape.getClass())){
            shps.add(shape);
            nms.add("circle_"+counter);
        }else if (Square.class.isAssignableFrom(shape.getClass())){
            shps.add(shape);
            nms.add("square_"+counter);
        }else if (Rectangle.class.isAssignableFrom(shape.getClass())){
            shps.add(shape);
            nms.add("rect_"+counter);
        }else if (LineSegment.class.isAssignableFrom(shape.getClass())){
            shps.add(shape);
            nms.add("line_"+counter);
        }
    }

    @Override
    public void removeShape(Shape shape) {
        int index = shps.indexOf(shape);
        System.out.println("removing selected shape...");
//        System.out.println("index of remove "+index);
//        System.out.println(nms.get(index));
//        System.out.println(shape);
        nms.remove(nms.get(index));
        shps.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        Shape[] shapes = shps.toArray(new Shape[0]);
        return shapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        System.out.println("refreshing....");
        for (Shape s:shps) {
            s.draw(canvas);
        }
    }
}
