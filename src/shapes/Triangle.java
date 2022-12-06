package shapes;

import java.awt.*;

public class Triangle extends AbstractShapeClass{
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        super(point1);
        this.point2 = point2;
        this.point3 = point3;
    }

    //    drawing a circle with its center on coordinates x, y
    public void draw(Graphics canvas) {
        int xs[] = {(int) this.getPosition().getX(), (int)this.point2.getX(), (int)this.point3.getX()}
        , ys[] = {(int) this.getPosition().getY(), (int)this.point2.getY(), (int)this.point3.getY()};
        canvas.setColor(this.getFillColor());
        canvas.fillPolygon(xs, ys, 3);
        canvas.setColor(this.getColor());
        canvas.drawPolygon(xs, ys, 3);
    }

    public boolean contains(Point point){
        double px =  point.getX(), py =  point.getY(),
                ax =  this.getPosition().getX(), ay =  this.getPosition().getY(),
                bx =  this.point2.getX(), by =  this.point2.getY(),
                cx = this.point3.getX(), cy = this.point3.getY();
        int pa = (int) Math.ceil(Math.sqrt(Math.pow((ax-px),2)+Math.pow((ay-py),2))),
            pb = (int) Math.ceil(Math.sqrt(Math.pow((bx-px),2)+Math.pow((by-py),2))),
            pc = (int) Math.ceil(Math.sqrt(Math.pow((cx-px),2)+Math.pow((cy-py),2))),
            ab = (int) Math.ceil(Math.sqrt(Math.pow((ax-bx),2)+Math.pow((ay-by),2))),
            bc = (int) Math.ceil(Math.sqrt(Math.pow((bx-cx),2)+Math.pow((by-cy),2))),
            ca = (int) Math.ceil(Math.sqrt(Math.pow((cx-ax),2)+Math.pow((cy-ay),2)));
        boolean contain = false;
        this.setDraggingPoint(point);
        if ((pa+pb+pc)<(ab+bc+ca))
            contain = true;
        return contain;
    }

    public void moveTo(Point point){
        Point old = this.getDraggingPoint(), oldpos1 = this.getPosition(), oldpos2 = this.point2, oldpos3 = this.point3;
        int xdiff = (int) (point.getX()-old.getX()), ydiff = (int) (point.getY()-old.getY());
        this.setDraggingPoint(point);
        this.setPosition(new Point((int) (oldpos1.getX()+xdiff), (int) (oldpos1.getY()+ydiff)));
        this.point2.setLocation(new Point((int) (oldpos2.getX()+xdiff), (int) (oldpos2.getY()+ydiff)));
        this.point3.setLocation(new Point((int) (oldpos3.getX()+xdiff), (int) (oldpos3.getY()+ydiff)));
    }
}
