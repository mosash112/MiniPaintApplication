package shapes;

import java.awt.*;

public class LineSegment extends AbstractShapeClass{
    private Point point2;

    public LineSegment(Point point1, Point point2) {
        super(point1);
        this.point2 = point2;
    }

    @Override
    public Point[] getPoints() {
        Point[] points = super.getPoints();
        points[0] = this.getPosition();
        points[1] = this.point2;
        return points;
    }

    @Override
    public int containWidth() {
        return 0;
    }

    @Override
    public int containHeight() {
        return 0;
    }

    @Override
    public void resize(Point point, int corner) {
        Point points[] = this.getPoints();
        int mx = (int) point.getX(), my = (int) point.getY(),
                px1 = (int) points[0].getX(), py1 = (int) points[0].getY(),
                px2 = (int) points[1].getX(), py2 = (int) points[1].getY();

        switch (corner) {
            case 1 -> {
                System.out.println("corner 1");
                points[0].setLocation(px1+(mx-px1),py1+(my-py1));
            }
            case 2 -> {
                System.out.println("corner 2");
                points[1].setLocation(px2+(mx-px2),py2+(my-py2));
            }
        }
    }

    public void draw(Graphics canvas){
        canvas.setColor(this.getColor());
        canvas.drawLine((int)this.getPosition().getX(),(int)this.getPosition().getY(), (int)this.point2.getX(),(int)this.point2.getY());
    }

    public boolean contains(Point point){
        double x =  point.getX(), y =  point.getY(),
            a =  this.getPosition().getX(), b =  this.getPosition().getY(),
            c =  this.point2.getX(), d =  this.point2.getY();
        boolean contain = false;
        this.setDraggingPoint(point);
//        if 'ad-bc+cy-dx+bx-ay == 0' then they are collinear points
        if (a*d-b*c+c*y-d*x+b*x-a*y == 0)
            contain = true;
        return contain;
    }

    public void moveTo(Point point){
        Point old = this.getDraggingPoint(), oldpos1 = this.getPosition(), oldpos2 = this.point2;
        int xdiff = (int) (point.getX()-old.getX()), ydiff = (int) (point.getY()-old.getY());
        this.setDraggingPoint(point);
        this.setPosition(new Point((int) (oldpos1.getX()+xdiff), (int) (oldpos1.getY()+ydiff)));
        this.point2.setLocation(new Point((int) (oldpos2.getX()+xdiff), (int) (oldpos2.getY()+ydiff)));
    }
}
