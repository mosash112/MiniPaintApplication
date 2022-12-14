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
        Point[] points = super.getPoints();
        points[0] = this.getPosition();
        points[1] = new Point((int) this.getPosition().getX()+containWidth(), (int) this.getPosition().getY());
        points[2] = new Point((int) this.getPosition().getX(), (int) this.getPosition().getY()+containHeight());
        points[3] = new Point((int) this.getPosition().getX()+containWidth(), (int) this.getPosition().getY()+containHeight());
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

    @Override
    public void resize(Point point, int corner) {
        int mx = (int) point.getX(), my = (int) point.getY(),
            px1 = (int) this.getPosition().getX(), py1 = (int) this.getPosition().getY(),
            px2 = px1 + width, py2 = py1 + height;
        Point points[] = this.getPoints();

        switch (corner) {
            case 1 -> {
                System.out.println("upper left corner");
                width = width - (mx - px1);
                height = height - (my - py1);
                points[0] = point;
            }
            case 2 -> {
                System.out.println("lower right corner");
                width = width + (mx - px2);
                height = height - (my - py1);
                points[2] = point;
                points[3].setLocation(px1 + (mx - px2), py1);
                points[0].setLocation(px1, py1 + (my - py1));
            }
            case 3 -> {
                System.out.println("lower right corner");
                width = width - (mx - px1);
                height = height + (my - py2);
                points[2] = point;
                points[0].setLocation(px1 + (mx - px1), py1);
                points[3].setLocation(px1, py1 + (my - py2));
            }
            case 4 -> {
                System.out.println("lower right corner");
                width = width + (mx - px2);
                height = height + (my - py2);
                points[3] = point;
                points[1].setLocation(px2, py1);
                points[2].setLocation(px1, py2);
            }
        }
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
