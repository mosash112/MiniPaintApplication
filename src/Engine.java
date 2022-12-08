import shapes.*;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;

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

    public int selectShape(Point point){
        int index = -1;
        for (AbstractShapeClass s:shps) {
            if (s.contains(point)) {
                index = shps.indexOf(s);
                System.out.println(index);
            }
        }
        AbstractShapeClass shape = shps.get(index);
        if (shape.getClass().isAssignableFrom(Oval.class)) {
            drawCorners(1, shape, shape.getPoints());
        }else if (shape.getClass().isAssignableFrom(LineSegment.class)) {
            drawCorners(2, shape, shape.getPoints());
        }else if (shape.getClass().isAssignableFrom(Triangle.class)) {
            drawCorners(3, shape, shape.getPoints());
        }else if (shape.getClass().isAssignableFrom(Rectangle.class)) {
            drawCorners(4, shape, shape.getPoints());
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

    public void newShape(int flag, Point[] ps, Color clr, Color fclr, int hei, int wid) {
        AbstractShapeClass shape = null;
        counter++;
        System.out.println(counter);
        switch (flag) {
            case 1 -> {
                shape = new Oval(ps[0], hei, wid);
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
                shape = new Rectangle(ps[0], hei, wid);
                nms.add("rect_" + counter);
            }
        }
        shape.setColor(clr);
        shape.setFillColor(fclr);
        shps.add(shape);
        addShape(shape);
    }

    public void drawCorners(int flag, AbstractShapeClass shape, Point[] points){
        ArrayList<Rectangle> corners = new ArrayList<>();
        int hei = 0, wid = 0;
        switch (flag){
            case 1:
            case 4:
                hei = shape.containHeight();
                wid = shape.containWidth();
                corners.add(new Rectangle(new Point((int)points[0].getX(), (int)points[0].getY()), 5, 5));
                corners.add(new Rectangle(new Point((int)points[0].getX()+(wid-5), (int)points[0].getY()), 5, 5));
                corners.add(new Rectangle(new Point((int)points[0].getX(), (int)points[0].getY()+(hei-5)), 5, 5));
                corners.add(new Rectangle(new Point((int)points[0].getX()+(wid-5), (int)points[0].getY()+(hei-5)), 5, 5));
                break;
            case 2:
                corners.add(new Rectangle(new Point((int)points[0].getX()-2, (int)points[0].getY()-2), 5, 5));
                corners.add(new Rectangle(new Point((int)points[1].getX()-2, (int)points[1].getY()-2), 5, 5));
                break;
            case 3:
                corners.add(new Rectangle(new Point((int)points[0].getX()-2, (int)points[0].getY()-2), 5, 5));
                corners.add(new Rectangle(new Point((int)points[1].getX()-2, (int)points[1].getY()-2), 5, 5));
                corners.add(new Rectangle(new Point((int)points[2].getX()-2, (int)points[2].getY()-2), 5, 5));
                break;
        }
        for (Rectangle r:corners) {
            r.draw(gui.drwcanv.getGraphics());
        }
    }
}
