package shapes;

import java.awt.*;

public class Rectangle extends AbstractShapeClass{
    private int height;
    private int width;

    public Rectangle(Point position, int height, int width) {
        super(position);
        this.height = height;
        this.width = width;
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

    public void draw(Graphics canvas) {
        canvas.setColor(this.getFillColor());
        canvas.fillRect((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.width, this.height);
        canvas.setColor(this.getColor());
        canvas.drawRect((int) this.getPosition().getX(), (int)this.getPosition().getY(), this.width, this.height);
    }

    public boolean contains(Point point){
        int mx = (int) point.getX(), my = (int) point.getY(), px = (int) this.getPosition().getX(), py = (int) this.getPosition().getY();
        boolean contain = false;
        this.setDraggingPoint(point);
        if (mx >= px && mx <= (px+this.width) &&
                my >= py && my <= (py+this.height))
            contain = true;
        return contain;
    }

    public void moveTo(Point point){
        Point old = this.getDraggingPoint(), oldpos = this.getPosition();
        int xdiff = (int) (point.getX()-old.getX()), ydiff = (int) (point.getY()-old.getY());
        this.setDraggingPoint(point);
        this.setPosition(new Point((int) (oldpos.getX()+xdiff), (int) (oldpos.getY()+ydiff)));
    }
}
