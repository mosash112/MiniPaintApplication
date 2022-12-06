import shapes.*;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Engine implements DrawingEngine{
    ArrayList<AbstractShapeClass> shps = new ArrayList<>();
    ArrayList<String> nms = new ArrayList<>();
    int counter = 0;
    Gui gui;

    public Engine() {}

    @Override
    public void addShape(AbstractShapeClass shape) {
        print(shape);
        shape.draw(gui.drwcanv.getGraphics());
    }

    public void print(AbstractShapeClass shape){
        System.out.println("-----------------");
        System.out.println(shape);
        System.out.println("pos: "+shape.getPosition().getX()+", "+shape.getPosition().getY());
        System.out.println("color: "+shape.getColor());
        System.out.println("fill color: "+shape.getFillColor());
        System.out.println("-----------------");
    }

    public int checkContain(Point point){
        int index = -1;
        for (AbstractShapeClass s:shps) {
            if (s.contains(point)) {
                index = shps.indexOf(s);
                System.out.println(index);
            }
        }
        return index;
    }

    @Override
    public void removeShape(AbstractShapeClass shape) {
        int index = shps.indexOf(shape);
        System.out.println("removing selected "+shape.getClass());
        nms.remove(nms.get(index));
        shps.remove(shape);
    }

    @Override
    public AbstractShapeClass[] getShapes() {
        return shps.toArray(new AbstractShapeClass[0]);
    }

    @Override
    public void refresh(Graphics canvas) {
        System.out.println("refreshing....");
        gui.drwcanv.repaint();
    }

    public void newShape(int flag, Point[] ps, Color clr, Color fclr, int rad, int wid) {
        AbstractShapeClass shape = null;
        counter++;
        System.out.println(counter);
        switch (flag) {
            case 1 -> {
                shape = new Circle(ps[0], rad);
                nms.add("circle_" + counter);
            }
            case 2 -> {
                shape = new LineSegment(ps[0], ps[1]);
                nms.add("line_" + counter);
            }
            case 3 -> {
                shape = new Triangle(ps[0], ps[1], ps[2]);
                nms.add("triangle_" + counter);
            }
            case 4 -> {
                shape = new Rectangle(ps[0], rad, wid);
                nms.add("rect_" + counter);
            }
        }
        shape.setColor(clr);
        shape.setFillColor(fclr);
        shps.add(shape);
        addShape(shape);
    }
}
