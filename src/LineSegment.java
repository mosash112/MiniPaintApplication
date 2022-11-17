import java.awt.*;

public class LineSegment implements Shape{
    private Point p1;
    private Point p2;
    private Color color;

    public LineSegment(Point p1, Point p2, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    public void draw(Graphics canvas){
        canvas.setColor(this.getColor());
        canvas.drawLine((int)this.getP1().getX(),(int)this.getP1().getY(), (int)this.getP2().getX(),(int)this.getP2().getY());
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public void setPosition(Point position) {

    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setFillColor(Color color) {

    }

    @Override
    public Color getFillColor() {
        return null;
    }
}
