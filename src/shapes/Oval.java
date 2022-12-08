package shapes;

import java.awt.*;
import java.lang.Math;

public class Oval extends AbstractShapeClass{
    private int height;
    private int width;

    public Oval(Point position, int verRadius, int horRadius) {
        super(position);
        this.height = verRadius;
        this.width = horRadius;
    }

//    drawing a circle with its center on coordinates x, y
    public void draw(Graphics canvas) {
            canvas.setColor(this.getFillColor());
            canvas.fillOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.width, this.height);
            canvas.setColor(this.getColor());
            canvas.drawOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.width, this.height);
    }

    @Override
    public Point[] getPoints() {
        Point[] points = new Point[3];
        points[0] = this.getPosition();
        return points;
    }

    @Override
    public int containWidth() {
        return width;
    }

    @Override
    public int containHeight() {
        return height;
    }

    public boolean contains(Point point){
        boolean contain = false;
//        Point center = new Point((int)this.getPosition().getX()+width,(int)this.getPosition().getY()+height);
        this.setDraggingPoint(point);
        if (((Math.pow((int)point.getX(),2)/Math.pow(width,2))+(Math.pow((int)point.getY(),2)/Math.pow(height,2)))==1) {
            contain = true;
            System.out.println("oval contain: "+contain);
        }
        return contain;
    }

    public void moveTo(Point point){
        Point old = this.getDraggingPoint(), oldpos = this.getPosition();
        int xdiff = (int) (point.getX()-old.getX()), ydiff = (int) (point.getY()-old.getY());
        this.setDraggingPoint(point);
        this.setPosition(new Point((int) (oldpos.getX()+xdiff), (int) (oldpos.getY()+ydiff)));
//        System.out.println("old: "+oldpos.getX()+", "+oldpos.getY()+" diff: "+xdiff+", "+ydiff+" new: "+(oldpos.getX()+xdiff)+", "+(oldpos.getY()+ydiff));
    }


}
