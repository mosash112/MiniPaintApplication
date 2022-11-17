import java.awt.*;

public class Rectangle implements Shape{
    private Point position;
    private Color color;
    private Color fillColor;
    private double length;
    private double width;

    public Rectangle(Point position, Color color, Color fillColor, double length, double width) {
        this.position = position;
        this.color = color;
        this.fillColor = fillColor;
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(this.getFillColor());
        canvas.fillRect((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) this.getLength(), (int) this.getWidth());
        canvas.setColor(this.getColor());
        canvas.drawRect((int)this.getPosition().getX(),(int)this.getPosition().getY(),(int)this.getLength(),(int)this.getWidth());
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }
}
